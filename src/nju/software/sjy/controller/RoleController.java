package nju.software.sjy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nju.software.sjy.common.Constants;
import nju.software.sjy.convertor.OperationConvertor;
import nju.software.sjy.convertor.ResourceConvertor;
import nju.software.sjy.convertor.RoleConvertor;
import nju.software.sjy.mapper.MOperation;
import nju.software.sjy.mapper.MResource;
import nju.software.sjy.mapper.MRole;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TResource;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TRoleoperation;
import nju.software.sjy.model.xy.TRoleres;
import nju.software.sjy.security.MySecurityMetadataSource;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.OperationService;
import nju.software.sjy.service.RoleService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.corba.se.impl.orbutil.closure.Constant;

@Controller
@RequestMapping("/role")
public class RoleController 
{
	protected static Logger log = Logger.getLogger(RoleController.class);
	
	private static final Object sync = new Object();
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private  MySecurityMetadataSource securityMetadataSource;
	
	@RequestMapping("/index")
	public ModelAndView index()
	{
		List<TRole> tlist = roleService.getAllRoles();
		List<MRole> mlist = new ArrayList<MRole>();
		for(TRole trole : tlist)
		{
			List<TOperation> operationList = roleService.getOperationByRole(trole);
			List<TResource> resList = roleService.getResByRole(trole);
			
			MRole mrole = RoleConvertor.convert(trole, operationList, resList);
			
			mlist.add(mrole);
		}
		
		/* 所有被激活的操作和范围 */
		List<TOperation> tOplist = operationService.getActiveOperation();
		List<MOperation> mOplist = OperationConvertor.convertToTree(tOplist);
		
		/* 所有的资源 */
		List<TResource> tReslist = roleService.getAllResource();
		List<MResource> mReslist = ResourceConvertor.convert(tReslist); 
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qxgl");
		
		mav.addObject("mlist", mlist);//角色-权限列表
		mav.addObject("mOplist", mOplist);
		mav.addObject("mReslist", mReslist);
		
		return mav;
	}
	
	@RequestMapping("/addRole")
	public ModelAndView addRole()
	{
		/* 获取角色名 */
		String rolename = request.getParameter("addRolename");
		
		/* 获取操作和范围 */
		String[] operationArr = request.getParameterValues("addOperation");
		
		/* 获取资源 */
		String[] resArr = request.getParameterValues("addRes");
		
		//缺少一步，判断该角色名是否已经存在，不能重复，还是用ajax做吧
		
		
		synchronized(sync)
		{
			//保存角色
			int rolebh = roleService.getMaxRoleBh() + 1;
			TRole role = new TRole(rolebh, rolename);
			
			roleService.saveRole(role);
			
			/* 保存角色的操作权限，包括操作和范围，如：查看-全院、编辑-本庭、审批-政治部 */
			if(operationArr != null)
			{
				for(int i=0;i<operationArr.length;i++)
				{
					/* 前台保存的是name_index形式 */
					String name_index = operationArr[i];
					String[] arr = name_index.split("_");
					String name = arr[0];
					int index = Integer.parseInt(arr[1]);
					String rangeStr = request.getParameter("addRange"+index);
					TGypz range;
					if(name.equals(Constants.SP))
					{
						range = gypzService.getGypzByLxMc(Constants.SPDW, rangeStr);
					}
					else
					{
						range = gypzService.getGypzByLxMc(Constants.FW, rangeStr);
					}
					
					TOperation operation = operationService.getOperationByNameRange(name, range);
					
					/* 看角色操作权限表中是否包含此记录(role-operation) */
					TRoleoperation tro = roleService.getRoByRoleOperation(role, operation);
					if(tro == null)
					{
						int robh = roleService.getMaxRoleoperationBh() + 1;
						TRoleoperation ro = new TRoleoperation(robh, role, operation);
						
						roleService.saveRoleoperation(ro);
					}
					
				}
			}//end if 保存角色操作权限结束
			
			//保存角色的资源访问权限
			if(resArr != null)
			{
				for(int i=0;i<resArr.length;i++)
				{
					//一级资源保存的是resname_index形式
					String resname_index = resArr[i];
					String[] arr = resname_index.split("_");
					String resname = arr[0];
					int index = Integer.parseInt(arr[1]);
					
					/* 获取一级资源 */
					TResource oneRes = roleService.getResByName(resname);
					
					/* 获取角色-资源 */
					TRoleres roleRes = roleService.getRrByRoleRes(role, oneRes);
					if(roleRes == null)
					{
						int rrbh = roleService.getMaxRoleResBh() + 1;
						roleRes = new TRoleres();
						roleRes.setRrbh(rrbh);
						roleRes.setRole(role);
						roleRes.setRes(oneRes);
						
						/* 保存一级资源 */
						roleService.saveRoleRes(roleRes);
					}
					
					
					//二级资源
					String[] twoReses = request.getParameterValues("addChildRes"+index);
					System.out.println(resname + "---");
					
					if(twoReses != null)
					{
						for(String twoRes : twoReses)
						{
							TResource res = roleService.getResByName(twoRes);
							TRoleres rr = roleService.getRrByRoleRes(role, res);
							
							if(rr == null)
							{
								int rrbh = roleService.getMaxRoleResBh() + 1;
								rr = new TRoleres();
								rr.setRrbh(rrbh);
								rr.setRole(role);
								rr.setRes(res);
								
								roleService.saveRoleRes(rr);
							}
						}
					}//end if 二级资源是否为空
				}//end for 遍历资源数组结束
			}//end if 保存角色资源访问权限结束
		}//end synchronized  锁结束
		
		reloadRoleres();
		
		ModelAndView mav = index();
		
		return mav;
	}
	
