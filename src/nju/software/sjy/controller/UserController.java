package nju.software.sjy.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.software.sjy.common.ComparatorTools;
import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.convertor.ResourceConvertor;
import nju.software.sjy.convertor.UserConvertor;
import nju.software.sjy.mapper.MResource;
import nju.software.sjy.mapper.MUser;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TResource;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.AuthorityService;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.ViewService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController 
{
	private static Logger log = Logger.getLogger(UserController.class);
	
	private static final Object sync = new Object();
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private ViewService viewService;
	
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
	@RequestMapping("/tcdl")
	public ModelAndView tcdl()
	{
		log.info("come to the tcdl page");
		
		//将session中的user清空
		request.getSession().removeAttribute(SessionKey.SESSION_USER);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login()
	{
		//重新登录时销毁该用户的session
		Object o = request.getSession().getAttribute(SessionKey.SPRING_SECURITY_CONTEXT);
		if(null != o)
		{
			request.getSession().removeAttribute(SessionKey.SPRING_SECURITY_CONTEXT);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/signIn")
	public ModelAndView signin()
	{
		log.info("user sign in the system");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		TUser user = userService.getUserByUsernamePassword(username, password);
		
		String status;
		ModelAndView mav = new ModelAndView();
		
		if(user != null)
		{
			Authentication authentication = myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);  
		    session.setAttribute(SessionKey.SPRING_SECURITY_CONTEXT, securityContext);  
		    // 当验证都通过后，把用户信息放在session里
			request.getSession().setAttribute(SessionKey.SESSION_USER, user);
			status = "success";
			
			//用户角色
			TRole role = user.getRole();
			
			//查询出用户所能访问的资源——导航栏将要显示的项目
			List<TResource> tResList = roleService.getResByRole(role);
			List<MResource> mResList = ResourceConvertor.convert(tResList);
			
			request.getSession().setAttribute("mResList", mResList);
			
			mav.setViewName("gztb");
			mav.addObject("status", status);
		}
		else
		{
			log.info("username or password is wrong");
			status = "error";
			mav.setViewName("login");
			mav.addObject("status", status);
		}
		
		return mav;
	}
	
	@RequestMapping("/fhsy")
	public ModelAndView fhsy()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gztb");
		return mav;
	}
	
	@RequestMapping("/deny")
	public ModelAndView deny()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("deny");
		return mav;
	}
	
	@RequestMapping("/timeout")
	public ModelAndView timeout()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="/checkPwd.aj", method=RequestMethod.GET)
	@ResponseBody
	public void checkPwd(HttpServletRequest request, HttpServletResponse response)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		String pwd = request.getParameter("pwd");
		int status;
		if(pwd.equals(user.getPassword()))
		{
			status = 1;
		}
		else
		{
			status = 0;
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		
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
	
	@RequestMapping("/modifyPwd")
	public ModelAndView modifyPwd()
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		String password = request.getParameter("newPwd");
		
		user.setPassword(password);
		
		userService.update(user);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jump");
		return mav;
	}
	
	@RequestMapping(value="/getAccessUser.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAccessUser(HttpServletRequest request, HttpServletResponse response)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		List<TUser> ryList = authorityService.getAccessUserByUserBm(user, null);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("ryList", ryList);
		
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
	
	@RequestMapping(value="/getAccessNeedEvaluationUser.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAccessNeedEvaluationUser(HttpServletRequest request, HttpServletResponse response)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		List<TUser> ryList = authorityService.getAccessNeedEvaluationUserByUserBm(user, null);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("ryList", ryList);
		
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
	
	@RequestMapping(value="/getAccessNeedEvaluationRyxm.aj")
	@ResponseBody
	public void getAccessNeedEvaluationRyxm(HttpServletRequest request, HttpServletResponse response)
	{
		String bmmc = request.getParameter("bm");
		TBm bm = bmService.getBmByBmmc(bmmc);
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		List<TUser> userList = authorityService.getAccessNeedEvaluationUserByUserBm(user, bm);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userList", userList);
		
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
	
	@RequestMapping("/getXmByBmmc.aj")
	@ResponseBody
	public void getXmByBm(HttpServletRequest request, HttpServletResponse response)
	{
		String bmmc = request.getParameter("bm");
		
		log.info("get all user of a bm " + bmmc);
		
		List<String> list = userService.getXmByBmmc(bmmc);
		
		JSONArray array = new JSONArray();
		for(String xm : list)
		{
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("xm", xm);
			
			array.add(jsonObj);
		}
		
		try
		{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(array.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/yhgl")
	public ModelAndView yhgl()
	{
		ModelAndView mav = viewYhgl(null, null);
		
		return mav;
	}
	
	@RequestMapping("/qxgl")
	public ModelAndView qxgl()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qxgl");
		return mav;
	}
	
	@RequestMapping("/cx")
	public ModelAndView cx()
	{
		String bmmc = request.getParameter("bm");
		String rolename = request.getParameter("role");
		
		ModelAndView mav = viewYhgl(bmmc, rolename);
		
		return mav;
	}
	
	@RequestMapping("/addUser")
	public ModelAndView addUser()
	{
		String bmmc = request.getParameter("addBm");
		String xm = request.getParameter("addXm");
		String rolename = request.getParameter("addRole");
		String username = request.getParameter("addUsername");
		String password = request.getParameter("addPassword");
		List<TGypz> pzList = gypzService.getGypzByLx("法院代码");
		String fydm="";
		if(pzList!=null && pzList.size()>0){
			TGypz gypz = pzList.get(0);
			fydm = gypz.getMc();
		}
			
		String userid = fydm.concat(username);
		TBm bm = bmService.getBmByBmmc(bmmc);
		TRole role = roleService.getRoleByRolename(rolename);
		
		synchronized(sync)
		{
			int bh = userService.getMaxBh() + 1;
			TUser user = new TUser(bh, xm, bm, username, password, "", role, userid);
			
			userService.save(user);
		}
		
		ModelAndView mav = viewYhgl(bmmc, rolename);
		
		return mav;
	}
	
	@RequestMapping(value="/editUser", method=RequestMethod.POST)
	@ResponseBody
	public void editUser(HttpServletRequest request, HttpServletResponse response)
	{
		String bhStr = request.getParameter("editBh");
		String bmmc = request.getParameter("editBm");
		String xm = request.getParameter("editXm");
		String rolename = request.getParameter("editRole");
		String username = request.getParameter("editUsername");
		String password = request.getParameter("editPassword");
		
		int bh = Integer.parseInt(bhStr);
		
		TBm bm = bmService.getBmByBmmc(bmmc);
		TRole role = roleService.getRoleByRolename(rolename);
		
		TUser user = userService.getUserByRybh(bh);
		if(user != null)
		{
			user.setBm(bm);
			user.setXm(xm);
			user.setUsername(username);
			user.setPassword(password);
			user.setRole(role);
			
			userService.update(user);
		}
		
		String status = "success";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		
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
	
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser()
	{
		String[] bhArr = request.getParameterValues("rybh");
		if(bhArr!=null && bhArr.length > 0)
		{
			for(String bhStr : bhArr)
			{
				int rybh = Integer.parseInt(bhStr);
				
				TUser user = userService.getUserByRybh(rybh);
				
				userService.delete(user);
			}
		}
		
		ModelAndView mav = viewYhgl(null, null);
		
		return mav;
	}
	
	/**
	 * 用户管理需要展示的内容
	 * 1.增加时的部门列表（不包含全部部门）
	 * 2.查询时的部门列表（包含全部部门）
	 * 3.增加时的角色列表（不包含所有角色）
	 * 4.查询时的角色列表（包含所有角色）
	 * 5.当前部门名称
	 * 6.当前角色名称
	 * 7.人员列表
	 * 
	 * @param bmmc
	 * @param rolename
	 * 
	 * @return
	 */
	public ModelAndView viewYhgl(String bmmc, String rolename)
	{
		
		/* 1.增加时的部门列表（不包含全部部门） */
		List<TBm> addBmList = viewService.getAllBmList();
		
		/* 2.查询时的部门列表（包含全部部门） */
		List<TBm> searchBmList = viewService.getAllWrappedBmList();
		
		/* 3.增加时的角色列表（不包含所有角色） */
		List<TRole> addRoleList = viewService.getAllRoleList();
		
		/* 4.查询时的角色列表（包含所有角色） */
		List<TRole> searchRoleList = viewService.getAllWrappedRoleList();
		
		/* 5.当前部门名称 */
		bmmc = viewService.getSimpleBmmc(bmmc);
		
		/* 6.当前角色名称 */
		rolename = viewService.getRolename(rolename);
		
		/* 7.人员列表 */
		List<TUser> tlist = null;
		if(bmmc != null && bmmc.equals(Constants.SYBM))
		{
			if(rolename != null && rolename.equals(Constants.SYJS))
			{
				tlist = userService.getAllRy();
			}
			else
			{
				TRole role = roleService.getRoleByRolename(rolename);
				tlist = userService.getUserByRole(role);
			}
		}
		else
		{
			TBm bm = bmService.getBmByBmmc(bmmc);
			if(rolename != null && rolename.equals(Constants.SYJS))
			{
				tlist = userService.getUserByBm(bm);
			}
			else
			{
				TRole role = roleService.getRoleByRolename(rolename);
				tlist = userService.getUserByBmRole(bm, role);
			}
		}
		
		Collections.sort(tlist, ComparatorTools.USER_COMP);
		
		List<MUser> mlist = UserConvertor.convert(tlist);
		
		/* 5.查询参数 */
		MUser lastUser = new MUser();
		lastUser.setBmmc(bmmc);
		lastUser.setRolename(rolename);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("yhgl");
		
		mav.addObject("addBmList", addBmList);
		mav.addObject("searchBmList", searchBmList);
		mav.addObject("addRoleList", addRoleList);
		mav.addObject("searchRoleList", searchRoleList);
		mav.addObject("userList", mlist);
		mav.addObject("lastUser", lastUser);
		
		return mav;
	}
}
