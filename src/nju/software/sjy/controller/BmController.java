package nju.software.sjy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.convertor.BmConvertor;
import nju.software.sjy.mapper.MBm;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.AuthorityService;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bm")
public class BmController
{
	private static Logger log = Logger.getLogger(BmController.class);
	
	private final static Object sync = new Object();
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping("/index")
	public ModelAndView index()
	{
		List<TBm> bmList = bmService.getAllBm();
		
		List<MBm> mlist = BmConvertor.convert(bmList);
		
		List<TGypz> bmlxList = gypzService.getGypzByLx(Constants.BMLX);
		bmlxList.add(0, new TGypz(Constants.SYBM));
		
		List<TGypz> editBmlxList = gypzService.getGypzByLx(Constants.BMLX);
		
		String lastBmlx = null;
		if(bmlxList != null && !bmlxList.isEmpty())
		{
			lastBmlx = bmlxList.get(0).getMc();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bmgl");
		
		mav.addObject("mlist", mlist);
		mav.addObject("bmlxList", bmlxList);
		mav.addObject("editBmlxList", editBmlxList);
		mav.addObject("lastBmlx", lastBmlx);
		
		return mav;
	}
	
	@RequestMapping("/cx")
	public ModelAndView cx()
	{
		String bmlxmc = request.getParameter("bmlx");
		List<TBm> tlist = null;
		if(bmlxmc.equals(Constants.SYBM))
		{
			tlist = bmService.getAllBm();
		}
		else
		{
			TGypz bmlx = gypzService.getGypzByLxMc(Constants.BMLX, bmlxmc);
			tlist = bmService.getBmByBmlx(bmlx);
		}
		
		List<MBm> mlist = BmConvertor.convert(tlist);
		
		List<TGypz> bmlxList = gypzService.getGypzByLx(Constants.BMLX);
		bmlxList.add(0, new TGypz(Constants.SYBM));
		
		List<TGypz> editBmlxList = gypzService.getGypzByLx(Constants.BMLX);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bmgl");
		
		mav.addObject("mlist", mlist);
		mav.addObject("bmlxList", bmlxList);
		mav.addObject("editBmlxList", editBmlxList);
		mav.addObject("lastBmlx", bmlxmc);
		
		return mav;
	}
	
	@RequestMapping("/addBm")
	public ModelAndView addBm()
	{
		String bmmc = request.getParameter("addBmmc");
		String bmid = request.getParameter("addBmid");
		String bmlxmc = request.getParameter("addBmlx");
		TGypz bmlx = gypzService.getGypzByLxMc(Constants.BMLX, bmlxmc);
		
		synchronized(sync)
		{
			int bmbh = bmService.getMaxBmbh() + 1;
			TBm bm = new TBm(bmbh, bmid, bmmc, bmlx);
			bmService.saveBm(bm);
		}
		
		ModelAndView mav = index();
		
		return mav;
	}
	
	@RequestMapping("/editBm")
	public void editBm(HttpServletRequest request, HttpServletResponse response)
	{
		String bmbhStr = request.getParameter("editBmbh");
		String bmmc = request.getParameter("editBmmc");
		String bmid = request.getParameter("editBmid");
		String bmlxmc = request.getParameter("editBmlx");
		
		int bmbh = Integer.parseInt(bmbhStr);
		TGypz bmlx = gypzService.getGypzByLxMc(Constants.BMLX, bmlxmc);
		
		TBm bm = bmService.getBmByBmbh(bmbh);
		bm.setBmmc(bmmc);
		bm.setBmid(bmid);
		bm.setBmlx(bmlx);
		
		bmService.updateBm(bm);
		
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
	
	@RequestMapping("/deleteBm")
	public ModelAndView deleteBm()
	{
		String[] bmbhArr = request.getParameterValues("bmbh");
		if(bmbhArr != null)
		{
			for(String bmbhStr : bmbhArr)
			{
				int bmbh = Integer.parseInt(bmbhStr);
				TBm bm = bmService.getBmByBmbh(bmbh);
				bmService.deleteBm(bm);
			}
		}
		
		ModelAndView mav = index();
		
		return mav;
	}
	
	@RequestMapping("/getXm")
	@ResponseBody
	public void getXmByBm(HttpServletRequest request, HttpServletResponse response)
	{
		String bmmc = request.getParameter("bm");
		
		log.info("get all user of a bm " + bmmc);
		
		
		List<String> list = bmService.getUserXmOfBm(bmmc);
		
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
	
	@RequestMapping(value="/getAccessBm.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAccessBm(HttpServletRequest request, HttpServletResponse response)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		List<TBm> bmList = authorityService.getAccessBmByUser(user);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("bmList", bmList);
		
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
	
	@RequestMapping(value="/getAccessBmExcludeQtbm.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAccessBmExcludeQtbm(HttpServletRequest request, HttpServletResponse response)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		List<TBm> bmList = authorityService.getAccessBmByUserExcludeQtbm(user);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("bmList", bmList);
		
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