	@RequestMapping("/editRole")
	public ModelAndView editRole()
	{
		String editBhStr = request.getParameter("editBh");
		//String editRolename = request.getParameter("editRolename");
		String[] editOperationArr = request.getParameterValues("editOperation");
		String[] editResArr = request.getParameterValues("editRes"); 
		
		int rolebh = Integer.parseInt(editBhStr);
		
		//限定了角色名称不能改变
		
		//获取角色和角色原来拥有的所有权限
		TRole role = roleService.getRoleByRolebh(rolebh);
		List<TRoleoperation> roList = roleService.getRoByRole(role);
		
		//将角色原来的操作权限全部删除
		if(roList != null)
		{
			for(TRoleoperation ro : roList)
			{
				roleService.deleteRoleoperation(ro);
			}
		}
		
		//保存角色的所有新的操作权限
		if(editOperationArr != null)
		{
			for(String operation_index : editOperationArr)
			{
				String[] arr = operation_index.split("_");
				String name = arr[0];
				int index = Integer.parseInt(arr[1]);
				String rangeStr = request.getParameter("editRange" + index);
				TGypz range;
				if(name.equals(Constants.SP))
				{
					range = gypzService.getGypzByLxMc(Constants.SPDW, rangeStr);
				}else if(name.equals(Constants.TH)){
					range = gypzService.getGypzByLxMc(Constants.THDW, rangeStr);
				}
				else
				{
					range = gypzService.getGypzByLxMc(Constants.FW, rangeStr);
				}
				
				TOperation operation = operationService.getOperationByNameRange(name, range);
				
				synchronized(sync)
				{
					int robh = roleService.getMaxRoleoperationBh() + 1;
					TRoleoperation ro = new TRoleoperation(robh, role, operation);
					
					roleService.saveRoleoperation(ro);
				}
			}
		}//end if 保存角色新权限结束
		
		//获取角色原来能访问的资源
		List<TRoleres> rrList = roleService.getRrByRole(role);
		
		//删除原来能访问的资源
		if(rrList != null)
		{
			for(TRoleres rr : rrList)
			{
				roleService.deleteRoleRes(rr);
			}
		}
		
		//保存角色可访问的新资源
		if(editResArr != null)
		{
			for(String editResIndex : editResArr)
			{
				String[] arr = editResIndex.split("_");
				String resname = arr[0];
				int index = Integer.parseInt(arr[1]);
				
				/* 获取一级资源 */
				TResource oneRes = roleService.getResByName(resname);
				
				/* 获取角色-资源 */
				TRoleres roleRes = roleService.getRrByRoleRes(role, oneRes);
				if(roleRes == null)
				{
					int rrbh = roleService.getMaxRoleResBh() + 1;
					roleRes = new TRoleres();
					roleRes.setRrbh(rrbh);
					roleRes.setRole(role);
					roleRes.setRes(oneRes);
					
					/* 保存一级资源 */
					roleService.saveRoleRes(roleRes);
				}
				
				//二级资源
				String[] twoReses = request.getParameterValues("editChildRes"+index);
				System.out.println(resname + "---");
				
				if(twoReses != null)
				{
					for(String twoRes : twoReses)
					{
						TResource res = roleService.getResByName(twoRes);
						TRoleres rr = roleService.getRrByRoleRes(role, res);
						
						if(rr == null)
						{
							int rrbh = roleService.getMaxRoleResBh() + 1;
							rr = new TRoleres();
							rr.setRrbh(rrbh);
							rr.setRole(role);
							rr.setRes(res);
							
							roleService.saveRoleRes(rr);
						}
					}
				}//end if 二级资源是否为空
			}
		}
		
		reloadRoleres();
		
		ModelAndView mav = index();
		
		return mav;
	}
	
	@RequestMapping("/deleteRole")
	public ModelAndView deleteRole()
	{
		String[] rolebhArr = request.getParameterValues("rolebh");
		if(rolebhArr != null)
		{
			for(String rolebhStr : rolebhArr)
			{
				int rolebh = Integer.parseInt(rolebhStr);
				TRole role = roleService.getRoleByRolebh(rolebh);
				
				//先删除角色的所有操作权限和所有能访问的资源，再删除角色本身
				List<TRoleoperation> roList = roleService.getRoByRole(role);
				if(roList != null)
				{
					for(TRoleoperation ro : roList)
					{
						roleService.deleteRoleoperation(ro);
					}
				}
				
				List<TRoleres> rrList = roleService.getRrByRole(role);
				if(rrList != null)
				{
					for(TRoleres rr : rrList)
					{
						roleService.deleteRoleRes(rr);
					}
				}
				
				roleService.deleteRole(role);
			}
		}
		
		reloadRoleres();

		ModelAndView mav = index();
		
		return mav;
	}
	
	private void reloadRoleres()
	{
		log.info("--------begin to reload role resource-----------");
		securityMetadataSource.clear();
		securityMetadataSource.loadResourcesDefine();
		log.info("--------end to reload role resource-----------");
	}
	
}
