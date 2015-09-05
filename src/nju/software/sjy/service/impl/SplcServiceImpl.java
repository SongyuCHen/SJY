package nju.software.sjy.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nju.software.sjy.bean.StatusMsg;
import nju.software.sjy.bean.ZtStatusMsg;
import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.model.xy.TZtoperation;
import nju.software.sjy.service.AuthorityService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.JfqxService;
import nju.software.sjy.service.OperationService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.SplcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理审批流程
 *
 */
@Service
public class SplcServiceImpl implements SplcService
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private JfqxService jfqxService;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private GypzService gypzService;
	
	/**
	 * 审批工作实绩
	 */
	@Override
	public ZtStatusMsg approvalGzsj(List<TGzsj> tlist, TUser user)
	{
		/**
		 * 1.如果列表中的状态都不一致，那么返回错误
		 *   方法：保存第一个的值，然后遍历剩下的，有一个不等于第一个就返回false
				
		 * 2.如果审批已经完成了，则OK
		 *   方法：1.判断zt.mc是否等于constants.xxx    2.ztoperation表中为已审批完成的设置下一个operation status=0
				
		 * 3.审批没有完成，判断登录用户的审批权限是否正好为工作实绩需要下一步的审批
		 *   方法：获取登录用户的审批权限，再从ztoperation表中获取当前zt对应的operation，如果相等则可以审批
		 *   如果当前用户的审批范围为所有，则通过，如果不是所有则判断oplist.contains()==true?
				
		 * 4.不符合以上情况，提示不能审批，提示信息可为：gzsj需要的审批，您当前的审批权限
		 */
		
		ZtStatusMsg result;
		int status = 0;
		String msg = null;
		String ztStr = null;
		
		/* 1.如果列表为空则返回1 */
		if(tlist == null || tlist.isEmpty())
		{
			status = 1;
			msg = NOT_NULL;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 2.如果列表中的状态不一致，则返回2 */
		TGzsj firstGzsj = tlist.get(0);
		TGypz firstZt = firstGzsj.getZt();
		boolean consistent = true;
		for(int i=1;i<tlist.size();i++)
		{
			TGypz curZt = tlist.get(i).getZt();
			if(!firstZt.equals(curZt))
			{
				consistent = false;
				break;
			}
		}
		if(!consistent)
		{
			status = 2;
			msg = NOT_CONSISTENT;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 3.如果已经审批完成了，则返回3 
		 * 如何判断审批是否完成：
		 * (1)根据当前状态获取下一个状态，如果下一个状态为null，则说明审批完成，整个判断结束，否则到(2)
		 * (2)获取当前操作和操作的状态，如果操作状态不为0，则说明审批没有完成，整个判断结束
		 * 	  否则循环获取下一个TZtoperation，得到nextZt，如果nextZt为null，则说明审批完成，判断结束，否则继续循环
		 *   根据设定，必有nextZt为null的记录，如果在nextZt到达null之前有操作状态不为0，则说明审批未完成，
		 *   如果在nextZt到达null之前所有操作状态都为0，则说明审批完成
		 * */
		TZtoperation ztOperation = operationService.getZtoperationByZt(firstZt);
		TGypz nextZt = null;
		if(ztOperation != null)
		{
			nextZt = ztOperation.getNextZt();
		}
		if(nextZt == null)
		{
			status = 3;
			msg = APPROVAL_AGAIN;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 获取接下来需要的审批操作 */
		TOperation operation = ztOperation.getOperation();/* 当前的操作 */
		int operationStatus = ztOperation.getStatus();/* 当前操作的状态 */
		/* 找到下一个激活的状态，对应的操作则为下一步需要的审批操作 */
		while(operationStatus == 0)
		{
			TZtoperation ztOp = operationService.getZtoperationByZt(nextZt);
			ztOperation = ztOp;
			operationStatus = ztOp.getStatus();
			operation = ztOp.getOperation();
			nextZt = ztOp.getNextZt();
			if(nextZt == null)
			{
				status = 3;
				msg = APPROVAL_AGAIN;
				result = new ZtStatusMsg(status, msg, ztStr);
				return result;
			}
		}
		
		/* 审批还没有完成，判断登录用户的审批权限是否符合接下来需要的审批权限 */
		
		//用户角色和用户具有的审批权限
		TRole role = user.getRole();
		List<TOperation> oplist = roleService.getOperationByRoleOperationname(role, Constants.SP);
		
		//当前需要的操作步骤
		TGypz range = operation.getRange();
		int needPzbh = range.getPzbh();
		
		boolean canApproval = false;
		if(oplist != null)
		{
			for(TOperation top : oplist)
			{
				int curPzbh = top.getRange().getPzbh();
				/* 如果审批权限为所有或者正好是当前需要的审批权限 */
				if((curPzbh == 0) || (curPzbh == needPzbh))
				{
					canApproval = true;
					break;
				}
			}
		}
		
		/* 4.如果登录用户的审批权限不是当前需要的权限 */
		if(!canApproval)
		{
			StringBuffer sb = new StringBuffer();
			if(oplist != null)
			{
				for(TOperation top : oplist)
				{
					String ran = top.getRange().getMc();
					sb.append(ran).append(",");
				}
				if(sb.length()>0)
				{
					sb.deleteCharAt(sb.length()-1);
				}
			}
			
			status = 4;
			msg = "无法执行审批操作，记录接下来需要的是" + range.getMc() + "审批，您的审批权限是" + sb.toString() + "。";
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 5.是当前需要的审批权限，则开始审批 */
		nextZt = ztOperation.getNextZt();
		for(TGzsj gzsj : tlist)
		{
			gzsj.setZt(nextZt);
		}
		gzsjService.updateAllGzsj(tlist);
		
		status = 5;
		msg = APPROVAL_SUCCESS;
		ztStr = nextZt.getMc();
		result = new ZtStatusMsg(status, msg, ztStr);
		
		return result;
	}
	
	public ZtStatusMsg approvalJfqx(List<TJfqx> tlist, TUser user)
	{
		ZtStatusMsg result;
		int status = 0;
		String msg = null;
		String ztStr = null;
		
		/* 1.如果列表为空则返回1 */
		if(tlist == null || tlist.isEmpty())
		{
			status = 1;
			msg = NOT_NULL;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 2.如果列表中的状态不一致，则返回2 */
		TJfqx firstJfqx = tlist.get(0);
		TGypz firstZt = firstJfqx.getZt();
		boolean consistent = true;
		for(int i=1;i<tlist.size();i++)
		{
			TGypz curZt = tlist.get(i).getZt();
			if(!firstZt.equals(curZt))
			{
				consistent = false;
				break;
			}
		}
		if(!consistent)
		{
			status = 2;
			msg = NOT_CONSISTENT;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 3.如果已经审批完成了，则返回3 */
		//判断审批是否完成
		TZtoperation ztOperation = operationService.getZtoperationByZt(firstZt);
		TGypz nextZt = null;
		if(ztOperation != null)
		{
			nextZt = ztOperation.getNextZt();
		}
		if(nextZt == null)
		{
			status = 3;
			msg = APPROVAL_AGAIN;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		//当前需要的操作
		TOperation operation = ztOperation.getOperation();
		int opStatus = ztOperation.getStatus();
		while(opStatus == 0)
		{
			TZtoperation ztOp = operationService.getZtoperationByZt(nextZt);
			ztOperation = ztOp;
			opStatus = ztOp.getStatus();
			operation = ztOp.getOperation();
			nextZt = ztOp.getNextZt();
			if(nextZt == null)
			{
				status = 3;
				msg = APPROVAL_AGAIN;
				result = new ZtStatusMsg(status, msg, ztStr);
				return result;
			}
		}
		
		/* 审批还没有完成，判断登录用户的审批权限是否符合接下来需要的审批权限 */
		
		//用户角色和用户具有的审批权限
		TRole role = user.getRole();
		List<TOperation> oplist = roleService.getOperationByRoleOperationname(role, Constants.SP);
		
		//当前需要的操作步骤
		TGypz range = operation.getRange();
		int needPzbh = range.getPzbh();
		
		boolean canApproval = false;
		if(oplist != null)
		{
			for(TOperation top : oplist)
			{
				int curPzbh = top.getRange().getPzbh();
				/* 如果审批权限为所有或者正好是当前需要的审批权限 */
				if((curPzbh == 0) || (curPzbh == needPzbh))
				{
					canApproval = true;
					break;
				}
			}
		}
		
		/* 4.如果登录用户的审批权限不是当前需要的权限 */
		if(!canApproval)
		{
			StringBuffer sb = new StringBuffer();
			if(oplist != null)
			{
				for(TOperation top : oplist)
				{
					String ran = top.getRange().getMc();
					sb.append(ran).append(",");
				}
				if(sb.length()>0)
				{
					sb.deleteCharAt(sb.length()-1);
				}
			}
			
			status = 4;
			msg = "无法执行审批操作，记录接下来需要的是" + range.getMc() + "审批，您的审批权限是" + sb.toString() + "。";
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 5.是当前需要的审批权限，则开始审批 */
		nextZt = ztOperation.getNextZt();
		for(TJfqx jfqx : tlist)
		{
			jfqx.setZt(nextZt);
		}
		jfqxService.updateAllJfqx(tlist);
		
		status = 5;
		msg = APPROVAL_SUCCESS;
		ztStr = nextZt.getMc();
		result = new ZtStatusMsg(status, msg, ztStr);
		
		return result;
	}

	
	/**
	 * 判断审批是否完成
	 */
	public boolean approvalFinished(TGypz zt)
	{
		if(zt == null)
		{
			return false;
		}
		
		TZtoperation ztOperation = operationService.getZtoperationByZt(zt);
		TGypz nextZt = null;
		if(ztOperation != null)
		{
			nextZt = ztOperation.getNextZt();
		}
		if(nextZt == null)
		{
			return true;
		}
		int opStatus = ztOperation.getStatus();
		while(opStatus == 0)
		{
			TZtoperation ztOp = operationService.getZtoperationByZt(nextZt);
			opStatus = ztOp.getStatus();
			nextZt = ztOp.getZt();
			if(nextZt == null)
			{
				return true;
			}
		}
		return false;
	}

	
	/**
	 * 获取最终状态————审批完成时的状态
	 * T_ZTOPERATION表中，status=1且bh最大的那条记录对应的nextZt，一定是最终状态
	 */
	public TGypz getFinalZt()
	{
		TGypz finalZt = operationService.getFinalZt();
		
		return finalZt;
	}

	/**
	 * 判断某个状态是否可以进行审批
	 * 1.获取登录用户的审批操作权限和范围
	 * 2.根据TZtoperation表进行查询，类似链表式的推进查询
	 * 3.如果状态小于用户可审批的状态，则可编辑；否则不可以
	 */
	public StatusMsg canEdit(TGypz zt)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		TRole role = user.getRole();
		
		/* 取出用户所有的审批操作 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, Constants.SP);
		
		String msg = zt.getMc() + ", " + AuthorityService.IS_NOT_ACCESS;
		StatusMsg sm = new StatusMsg(0, msg);
		if(operationList != null && !operationList.isEmpty())
		{
			for(TOperation operation : operationList)
			{
				TZtoperation ztOperation = operationService.getZtoperationByOperation(operation);
				TGypz targetZt = ztOperation.getZt();
				TGypz curZt = zt;
				TGypz nextZt = operationService.getNextZt(curZt);
				while(curZt != null && !curZt.equals(targetZt) && nextZt != null)
				{
					ztOperation = operationService.getZtoperationByZt(nextZt);
					curZt = ztOperation.getZt();
					nextZt = ztOperation.getNextZt();
				}
				if(curZt != null && curZt.equals(targetZt))
				{
					sm = new StatusMsg(1, AuthorityService.IS_ACCESS);
					break;
				}
			}
		}
		
		return sm;
	}

	//退回操作
	@Override
	public ZtStatusMsg rejectlGzsj(List<TGzsj> tlist, TUser user) {
		// TODO Auto-generated method stub
		/**
		 * 1.如果列表中的状态都不一致，那么返回错误
		 *   方法：保存第一个的值，然后遍历剩下的，有一个不等于第一个就返回false
				
		 * 2.如果审批已经完成了，则OK
		 *   方法：1.判断zt.mc是否等于constants.xxx    2.ztoperation表中为已审批完成的设置下一个operation status=0
				
		 * 3.审批没有完成，判断登录用户的审批权限是否正好为工作实绩需要下一步的审批
		 *   方法：获取登录用户的审批权限，再从ztoperation表中获取当前zt对应的operation，如果相等则可以审批
		 *   如果当前用户的审批范围为所有，则通过，如果不是所有则判断oplist.contains()==true?
				
		 * 4.不符合以上情况，提示不能审批，提示信息可为：gzsj需要的审批，您当前的审批权限
		 */
		ZtStatusMsg result;
		int status = 0;
		String msg = null;
		String ztStr = null;
		
		/* 1.如果列表为空则返回1 */
		if(tlist == null || tlist.isEmpty())
		{
			status = 1;
			msg = NOT_NULL;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		/* 2.如果列表中的状态不一致，则返回2 */
		TGzsj firstGzsj = tlist.get(0);
		TGypz firstZt = firstGzsj.getZt();
		boolean consistent = true;
		for(int i=1;i<tlist.size();i++)
		{
			TGypz curZt = tlist.get(i).getZt();
			if(!firstZt.equals(curZt))
			{
				consistent = false;
				break;
			}
		}
		if(!consistent)
		{
			status = 2;
			msg = NOT_CONSISTENT;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		/* 3.如果已经审批完成了，则返回3 
		 * 如何判断审批是否完成：
		 * (1)根据当前状态获取下一个状态，如果下一个状态为null，则说明审批完成，整个判断结束，否则到(2)
		 * (2)获取当前操作和操作的状态，如果操作状态不为0，则说明审批没有完成，整个判断结束
		 * 	  否则循环获取下一个TZtoperation，得到nextZt，如果nextZt为null，则说明审批完成，判断结束，否则继续循环
		 *   根据设定，必有nextZt为null的记录，如果在nextZt到达null之前有操作状态不为0，则说明审批未完成，
		 *   如果在nextZt到达null之前所有操作状态都为0，则说明审批完成
		 * */
		TZtoperation ztOperation = operationService.getZtoperationByZt(firstZt);
		TGypz beforeZt = null;
		if(ztOperation != null)
		{
			beforeZt = ztOperation.getBeforeZt();
		}
		if(beforeZt == null)
		{
			status = 3;
			msg = REJECT_AGAIN;
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 获取接下来需要的审批操作 */
		TOperation operation = ztOperation.getOperation();/* 当前的操作 */
		int operationStatus = ztOperation.getStatus();/* 当前操作的状态 */
		/* 找到上一个退回的状态，对应的操作则为上一步需要的退回操作 */
		while(operationStatus == 0)
		{
			TZtoperation ztOp = operationService.getZtoperationByZt(beforeZt);
			ztOperation = ztOp;
			operationStatus = ztOp.getStatus();
			operation = ztOp.getOperation();
			beforeZt = ztOp.getBeforeZt();
			if(beforeZt == null)
			{
				status = 3;
				msg = REJECT_AGAIN;
				result = new ZtStatusMsg(status, msg, ztStr);
				return result;
			}
		}
		
		/* 审批还没有完成，判断登录用户的审批权限是否符合接下来需要的审批权限 */
		
		//用户角色和用户具有的审批权限
		TRole role = user.getRole();
		List<TOperation> oplist = roleService.getOperationByRoleOperationname(role, Constants.SP);
		
		//当前需要的操作步骤
		TGypz range = operation.getRange();
		int needPzbh = range.getPzbh();
		
		boolean canReject = false;
		if(oplist != null)
		{
			for(TOperation top : oplist)
			{
				int curPzbh = top.getRange().getPzbh();
				/* 如果审批权限为所有或者正好是当前需要的审批权限 */
				if((curPzbh == 0) || (curPzbh == needPzbh))
				{
					canReject = true;
					break;
				}
			}
		}
		
		/* 4.如果登录用户的审批权限不是当前需要的权限 */
		if(!canReject)
		{
			StringBuffer sb = new StringBuffer();
			if(oplist != null)
			{
				for(TOperation top : oplist)
				{
					String ran = top.getRange().getMc();
					sb.append(ran).append(",");
				}
				if(sb.length()>0)
				{
					sb.deleteCharAt(sb.length()-1);
				}
			}
			
			status = 4;
			msg = "无法执行退回操作，记录接下来需要的是" + range.getMc() + "审批，您的审批权限是" + sb.toString() + "。";
			result = new ZtStatusMsg(status, msg, ztStr);
			return result;
		}
		
		/* 5.是当前需要的审批权限，则开始审批 */
		beforeZt = ztOperation.getBeforeZt();
		for(TGzsj gzsj : tlist)
		{
			gzsj.setZt(beforeZt);
		}
		gzsjService.updateAllGzsj(tlist);
		
		status = 5;
		msg = APPROVAL_SUCCESS;
		ztStr = beforeZt.getMc();
		result = new ZtStatusMsg(status, msg, ztStr);
		
		return result;
	}
	
}
