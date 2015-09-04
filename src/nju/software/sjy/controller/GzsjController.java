package nju.software.sjy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.software.sjy.bean.StatusMsg;
import nju.software.sjy.bean.ZtStatusMsg;
import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.convertor.GzsjConvertor;
import nju.software.sjy.mapper.MGypz;
import nju.software.sjy.mapper.MGzsj;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjChangelog;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.model.xy.TGzsjxxBase;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjChangelogService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.GzsjxxBaseService;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.SplcService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.ViewGztbService;
import nju.software.sjy.service.ViewService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gzsj")
public class GzsjController 
{
	private static Logger log = Logger.getLogger(GzsjController.class);
	
	/**
	 * 保护锁————注意这个锁不该通用的，这里简单期间通用了。
	 */
	private static final Object sync = new Object();
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private GzsjxxService gzsjxxService;
	
	@Autowired
	private GzsjxxBaseService gzsjxxBaseService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SplcService splcService;
	
	@Autowired
	private ViewService viewService;
	
	@Autowired
	private ViewGztbService viewGztbService;
	
	@Autowired
	private GzsjChangelogService changelogService;
	
	@RequestMapping("/index")
	public ModelAndView index()
	{
		log.info("come to the gzsj page");
		
		ModelAndView mav = view(null, null, null);
		
		return mav;
	}
	
	@RequestMapping("/cx")
	public ModelAndView cx(HttpServletRequest request)
	{
		log.info("通过部门、年份和月份查询工作实绩");
		
		String bmmc = request.getParameter("bm");
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		ModelAndView mav = view(bmmc, startDate, endDate);
		
		return mav;
	}
	
