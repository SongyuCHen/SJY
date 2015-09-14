package nju.software.sjy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.common.Constants;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.PfkhService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.ViewService;
import nju.software.sjy.util.DateUtil;
import nju.software.sjy.util.NumberUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/tjfx")
public class TjfxController {
	private static Logger log = Logger.getLogger(TjfxController.class);
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private BmService bmService;

	@Autowired
	private UserService userService;

	@Autowired
	private PfkhService pfkhService;
	
	@Autowired
	private ViewService viewService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private GypzService gypzService;
	//@RequestMapping("/index")
	public ModelAndView tjfx()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tjfx");
		return mav;
	}
	
	@RequestMapping("/yzjc")
	public ModelAndView yzjc()
	{
		log.info("come to the yzjc page");
		//List<TBm> bmList = bmService.getAllBm();
		List<TBm> bmList = viewService.getBmList();
		
		String[] bmArr = new String[bmList.size()];
		//int[] ryArr = new int[bmList.size()];
		//int[] allRyArr = new int[bmList.size()];
		//double[] scoreArr = new double[bmList.size()];
		//double[] averArr = new double[bmList.size()];
		int index = 0;
		for(TBm bm:bmList){
			bmArr[index]=bm.getBmmc();
			//ryArr[index]=0;
			//allRyArr[index]=0;
			//scoreArr[index]=0.0;
			//averArr[index]=0.0;			
			++index;
		}

		
				
		//年份和季度
		int[] years = DateUtil.getPastYears();
		int[] quarters = Constants.QUARTERS;
		//int year = DateUtil.getCurrentYear();
		int quarter = DateUtil.getCurrentQuarter();
		//int top_num = Constants.TOP_NUM;
		/**
		 * 1.查找当前指定日期下排名在TOP_NUM的人员信息
		 * 2.最终按照部门排序，获得各个部门的人员数目分布
		 * 3.将部门，人员数目、人员姓名和排名返回至页面
		 */
		/*List<TUser> userList = pfkhService.getTopUserByNfJd(top_num, year, quarter);
		for(TUser user:userList){
			index = bmList.indexOf(user.getBm());
			ryArr[index] = ryArr[index] + 1;
		}*/
		/**
		 * 1.根据部门名称获取当前部门的所有人员
		 * 2.查找每个人的考评情况
		 * 3。计算部门获得的总分和纳入计分的人员姓名（若该人员没有评分，则不纳入平均分计算）
		 */
		/*index = 0;
		for(TBm bm:bmList){
			userList = userService.getUserByBmmc(bm.getBmmc());
			for(TUser user:userList){
				TPfkh tpfkh = pfkhService.getPfkhByUserNfJd(user, year, quarter);
				if(null != tpfkh){
					scoreArr[index] = scoreArr[index] + tpfkh.getJddf();
					allRyArr[index] = allRyArr[index] + 1;
				}
			}
			++index;		
		}
		for(index=0;index < bmList.size(); index++ ){
			if(allRyArr[index]!=0)averArr[index] = scoreArr[index]/allRyArr[index];
		}*/
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("yzjc");
		mav.addObject("bmArr", bmArr);
		//mav.addObject("ryArr", ryArr);
		//mav.addObject("averScore", averArr);
		mav.addObject("years", years);
		mav.addObject("quarters", quarters);
		mav.addObject("thisQuarter", quarter);
		
		return mav;
	}
	@RequestMapping("/getYzjcByTop")
	public void getYzjcByTop(HttpServletRequest request, HttpServletResponse response)
	{
		log.info("come to the yzjc page");
		int year = Integer.valueOf(request.getParameter("year"));
		int quarter = Integer.valueOf(request.getParameter("quarter"));
		int top_num = Constants.TOP_NUM;
		TGypz qtlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
		List<TBm> bmList = bmService.getBmExcludeBmlx(qtlx);
		int[] ryArr = new int[bmList.size()];
		List<Integer> ryNum = new ArrayList<Integer>();
		int index = 0;
		for(index=0;index<bmList.size();index++)
			ryArr[index]=0;
		
		List<TUser> userList = pfkhService.getTopUserByNfJd(top_num, year, quarter);
		for(TUser user:userList){
			index = bmList.indexOf(user.getBm());
			ryArr[index] = ryArr[index] + 1;
		}
		for(index=0;index<bmList.size();index++ )
			ryNum.add(ryArr[index]);
		
		JSONObject jsonObj = new JSONObject();
		
		List<String>bms=new ArrayList<String>();
		for(TBm bm : bmList) bms.add(bm.getBmmc());			

		jsonObj.put("bms", bms);
		jsonObj.put("nums", ryNum);
		
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
	@RequestMapping("/getYzjcByAverage")
	public void getYzjcByAverage(HttpServletRequest request, HttpServletResponse response)
	{
		log.info("come to the yzjc page");
		int year = Integer.valueOf(request.getParameter("year"));
		int quarter = Integer.valueOf(request.getParameter("quarter"));
		TGypz qtlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
		List<TBm> bmList = bmService.getBmExcludeBmlx(qtlx);
		int[] allRyArr = new int[bmList.size()];
		double[] scoreArr = new double[bmList.size()];
		List<Double> averScore = new ArrayList<Double>();
		int index = 0;
		List<TUser> userList;
		for(index=0;index<bmList.size();index++){
			scoreArr[index]=0.0;
			allRyArr[index] = 0;
		}
		index = 0;
		for(TBm bm:bmList){
			TRole sjy = roleService.getRoleByRolename(Constants.SJY);
			userList = userService.getUserByBmRole(bm, sjy);
			for(TUser user:userList){
				TPfkh tpfkh = pfkhService.getPfkhByUserNfJd(user, year, quarter);
				if(null != tpfkh){
					scoreArr[index] = scoreArr[index] + tpfkh.getJddf();
					allRyArr[index] = allRyArr[index] + 1;
				}
			}
			++index;		
		}
		for(index=0;index < bmList.size(); index++ ){
			if(allRyArr[index]!=0) {
				averScore.add(NumberUtil.formatDouble(scoreArr[index]/allRyArr[index]));
			}else{
				averScore.add(0.0);
			}
		}
		
		JSONObject jsonObj = new JSONObject();
		
		List<String>bms=new ArrayList<String>();
		for(TBm bm : bmList) bms.add(bm.getBmmc());			

		jsonObj.put("bms", bms);
		jsonObj.put("scores", averScore);
		
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
	
	
	@RequestMapping("/tzjc")
	public ModelAndView tzjc()
	{
		log.info("come to the tzjc page");
		//List<TBm> bmList = bmService.getAllBm();
		List<TBm> bmList = viewService.getBmList();
		String[] bmArr = new String[bmList.size()];
		int index = 0;
		for(TBm bm:bmList){
			bmArr[index]=bm.getBmmc();
			++index;
		}
		String defaultBm = bmList.get(0).getBmmc();
		
		//年份和季度
		int[] years = DateUtil.getPastYears();
		int[] quarters = Constants.QUARTERS;
		//int year = DateUtil.getCurrentYear();
		int quarter = DateUtil.getCurrentQuarter();
		/**
		 * 1.查找默认部门下的所有书记员
		 * 2.查找这些人员在指定日期的评分考核
		 * 3.将人员姓名和评分考核返回至页面
		 */
		TBm bm = bmService.getBmByBmmc(defaultBm);
		TRole sjy = roleService.getRoleByRolename(Constants.SJY);
		List<TUser> userList = userService.getUserByBmRole(bm, sjy);
		List<String> xmList = new ArrayList<String>();
		//List<Double> scoreList = new ArrayList<Double>();
		String deafultRyxm="";
		for(int i=0;i<userList.size();i++)
		{	
			TUser user = userList.get(i);
			if(i==0) deafultRyxm= user.getXm();			
			//TPfkh tpfkh = pfkhService.getPfkhByUserNfJd(user, year, quarter);
			//if(null != tpfkh){
				//xmList.add(tpfkh.getUser().getXm());
				//scoreList.add(tpfkh.getJddf());
			//}else{
				xmList.add(user.getXm());
				//scoreList.add(0.0);
			//}
		
		}
		//获取default人员的分数和所在部门的平均分,默认获取时间跨度为4个季度
		/*List<Double> ryScoreList = new ArrayList<Double>();
		List<Double> bmScoreList = new ArrayList<Double>();
		List<String> jdList = new ArrayList<String>();
		TUser defaultUser = userService.getUserByUsername(deafultRyxm);
		int toYear,toQuarter;
		for(int i=4;i>=0;i--){
			int totalRyNums=0;
			double totalScores = 0.0;
			toYear = year - (4+i-quarter)/4;
			toQuarter = (4+quarter-i)%4;		
			TPfkh tpfkh = pfkhService.getPfkhByUserNfJd(defaultUser, toYear, toQuarter);
			ryScoreList.add(tpfkh.getJddf());
			//获取人员所在部门的平均得分
			for(TUser user:userList){
				tpfkh = pfkhService.getPfkhByUserNfJd(user, toYear, toQuarter);
				if(null != tpfkh){
					totalScores += tpfkh.getJddf();
					++totalRyNums;
				}
			}
			//bmScoreList.add(totalScores/totalRyNums);
			jdList.add(Integer.toString(toYear).concat(Integer.toString(toQuarter)));
		}*/
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tzjc");
		
		mav.addObject("xmList", xmList);
		//mav.addObject("scoreList", scoreList);
		mav.addObject("bmArr", bmArr);
		mav.addObject("deafultUser", deafultRyxm);
		mav.addObject("years", years);
		mav.addObject("quarters", quarters);
		mav.addObject("years_bef", years);
		mav.addObject("quarters_bef", quarters);
		mav.addObject("thisQuarter", quarter);
		//mav.addObject("ryScoreList",ryScoreList);
		//mav.addObject("bmScoreList",bmScoreList);
		//mav.addObject("jdList",jdList);
		return mav;
	
	}
	
	@RequestMapping("/getTzjcByBm")
	@ResponseBody
	public void getTzjcByBm(HttpServletRequest request, HttpServletResponse response)
	{
		String bmmc = request.getParameter("bm");
		String year = request.getParameter("year");
		String quarter = request.getParameter("quarter");
		log.info("获取部门制定季度人员考核排名 " + bmmc);
		TBm bm = bmService.getBmByBmmc(bmmc);
		TRole sjy = roleService.getRoleByRolename(Constants.SJY);
		List<TUser> userList = userService.getUserByBmRole(bm, sjy);
		List<String> xmList = new ArrayList<String>();
		List<Double> scoreList = new ArrayList<Double>();
		for(int i=0;i<userList.size();i++)
		{	
			TUser user = userList.get(i);		
			TPfkh tpfkh = pfkhService.getPfkhByUserNfJd(user, Integer.parseInt(year), Integer.parseInt(quarter));
			if(null != tpfkh){
				xmList.add(tpfkh.getUser().getXm());
				scoreList.add(tpfkh.getJddf());
			}else{
				xmList.add(user.getXm());
				scoreList.add(0.0);
			}
		
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("xms", xmList);			
		jsonObj.put("scores", scoreList);
		
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
	
	@RequestMapping("/getTzjcByGr")
	@ResponseBody
	public void getTzjcByGr(HttpServletRequest request, HttpServletResponse response)
	{
		String bmmc = request.getParameter("bm");
		int fromYear = Integer.valueOf(request.getParameter("fromYear"));
		int fromQuarter = Integer.valueOf(request.getParameter("fromQuarter"));
		int toYear = Integer.valueOf(request.getParameter("toYear"));
		int toQuarter = Integer.valueOf(request.getParameter("toQuarter"));
		String xm = request.getParameter("xm");
		log.info("获取部门制定季度人员考核排名 " + bmmc);
		TBm bm = bmService.getBmByBmmc(bmmc);
		TRole sjy = roleService.getRoleByRolename(Constants.SJY);
		List<TUser> userList = userService.getUserByBmRole(bm, sjy);
		//获取default人员的分数和所在部门的平均分,默认获取时间跨度为4个季度
		List<Double> ryScoreList = new ArrayList<Double>();
		List<Double> bmScoreList = new ArrayList<Double>();
		List<String> jdList = new ArrayList<String>();
		TUser defaultUser = userService.getUserByXmAndBmmc(xm,bmmc);
		int iYear,iQuarter,span;
		span = (toYear-fromYear)*4 + toQuarter - fromQuarter;
		for(int i=span;i>=0;i--){
			int totalRyNums=0;
			double totalScores = 0.0;
			iYear = toYear - (4+i-fromQuarter)/4;
			iQuarter = (4+fromQuarter-i)%4;
			if(iQuarter == 0) iQuarter = 4;
			TPfkh tpfkh = pfkhService.getPfkhByUserNfJd(defaultUser, iYear, iQuarter);
			if(null != tpfkh){
				ryScoreList.add(tpfkh.getJddf());
			}else{
				ryScoreList.add(0.0);
			}
			
			//获取人员所在部门的平均得分
			for(TUser user:userList){
				tpfkh = pfkhService.getPfkhByUserNfJd(user, iYear, iQuarter);
				if(null != tpfkh){
					totalScores += tpfkh.getJddf();
					++totalRyNums;
				}
			}
			if(totalRyNums == 0){
				bmScoreList.add(0.0);
			}else{
				bmScoreList.add(NumberUtil.formatDouble(totalScores/totalRyNums));
			}
			
			jdList.add(Integer.toString(iYear).concat(Integer.toString(iQuarter)));
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jds", jdList);
		jsonObj.put("ryScores", ryScoreList);
		jsonObj.put("bmScores", bmScoreList);			
		
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
