package nju.software.sjy.aspectj;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.bean.StatusMsg;
import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TKfqx;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.AuthorityService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.JfqxService;
import nju.software.sjy.service.KfqxService;
import nju.software.sjy.service.SplcService;
import nju.software.sjy.service.UserService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 工作填报的切面类
 * 这个类用来判断登录用户是否有增加、编辑、删除工作实绩、加分情形、扣分情形的权限
 * 如果有权限则执行原来的方法，如果没有则另作处理。
 * 
 * @author zceolrj
 *
 */
@Aspect
@Component
public class GztbAspect
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private JfqxService jfqxService;
	
	@Autowired
	private KfqxService kfqxService;
	
	@Autowired
	private SplcService splcService;
	
	/************************************************工作实绩**************************************************/
	
	/**
	 * 增加工作实绩的操作
	 * 1.判断用户是否有权限增加
	 * 
	 * @param pjp	连接点
	 * @return		ModelAndView
	 */
	@Around("execution(* nju.software.sjy.controller.GzsjController.add(..))")
	public ModelAndView addGzsjAccess(ProceedingJoinPoint pjp)
	{
		ModelAndView mav = null;
		
		/* 获取request对象 */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		String bmmc = request.getParameter("addModalBm");
		String xm = request.getParameter("addModalXm");
		
		TUser target = userService.getUserByXmAndBmmc(xm, bmmc);
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 操作权限认证 */
		StatusMsg sm = authorityService.isAccessedOperation(self, target, Constants.EDIT_CN);
		
		/* 如果有权限增加则执行原来的方法，否则阻止执行原来的方法并返回deny页面 */
		if(sm != null && sm.getStatus() != 0)
		{
			try 
			{
				/* 相当于执行GzsjController.add(..) */
				mav = (ModelAndView)pjp.proceed();
			}
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			mav = new ModelAndView();
			mav.setViewName("deny");
		}
		
		return mav;
	}
	
	/**
	 * 编辑工作实绩的操作
	 * 1.判断用户是否有权限编辑
	 * 2.判断编辑的记录是否已经审批
	 * 
	 * @param pjp	连接点
	 */
	@Around("execution(* nju.software.sjy.controller.GzsjController.edit(..))")
	public void editGzsjAccess(ProceedingJoinPoint pjp)
	{
		/* 同时获取request和response，edit是一个ajax请求 */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		HttpServletResponse response = (HttpServletResponse)pjp.getArgs()[1];
		String bhStr = request.getParameter("editModalBh");
		int bh = Integer.parseInt(bhStr);
		TGzsj gzsj = gzsjService.getGzsjByBh(bh);
		TUser target = gzsj.getUser();
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 操作权限认证 */
		StatusMsg sm = authorityService.isAccessedOperation(self, target, Constants.EDIT_CN);
		
		/* 有权限则执行原来的方法，否则阻止执行原来的方法 */
		if(sm != null && sm.getStatus() != 0)
		{
			/* 有操作权限后，判断该工作实绩的审批情况 */
			TGypz gzsjZt = gzsj.getZt();/* 工作实绩的状态 */
			
			sm = splcService.canEdit(gzsjZt);
			
			if(sm != null && sm.getStatus() != 0)
			{
				try 
				{
					/* 相当于执行GzsjController.edit(..) */
					pjp.proceed();
					return;
				}
				catch (Throwable e) 
				{
					e.printStackTrace();
				}
			}
		}

		/* 没有权限执行时返回StatusMsg中的信息 */
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("StatusMsg", sm);
			
		try 
		{
			response.setContentType("text/html;charset=UTF-8");
			String jsonStr = JSONObject.fromObject(jsonObj).toString();
			response.getWriter().print(jsonStr);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

		
	}
	
	/**
	 * 删除工作实绩的操作
	 * 1.判断用户是否有权限删除
	 * 2.判断要删除的记录是否已经审批
	 * 
	 * @param pjp	连接点
	 * @return		ModelAndView
	 */
	@Around("execution(* nju.software.sjy.controller.GzsjController.delete(..))")
	public ModelAndView deleteGzsjAccess(ProceedingJoinPoint pjp)
	{
		/* 获取request对象 */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		
		/* 获取待删除的工作实绩编号 */
		String[] gzsjbhStrArr = request.getParameterValues("gzsjBh");
		int[] gzsjbhArr = null;
		
		if(gzsjbhStrArr != null)
		{
			gzsjbhArr = new int[gzsjbhStrArr.length];
			for(int i=0;i<gzsjbhStrArr.length;i++)
			{
				gzsjbhArr[i] = Integer.parseInt(gzsjbhStrArr[i]);
			}
		}
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 循环遍历每一个待删除的工作实绩，判断是否有删除它的权力 ，一旦没有就立即break*/
		boolean hasAccess = true;
		if(gzsjbhArr != null)
		{
			for(int gzsjbh : gzsjbhArr)
			{
				TGzsj gzsj = gzsjService.getGzsjByBh(gzsjbh);
				/* 操作权限认证 */
				StatusMsg sm = authorityService.isAccessedOperation(self, gzsj.getUser(), Constants.EDIT_CN);
				if(sm != null && sm.getStatus() == 0)
				{
					hasAccess = false;
					break;
				}
				else
				{
					/* 有操作权限后，判断该工作实绩的审批情况 */
					TGypz gzsjZt = gzsj.getZt();/* 工作实绩的状态 */
					
					sm = splcService.canEdit(gzsjZt);
					if(sm != null && sm.getStatus() == 0)
					{
						hasAccess = false;
						break;
					}
				}
			}
		}
		
		ModelAndView mav = null;
		
		/* 如果有权限则执行原来的操作，否则不执行原操作而返回deny */
		if(hasAccess)
		{
			try 
			{
				/* 相当于执行GzsjController.delete(..) */
				mav = (ModelAndView)pjp.proceed();
			}
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			mav = new ModelAndView();
			mav.setViewName("deny");
		}
		
		return mav;
	}
	
	
	/******************************************加分情形*****************************************/
	
	/**
	 * 增加加分情形的方法
	 * 
	 * @param pjp	连接点
	 * @return		ModelAndView
	 */
	@Around("execution(* nju.software.sjy.controller.JfqxController.addJfqx(..))")
	public ModelAndView addJfqxAccess(ProceedingJoinPoint pjp)
	{
		ModelAndView mav = null;
		/* 获取request对象 */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		String rybhStr = request.getParameter("rybh");
		int rybh = Integer.parseInt(rybhStr);
		
		TUser target = userService.getUserByRybh(rybh);
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 操作权限认证 */
		StatusMsg sm = authorityService.isAccessedOperation(self, target, Constants.EDIT_CN);
		
		/* 如果有权限则执行原来的方法，否则不执行且返回deny */
		if(sm != null && sm.getStatus() != 0)
		{
			try 
			{
				/* 相当于执行JfqxController.addJfqx(..) */
				mav = (ModelAndView)pjp.proceed();
			}
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			mav = new ModelAndView();
			mav.setViewName("deny");
		}
		
		return mav;
	}
	
	/**
	 * 编辑加分情形的操作
	 * 
	 * @param pjp	连接点
	 */
	@Around("execution(* nju.software.sjy.controller.JfqxController.editJfqx(..))")
	public void editJfqxAccess(ProceedingJoinPoint pjp)
	{
		/* 获取request和response，edit是ajax请求 */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		HttpServletResponse response = (HttpServletResponse)pjp.getArgs()[1];
		String bhStr = request.getParameter("bh");
		int bh = Integer.parseInt(bhStr);
		TJfqx jfqx = jfqxService.getJfqxByBh(bh);
		TUser target = jfqx.getUser();
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 操作权限认证 */
		StatusMsg sm = authorityService.isAccessedOperation(self, target, Constants.EDIT_CN);
		
		/* 如果有权限则执行原来的方法，否则不执行原方法 */
		if(sm != null && sm.getStatus() != 0)
		{
			/* 有操作权限后，判断该加分情形的审批情况 */
			TGypz jfqxZt = jfqx.getZt();/* 加分情形的状态 */
			
			sm = splcService.canEdit(jfqxZt);
			
			if(sm != null && sm.getStatus() != 0)
			{
				try 
				{
					/* 相当于执行JfqxController.editJfqx(..) */
					pjp.proceed();
					return;
				}
				catch (Throwable e) 
				{
					e.printStackTrace();
				}
			}
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("StatusMsg", sm);
			
		try 
		{
			response.setContentType("text/html;charset=UTF-8");
			String jsonStr = JSONObject.fromObject(jsonObj).toString();
			response.getWriter().print(jsonStr);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 删除加分情形的操作
	 * 
	 * @param pjp	连接点
	 * @return		ModelAndView
	 */
	@Around("execution(* nju.software.sjy.controller.JfqxController.deleteJfqx(..))")
	public ModelAndView deleteJfqxAccess(ProceedingJoinPoint pjp)
	{
		/* 获取request对象 */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		String[] jfqxbhStrArr = request.getParameterValues("jfqxbh");
		int[] jfqxbhArr = null;
		
		if(jfqxbhStrArr != null)
		{
			jfqxbhArr = new int[jfqxbhStrArr.length];
			for(int i=0;i<jfqxbhStrArr.length;i++)
			{
				jfqxbhArr[i] = Integer.parseInt(jfqxbhStrArr[i]);
			}
		}
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 遍历待删除的每一个加分情形，如果有一个不具备删除权限，就立即break */
		boolean hasAccess = true;
		if(jfqxbhArr != null)
		{
			for(int jfqxbh : jfqxbhArr)
			{
				TJfqx jfqx = jfqxService.getJfqxByBh(jfqxbh);
				
				/* 操作权限认证 */
				StatusMsg sm = authorityService.isAccessedOperation(self, jfqx.getUser(), Constants.EDIT_CN);
				if(sm != null && sm.getStatus() == 0)
				{
					hasAccess = false;
					break;
				}
				else
				{
					/* 有操作权限后，判断该加分情形的审批情况 */
					TGypz jfqxZt = jfqx.getZt();/* 加分情形的状态 */
					
					sm = splcService.canEdit(jfqxZt);
					if(sm != null && sm.getStatus() == 0)
					{
						hasAccess = false;
						break;
					}
				}
			}
		}
		
		ModelAndView mav = null;
		
		/* 如果有权限则执行原来的删除操作，否则不执行删除返回deny */
		if(hasAccess)
		{
			try 
			{
				/* 相当于执行JfqxController.deleteJfqx(..) */
				mav = (ModelAndView)pjp.proceed();
			}
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			mav = new ModelAndView();
			mav.setViewName("deny");
		}
		
		return mav;
	}
	
	
	/************************************************扣分情形**************************************************/
	
	
	/**
	 * 增加扣分情形的操作
	 * 
	 * @param pjp	连接点
	 * @return		ModelAndView
	 */
	@Around("execution(* nju.software.sjy.controller.KfqxController.addKfqx(..))")
	public ModelAndView addKfqxAccess(ProceedingJoinPoint pjp)
	{
		/* 获取request对象 */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		String rybhStr = request.getParameter("rybh");
		int rybh = Integer.parseInt(rybhStr);
		
		TUser target = userService.getUserByRybh(rybh);
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 操作权限认证 */
		StatusMsg sm = authorityService.isAccessedOperation(self, target, Constants.EDIT_CN);
		
		ModelAndView mav = null;
		
		/* 如果有权限则执行原来的增加操作，否则不执行原操作返回deny */
		if(sm != null && sm.getStatus() != 0)
		{
			try 
			{
				/* 相当于执行KfqxController.addKfqx(..) */
				mav = (ModelAndView)pjp.proceed();
			}
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			mav = new ModelAndView();
			mav.setViewName("deny");
		}
		
		return mav;
	}
	
	/**
	 * 编辑扣分情形的操作
	 * 
	 * @param pjp	连接点
	 */
	@Around("execution(* nju.software.sjy.controller.KfqxController.editKfqx(..))")
	public void editKfqxAccess(ProceedingJoinPoint pjp)
	{
		/* 获取request和response，edit是ajax请求 */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		HttpServletResponse response = (HttpServletResponse)pjp.getArgs()[1];
		String bhStr = request.getParameter("bh");
		int bh = Integer.parseInt(bhStr);
		TKfqx kfqx = kfqxService.getKfqxByBh(bh);
		TUser target = kfqx.getRy();
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 操作权限认证 */
		StatusMsg sm = authorityService.isAccessedOperation(self, target, Constants.EDIT_CN);
		
		/* 如果有权限则执行原来的编辑操作，否则不执行原操作 */
		if(sm != null && sm.getStatus() != 0)
		{
			try 
			{
				/* 相当于执行KfqxController.editKfqx(..) */
				pjp.proceed();
			}
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("StatusMsg", sm);
			
			try 
			{
				response.setContentType("text/html;charset=UTF-8");
				String jsonStr = JSONObject.fromObject(jsonObj).toString();
				response.getWriter().print(jsonStr);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 删除扣分情形的操作
	 * 
	 * @param pjp	连接点
	 * @return		ModelAndView
	 */
	@Around("execution(* nju.software.sjy.controller.KfqxController.deleteKfqx(..))")
	public ModelAndView deleteKfqxAccess(ProceedingJoinPoint pjp)
	{
		/* 获取request */
		HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[0];
		String[] kfqxbhStrArr = request.getParameterValues("kfqxbh");
		int[] kfqxbhArr = null;
		
		if(kfqxbhStrArr != null)
		{
			kfqxbhArr = new int[kfqxbhStrArr.length];
			for(int i=0;i<kfqxbhStrArr.length;i++)
			{
				kfqxbhArr[i] = Integer.parseInt(kfqxbhStrArr[i]);
			}
		}
		
		TUser self = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 遍历待删除的扣分情形列表，如果有一个没有权限删除，则立即break */
		boolean hasAccess = true;
		if(kfqxbhArr != null)
		{
			for(int kfqxbh : kfqxbhArr)
			{
				TKfqx kfqx = kfqxService.getKfqxByBh(kfqxbh);
				StatusMsg sm = authorityService.isAccessedOperation(self, kfqx.getRy(), Constants.EDIT_CN);
				if(sm != null && sm.getStatus() == 0)
				{
					hasAccess = false;
					break;
				}
			}
		}
		
		ModelAndView mav = null;
		
		/* 如果有权限则执行原来的删除操作，否则不执行原操作返回deny */
		if(hasAccess)
		{
			try 
			{
				/* 相当于执行KfqxController.deleteKfqx(..) */
				mav = (ModelAndView)pjp.proceed();
			}
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			mav = new ModelAndView();
			mav.setViewName("deny");
		}
		
		return mav;
	}
	
}
