package nju.software.sjy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.common.Constants;
import nju.software.sjy.convertor.PfpzConvertor;
import nju.software.sjy.mapper.MPfpz;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfpz;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.PfpzService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pfpz")
public class PfpzController 
{
	private static Logger log = Logger.getLogger(PfpzController.class);
	
	private final static Object sync = new Object();
	
	@Autowired
	private PfpzService pfpzService;
	
	@Autowired
	private GypzService gypzService;
	
	@RequestMapping("/index")
	public ModelAndView pfpz()
	{
		log.info("come to the pfpz page");
		
		List<TPfpz> tlist = pfpzService.getAllPfpz();
		List<MPfpz> mlist = PfpzConvertor.convert(tlist);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pfpz");
		mav.addObject("list", mlist);
		
		return mav;
	}
	
	@RequestMapping("/gzlb")
	public ModelAndView gzlb()
	{
		List<TGypz> gzList = gypzService.getGypzByLx(Constants.GZ);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("gzList", gzList);
		
		mav.setViewName("gzlb");
		
		return mav;
	}
	
	@RequestMapping("/addGzlb")
	public ModelAndView addGzlb(HttpServletRequest request)
	{
		String gzmc = request.getParameter("addMc");
		
		synchronized(sync)
		{
			int bh = gypzService.getMaxBh() + 1;
			int pzbh = gypzService.getMaxPzbhByLx(Constants.GZ) + 1;
			
			TGypz gypz = new TGypz(bh, pzbh, Constants.GZ, gzmc);
			gypzService.save(gypz);
			
			// 添加到表T_PFPZ中
			int pfpzBh = pfpzService.getMaxBh() + 1;
			TPfpz pfpz = new TPfpz(pfpzBh, gypz, 0);
			pfpzService.save(pfpz);
		}
		
		List<TGypz> gzList = gypzService.getGypzByLx(Constants.GZ);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("gzList", gzList);
		
		mav.setViewName("gzlb");
		
		return mav;
	}
	
	@RequestMapping(value="/editGzlb", method=RequestMethod.POST)
	@ResponseBody
	public void editGzlb(HttpServletRequest request, HttpServletResponse response)
	{
		String editGzbh = request.getParameter("editGzbh");
		String editMc = request.getParameter("editMc");
		
		int bh = Integer.parseInt(editGzbh);
		TGypz gz = gypzService.getGypzByBh(bh);
		
		if(gz != null)
		{
			gz.setMc(editMc);
			
			gypzService.update(gz);
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
	
	@RequestMapping(value="/deleteGzlb", method=RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
	{
		String[] bhArr = request.getParameterValues("gzbh");
		
		if(bhArr!=null && bhArr.length > 0)
		{
			for(String bhStr : bhArr)
			{
				int bh = Integer.parseInt(bhStr);
				TGypz gz = gypzService.getGypzByBh(bh);
				
				//这里需要注意先后
				// T_PFPZ表中要删除该规则
				TPfpz tpfpz = pfpzService.getPfpzByGz(gz);
				if(tpfpz != null)
				{
					pfpzService.delete(tpfpz);
				}
				
				// T_GYPZ表中删除该规则，包括两步
				// 1.删除MC=GZMC
				// 2.删除LX=GZMC
				gypzService.delete(gz);
				
				List<TGypz> list = gypzService.getGypzByLx(gz.getMc());
				if(list != null)
				{
					for(TGypz gypz : list)
					{
						gypzService.delete(gypz);
					}
				}
			}
		}
		
		List<TGypz> gzList = gypzService.getGypzByLx(Constants.GZ);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("gzList", gzList);
		
		mav.setViewName("gzlb");
		
		return mav;
	}
	
	@RequestMapping("/gzqz")
	public ModelAndView gzqz()
	{
		ModelAndView mav = viewGzfs();
		
		return mav;
	}
	
	@RequestMapping("/addGzqz")
	public ModelAndView addGzqz(HttpServletRequest request)
	{
		String addMc = request.getParameter("addMc");
		String addLx = request.getParameter("addLx");
		String addFs = request.getParameter("addFs");
		
		TGypz gypz = gypzService.getGypzByLxMc(addLx, addMc);
		
		synchronized(sync)
		{
			//如果在T_GYPZ表中没有该规则记录，则首先增加到T_GYPZ表中
			if(gypz == null)
			{
				int bh = gypzService.getMaxBh() + 1;
				int pzbh = gypzService.getMaxPzbhByLx(addLx) + 1;
				
				gypz = new TGypz(bh, pzbh, addLx, addMc);
				gypzService.save(gypz);
			}
			
			// 添加到表T_PFPZ中
			int pfpzBh = pfpzService.getMaxBh() + 1;
			int fs = Integer.parseInt(addFs);
			TPfpz pfpz = new TPfpz(pfpzBh, gypz, fs);
			pfpzService.save(pfpz);
		}
		
		ModelAndView mav = viewGzfs();
		
		return mav;
	}
	
	@RequestMapping(value="/editGzqz", method=RequestMethod.POST)
	@ResponseBody
	public void editGzqz(HttpServletRequest request, HttpServletResponse response)
	{
		String editPzbh = request.getParameter("editPfbh");
		String editMc = request.getParameter("editMc");
		String editLx = request.getParameter("editLx");
		String editFs = request.getParameter("editFs");
		
		int bh = Integer.parseInt(editPzbh);
		TPfpz pfpz = pfpzService.getPfpzByBh(bh);
		
		if(pfpz != null)
		{
			TGypz gz = gypzService.getGypzByLxMc(editLx, editMc);
			pfpz.setGz(gz);
			
			int fs = Integer.parseInt(editFs);
			pfpz.setFs(fs);
			
			pfpzService.update(pfpz);
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
	
	@RequestMapping("/deleteGzqz")
	public ModelAndView deleteGzqz(HttpServletRequest request)
	{
		String[] bhArr = request.getParameterValues("pfbh");
		
		if(bhArr!=null && bhArr.length > 0)
		{
			for(String bhStr : bhArr)
			{
				int bh = Integer.parseInt(bhStr);
				TPfpz pfpz = pfpzService.getPfpzByBh(bh);
				
				if(pfpz != null)
				{
					TGypz gz = pfpz.getGz();
					
					//这里需要注意先后
					// T_PFPZ表中要删除该规则
					pfpzService.delete(pfpz);
					
					// T_GYPZ表中删除该规则，包括两步
					// 1.删除MC=GZMC
					// 2.删除LX=GZMC
					gypzService.delete(gz);
					
					List<TGypz> list = gypzService.getGypzByLx(gz.getMc());
					if(list != null)
					{
						for(TGypz gypz : list)
						{
							gypzService.delete(gypz);
						}
					}
				}
				
			}
		}
		
		ModelAndView mav = viewGzfs();
		
		return mav;
	}
	
	public ModelAndView viewGzfs()
	{
		List<TPfpz> tlist = pfpzService.getAllPfpz();
		List<MPfpz> mlist = PfpzConvertor.convert(tlist);
		
		List<String> lxList = new ArrayList<String>();
		lxList.add(Constants.GZ);
		List<String> gzlxList = gypzService.getMcByLx(Constants.GZ);
		lxList.addAll(gzlxList);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gzqz");
		
		mav.addObject("mlist", mlist);
		mav.addObject("lxList", lxList);
		
		return mav;
	}
}