	@RequestMapping(value="/previous", method=RequestMethod.POST)
	@ResponseBody
	public void previous(HttpServletRequest request, HttpServletResponse response)
	{
		String gzsjbh = request.getParameter("gzsjbh");
		int bh = Integer.parseInt(gzsjbh);
		
		TGzsj tgzsj = gzsjService.getGzsjByBh(bh);
		MGzsj mgzsj = GzsjConvertor.convertGzsj(tgzsj);
		
		List<TGzsjxxBase> baseList = gzsjxxBaseService.getGzsjxxBaseByGzsj(tgzsj);
		
		JSONObject jsonObj = new JSONObject();
		int status = 0;
		if(baseList != null && !baseList.isEmpty())
		{
			status = 1;
			List<MGypz> mlist = GzsjConvertor.convertGzsjxxBaseToGypz(baseList);
			jsonObj.put("gzsj", mgzsj);
			jsonObj.put("mlist", mlist);
		}
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
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public void edit(HttpServletRequest request, HttpServletResponse response)
	{
		String bhStr = request.getParameter("editModalBh");
		String sj = request.getParameter("editModalSj");
		
		int gzsjbh = Integer.parseInt(bhStr);
		
		/* 1.保存基础数据
		 * 若工作实绩主表的Edit字段在编辑前为1，则无需保存，否则保存基础数据且将主表的Edit字段置为1
		 */
		TGzsj gzsj = gzsjService.getGzsjByBh(gzsjbh);
		if(gzsj.getEdit()==0){
			gzsj.setEdit(1);
			List<TGzsjxx> gzsjxxList = gzsjxxService.getGzsjxxByGzsj(gzsj);
			for(TGzsjxx gzsjxx:gzsjxxList)
			{
				synchronized(sync)
				{
					int bh = gzsjxxBaseService.getMaxBh();
					++ bh;
					TGzsjxxBase gzsjxxBase=new TGzsjxxBase(bh, gzsj, gzsjxx.getGzxx(), gzsjxx.getSz());
					gzsjxxBaseService.save(gzsjxxBase);
				}
			}
		}
		/* 2.编辑工作实绩主表 */
		
		gzsj.setRq(sj);
		
		gzsjService.updateGzsj(gzsj);
		
		
		
		/* 3.编辑工作实绩子表 */
		/* 获取所有的配置项 */
		List<TGypz> pzList = gypzService.getGypzByLx(Constants.GZSJ);
		int pzSize = pzList.size();
		
		String[] pzInfos = new String[pzSize];
		
		for(int i=1;i<=pzSize;i++)
		{
			pzInfos[i-1] = request.getParameter("editpz"+i);
			int sz = Integer.parseInt(pzInfos[i-1]);
			
			TGypz gzxx = pzList.get(i-1);
			/* 根据工作实绩和规则细项或者该工作实绩的规则细项详细数值 */
			TGzsjxx gzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(gzsj, gzxx);
			if(gzsjxx != null)
			{
				gzsjxx.setSz(sz);
				gzsjxxService.update(gzsjxx);
			}
			else
			{
				synchronized(sync)
				{
					int bh = gzsjxxService.getMaxBh() + 1;
					
					gzsjxx = new TGzsjxx(bh, gzsj, gzxx, sz);
					gzsjxxService.save(gzsjxx);
				}
			}
		}
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
	
	@RequestMapping(value="/editSubmit", method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public void editSubmit(HttpServletRequest request, HttpServletResponse response)
	{
		String bhStr = request.getParameter("editModalBh");
		String sj = request.getParameter("editModalSj");
		
		int gzsjbh = Integer.parseInt(bhStr);
		
		/* 1.保存基础数据
		 * 若工作实绩主表的Edit字段在编辑前为1，则无需保存，否则保存基础数据且将主表的Edit字段置为1
		 */
		TGzsj gzsj = gzsjService.getGzsjByBh(gzsjbh);
		TGypz zt = gypzService.getGypzByPzbhLx(1, "状态");
		if(gzsj.getEdit()==0){
			gzsj.setEdit(1);
			List<TGzsjxx> gzsjxxList = gzsjxxService.getGzsjxxByGzsj(gzsj);
			for(TGzsjxx gzsjxx:gzsjxxList)
			{
				synchronized(sync)
				{
					int bh = gzsjxxBaseService.getMaxBh();
					++ bh;
					TGzsjxxBase gzsjxxBase=new TGzsjxxBase(bh, gzsj, gzsjxx.getGzxx(), gzsjxx.getSz());
					gzsjxxBaseService.save(gzsjxxBase);
				}
			}
		}
		/* 2.编辑工作实绩主表 */
		
		gzsj.setRq(sj);
		gzsj.setZt(zt);
		gzsjService.updateGzsj(gzsj);
		
		
		
		/* 3.编辑工作实绩子表 */
		/* 获取所有的配置项 */
		List<TGypz> pzList = gypzService.getGypzByLx(Constants.GZSJ);
		int pzSize = pzList.size();
		
		String[] pzInfos = new String[pzSize];
		
		for(int i=1;i<=pzSize;i++)
		{
			pzInfos[i-1] = request.getParameter("editpz"+i);
			int sz = Integer.parseInt(pzInfos[i-1]);
			
			TGypz gzxx = pzList.get(i-1);
			/* 根据工作实绩和规则细项或者该工作实绩的规则细项详细数值 */
			TGzsjxx gzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(gzsj, gzxx);
			if(gzsjxx != null)
			{
				gzsjxx.setSz(sz);
				gzsjxxService.update(gzsjxx);
			}
			else
			{
				synchronized(sync)
				{
					int bh = gzsjxxService.getMaxBh() + 1;
					
					gzsjxx = new TGzsjxx(bh, gzsj, gzxx, sz);
					gzsjxxService.save(gzsjxx);
				}
			}
		}
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
	
	/**
	 * 审批操作
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
		List<TGzsj> tlist = gzsjService.getGzsjByBhArr(bhArr);
		
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		/* 开始审批并得到返回结果 */
		ZtStatusMsg result = splcService.approvalGzsj(tlist, user);
		
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
	
	@RequestMapping(value="/add")
	@Transactional
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		String bmmc = request.getParameter("addModalBm");
		String xm = request.getParameter("addModalXm");
		String sj = request.getParameter("addModalSj");
		
		TUser user = userService.getUserByXmAndBmmc(xm, bmmc);
		TBm bm = user.getBm();
		
		List<TGypz> pzList = gypzService.getGypzByLx(Constants.GZSJ);
		int pzSize = pzList.size();
		
		/*
		 * 根据RYBH, NF, YF查找GZSJ，如果已经存在，则不能重复添加
		 * 某人在某年某月只能有一份工作填报，后面在该月只能编辑，不能重复添加
		 */
		String status;
		TGzsj gzsj = gzsjService.getGzsjByUserDate(user, sj);
		if(gzsj == null)
		{
			synchronized (sync)
			{
				/* 1.保存到工作实绩主表 */
				int gzsjbh = gzsjService.getMaxGzsjBh() + 1;
				TGypz zt = gypzService.getGypzByPzbhLx(1, Constants.ZT);
				TGzsj tgzsj = new TGzsj(gzsjbh, user, bm, sj, 0, zt);
				gzsjService.saveGzsj(tgzsj);
				
				/* 2.保存到工作实绩子表 */
				String[] pzInfos = new String[pzSize];
				for (int i = 1; i <= pzSize; i++)
				{
					pzInfos[i - 1] = request.getParameter("addpz" + i);
					int sz = Integer.parseInt(pzInfos[i - 1]);
					TGypz gzxx = pzList.get(i-1);
					
					synchronized (sync)
					{
						int gzsjxxbh = gzsjxxService.getMaxBh() + 1;
		
						TGzsjxx gzsjxx = new TGzsjxx(gzsjxxbh, tgzsj, gzxx, sz);
						gzsjxxService.save(gzsjxx);
					}
				}
			}
			
			status = "success";
		}
		else
		{
			status = "fail";
		}
		/* 增加结束 */
		
		/* 页面需要展示的工作实绩列表————这里可以改动，目前是将新增的那一项展示了出来 */
		ModelAndView mav = view(bmmc, sj, sj);
		mav.addObject("status", status);//添加成功与否
		
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@Transactional
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		log.info("delete gzsj");
		
		request.setCharacterEncoding("UTF-8");
		
		String[] bhArr = request.getParameterValues("gzsjBh");
		String bmmc = request.getParameter("bmmc");
		bmmc = URLDecoder.decode(bmmc, "UTF-8");
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		if(bhArr!=null && bhArr.length > 0)
		{
			for(String bhStr : bhArr)
			{
				int bh = Integer.parseInt(bhStr);
				TGzsj gzsj = gzsjService.getGzsjByBh(bh);
				
				/* 1.删除工作实绩子表中的相应数据 */
				List<TGzsjxx> gzsjxxList = gzsjxxService.getGzsjxxByGzsjGzlx(gzsj, Constants.GZSJ);
				for(TGzsjxx gzsjxx : gzsjxxList)
				{
					gzsjxxService.delete(gzsjxx);
				}
				
				/* 2.删除工作实绩主表中的相应数据 */
				gzsjService.deleteGzsj(gzsj);
			}
		}
		/* 删除结束 */
		
		/* 页面需要展示的信息————目前保留了上一次的查询参数 */
		ModelAndView mav = view(bmmc, startDate, endDate);
		
		return mav;
	}
	
	@RequestMapping(value="/modifyGzsjxx.aj", method=RequestMethod.POST)
	@ResponseBody
	public void modifyGzsjxx(HttpServletRequest request, HttpServletResponse response)
	{
		String gzxxpzbh = request.getParameter("gzxxpzbh");
		String gzsjxxEditVal = request.getParameter("gzsjxxEditVal");
		String gzsjxxEditReason = request.getParameter("gzsjxxEditReason");
		String bhStr = request.getParameter("editGzsjBh");
		
		log.info("pzbh:" + gzxxpzbh + ", gzsjxxEditVal:" + gzsjxxEditVal + ", gzsjxxEditReason:" + gzsjxxEditReason);
		
		int pzbh = Integer.parseInt(gzxxpzbh);
		int gzsjbh = Integer.parseInt(bhStr);
		
		//工作实绩具体的配置项
		TGypz gypz = gypzService.getGypzByPzbhLx(pzbh, Constants.GZSJ);
		TGzsj gzsj = gzsjService.getGzsjByBh(gzsjbh);
		TGzsjxx gzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(gzsj, gypz);
		
		/* add your code --- note that gzsjxx may be null, gzsj and gypz can not be null */

		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		TGzsjChangelog changelog = new TGzsjChangelog();
		int sz1 = gzsjxx.getSz();
		int sz2 = Integer.parseInt(gzsjxxEditVal);
		int maxbh = changelogService.getMaxBh();
		maxbh++;
		changelog.setBh(maxbh);
		changelog.setGzsjxx(gzsjxx);
		changelog.setSz1(sz1);
		changelog.setSz2(sz2);
		changelog.setXgr(user.getXm());
		changelog.setXgrq(new Date());
		changelog.setXgyy(gzsjxxEditReason);
		changelogService.save(changelog);

		
		/* return */
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", "success");
		
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
		request.setCharacterEncoding("UTF-8");
		
		String bmmc = request.getParameter("bmmc");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		bmmc = URLDecoder.decode(bmmc, "UTF-8");
		
		/* 需要获取gzsjList和pzList */
		List<TGypz> pzList = gypzService.getGypzByLx(Constants.GZSJ);
		int pzSize = pzList.size();
		
		List<MGzsj> mlist = new ArrayList<MGzsj>();
		List<TGzsj> tlist = null;
		if(bmmc.equals(Constants.SYBM))
		{
			TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
			tlist = gzsjService.getGzsjByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
		}
		else
		{
			TBm bm = bmService.getBmByBmmc(bmmc);
			tlist = gzsjService.getGzsjByBmDateRange(bm, startDate, endDate);
		}
		for(TGzsj tgzsj : tlist)
		{
			/* 根据工作实绩和规则类型 查询出所有的规则细项及数值 */
			List<TGzsjxx> gzsjxxList = gzsjxxService.getGzsjxxByGzsjGzlx(tgzsj, Constants.GZSJ);
			List<TGzsjxxBase> gzsjxxBaseList = gzsjxxBaseService.getGzsjxxBaseByGzsj(tgzsj);
			MGzsj mgzsj = GzsjConvertor.convertGzsj(tgzsj, gzsjxxList, gzsjxxBaseList, pzSize);
			
			mlist.add(mgzsj);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gzsjExcelView");
		
		mav.addObject("gzsjList", mlist);
		mav.addObject("pzList", pzList);
		
		return mav;
		
	}
	
	/**
	 * 页面需要显示的信息
	 * 1.查询部门列表
	 * 2.增加部门列表
	 * 3.开始日期 
	 * 4.结束日期
	 * 5.当前部门名称
	 * 6.增加人员列表
	 * 7.操作列表
	 * 8.工作实绩配置项列表
	 * 9.工作实绩配置项大小
	 * 10.工作实绩结果列表
	 * 
	 * @param bm 	查询部门，属于查询参数
	 * @param nf	查询年份，属于查询参数
	 * @param yf	查询月份，属于查询参数
	 * @return		ModelAndView
	 */
	public ModelAndView view(String bmmc, String startDate, String endDate)
	{
		/* 1.查询部门列表 */
		List<TBm> bmList = viewService.getWrappedBmList();
		
		/* 2.增加部门列表 */
		List<TBm> addBmList = viewService.getBmList();
		
		/* 3.开始日期 */
		startDate = viewService.getStartYearMonth(startDate);
		
		/* 4.结束日期 */
		endDate = viewService.getEndYearMonth(endDate);
		
		/* 5.当前部门名称 */
		bmmc = viewService.getBmmc(bmmc);
		
		/* 6.增加人员列表 */
		List<TUser> userList = viewService.getUserList(addBmList);
		
		/* 7.操作列表 */
		List<TOperation> operationList = viewService.getOperationList();
				
		/* 8.工作实绩配置项列表 */
		List<TGypz> pzList = gypzService.getGypzByLx(Constants.GZSJ);
		
		/* 9.工作实绩配置项大小 */
		int pzSize = pzList.size();
				
		/* 10.工作实绩列表---根据部门、年月去查询 */
		List<TGzsj> tlist = viewGztbService.getGzsjByBmmcDateRange(bmmc, startDate, endDate);
		List<MGzsj> mlist = new ArrayList<MGzsj>();
		if(tlist != null)
		{
			for(int i=0;i<tlist.size();i++)
			{
				TGzsj tgzsj = tlist.get(i);
						
				/* 根据工作实绩和规则类型 查询出所有的规则细项及数值 */
				List<TGzsjxx> gzsjxxList = gzsjxxService.getGzsjxxByGzsjGzlx(tgzsj, Constants.GZSJ);
				List<TGzsjxxBase> gzsjxxBaseList = gzsjxxBaseService.getGzsjxxBaseByGzsj(tgzsj);
				MGzsj mgzsj = GzsjConvertor.convertGzsj(tgzsj, gzsjxxList, gzsjxxBaseList, pzSize);
						
				mlist.add(mgzsj);
			}
		}
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gzsj");
				
		/* 添加页面需要展示的信息 */
		mav.addObject("bmList", bmList);//1.查询部门列表
		mav.addObject("addBmList", addBmList);//2.增加部门列表
		mav.addObject("startDate", startDate);//3.开始日期
		mav.addObject("endDate", endDate);//4.结束日期
		mav.addObject("curBmmc", bmmc);//5.当前部门名称
		mav.addObject("userList", userList);//6.增加modal默认部门下的人员
		mav.addObject("operationList", operationList);//7.用户能执行的操作列表
		mav.addObject("pzList", pzList);//8.配置列表
		mav.addObject("pzSize", pzSize);//9.配置size
		mav.addObject("mlist", mlist);//10.工作实绩列表
				
		return mav;
	}
}
