package nju.software.sjy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.bean.StatusMsg;
import nju.software.sjy.common.Constants;
import nju.software.sjy.convertor.KfqxConvertor;
import nju.software.sjy.mapper.MKflb;
import nju.software.sjy.mapper.MKfqx;
import nju.software.sjy.mapper.MKfxm;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TKfqx;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.KfqxService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.ViewGztbService;
import nju.software.sjy.service.ViewService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/kfqx")
public class KfqxController 
{
	private static Logger log = Logger.getLogger(KfqxController.class);
	
	private final static Object sync = new Object();
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KfqxService kfqxService;
	
	@Autowired
	private ViewService viewService;
	
	@Autowired
	private ViewGztbService viewGztbService;

	@RequestMapping("/index")
	public ModelAndView kfqx()
	{
		log.info("come to the kfqx page");
		
		ModelAndView mav = view(-1, null, null);
		
		return mav;
	}
	
	@RequestMapping(value="/getAllBm.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAllBm(HttpServletRequest request, HttpServletResponse response)
	{
		List<TBm> bmList = bmService.getAllBm();
		
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
	
	@RequestMapping(value="/getAllRy.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAllRy(HttpServletRequest request, HttpServletResponse response)
	{
		List<TUser> ryList = userService.getAllRy();
		
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
	
	@RequestMapping(value="/getAllKflb.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAllKflb(HttpServletRequest request, HttpServletResponse response)
	{
		List<MKflb> kflbList = kfqxService.getAllKflb();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("kflbList", kflbList);
		
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
	
	@RequestMapping(value="/getAllKfxm.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAllKfxm(HttpServletRequest request, HttpServletResponse response)
	{
		List<MKfxm> kfxmList = kfqxService.getAllKfxm();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("kfxmList", kfxmList);
		
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
	
	@RequestMapping("/kflb")
	public ModelAndView kflb()
	{
		List<MKflb> kflbList = kfqxService.getAllKflb();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kflb");
		mav.addObject("kflbList", kflbList);//姓名列表
		
		return mav;
	}
	
	@RequestMapping(value="/addKflb", method=RequestMethod.POST)
	public ModelAndView addKflb(HttpServletRequest request, HttpServletResponse response)
	{
		String kflbmc = request.getParameter("modalMc");
		
		//获取Tjfqx表中最大的bh--需要用一个锁对象
		synchronized(sync)
		{
			int newBh = kfqxService.getMaxKflbBh() + 1;
			int maxKflbPzbh = kfqxService.getMaxKflbPzbh() + 1;
			
			TGypz kflb = new TGypz();
			kflb.setBh(newBh);
			kflb.setPzbh(maxKflbPzbh);
			kflb.setLx(Constants.KFLB_STR);
			kflb.setMc(kflbmc);
			
			kfqxService.addKflb(kflb);
		}
		
		List<MKflb> kflbList = kfqxService.getAllKflb();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kflb");
		mav.addObject("kflbList", kflbList);
		
		return mav;
	}
	
	@RequestMapping(value="/deleteKflb", method=RequestMethod.POST)
	public ModelAndView deleteKflb(HttpServletRequest request, HttpServletResponse response)
	{
		String[] kflbbhList = request.getParameterValues("kflbbh");
		
		if(kflbbhList!=null && kflbbhList.length > 0){
			for(String kflbbhStr : kflbbhList){
				int kflbbh = Integer.parseInt(kflbbhStr);
				kfqxService.deleteKflb(gypzService.getGypzByBh(kflbbh));
			}
		}
		
		List<MKflb> kflbList = kfqxService.getAllKflb();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kflb");
		mav.addObject("kflbList", kflbList);
		
		return mav;
	}
	
	@RequestMapping(value="/editKflb", method=RequestMethod.POST)
	public ModelAndView editKflb(HttpServletRequest request, HttpServletResponse response)
	{
		String kflbbh = request.getParameter("kflbbh");
		String mc = request.getParameter("mc");
		
		TGypz kflb = gypzService.getGypzByBh(Integer.parseInt(kflbbh));
		kflb.setMc(mc);
		kfqxService.editKflb(kflb);
		
		List<MKflb> kflbList = kfqxService.getAllKflb();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kflb");
		mav.addObject("kflbList", kflbList);
		
		return mav;
	}
	
	@RequestMapping("/kfxm")
	public ModelAndView kfxm()
	{
		List<MKfxm> kfxmList = kfqxService.getAllKfxm();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kfxm");
		mav.addObject("kfxmList", kfxmList);//扣分项目列表
		
		return mav;
	}
	
	@RequestMapping(value="/addKfxm", method=RequestMethod.POST)
	public ModelAndView addKfxm(HttpServletRequest request, HttpServletResponse response)
	{
		String lbbh = request.getParameter("lbbh");
		String mc = request.getParameter("mc");
		String fs = request.getParameter("fs");
		
		//获取Tjfqx表中最大的bh--需要用一个锁对象
		synchronized(sync)
		{
			int newBh = kfqxService.getMaxKfxmBh() + 1;
			
			TGypz kflb = gypzService.getGypzByBh(Integer.parseInt(lbbh));
			
			TJfkfxm kfxm = new TJfkfxm();
			kfxm.setJfkf(Constants.KF_FLAG);
			kfxm.setBh(newBh);
			kfxm.setFs(Double.parseDouble(fs));
			kfxm.setMc(mc);
			kfxm.setLb(kflb);
			kfqxService.addKfxm(kfxm);
		}
		
		List<MKfxm> kfxmList = kfqxService.getAllKfxm();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kfxm");
		mav.addObject("kfxmList", kfxmList);//扣分项目列表
		
		return mav;
	}
	
	@RequestMapping(value="/editKfxm", method=RequestMethod.POST)
	public void editKfxm(HttpServletRequest request, HttpServletResponse response)
	{
		String bh = request.getParameter("bh");
		String lbbh = request.getParameter("lbbh");
		String mc = request.getParameter("mc");
		String fs = request.getParameter("fs");
		
		TJfkfxm kfxm = kfqxService.getKfxmByBh(Integer.parseInt(bh));
		TGypz kflb = gypzService.getGypzByBh(Integer.parseInt(lbbh));
		
		kfxm.setFs(Double.parseDouble(fs));
		kfxm.setMc(mc);
		kfxm.setLb(kflb);
		kfqxService.editKfxm(kfxm);
		
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
	
	@RequestMapping(value="/deleteKfxm", method=RequestMethod.POST)
	public ModelAndView deleteKfxm(HttpServletRequest request, HttpServletResponse response)
	{
		String[] bhList = request.getParameterValues("kfxmbh");
		
		if(bhList!=null && bhList.length > 0){
			for(String bhStr : bhList){
				int bh = Integer.parseInt(bhStr);
				kfqxService.deleteKfxm(kfqxService.getKfxmByBh(bh));
			}
		}
		
		List<MKfxm> kfxmList = kfqxService.getAllKfxm();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kfxm");
		mav.addObject("kfxmList", kfxmList);//扣分项目列表
		
		return mav;
	}
	
	@RequestMapping(value="/addKfqx", method=RequestMethod.POST)
	public ModelAndView addKfqx(HttpServletRequest request, HttpServletResponse response)
	{
		String bmbhStr = request.getParameter("bmbh");
		String rybhStr = request.getParameter("rybh");
		
		String xmbh = request.getParameter("xmbh");
		String hdsj = request.getParameter("hdsj");
		String kfcsStr = request.getParameter("addKfcs");
		String comment = request.getParameter("addComment");
		
		int bmbh = Integer.parseInt(bmbhStr);
		int rybh = Integer.parseInt(rybhStr);
		
		int nf = Integer.parseInt(hdsj.substring(0, hdsj.indexOf("-")));
		int yf = Integer.parseInt(hdsj.substring(hdsj.indexOf("-")+1, hdsj.lastIndexOf("-")));
		
		int kfcs = Integer.parseInt(kfcsStr);
		
		TUser ry = userService.getRyByRybh(rybh);
		
		synchronized(sync)
		{
			int newBh = kfqxService.getMaxKfqxBh() + 1;
			
			TBm bm = bmService.getBmByBmbh(bmbh);
			TJfkfxm kfxm = kfqxService.getKfxmByBh(Integer.parseInt(xmbh));
			
			
			TKfqx kfqx = new TKfqx();
			kfqx.setBh(newBh);
			kfqx.setBm(bm);
			kfqx.setHdsj(hdsj);
			kfqx.setKfxm(kfxm);
			kfqx.setRy(ry);
			kfqx.setNf(nf);
			kfqx.setYf(yf);
			kfqx.setKfcs(kfcs);
			kfqx.setComment(comment);
			
			kfqxService.addKfqx(kfqx);
		}

		ModelAndView mav = view(bmbh, hdsj, hdsj);
		
		return mav;
	}
	
	@RequestMapping(value="/deleteKfqx", method=RequestMethod.POST)
	public ModelAndView deleteKfqx(HttpServletRequest request, HttpServletResponse response)
	{
		String[] bhList = request.getParameterValues("kfqxbh");
		String bmbhStr = request.getParameter("bmbh");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int bmbh = Integer.parseInt(bmbhStr);
		
		if(bhList!=null && bhList.length > 0)
		{
			for(String bhStr : bhList)
			{
				int bh = Integer.parseInt(bhStr);
				kfqxService.deleteKfqx(kfqxService.getKfqxByBh(bh));
			}
		}
		
		ModelAndView mav = view(bmbh, startDate, endDate);
		
		return mav;
	}
	
	@RequestMapping(value="/editKfqx", method=RequestMethod.POST)
	public void editKfqx(HttpServletRequest request, HttpServletResponse response)
	{
		String bh = request.getParameter("bh");
		
		String xmbh = request.getParameter("xmbh");
		String hdsj = request.getParameter("hdsj");
		String kfcsStr = request.getParameter("editKfcs");
		String comment = request.getParameter("editComment");
		int nf = Integer.parseInt(hdsj.substring(0, hdsj.indexOf("-")));
		int yf = Integer.parseInt(hdsj.substring(hdsj.indexOf("-")+1, hdsj.lastIndexOf("-")));
		int kfcs = Integer.parseInt(kfcsStr);
		
		TJfkfxm kfxm = kfqxService.getKfxmByBh(Integer.parseInt(xmbh));
		
		TKfqx kfqx = kfqxService.getKfqxByBh(Integer.parseInt(bh));
		kfqx.setHdsj(hdsj);
		kfqx.setKfxm(kfxm);
		kfqx.setNf(nf);
		kfqx.setYf(yf);
		kfqx.setKfcs(kfcs);
		kfqx.setComment(comment);
		
		kfqxService.editKfqx(kfqx);
		
		String msg = "success";
		StatusMsg sm = new StatusMsg(1, msg);
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
	
	@RequestMapping(value="/cx", method=RequestMethod.POST)
	public ModelAndView cx(HttpServletRequest request, HttpServletResponse response)
	{
		String bmbhStr = request.getParameter("bmbh");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		int bmbh = Integer.parseInt(bmbhStr);
		
		ModelAndView mav = view(bmbh, startDate, endDate);
		
		return mav;
	}
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() 
	{
		String bmbhStr = request.getParameter("bmbh");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int bmbh = Integer.parseInt(bmbhStr);
		
		List<TKfqx> tlist = null;
		if(bmbh == 0)
		{
			TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
			tlist = kfqxService.getKfqxByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
		}
		else
		{
			TBm bm = bmService.getBmByBmbh(bmbh);
			tlist = kfqxService.getKfqxByBmDateRange(bm, startDate, endDate);
		}
		List<MKfqx> kfqxList = KfqxConvertor.convertKfqx(tlist);
		
        return new ModelAndView("kfqxExcelView", "kfqxList", kfqxList);
    }
	
	/**
	 * 扣分情形页面需要显示的信息
	 * 1.部门列表
	 * 2.开始日期
	 * 3.结束日期
	 * 4.当前部门编号
	 * 5.操作列表
	 * 6.扣分情形列表
	 */
	public ModelAndView view(int bmbh, String startDate, String endDate)
	{
		/* 1.部门信息 */
		List<TBm> bmList = viewService.getWrappedBmList();
		
		/* 2.开始日期 */
		startDate = viewService.getStartDate(startDate);
		
		/* 3.结束日期 */
		endDate = viewService.getEndDate(endDate);
		
		/* 4.当前部门编号 */
		bmbh = viewService.getBmbh(bmbh);
		
		/* 5.操作列表 */
		List<TOperation> operationList = viewService.getOperationList();
		/* 特殊需求，扣分情形不能出现审批按钮 */
		if(operationList != null)
		{
			for(int i=0;i<operationList.size();i++)
			{
				TOperation operation = operationList.get(i);
				if(operation.getName().equals(Constants.SP))
				{
					operationList.remove(i);
					break;
				}
			}
		}
		
		/* 6.扣分情形列表 */
		List<TKfqx> tlist = viewGztbService.getKfqxByGmbhDateRange(bmbh, startDate, endDate);
		List<MKfqx> kfqxList = KfqxConvertor.convertKfqx(tlist);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("kfqx");
		
		
		mav.addObject("bmList", bmList);//1.部门列表
		mav.addObject("startDate", startDate);//2.开始日期
		mav.addObject("endDate", endDate);//3.结束日期
		mav.addObject("curBmbh", bmbh);//4.当前部门编号
		mav.addObject("operationList", operationList);//5.操作列表
		mav.addObject("kfqxList", kfqxList);//6.扣分情形列表
		
		return mav;
	}
}