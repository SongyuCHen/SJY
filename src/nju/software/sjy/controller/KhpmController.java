package nju.software.sjy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.common.ComparatorTools;
import nju.software.sjy.common.Constants;
import nju.software.sjy.convertor.PfkhConvertor;
import nju.software.sjy.mapper.MPfkh;
import nju.software.sjy.mapper.MPfkhgz;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TPfkhgz;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.PfkhService;
import nju.software.sjy.service.PfkhgzService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.ViewService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/khpm")
public class KhpmController
{
	private static Logger log = Logger.getLogger(KhpmController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PfkhService pfkhService;
	
	@Autowired
	private PfkhgzService pfkhgzService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ViewService viewService;
	
	@RequestMapping("/index")
	public ModelAndView khpm()
	{
		log.info("come to the khpm page");
		
		ModelAndView mav = (ModelAndView) viewKhpm(null, null, 0, 0, false, false);
		
		return mav;
	}
	
	@RequestMapping(value="/cx.aj", method=RequestMethod.GET)
	@ResponseBody
	public void cx(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		
		String khzbmc = request.getParameter("khzb");
		khzbmc = URLDecoder.decode(khzbmc, "UTF-8");
		String bmlxmc = request.getParameter("bmlx");
		bmlxmc = URLDecoder.decode(bmlxmc, "UTF-8");
		String nf = request.getParameter("nf");
		String jd = request.getParameter("jd");
		int year = Integer.parseInt(nf);
		int quarter = Integer.parseInt(jd);
		
		JSONObject jsonObj = (JSONObject)viewKhpm(khzbmc, bmlxmc, year, quarter, true, true);

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
	
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel() throws UnsupportedEncodingException
	{
		String khzbmc = request.getParameter("khzbmc");
		String bmlxmc = request.getParameter("bmlxmc");
		String nf = request.getParameter("nf");
		String jd = request.getParameter("jd");
		khzbmc = URLDecoder.decode(khzbmc, "UTF-8");
		bmlxmc = URLDecoder.decode(bmlxmc, "UTF-8");
		int year = Integer.parseInt(nf);
		int quarter = Integer.parseInt(jd);
		
		List<MPfkh> mPfkhList = null;
		List<MPfkhgz> mPfkhgzList = null;
		
		TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
		
		/*  */
		List<String> khjsmcList = gypzService.getMcByLx(Constants.KHJS);
		List<TRole> roleList = roleService.getRoleByRolenamelist(khjsmcList);
		
		List<TUser> userList;
		if(bmlxmc != null && bmlxmc.equals(Constants.SYBM))
		{
			userList = userService.getUserByRolelistExcludeQtbmlx(roleList, qtbmlx);
		}
		else
		{
			TGypz bmlx = gypzService.getGypzByLxMc(Constants.BMLX, bmlxmc);
			List<TBm> bmList = bmService.getBmByBmlx(bmlx);
			userList = userService.getUserByBmlistRolelist(bmList, roleList);
		}
		
		/*  */
		List<TPfkh> tPfkhList = pfkhService.getPfkhByUserlistNfJd(userList, year, quarter);
		List<TPfkhgz> tPfkhgzList = null;
		
		/*  */
		if(khzbmc != null && !khzbmc.equals(Constants.SYZB))
		{
			TGypz gz = gypzService.getGypzByLxMc(Constants.GZ, khzbmc);
			tPfkhgzList = pfkhgzService.getPfkhgzByPfkhlistGz(tPfkhList, gz);
			mPfkhgzList = PfkhConvertor.convertTPfkhgz(tPfkhgzList);
			Collections.sort(mPfkhgzList, ComparatorTools.PFKHGZ_DESC_COMP);
		}
		else
		{
			mPfkhList = new ArrayList<MPfkh>();
			/*  */
			if(tPfkhList != null)
			{
				for(TPfkh tpfkh : tPfkhList)
				{
					List<TPfkhgz> pfkhgzList = pfkhgzService.getPfkhgzByPfkh(tpfkh);
					List<MPfkhgz> mpfkhgzList = PfkhConvertor.convertTPfkhgz(pfkhgzList);
					MPfkh mpfkh = PfkhConvertor.convert(tpfkh, mpfkhgzList);
					mPfkhList.add(mpfkh);
				}
			}
			Collections.sort(mPfkhList, ComparatorTools.PFKH_DESC_COMP);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("khpmExcelView");
		
		mav.addObject("mPfkhList", mPfkhList);//9.
		mav.addObject("mPfkhgzList", mPfkhgzList);//9.
		
		return mav;
	}
	
	/**
	 * 考核排名页面需要显示的内容
	 * 1.考核指标列表
	 * 2.部门类型列表
	 * 3.年份列表
	 * 4.季度列表
	 * 5.当前考核指标名称
	 * 6.当前部门类型名称
	 * 7.当前年份
	 * 8.当前季度
	 * 9.考核排名列表
	 * 
	 * @param bmlxmc
	 * @param year
	 * @param quarter
	 * @return
	 */
	public Object viewKhpm(String khzbmc, String bmlxmc, int year, int quarter, boolean needGenerate, boolean isJson)
	{
		/* 1.考核指标列表 */
		List<TGypz> gzList = viewService.getWrappedKhzbList();
		
		/* 2.部门类型列表 */
		List<String> bmlxmcList = viewService.getWrappedBmlxmcList();
		
		/* 3.年份列表 */
		int[] years = viewService.getYears();
		
		/* 4.季度列表 */
		int[] quarters = viewService.getQuarters();
		
		/* 5.当前考核指标名称 */
		khzbmc = viewService.getKhzbmc(khzbmc);
		
		/* 6.当前部门类型名称 */
		bmlxmc = viewService.getBmlxmc(bmlxmc);
		
		/* 7.当前年份 */
		year = viewService.getYear(year);
		
		/* 8.当前季度 */
		quarter = viewService.getQuarter(quarter);
		
		/* 9.考核排名列表 */
		List<MPfkh> mPfkhList = null;
		List<MPfkhgz> mPfkhgzList = null;
		if(needGenerate)
		{
			TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
			
			/* 获取配置的需要进行考核的人员————只有人员类型名称有用 */
			List<String> khjsmcList = gypzService.getMcByLx(Constants.KHJS);
			List<TRole> roleList = roleService.getRoleByRolenamelist(khjsmcList);
			
			List<TUser> userList;
			if(bmlxmc != null && bmlxmc.equals(Constants.SYBM))
			{
				userList = userService.getUserByRolelistExcludeQtbmlx(roleList, qtbmlx);
			}
			else
			{
				TGypz bmlx = gypzService.getGypzByLxMc(Constants.BMLX, bmlxmc);
				List<TBm> bmList = bmService.getBmByBmlx(bmlx);
				userList = userService.getUserByBmlistRolelist(bmList, roleList);
			}
			
			/* 如果考核指标为所有指标，则直接到TPfkh中查询总分 */
			List<TPfkh> tPfkhList = pfkhService.getPfkhByUserlistNfJd(userList, year, quarter);
			List<TPfkhgz> tPfkhgzList = null;
			
			/* 如果考核指标不是所有，则需要到Tpfkhgz中查询相应规则的得分 */
			if(khzbmc != null && !khzbmc.equals(Constants.SYZB))
			{
				TGypz gz = gypzService.getGypzByLxMc(Constants.GZ, khzbmc);
				tPfkhgzList = pfkhgzService.getPfkhgzByPfkhlistGz(tPfkhList, gz);
				mPfkhgzList = PfkhConvertor.convertTPfkhgz(tPfkhgzList);
				Collections.sort(mPfkhgzList, ComparatorTools.PFKHGZ_DESC_COMP);
			}
			else
			{
				mPfkhList = new ArrayList<MPfkh>();
				/* 查询该评分考核所有的规则的得分 */
				if(tPfkhList != null)
				{
					for(TPfkh tpfkh : tPfkhList)
					{
						List<TPfkhgz> pfkhgzList = pfkhgzService.getPfkhgzByPfkh(tpfkh);
						List<MPfkhgz> mpfkhgzList = PfkhConvertor.convertTPfkhgz(pfkhgzList);
						MPfkh mpfkh = PfkhConvertor.convert(tpfkh, mpfkhgzList);
						mPfkhList.add(mpfkh);
					}
				}
				Collections.sort(mPfkhList, ComparatorTools.PFKH_DESC_COMP);
			}
		}
		
		if(isJson){
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("gzList", gzList);
			jsonObj.put("bmlxmcList", bmlxmcList);
			jsonObj.put("years", years);
			jsonObj.put("quarters", quarters);
			jsonObj.put("curKhzbmc", khzbmc);//5.当前考核指标名称
			jsonObj.put("curBmlxmc", bmlxmc);//6.当前部门类型名称
			jsonObj.put("curYear", year);//7.当前年份
			jsonObj.put("curQuarter", quarter);//8.当前季度
			jsonObj.put("mPfkhList", mPfkhList);
			jsonObj.put("mPfkhgzList", mPfkhgzList);
			
			return jsonObj;
		}
		else{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("khpm");
			
			mav.addObject("gzList", gzList);//1.考核指标列表
			mav.addObject("bmlxmcList", bmlxmcList);//2.部门类型名称列表
			mav.addObject("years", years);//3.年份列表
			mav.addObject("quarters", quarters);//4.季度列表
			mav.addObject("curKhzbmc", khzbmc);//5.当前考核指标名称
			mav.addObject("curBmlxmc", bmlxmc);//6.当前部门类型名称
			mav.addObject("curYear", year);//7.当前年份
			mav.addObject("curQuarter", quarter);//8.当前季度
			mav.addObject("mPfkhList", mPfkhList);//9.考核排名列表
			mav.addObject("mPfkhgzList", mPfkhgzList);//9.考核排名列表
			
			return mav;
		}
	}
}
