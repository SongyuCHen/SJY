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
import nju.software.sjy.common.Constants;
import nju.software.sjy.convertor.PfkhConvertor;
import nju.software.sjy.mapper.MGypz;
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
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.service.GzxxService;
import nju.software.sjy.service.JfqxService;
import nju.software.sjy.service.KfqxService;
import nju.software.sjy.service.PfkhService;
import nju.software.sjy.service.PfkhgzService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.ScoreService;
import nju.software.sjy.service.StatisticsService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.ViewService;
import nju.software.sjy.util.NumberUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pfkh")
public class PfkhController 
{
	private static Logger log = Logger.getLogger(PfkhController.class);
	
	private final static Object sync = new Object();
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private PfkhService pfkhService;
	
	@Autowired
	private PfkhgzService pfkhgzService;
	
	@Autowired
	private GzsjxxService gzsjxxService;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JfqxService jfqxService;
	
	@Autowired
	private KfqxService kfqxService;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private GzxxService gzxxService;
	
	@Autowired
	private ViewService viewService;
	
	/**
	 * 生成考核主页面
	 * 
	 * @return	ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView pfkh()
	{
		log.info("come to the pfkh page");
		
		ModelAndView mav = viewDetailPfkh(null ,null, 0, 0);
		
		return mav;
	}
	
	/**
	 * 生成单个评分考核
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cx")
	public ModelAndView cx(HttpServletRequest request)
	{
		String bmmc = request.getParameter("bm");
		String ryxm = request.getParameter("xm");
		String nf = request.getParameter("nf");
		String jd = request.getParameter("jd");
		
		int year = Integer.parseInt(nf);
		int quarter = Integer.parseInt(jd);
		
		ModelAndView mav = viewDetailPfkh(bmmc, ryxm, year, quarter);
		
		return mav;
	}
	
	/**
	 * 批量考核
	 * 
	 * @return
	 */
	@RequestMapping("/plkh")
	public ModelAndView plkh()
	{
		log.info("come to the plkh page");
		
		ModelAndView mav = (ModelAndView) viewPlkh(null, 0, 0, false, false);
		
		return mav;
	}
	
