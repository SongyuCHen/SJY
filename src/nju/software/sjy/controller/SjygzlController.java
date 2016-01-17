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
import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SjygzlTools;
import nju.software.sjy.convertor.SjygzlConvertor;
import nju.software.sjy.mapper.MSjy;
import nju.software.sjy.mapper.MSjygzl;
import nju.software.sjy.model.da.ViewDajgSsfzxx;
import nju.software.sjy.model.tdh.SjygzlAjxx;
import nju.software.sjy.model.tdh.SjygzlBlxq;
import nju.software.sjy.model.tdh.SjygzlKtxq;
import nju.software.sjy.model.tdh.SjygzlSdxq;
import nju.software.sjy.model.xy.LocalBlxq;
import nju.software.sjy.model.xy.LocalKtxq;
import nju.software.sjy.model.xy.LocalSdxq;
import nju.software.sjy.model.xy.LocalZdjz;
import nju.software.sjy.model.xy.LocalZdjzId;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.tdh.SjygzlXqService;
import nju.software.sjy.util.DateUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sjygzl")
public class SjygzlController
{
	private static Logger log = Logger.getLogger(SjygzlController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SjygzlXqService sjygzlXqService;
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/detail", method=RequestMethod.POST)
	@ResponseBody
	public void edit(HttpServletRequest request, HttpServletResponse response)
	{
		log.info("get details of gzxx");
		
		String attrname = request.getParameter("attrname");
		String gzsjbh = request.getParameter("gzsjbh");
		String kssj = request.getParameter("kssj");
		String jssj = request.getParameter("jssj");
		
		log.info("attrname: " + attrname + "   kssj:" + kssj + "    jssj:"+jssj);
		
		String fjm = null;
		List<String> strList = gypzService.getMcByLx(Constants.FYDM);
		if(strList != null && !strList.isEmpty())
		{
			fjm = strList.get(0);
		}
		int bh = Integer.parseInt(gzsjbh);
		TGzsj gzsj = gzsjService.getGzsjByBh(bh);
		String yhdm = null;
		if(gzsj != null)
		{
			yhdm = gzsj.getUser().getUserid();
		}
		
		kssj = DateUtil.getFirstDayOfDate(kssj);
		jssj = DateUtil.getLastDayOfDate(jssj);
		String today = DateUtil.getSimpleFormatStr(new Date());
		if(today.compareTo(jssj)>0) today = jssj;
		List<String> attrList = SjygzlTools.getAttrList(attrname);
		
		List<MSjygzl> sjygzlList = null;
		
//		if(attrname.equals(Constants.ZDJZ))
//		{
//			List<SjygzlAjxx> ajxxList = sjygzlXqService.getAjxxByFydmAndGdjsrq(fjm, yhdm, kssj, jssj);
//			if(ajxxList != null)
//			{
//				sjygzlList = new ArrayList<MSjygzl>();
//				for(SjygzlAjxx ajxx : ajxxList)
//				{
////					VSsjcbmgdWjys vw = sjygzlXqService.getWjysByLSH(ajxx.getId().getAhdm());
////					if(vw != null){
////						MSjygzl ms = SjygzlConvertor.convertAjxx(ajxx, vw);
////						sjygzlList.add(ms);
////					}
//					ViewDajgSsfzxx da = sjygzlXqService.getDaysByAhdm(ajxx.getId().getAhdm());
//					if(da != null){
//						MSjygzl ms = SjygzlConvertor.convertAjxx(ajxx, da);
//						sjygzlList.add(ms);
//					}
//				}
//			}
//			
//		}
//		else if(attrname.equals(Constants.TSJL))
//		{
//			List<SjygzlKtxq> ktxqList = sjygzlXqService.getKtxqByFyAndYhdm(fjm, yhdm, kssj, today);
//			sjygzlList = SjygzlConvertor.convertKtxq(ktxqList);
//		}
//		else if(attrname.equals(Constants.TSLJSJ))
//		{
//			List<SjygzlKtxq> ktxqList = sjygzlXqService.getKtxqByFyAndYhdm(fjm, yhdm, kssj, today);
//			sjygzlList = SjygzlConvertor.convertKtxq(ktxqList);
//		}
//		else if(attrname.equals(Constants.SDS))
//		{
//			List<SjygzlSdxq> sdxqList = sjygzlXqService.getSdxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
//			sjygzlList = SjygzlConvertor.convertSdxq(sdxqList);
//		}
//		else if(attrname.equals(Constants.BLZS))
//		{
//			List<SjygzlBlxq> blxqList = sjygzlXqService.getBlxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
//			sjygzlList = SjygzlConvertor.convertBlxq(blxqList);
//		}
		if(attrname.equals(Constants.ZDJZ))
		{
			List<LocalZdjz> zdjzList = sjygzlXqService.getLocalZdjzByFyAndYhdm(fjm, yhdm, kssj, jssj);
			sjygzlList = SjygzlConvertor.convertLocalZdjz(zdjzList);
			
		}
		else if(attrname.equals(Constants.TSJL))
		{
			List<LocalKtxq> ktxqList = sjygzlXqService.getLocalKtxqByFyAndYhdm(fjm, yhdm, kssj, today);
			sjygzlList = SjygzlConvertor.convertLocalKtxq(ktxqList);
		}
		else if(attrname.equals(Constants.TSLJSJ))
		{
			List<LocalKtxq> ktxqList = sjygzlXqService.getLocalKtxqByFyAndYhdm(fjm, yhdm, kssj, today);
			sjygzlList = SjygzlConvertor.convertLocalKtxq(ktxqList);
		}
		else if(attrname.equals(Constants.SDS))
		{
			List<LocalSdxq> sdxqList = sjygzlXqService.getLocalSdxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
			sjygzlList = SjygzlConvertor.convertLocalSdxq(sdxqList);
		}
		else if(attrname.equals(Constants.BLZS))
		{
			List<LocalBlxq> blxqList = sjygzlXqService.getLocalBlxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
			sjygzlList = SjygzlConvertor.convertLocalBlxq(blxqList);
		}
		List<MSjy> sjyList = userService.getAllSjy();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("attrname", attrname);
		jsonObj.put("gzsjbh", gzsjbh);
		jsonObj.put("attrList", attrList);
		jsonObj.put("sjygzlList", sjygzlList);
		jsonObj.put("sjyLisy", sjyList);
		
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
	public void changeSjy(HttpServletRequest request, HttpServletResponse response){
		String attrname = request.getParameter("attrname");
		String xzsjy = request.getParameter("xzsjy");
		String xzsjyname = request.getParameter("xzsjyname");
		String gzsjbh = request.getParameter("gzsjbh");
		String fydm = request.getParameter("fydm");
		String ahdm = request.getParameter("ahdm");
		String attr1 = request.getParameter("attr1");
		String attr2 = request.getParameter("attr2");
		if(attrname.equals(Constants.ZDJZ)){
			LocalZdjzId id = new LocalZdjzId(fydm,ahdm);
			LocalZdjz localZdjz= sjygzlXqService.getLocalZdjzById(id);
			if(null != localZdjz){
				localZdjz.setXzsjy(xzsjy);
				localZdjz.setXzsjymc(xzsjyname);
				sjygzlXqService.updateLocalZdjz(localZdjz);
			}
		}
		
	}
	
	
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel() throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		
		String attrname = request.getParameter("attrname");
		String gzsjBh = request.getParameter("bh");
		String kssj = request.getParameter("kssj");
		String jssj = request.getParameter("jssj");
		attrname = URLDecoder.decode(attrname, "UTF-8");
		int bh = Integer.parseInt(gzsjBh);
		
		log.info("attrname: " + attrname + "   kssj:" + kssj + "    jssj:"+jssj);
		
		String fjm = null;
		List<String> strList = gypzService.getMcByLx(Constants.FYDM);
		if(strList != null && !strList.isEmpty())
		{
			fjm = strList.get(0);
		}
		TGzsj gzsj = gzsjService.getGzsjByBh(bh);
		String yhdm = null;
		if(gzsj != null)
		{
			yhdm = gzsj.getUser().getUserid();
		}
		
		kssj = DateUtil.getFirstDayOfDate(kssj);
		jssj = DateUtil.getLastDayOfDate(jssj);
		
		List<String> attrList = SjygzlTools.getAttrList(attrname);
		
		List<MSjygzl> sjygzlList = null;
		
		if(attrname.equals(Constants.ZDJZ))
		{
			List<SjygzlAjxx> ajxxList = sjygzlXqService.getAjxxByFydmAndGdjsrq(fjm, yhdm, kssj, jssj);
			if(ajxxList != null)
			{
				sjygzlList = new ArrayList<MSjygzl>();
				for(SjygzlAjxx ajxx : ajxxList)
				{
//					VSsjcbmgdWjys vw = sjygzlXqService.getWjysByLSH(ajxx.getId().getAhdm());
//					if(vw != null){
//						MSjygzl ms = SjygzlConvertor.convertAjxx(ajxx, vw);
//						sjygzlList.add(ms);
//					}
					ViewDajgSsfzxx da = sjygzlXqService.getDaysByAhdm(ajxx.getId().getAhdm());
					if(da != null){
						MSjygzl ms = SjygzlConvertor.convertAjxx(ajxx, da);
						sjygzlList.add(ms);
					}
				}
			}
			
		}
		else if(attrname.equals(Constants.TSJL))
		{
			List<SjygzlKtxq> ktxqList = sjygzlXqService.getKtxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
			sjygzlList = SjygzlConvertor.convertKtxq(ktxqList);
		}
		else if(attrname.equals(Constants.TSLJSJ))
		{
			List<SjygzlKtxq> ktxqList = sjygzlXqService.getKtxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
			sjygzlList = SjygzlConvertor.convertKtxq(ktxqList);
		}
		else if(attrname.equals(Constants.SDS))
		{
			List<SjygzlSdxq> sdxqList = sjygzlXqService.getSdxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
			sjygzlList = SjygzlConvertor.convertSdxq(sdxqList);
		}
		else if(attrname.equals(Constants.BLZS))
		{
			List<SjygzlBlxq> blxqList = sjygzlXqService.getBlxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
			sjygzlList = SjygzlConvertor.convertBlxq(blxqList);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sjygzlExcelView");
		
		mav.addObject("attrname", attrname);
		mav.addObject("attrList", attrList);
		mav.addObject("sjygzlList", sjygzlList);
		
		return mav;
	}
}
