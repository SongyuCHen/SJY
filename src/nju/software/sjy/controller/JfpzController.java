package nju.software.sjy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.common.Constants;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TJfpz;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.JfpzService;
import nju.software.sjy.service.KfqxService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jfpz")
public class JfpzController
{
	private static Logger log = Logger.getLogger(JfpzController.class);
	
	private static final Object sync = new Object();
	
	@Autowired
	private JfpzService jfpzService;
	
	@Autowired
	private KfqxService kfqxService;
	
	@Autowired
	private GypzService gypzService;
	
	@RequestMapping("/index")
	public ModelAndView jfpz()
	{
		log.info("come to the jfpz page");
		
		List<TJfpz> list = jfpzService.getAllJfpz();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jfpz");
		mav.addObject("list", list);
		
		return mav;
	}
	
	@RequestMapping(value="/getJfz", method=RequestMethod.POST)
	@ResponseBody
	public void get(HttpServletRequest request, HttpServletResponse response)
	{
		log.info("try to get jfz by jb");
		
		String jb = request.getParameter("jb");
		int jfz = jfpzService.getJfzByJb(jb);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jfz", jfz);
		
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
	
	@RequestMapping(value="/getJlfs", method=RequestMethod.POST)
	@ResponseBody
	public void getJlfs(HttpServletRequest request, HttpServletResponse response)
	{
		log.info("try to get jfz by jb");
		
		String jlmc = request.getParameter("jlmc");
		TGypz gypz = gypzService.getGypzByLxMc(Constants.JFLB_STR, jlmc);
		List<TJfkfxm> list = kfqxService.getKfxmByGypz(gypz);
		
		List<String> jbList = new ArrayList<String>();
		int jfz = 0;
		if(list != null && !list.isEmpty())
		{
			jfz = (int)list.get(0).getFs();
			
			for(int i=0;i<list.size();i++)
			{
				jbList.add(list.get(i).getMc());
			}
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jbList", jbList);
		jsonObj.put("jfz", jfz);
		
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
	
	@RequestMapping(value="/getJbfs", method=RequestMethod.POST)
	@ResponseBody
	public void getJbfs(HttpServletRequest request, HttpServletResponse response)
	{
		log.info("try to get jfz by jb");
		String jlmc = request.getParameter("jlmc");
		String jbmc = request.getParameter("jbmc");
		
		TGypz gypz = gypzService.getGypzByLxMc(Constants.JFLB_STR, jlmc);
		
		int jfz = (int)kfqxService.getFsByLbMc(gypz, jbmc);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jfz", jfz);
		
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
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response)
	{
		String jb = request.getParameter("addModalJb");
		String jfzStr = request.getParameter("addModalJfz");
		int jfz = Integer.parseInt(jfzStr);
		
		TJfpz tjfpz;
		synchronized(sync)
		{
			int bh = jfpzService.getMaxBh() + 1;
			tjfpz = new TJfpz(bh, jb, jfz);
			jfpzService.save(tjfpz);
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jfpz", tjfpz);
		
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
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(HttpServletRequest request, HttpServletResponse response)
	{
		String bhStr = request.getParameter("editModalBh");
		int bh = Integer.parseInt(bhStr);
		String jb = request.getParameter("editModalJb");
		String jfzStr = request.getParameter("editModalJfz");
		int jfz = Integer.parseInt(jfzStr);
		
		TJfpz jfpz = new TJfpz(bh, jb, jfz);
		jfpzService.update(jfpz);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jfpz", jfpz);
		
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