	/**
	 * 批量生成考核
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/scplkh.aj", method=RequestMethod.GET)
	@ResponseBody
	public void scplkh(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		log.info("generate phkh of one bm");
		
		request.setCharacterEncoding("UTF-8");
		String bmmc = request.getParameter("bmmc");
		bmmc = URLDecoder.decode(bmmc, "UTF-8");
		
		String nf = request.getParameter("nf");
		String jd = request.getParameter("jd");
		
		int year = Integer.parseInt(nf);
		int quarter = Integer.parseInt(jd);
		
		JSONObject jsonObj = (JSONObject) viewPlkh(bmmc, year, quarter, true, true);
		
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
    public ModelAndView downloadExcel(HttpServletRequest request, HttpServletResponse response) 
	{
		String bmlxmc = request.getParameter("bmlxmc");
		String nf = request.getParameter("nf");
		String jd = request.getParameter("jd");
		
		int year = Integer.parseInt(nf);
		int quarter = Integer.parseInt(jd);
		
		TGypz bmlx = gypzService.getGypzByLxMc(Constants.BMLX, bmlxmc);
		List<TBm> bmList = bmService.getBmByBmlx(bmlx);
		
		List<TPfkh> tlist = pfkhService.getPfkhByBmlistNfJd(bmList, year, quarter);
		List<MPfkh> mlist = PfkhConvertor.convert(tlist);
		
        return new ModelAndView("pfkhExcelView", "mlist", mlist);
    }
	
	@RequestMapping(value = "/downloadPlkhExcel", method = RequestMethod.GET)
	public ModelAndView downPlkhExcel() throws UnsupportedEncodingException
	{
		String bmmc = request.getParameter("bmmc");
		String nf = request.getParameter("nf");
		String jd = request.getParameter("jd");
		bmmc = URLDecoder.decode(bmmc, "UTF-8");
		int year = Integer.parseInt(nf);
		int quarter = Integer.parseInt(jd);
		
		List<MPfkh> mlist = new ArrayList<MPfkh>();

		TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
		List<String> khjsmcList = gypzService.getMcByLx(Constants.KHJS);
		List<TRole> roleList = roleService.getRoleByRolenamelist(khjsmcList);
			
		List<TUser> userList;
		if(bmmc != null && bmmc.equals(Constants.SYBM))
		{
			userList = userService.getUserByRolelistExcludeQtbmlx(roleList, qtbmlx);
		}
		else
		{
			TBm bm =  bmService.getBmByBmmc(bmmc);
			userList = userService.getUserByBmRolelist(bm, roleList);
		}
		if(userList != null)
		{
			for(TUser user : userList)
			{
				MPfkh mpfkh = generateSimplePfkh(user, year, quarter);
				mlist.add(mpfkh);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("plkhExcelView");
		
		mav.addObject("plkhList", mlist);
		
		return mav;
	}
	
	/**
	 * 生成某个人员指定年份季度下的评分考核
	 * 
	 * @param user		人员
	 * @param year		年份
	 * @param quarter	季度
	 * @return			TPfkh
	 */
	public MPfkh generateSimplePfkh(TUser user, int year, int quarter)
	{
		/* 总分 */
		double totalScore = 0;
		
		/* 根据人员、年份、季度获取评分考核 */
		TPfkh tpfkh = pfkhService.getPfkhByUserNfJd(user, year, quarter);
		/* 如果不存在该人员的评分考核，则新建并保存到数据库，此时得分为0，后面更新 */
		if(tpfkh == null)
		{
			synchronized(sync)
			{
				int bh = pfkhService.getMaxPfkhBh() + 1;
				tpfkh = new TPfkh();
				tpfkh.setBh(bh);
				tpfkh.setUser(user);
				tpfkh.setNf(year);
				tpfkh.setJd(quarter);
				tpfkh.setJddf(0.0);
				
				pfkhService.save(tpfkh);
			}
		}
		
		/* 决定评分的规则 */
		List<TGypz> gzList = gypzService.getGypzByLx(Constants.GZ);
		List<MPfkhgz> pfkhgzList = new ArrayList<MPfkhgz>();
		for(int i=0;i<gzList.size();i++)
		{
			TGypz gz = gzList.get(i);
			/*
			 * 根据规则名称得出该规则名称拥有的规则细项
			 * 这里的规则名称当作TGypz表中的lx字段
			 * 得出如：工作实值-装订卷宗; 工作实值-庭审记录; 工作实绩-送达数
			 */
			List<TGypz> gzxxpz = gzxxService.getGzxx(gz);
			
			/* 每个规则对应的得分，这里应该是各个规则细项的得分总和 */
			double gzScore = scoreService.getScore(gz, gzxxpz, user, year, quarter);
			
			/* 保存每个规则的得分 */
			TPfkhgz tpfkhgz = pfkhgzService.getPfkhgzByPfkhGz(tpfkh, gz);
			if(tpfkhgz != null)
			{
				tpfkhgz.setGzdf(gzScore);
				pfkhgzService.updatePfkhgz(tpfkhgz);
			}
			else
			{
				synchronized(sync)
				{
					int bh = pfkhgzService.getMaxbh() + 1;
					tpfkhgz = new TPfkhgz(bh, tpfkh, gz, gzScore);
					pfkhgzService.savePfkhgz(tpfkhgz);
				}
			}
			
			MPfkhgz pfkhgz = new MPfkhgz(gz.getMc(), gzScore);
			pfkhgzList.add(pfkhgz);

			totalScore += gzScore;
		}
		
		totalScore = NumberUtil.formatDouble(totalScore);
		if(tpfkh != null)
		{
			if(totalScore != tpfkh.getJddf())
			{
				tpfkh.setJddf(totalScore);
				pfkhService.update(tpfkh);
			}
		}
		
		MPfkh mpfkh = PfkhConvertor.convert(tpfkh, pfkhgzList);
		
		return mpfkh;
	}
	
