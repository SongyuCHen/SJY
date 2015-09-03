package nju.software.sjy.service.impl;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.bean.StatusMsg;
import nju.software.sjy.common.ComparatorTools;
import nju.software.sjy.common.Constants;
import nju.software.sjy.convertor.OperationConvertor;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.AuthorityService;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService
{
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GypzService gypzService;
	
	/**
	 * 根据用户角色得到用户能执行的操作
	 */
	public List<TOperation> getAccessOperationByUser(TUser user)
	{
		if(user == null)
		{
			return null;
		}
		
		TRole role = user.getRole();
		
		List<TOperation> tlist = roleService.getOperationByRole(role);
		
		List<TOperation> operationList = OperationConvertor.convertToButton(tlist);
		
		return operationList;
	}
	
	public List<TBm> getAccessBmByUser(TUser user)
	{
		if(user == null)
		{
			return null;
		}
		
		TRole role = user.getRole();
		
		/* 先查询出该角色拥有的查询范围 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, Constants.SEARCH_CN);
		
		List<TBm> bmList = null;
		
		if(operationList != null && !operationList.isEmpty())
		{
			String range = operationList.get(0).getRange().getMc();
			
			/* 
			 * 根据范围是"全院","本庭","个人"分别作出处理 
			 * 1.范围是全院————可以查看所有部门
			 * 2.范围是本庭或个人————只能查看登录者所属部门
			 */
			if(range.equals(Constants.QY))
			{
				bmList = bmService.getAllBm();
			}
			else
			{
				bmList = new ArrayList<TBm>();
				bmList.add(user.getBm());
			}
		}
		
		return bmList;
	}

	/**
	 * 根据用户权限获取用户所能访问的部门，除去类型为"其它"的部门
	 */
	public List<TBm> getAccessBmByUserExcludeQtbm(TUser user)
	{
		if(user == null)
		{
			return null;
		}
		
		TRole role = user.getRole();
		
		/* 先查询出该角色拥有的查询范围 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, Constants.SEARCH_CN);
		
		List<TBm> bmList = null;
		
		if(operationList != null && !operationList.isEmpty())
		{
			String range = operationList.get(0).getRange().getMc();
			TGypz qtlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
			
			/* 
			 * 根据范围是"全院","本庭","个人"分别作出处理 
			 * 1.范围是全院————可以查看所有部门
			 * 2.范围是本庭或个人————只能查看登录者所属部门
			 */
			if(range.equals(Constants.QY))
			{
				bmList = bmService.getBmExcludeBmlx(qtlx);
			}
			else
			{
				bmList = new ArrayList<TBm>();
				TBm bm = user.getBm();
				TGypz bmlx = bm.getBmlx();
				if(bmlx != null && !bmlx.equals(qtlx))
				{
					bmList.add(bm);
				}
			}
		}
		
		return bmList;
	}

	
	/**
	 * 根据用户权限获取用户所能访问的指定部门下的人员
	 * 
	 * @param user		登录用户
	 * @param bm		指定部门，如果为null，则指所有部门
	 */
	public List<TUser> getAccessUserByUserBm(TUser user, TBm bm)
	{
		if(user == null)
		{
			return null;
		}
		
		TRole role = user.getRole();
		
		/* 先查询出该角色拥有的查询范围 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, Constants.SEARCH_CN);
		
		List<TUser> userList = null;
		
		if(operationList != null && !operationList.isEmpty())
		{
			String range = operationList.get(0).getRange().getMc();
			
			/*
			 * 根据范围是"全院","本庭","个人"分别作出处理
			 * 1.范围是全院————可以查看任意部门下的所有人员
			 * 2.范围是本庭————只能查看本庭的所有人员
			 * 3.范围是个人————只能查看自己一个人
			 * 由于人员列表目前是依赖于部门列表的，所以现在做的简单点
			 */
			if(range.equals(Constants.QY))
			{
				if(bm == null)
				{
					userList = userService.getAllRy();
				}
				else
				{
					userList = userService.getUserByBm(bm);
				}
			}
			else if(range.equals(Constants.BT))
			{
				if((bm == null) || (bm != null && bm.equals(user.getBm())))
				{
					userList = userService.getUserByBm(user.getBm());
				}
			}
			else
			{
				if((bm == null) || (bm != null && bm.equals(user.getBm())))
				{
					userList = new ArrayList<TUser>();
					userList.add(user);
				}
			}
		}
		
		return userList;
	}

	/**
	 * 根据用户权限判断用户是否有权限执行某个操作
	 * 
	 * @param self			登录用户
	 * @param target		目标用户（工作实绩、加分扣分情形表中都有用户信息）
	 * @param operationname	操作名称
	 * 
	 * @return StatusMsg	是否有权限执行该操作以及提示信息
	 */
	public StatusMsg isAccessedOperation(TUser self, TUser target, String operationname)
	{
		if(self == null || target == null)
		{
			return null;
		}
		
		TRole role = self.getRole();
		
		/* 根据用户角色和操作名称获取该用户此操作的集合 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, operationname);
		
		/* 获取用户自身的姓名和部门以及操作的目标对象的姓名和部门 */
		String selfRyxm = self.getXm();
		String targetRyxm = target.getXm();
		String selfBmmc = self.getBm().getBmmc();
		String targetBmmc = target.getBm().getBmmc();
		
		String needRangeStr = "";
		
		/* 如果自身部门不等于操作的目标对象的部门，说明操作范围为全院 */
		if(!selfBmmc.equals(targetBmmc))
		{
			needRangeStr = Constants.QY;
		}
		/* 如果部门相同但是姓名不同，说明操作范围为本庭 */
		else if(!selfRyxm.equals(targetRyxm))
		{
			needRangeStr = Constants.BT;
		}
		/* 否则操作范围为个人 */
		else
		{
			needRangeStr = Constants.GR;
		}
		
		TGypz needRange = gypzService.getGypzByLxMc(Constants.FW, needRangeStr);
		StatusMsg sm = new StatusMsg(0, null);
		if(operationList != null)
		{
			for(TOperation top : operationList)
			{
				TGypz userRange = top.getRange();
				
				// >= 0
				if(!(ComparatorTools.FW_COMP.compare(userRange, needRange) < 0))
				{
					sm.setStatus(1);
					sm.setMsg(IS_ACCESS);
					return sm;
				}
			}
		}
		
		sm.setMsg(IS_NOT_ACCESS);
		return sm;
	}

	@Override
	public List<TUser> getAccessNeedEvaluationUserByUserBm(TUser user, TBm bm)
	{
		if(user == null)
		{
			return null;
		}
		
		TRole role = user.getRole();
		
		/* 先查询出该角色拥有的查询范围 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, Constants.SEARCH_CN);
		
		List<TUser> userList = null;
		
		if(operationList != null && !operationList.isEmpty())
		{
			String range = operationList.get(0).getRange().getMc();
			
			/* 获取配置的需要进行考核的人员——只有人员类型名称有用 */
			List<String> khjsmcList = gypzService.getMcByLx(Constants.KHJS);
			
			List<TRole> roleList = roleService.getRoleByRolenamelist(khjsmcList);
			
			/*
			 * 根据范围是"全院","本庭","个人"分别作出处理
			 * 1.范围是全院————可以查看任意部门下的所有人员
			 * 2.范围是本庭————只能查看本庭的所有人员
			 * 3.范围是个人————只能查看自己一个人
			 * 由于人员列表目前是依赖于部门列表的，所以现在做的简单点
			 */
			if(range.equals(Constants.QY))
			{
				if(bm == null)
				{
					userList = userService.getUserByRolelist(roleList);
				}
				else
				{
					userList = userService.getUserByBmRolelist(bm, roleList);
				}
			}
			else if(range.equals(Constants.BT))
			{
				if((bm == null) || (bm != null && bm.equals(user.getBm())))
				{
					userList = userService.getUserByBmRolelist(user.getBm(), roleList);
				}
			}
			else
			{
				if((bm == null) || (bm != null && bm.equals(user.getBm())))
				{
					if(roleList.contains(user.getRole()))
					{
						userList = new ArrayList<TUser>();
						userList.add(user);
					}
				}
			}
		}
		
		return userList;
	}

}
