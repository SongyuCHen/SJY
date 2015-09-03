package nju.software.sjy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.bean.StatusMsg;
import nju.software.sjy.bean.ZtStatusMsg;
import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.convertor.JfqxConvertor;
import nju.software.sjy.mapper.MJfqx;
import nju.software.sjy.mapper.MKflb;
import nju.software.sjy.mapper.MKfxm;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.JfpzService;
import nju.software.sjy.service.JfqxService;
import nju.software.sjy.service.KfqxService;
import nju.software.sjy.service.SplcService;
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
@RequestMapping("/jfqx")
public class JfqxController
{
	private static Logger log = Logger.getLogger(JfqxController.class);
	
	private final static Object sync = new Object();
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private KfqxService kfqxService;
	
	@Autowired
	private JfqxService jfqxService;
	
	@Autowired
	private JfpzService jfpzService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SplcService splcService;
	
	@Autowired
	private ViewService viewService;
	
	@Autowired
	private ViewGztbService viewGztbService;

	@RequestMapping("/index")
	public ModelAndView jfqx()
	{
		log.info("come to the jfqx page");
		
		//默认查询参数为所有部门、开始日期、结束日期
		ModelAndView mav = view(-1, null, null);
				
		return mav;
	}
	
	@RequestMapping("/jflb")
	public ModelAndView jflb()
	{
		List<MKflb> jflbList = kfqxService.getAllJflb();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jflb");
		mav.addObject("jflbList", jflbList);//加分类别列表
		
		return mav;
	}
	
	@RequestMapping(value="/addJflb", method=RequestMethod.POST)
	public ModelAndView addJflb(HttpServletRequest request, HttpServletResponse response)
	{
		String kflbmc = request.getParameter("modalMc");
		
		//获取Tjfqx表中最大的bh--需要用一个锁对象
		synchronized(sync)
		{
			int newBh = kfqxService.getMaxKflbBh() + 1;
			int maxKflbPzbh = kfqxService.getMaxJflbPzbh() + 1;
			
			TGypz jflb = new TGypz();
			jflb.setBh(newBh);
			jflb.setPzbh(maxKflbPzbh);
			jflb.setLx(Constants.JFLB_STR);
			jflb.setMc(kflbmc);
			
			kfqxService.addKflb(jflb);
		}
		
		List<MKflb> jflbList = kfqxService.getAllJflb();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jflb");
		mav.addObject("jflbList", jflbList);//加分类别列表
		
		return mav;
	}
	
	@RequestMapping(value="/deleteJflb", method=RequestMethod.POST)
	public ModelAndView deleteJflb(HttpServletRequest request, HttpServletResponse response)
	{
		String[] kflbbhList = request.getParameterValues("jflbbh");
		
		if(kflbbhList!=null && kflbbhList.length > 0){
			for(String kflbbhStr : kflbbhList){
				int kflbbh = Integer.parseInt(kflbbhStr);
				kfqxService.deleteKflb(gypzService.getGypzByBh(kflbbh));
			}
		}
		
		List<MKflb> jflbList = kfqxService.getAllJflb();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jflb");
		mav.addObject("jflbList", jflbList);//加分类别列表
		
		return mav;
	}
	
	@RequestMapping(value="/editJflb", method=RequestMethod.POST)
	public ModelAndView editKflb(HttpServletRequest request, HttpServletResponse response)
	{
		String kflbbh = request.getParameter("jflbbh");
		String mc = request.getParameter("mc");
		
		TGypz kflb = gypzService.getGypzByBh(Integer.parseInt(kflbbh));
		kflb.setMc(mc);
		kfqxService.editKflb(kflb);
		
		List<MKflb> jflbList = kfqxService.getAllJflb();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jflb");
		mav.addObject("jflbList", jflbList);//加分类别列表
		
		return mav;
	}
	
	@RequestMapping("/jfxm")
	public ModelAndView jfxm()
	{
		List<MKfxm> jfxmList = kfqxService.getAllJfxm();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jfxm");
		mav.addObject("jfxmList", jfxmList);//扣分项目列表
		
		return mav;
	}
	