	/**
	 * 生成详细的评分考核
	 * 
	 * @param user		人员
	 * @param year		年份
	 * @param quarter	季度
	 * @return			详细的评分考核
	 */
	public MPfkh generateDetailPfkh(TUser user, int year, int quarter)
	{
		if(user == null)
		{
			return null;
		}
		
		/* 总分 */
		double totalScore = 0;
		
		/* 根据人员、年份、季度获取评分考核 */
		TPfkh tpfkh = pfkhService.getPfkhByUserNfJd(user, year, quarter);
		/* 如果不存在该人员的评分考核，则新建并保存到数据库，此时得分为0，后面更新 */
		if(tpfkh == null)
		{
			synchronized(sync)
			{
				int bh = pfkhService.getMaxPfkhBh() + 1;
				tpfkh = new TPfkh();
				tpfkh.setBh(bh);
				tpfkh.setUser(user);
				tpfkh.setNf(year);
				tpfkh.setJd(quarter);
				tpfkh.setJddf(0.0);
				
				pfkhService.save(tpfkh);
			}
		}
		
		List<MPfkhgz> pfkhgzList = new ArrayList<MPfkhgz>();
		
		/* 获取所有决定评分的规则 */
		List<TGypz> gzList = gypzService.getGypzByLx(Constants.GZ);
		
		for(int i=0;i<gzList.size();i++)
		{
			TGypz gz = gzList.get(i);
			String gzmc = gz.getMc();
			
			/* 根据规则获取所有的规则细项 */
			List<TGypz> gzxxpz = gzxxService.getGzxx(gz);
			
			/* 记录规则细项的名称和数值 */
			List<MGypz> gzxxList = new ArrayList<MGypz>();
			/* 每个规则对应的得分，这里应该是各个规则细项的得分总和 */
			double gzScore = scoreService.getScore(gz, gzxxpz, user, year, quarter);
			
			/* 保存每个规则的得分 */
			TPfkhgz tpfkhgz = pfkhgzService.getPfkhgzByPfkhGz(tpfkh, gz);
			if(tpfkhgz != null)
			{
				tpfkhgz.setGzdf(gzScore);
				pfkhgzService.updatePfkhgz(tpfkhgz);
			}
			else
			{
				synchronized(sync)
				{
					int bh = pfkhgzService.getMaxbh() + 1;
					tpfkhgz = new TPfkhgz(bh, tpfkh, gz, gzScore);
					pfkhgzService.savePfkhgz(tpfkhgz);
				}
			}
			
			if(gzxxList != null)
			{
				for (int j = 0; j < gzxxpz.size(); j++)
				{
					TGypz gzxx = gzxxpz.get(j);
					String gzxxmc = gzxx.getMc();

					/* 查询出规则细项的数值，得到如：装订卷宗的数值20 */
					int count = statisticsService.getCount(gzxx, user, year, quarter);
					
					/* 页面上只显示规则细项的  名称-数值 */
					MGypz mgypz = new MGypz();
					mgypz.setMc(gzxxmc);
					mgypz.setSz(count);

					gzxxList.add(mgypz);
				}
				
				/* 如果某个规则下一个规则细项都没有，用无代替 */
				if(gzxxList.isEmpty())
				{
					MGypz mgypz = new MGypz();
					mgypz.setMc("无");
					mgypz.setSz(0);
					
					gzxxList.add(mgypz);
				}
				
			}
			/* 按照数值倒序排列，方便实现slideToggle效果，尽可能保证每个分类的第一个项目不会被折叠掉 */
			Collections.sort(gzxxList, MGypz.MGypzComparator);
			
			MPfkhgz mpfkhgz = new MPfkhgz();
			mpfkhgz.setGzmc(gzmc);
			mpfkhgz.setGzdf(gzScore);
			mpfkhgz.setGzxxList(gzxxList);
			
			pfkhgzList.add(mpfkhgz);
			
			totalScore += gzScore;
		}
		
		totalScore = NumberUtil.formatDouble(totalScore);
		
		if(tpfkh != null && tpfkh.getJddf() != null && tpfkh.getJddf() != totalScore)
		{
			tpfkh.setJddf(totalScore);
			pfkhService.update(tpfkh);
		}
		
		MPfkh mpfkh = PfkhConvertor.convert(tpfkh, pfkhgzList);
		
		return mpfkh;
	}
	
