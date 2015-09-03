package nju.software.sjy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.convertor.GzsjConvertor;
import nju.software.sjy.mapper.MGzsj;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.model.xy.TLog;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.service.LogService;
import nju.software.sjy.service.ViewService;
import nju.software.sjy.service.tdh.SjygzlXqService;
import nju.software.sjy.util.DateUtil;
import nju.software.sjy.webservice.service.SjyWsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gzldj")
public class GzldjController
{
	private static Logger log = Logger.getLogger(GzldjController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ViewService viewService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private SjyWsService sjyWsService;
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private GzsjxxService gzsjxxService;
	
	@Autowired
	private SjygzlXqService sjygzlxqService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("/index")
	public ModelAndView index()
	{
		log.info("come to the gzldj page");
		
		ModelAndView mav = (ModelAndView)view(0, 0, null, false, false);
		
		return mav;
	}
	
	@RequestMapping(value="/fetch.aj", method=RequestMethod.GET)
	@ResponseBody
	public void fetch(HttpServletRequest request, HttpServletResponse response)
	{
		log.info("fetch gzsj by webservice");
		
		String nf = request.getParameter("nf");
		String yf = request.getParameter("yf");
		int year = Integer.parseInt(nf);
		int month = Integer.parseInt(yf);
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		JSONObject jsonObj = (JSONObject)view(year, month, user, true, true);
		
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
	 * 页面需要显示的内容
	 * 1.年份列表
	 * 2.月份列表
	 * 3.当前年份
	 * 4.当前月份
	 * 5.工作实绩配置项列表
	 * 6.工作实绩配置项大小
	 * 7.工作实绩结果列表
	 * 
	 * @param year
	 * @param month
	 * @param needGenerate
	 * @return
	 */
	public Object view(int year, int month, TUser user, boolean needGenerate, boolean isJson)
	{	
		/* 1.年份列表 */
		int[] years = viewService.getYears();
		
		/* 2.月份列表 */
		int[] months = viewService.getMonths();
		
		/* 3.当前年份 */
		year = viewService.getYear(year);
		
		/* 4.当前月份 */
		month = viewService.getMonth(month);
		
		/* 5.工作实绩配置项列表 */
		List<TGypz> pzList = gypzService.getGypzByLx(Constants.GZSJ);
		
		/* 6.工作实绩配置项大小 */
		int pzSize = pzList.size();
		
		/* 7.工作实绩列表 */
		List<TGzsj> tlist = null;
		List<MGzsj> mlist = new ArrayList<MGzsj>();
		if(needGenerate)
		{
			Date date = DateUtil.getDate(year, month);
//			tlist = sjyWsService.getSjyGzl(date, 0);
			TLog tLog = new TLog();
			int maxbh = logService.getMaxBh();
			maxbh++;
			tLog.setBh(maxbh);
			tLog.setSj(date);
			tLog.setNr("系统手工提取数据，提取人："+user.getXm());
			logService.saveLog(tLog);
			
			tlist = sjygzlxqService.getSjyGzlFromView(date, 0);
			//tlist = gzsjService.getAllGzsj();
			//tlist = gzsjService.getGzsjByBhArr(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
			if(tlist != null)
			{
//				TLog tLog = new TLog();
//				int maxbh = logService.getMaxBh();
//				maxbh++;
//				tLog.setBh(maxbh);
//				tLog.setSj(date);
//				tLog.setNr("系统手工提取数据，提取人："+user.getXm());
//				logService.saveLog(tLog);
				for(TGzsj gzsj : tlist)
				{
					List<TGzsjxx> xxList = gzsjxxService.getGzsjxxByGzsj(gzsj);
					gzsj.setXxList(xxList);
				}
			}
		}
		if(tlist != null)
		{
			for(TGzsj tgzsj : tlist)
			{
				MGzsj mgzsj = GzsjConvertor.convertGzsj(tgzsj, pzSize);
				mlist.add(mgzsj);
			}
		}
		
		int size = 0;
		if(mlist != null)
		{
			size = mlist.size();
		}
		
		if(isJson)
		{
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("years", years);//1.年份列表
			jsonObj.put("months", months);//2.月份列表
			jsonObj.put("curYear", year);//3.当前年份
			jsonObj.put("curMonth", month);//4.当前月份
			jsonObj.put("pzList", pzList);//5.工作实绩配置项列表
			jsonObj.put("pzSize", pzSize);//6.工作实绩配置项大小
			jsonObj.put("mlist", mlist);//7.工作实绩列表
			jsonObj.put("gzsjSize", size);
			
			return jsonObj;

		}
		else
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("gzldj");
			
			mav.addObject("years", years);//1.年份列表
			mav.addObject("months", months);//2.月份列表
			mav.addObject("curYear", year);//3.当前年份
			mav.addObject("curMonth", month);//4.当前月份
			mav.addObject("pzList", pzList);//5.工作实绩配置项列表
			mav.addObject("pzSize", pzSize);//6.工作实绩配置项大小
			mav.addObject("mlist", mlist);//7.工作实绩列表
			
			return mav;
		}
	}
}