	@RequestMapping(value="/addJfxm", method=RequestMethod.POST)
	public ModelAndView addJfxm(HttpServletRequest request, HttpServletResponse response)
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
			kfxm.setJfkf(Constants.JF_FLAG);
			kfxm.setBh(newBh);
			kfxm.setFs(Double.parseDouble(fs));
			kfxm.setMc(mc);
			kfxm.setLb(kflb);
			kfqxService.addKfxm(kfxm);
		}
		
		List<MKfxm> jfxmList = kfqxService.getAllJfxm();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jfxm");
		mav.addObject("jfxmList", jfxmList);//扣分项目列表
		
		return mav;
	}
	
	@RequestMapping(value="/editJfxm", method=RequestMethod.POST)
	public void editJfxm(HttpServletRequest request, HttpServletResponse response)
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
	
	@RequestMapping(value="/deleteJfxm", method=RequestMethod.POST)
	public ModelAndView deleteKfxm(HttpServletRequest request, HttpServletResponse response)
	{
		String[] bhList = request.getParameterValues("jfxmbh");
		
		if(bhList!=null && bhList.length > 0){
			for(String bhStr : bhList){
				int bh = Integer.parseInt(bhStr);
				kfqxService.deleteKfxm(kfqxService.getKfxmByBh(bh));
			}
		}
		
		List<MKfxm> jfxmList = kfqxService.getAllJfxm();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jfxm");
		mav.addObject("jfxmList", jfxmList);//扣分项目列表
		
		return mav;
	}
	
	@RequestMapping(value="/getAllJflb.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAllJflb(HttpServletRequest request, HttpServletResponse response)
	{
		List<MKflb> kflbList = kfqxService.getAllJflb();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jflbList", kflbList);
		
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
	
	@RequestMapping(value="/getAllJfxm.aj", method=RequestMethod.GET)
	@ResponseBody
	public void getAllJfxm(HttpServletRequest request, HttpServletResponse response)
	{
		List<MKfxm> kfxmList = kfqxService.getAllJfxm();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jfxmList", kfxmList);
		
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
	
	@RequestMapping(value="/addJfqx", method=RequestMethod.POST)
	public ModelAndView addJfqx(HttpServletRequest request, HttpServletResponse response)
	{
		String bmbhStr = request.getParameter("bmbh");
		String rybhStr = request.getParameter("rybh");
		int bmbh = Integer.parseInt(bmbhStr);
		int rybh = Integer.parseInt(rybhStr);
		TUser user = userService.getUserByRybh(rybh);
		
		String xmbh = request.getParameter("xmbh");
		String hdsj = request.getParameter("hdsj");
		String jfcsStr = request.getParameter("addJfcs");
		String comment = request.getParameter("addComment");
		
		int nf = Integer.parseInt(hdsj.substring(0, hdsj.indexOf("-")));
		int yf = Integer.parseInt(hdsj.substring(hdsj.indexOf("-")+1, hdsj.lastIndexOf("-")));
		
		int jfcs = Integer.parseInt(jfcsStr);
		
		//状态默认设为1————用户保存
		TGypz zt = gypzService.getGypzByPzbhLx(1, Constants.ZT);
		
		synchronized(sync)
		{
			int newBh = jfqxService.getMaxBh() + 1;
			
			TJfkfxm kfxm = kfqxService.getKfxmByBh(Integer.parseInt(xmbh));
			
			TJfqx jfqx = new TJfqx();
			jfqx.setBh(newBh);
			jfqx.setUser(user);
			jfqx.setBm(user.getBm());
			jfqx.setHdsj(hdsj);
			jfqx.setJfxm(kfxm);
			jfqx.setNf(nf);
			jfqx.setYf(yf);
			jfqx.setZt(zt);
			jfqx.setJfcs(jfcs);
			jfqx.setComment(comment);
			
			jfqxService.save(jfqx);
		}

		ModelAndView mav = view(bmbh, hdsj, hdsj);
				
		return mav;
	}
	
	@RequestMapping(value="/deleteJfqx", method=RequestMethod.POST)
	public ModelAndView deleteJfqx(HttpServletRequest request, HttpServletResponse response)
	{
		String[] bhList = request.getParameterValues("jfqxbh");
		String bmbhStr = request.getParameter("bmbh");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int bmbh = Integer.parseInt(bmbhStr);
		
		if(bhList!=null && bhList.length > 0)
		{
			for(String bhStr : bhList)
			{
				int bh = Integer.parseInt(bhStr);
				jfqxService.delete(jfqxService.getJfqxByBh(bh));
			}
		}
		
		ModelAndView mav = view(bmbh, startDate, endDate);
		
		return mav;
	}
	
	@RequestMapping(value="/editJfqx", method=RequestMethod.POST)
	public void editJfqx(HttpServletRequest request, HttpServletResponse response)
	{
		String bh = request.getParameter("bh");
		
		String xmbh = request.getParameter("xmbh");
		String hdsj = request.getParameter("hdsj");
		String jfcsStr = request.getParameter("editJfcs");
		String comment = request.getParameter("editComment");
		int nf = Integer.parseInt(hdsj.substring(0, hdsj.indexOf("-")));
		int yf = Integer.parseInt(hdsj.substring(hdsj.indexOf("-")+1, hdsj.lastIndexOf("-")));
		int jfcs = Integer.parseInt(jfcsStr);
		
		TJfkfxm kfxm = kfqxService.getKfxmByBh(Integer.parseInt(xmbh));
		
		TJfqx jfqx = jfqxService.getJfqxByBh(Integer.parseInt(bh));
		//jfqx.setBmbh(Integer.parseInt(bmbhStr));
		jfqx.setHdsj(hdsj);
		jfqx.setJfxm(kfxm);
		//jfqx.setRybh(Integer.parseInt(rybh));
		jfqx.setNf(nf);
		jfqx.setYf(yf);
		jfqx.setJfxm(kfxm);
		jfqx.setJfcs(jfcs);
		jfqx.setComment(comment);
		
		jfqxService.update(jfqx);
		
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
	
	/**
	 * 审批加分情形
	 */
	@RequestMapping(value="/approval", method=RequestMethod.POST)
	@ResponseBody
	public void approval(HttpServletRequest request, HttpServletResponse response)
	{
		String bhArrStr = request.getParameter("bhArr");
		String[] bhStrArr = bhArrStr.split("-");
		Integer[] bhArr = null;
		if(bhStrArr != null)
		{
			bhArr = new Integer[bhStrArr.length];
			for(int i=0; i < bhStrArr.length; i++)
			{
				String bhStr = bhStrArr[i];
				int bh = Integer.parseInt(bhStr);
				bhArr[i] = bh;
			}
		}
		
		/* 获取要审批的记录 */
		List<TJfqx> tlist = jfqxService.getJfqxByBhArr(bhArr);
		
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 开始审批并得到返回结果 */
		ZtStatusMsg result = splcService.approvalJfqx(tlist, user);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
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
		String bmbhStr = request.getParameter("bmbh");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int bmbh = Integer.parseInt(bmbhStr);
		
		List<TJfqx> tlist;
		if(bmbh == 0)
		{
			TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
			tlist = jfqxService.getJfqxByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
		}
		else
		{
			TBm bm = bmService.getBmByBmbh(bmbh);
			tlist = jfqxService.getJfqxByBmDateRange(bm, startDate, endDate);
		}	
		List<MJfqx> jfqxList = JfqxConvertor.convertJfqx(tlist);
		
        return new ModelAndView("jfqxExcelView", "jfqxList", jfqxList);
    }
	
	/**
	 * 页面需要显示的信息
	 * 1.部门列表
	 * 2.开始日期
	 * 3.结束日期
	 * 4.当前部门编号
	 * 5.操作列表
	 * 6.加分情形结果列表
	 * 
	 * @param bmbh 	查询部门，属于查询参数
	 * @param nf	查询年份，属于查询参数，如果为0则代表当前年份
	 * @param yf	查询月份，属于查询参数，如果为0则代表当前月份
	 * @return		ModelAndView
	 */
	public ModelAndView view(int bmbh, String startDate, String endDate)
	{
		/* 1.部门列表 */
		List<TBm> bmList = viewService.getWrappedBmList(); 
		
		/* 2.年份列表 */
		startDate = viewService.getStartDate(startDate);
		
		/* 3.月份列表 */
		endDate = viewService.getEndDate(endDate);
		
		/* 4.当前部门编号 */
		bmbh = viewService.getBmbh(bmbh);
		
		/* 5.操作列表 */
		List<TOperation> operationList =  viewService.getOperationList();
		
		/* 6.加分情形列表 */
		List<TJfqx> tlist = viewGztbService.getJfqxByBmbhDateRange(bmbh, startDate, endDate);
		List<MJfqx> jfqxList = JfqxConvertor.convertJfqx(tlist);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jfqx");
		
		mav.addObject("bmList", bmList);//1.部门列表
		mav.addObject("startDate", startDate);//2.年份列表
		mav.addObject("endDate", endDate);//3.月份列表
		mav.addObject("curBmbh", bmbh);//4.当前部门编号
		mav.addObject("operationList", operationList);//5.操作列表
		mav.addObject("jfqxList", jfqxList);//6.加分情形列表
		
		return mav;
	}
}