	/**
	 * 生成考核页面需要展示的内容如下
	 * 1.部门列表
	 * 2.人员列表
	 * 3.年份列表
	 * 4.季度列表
	 * 5.当前部门名称
	 * 6.当前人员姓名
	 * 7.当前年份
	 * 8.当前季度
	 * 9.详细评分考核
	 */
	public ModelAndView viewDetailPfkh(String bmmc, String ryxm, int year, int quarter)
	{
		/* 1.部门列表 */
		List<TBm> bmList = viewService.getBmList();
		
		/* 2.人员列表 */
		List<TUser> userList = viewService.getUserList(bmmc, bmList);
		//List<TUser> userList = viewService.getUserList(bmList);
		
		/* 3.年份列表 */
		int[] years = viewService.getYears();
		
		/* 4.季度列表 */
		int[] quarters = viewService.getQuarters();
		
		/* 5.当前部门名称 
		 * 6.当前人员姓名
		 * 7.当前年份
		 * 8.当前季度 */
		year = viewService.getYear(year);
		quarter = viewService.getQuarter(quarter);
		
		/* 9.详细评分考核 */
		TUser user = userService.getUserByXmAndBmmc(ryxm, bmmc);
		if(user == null)
		{
			if(userList != null && !userList.isEmpty())
			{
				user = userList.get(0);
			}
		}
		/* 生成详细的评分考核 */
		MPfkh mpfkh = generateDetailPfkh(user, year, quarter);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pfkh");
		
		mav.addObject("bmList", bmList);//1.部门列表
		mav.addObject("userList", userList);//2.人员列表
		mav.addObject("years", years);//3.年份列表
		mav.addObject("quarters", quarters);//4.月份列表
		mav.addObject("mpfkh", mpfkh);//5~9.详细评分考核
		
		return mav;
	}
	
	/**
	 * 批量考核页面需要显示的内容
	 * 
	 * 1.部门列表
	 * 2.年份列表
	 * 3.季度列表
	 * 4.当前部门名称
	 * 5.当前年份
	 * 6.当前季度
	 * 7.评分考核列表
	 * 
	 * @param bmmc		部门名称
	 * @param year		年份
	 * @param quarter	季度
	 * @return			ModelAndView
	 */
	public Object viewPlkh(String bmmc, int year, int quarter, boolean needGenerate, boolean isJson)
	{	
		/* 1.部门列表 */
		List<TBm> bmList = viewService.getWrappedBmList();
		
		/* 2.年份列表 */
		int[] years = viewService.getYears();
		
		/* 3.季度列表 */
		int[] quarters = viewService.getQuarters();
		
		/* 4.当前部门名称 */
		bmmc = viewService.getBmmc(bmmc);
		
		/* 5.当前年份 */
		year = viewService.getYear(year);
		
		/* 6.当前季度 */
		quarter = viewService.getQuarter(quarter);
		
		/* 7.评分考核列表 */
		List<MPfkh> mlist = new ArrayList<MPfkh>();
		if(needGenerate)
		{
			TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
			List<String> khjsmcList = gypzService.getMcByLx(Constants.KHJS);
			List<TRole> roleList = roleService.getRoleByRolenamelist(khjsmcList);
			
			List<TUser> userList;
			if(bmmc != null && bmmc.equals(Constants.SYBM))
			{
				userList = userService.getUserByRolelistExcludeQtbmlx(roleList, qtbmlx);
			}
			else
			{
				TBm bm =  bmService.getBmByBmmc(bmmc);
				userList = userService.getUserByBmRolelist(bm, roleList);
			}
			if(userList != null)
			{
				for(TUser user : userList)
				{
					MPfkh mpfkh = generateSimplePfkh(user, year, quarter);
					mlist.add(mpfkh);
				}
			}
		}
		
		if(isJson)
		{
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("bmList", bmList);
			jsonObj.put("years", years);
			jsonObj.put("quarters", quarters);
			jsonObj.put("mlist", mlist);
			jsonObj.put("curBmmc", bmmc);//4.当前部门名称
			jsonObj.put("curYear", year);//5.当前年份
			jsonObj.put("curQuarter", quarter);//6.当前季度
			
			return jsonObj;
		}
		else
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("plkh");
			
			mav.addObject("bmList", bmList);//1.部门列表
			mav.addObject("years", years);//2.年份列表
			mav.addObject("quarters", quarters);//3.季度列表
			mav.addObject("curBmmc", bmmc);//4.当前部门名称
			mav.addObject("curYear", year);//5.当前年份
			mav.addObject("curQuarter", quarter);//6.当前季度
			mav.addObject("mlist", mlist);//7.评分考核列表
			
			return mav;
		}
	}
}
