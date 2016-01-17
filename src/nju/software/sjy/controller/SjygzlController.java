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
import nju.software.sjy.model.xy.LocalBlxqId;
import nju.software.sjy.model.xy.LocalKtxq;
import nju.software.sjy.model.xy.LocalKtxqId;
import nju.software.sjy.model.xy.LocalSdxq;
import nju.software.sjy.model.xy.LocalSdxqId;
import nju.software.sjy.model.xy.LocalZdjz;
import nju.software.sjy.model.xy.LocalZdjzId;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.tdh.SjygzlXqService;
import nju.software.sjy.util.DateUtil;

import org.apache.commons.lang.StringUtils;
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
	private GzsjxxService gzsjxxService;
	
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
	
	//此处缺少权限设定
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void changeSjy(HttpServletRequest request, HttpServletResponse response){
		String attrname = request.getParameter("attrname");
		String xzsjy = request.getParameter("xzsjy");
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
				TUser otherSjy = userService.getUserByUserid(xzsjy);
				localZdjz.setXzsjymc(otherSjy.getXm());
				sjygzlXqService.updateLocalZdjz(localZdjz);
				//更新工作实绩细项 若代办书记员和书记员不同，说明工作被代办了
				if(!localZdjz.getSjy().equals(localZdjz.getXzsjy())){
					TGzsj gzsj = gzsjService.getGzsjByBh(Integer.valueOf(gzsjbh));
					TGypz gypz = gypzService.getGypzByLxMc("工作实绩", Constants.ZDJZ);
					TGzsjxx gzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(gzsj, gypz);
					int sz = gzsjxx.getSz() - localZdjz.getZjys() - localZdjz.getFjys();
					gzsjxx.setSz(sz);
					gzsjxxService.update(gzsjxx);					
					TGzsj otherGzsj = gzsjService.getGzsjByUserDate(otherSjy, gzsj.getRq());
					TGzsjxx otherGzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(otherGzsj, gypz);
					sz = otherGzsjxx.getSz() + localZdjz.getZjys() + localZdjz.getFjys();
					otherGzsjxx.setSz(sz);
					gzsjxxService.update(otherGzsjxx);
				}
			}
		}
		else if(attrname.equals(Constants.TSJL) || attrname.equals(Constants.TSLJSJ)){
			LocalKtxqId id = new LocalKtxqId(fydm,ahdm,attr1,attr2);
			LocalKtxq localKtxq = sjygzlXqService.getLocalKtxqById(id);
			if(null != localKtxq){
				localKtxq.setXzsjy(xzsjy);
				TUser otherSjy = userService.getUserByUserid(xzsjy);
				localKtxq.setXzsjymc(otherSjy.getXm());
				sjygzlXqService.updateLocalKtxq(localKtxq);
				if(!localKtxq.getSjy().equals(localKtxq.getXzsjy())){
					TGzsj gzsj = gzsjService.getGzsjByBh(Integer.valueOf(gzsjbh));
					//更新开庭记录
					TGypz gypz = gypzService.getGypzByLxMc("工作实绩", Constants.TSJL);
					TGzsjxx gzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(gzsj, gypz);
					int sz = gzsjxx.getSz() - 1;
					gzsjxx.setSz(sz);
					gzsjxxService.update(gzsjxx);					
					TGzsj otherGzsj = gzsjService.getGzsjByUserDate(otherSjy, gzsj.getRq());
					TGzsjxx otherGzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(otherGzsj, gypz);
					sz = otherGzsjxx.getSz() + 1;
					otherGzsjxx.setSz(sz);
					gzsjxxService.update(otherGzsjxx);
					//更新开庭分钟数
					int fzs = getFzs(localKtxq.getId().getKssj(),localKtxq.getJssj());
					gypz = gypzService.getGypzByLxMc("工作实绩", Constants.TSLJSJ);
					gzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(gzsj, gypz);
					sz = gzsjxx.getSz() - fzs;
					gzsjxx.setSz(sz);
					gzsjxxService.update(gzsjxx);
					otherGzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(otherGzsj, gypz);
					sz = otherGzsjxx.getSz() + fzs;
					otherGzsjxx.setSz(sz);
					gzsjxxService.update(otherGzsjxx);
				}
			}
			
		}
		else if(attrname.equals(Constants.SDS)){
			LocalSdxqId id = new LocalSdxqId(fydm,ahdm,attr1,attr2);
			LocalSdxq localSdxq = sjygzlXqService.getLocalSdxqById(id);
			if(null != localSdxq){
				localSdxq.setXzsjy(xzsjy);
				TUser otherSjy = userService.getUserByUserid(xzsjy);
				localSdxq.setXzsjymc(otherSjy.getXm());
				sjygzlXqService.updateLocalSdxq(localSdxq);
				if(!localSdxq.getSjy().equals(localSdxq.getXzsjy())){
					TGzsj gzsj = gzsjService.getGzsjByBh(Integer.valueOf(gzsjbh));
					TGypz gypz = gypzService.getGypzByLxMc("工作实绩", Constants.SDS);
					TGzsjxx gzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(gzsj, gypz);
					int sz = gzsjxx.getSz() - 1;
					gzsjxx.setSz(sz);
					gzsjxxService.update(gzsjxx);					
					TGzsj otherGzsj = gzsjService.getGzsjByUserDate(otherSjy, gzsj.getRq());
					TGzsjxx otherGzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(otherGzsj, gypz);
					sz = otherGzsjxx.getSz() + 1;
					otherGzsjxx.setSz(sz);
					gzsjxxService.update(otherGzsjxx);
				}
			}
		}
		else if(attrname.equals(Constants.BLZS)){
			LocalBlxqId id = new LocalBlxqId(fydm,ahdm,attr1);
			LocalBlxq localBlxq = sjygzlXqService.getLocalBlxqById(id);
			if(null != localBlxq){
				localBlxq.setXzsjy(xzsjy);
				TUser otherSjy = userService.getUserByUserid(xzsjy);
				localBlxq.setXzsjymc(otherSjy.getXm());
				sjygzlXqService.updateLocalBlxq(localBlxq);
				if(!localBlxq.getSjy().equals(localBlxq.getXzsjy())){
					TGzsj gzsj = gzsjService.getGzsjByBh(Integer.valueOf(gzsjbh));
					TGypz gypz = gypzService.getGypzByLxMc("工作实绩", Constants.BLZS);
					TGzsjxx gzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(gzsj, gypz);
					int sz = gzsjxx.getSz() - localBlxq.getWjzs();
					gzsjxx.setSz(sz);
					gzsjxxService.update(gzsjxx);					
					TGzsj otherGzsj = gzsjService.getGzsjByUserDate(otherSjy, gzsj.getRq());
					TGzsjxx otherGzsjxx = gzsjxxService.getGzsjxxByGzsjGzxx(otherGzsj, gypz);
					sz = otherGzsjxx.getSz() + localBlxq.getWjzs();
					otherGzsjxx.setSz(sz);
					gzsjxxService.update(otherGzsjxx);
				}

			}
		}
	}
	
	public int getFzs(String kssj,String jssj){
		String ks="",js="";
		int fzs = 0,ksHour,ksMin,jsHour,jsMin;
		ks = kssj.toString().replace("：", ":");
		js = jssj.toString().replace("：", ":");
		String ksSplit[],jsSplit[];
		if(!ks.trim().equals("") && !js.trim().equals("")){
			ksSplit = ks.split(":");
			jsSplit = js.split(":");
			if(ksSplit[0]==null || ksSplit[0].trim().equals("") || !StringUtils.isNumeric(ksSplit[0].trim()))
				ksHour = 0;
			else
				ksHour = Integer.valueOf(ksSplit[0]);
			
			if(jsSplit[0]==null || jsSplit[0].trim().equals("") || !StringUtils.isNumeric(jsSplit[0].trim()))
				jsHour = 0;
			else
				jsHour = Integer.valueOf(jsSplit[0]);
			
			if(ksSplit.length<2 || ksSplit[1].trim().equals("") || !StringUtils.isNumeric(ksSplit[1].trim()))
				ksMin = 0;
			else
				ksMin = Integer.valueOf(ksSplit[1]);
			
			if(jsSplit.length<2 || jsSplit[1].trim().equals("") || !StringUtils.isNumeric(jsSplit[1].trim()))
				jsMin = 0;
			else
				jsMin = Integer.valueOf(jsSplit[1]);

			fzs += (jsHour-ksHour) * 60 + (jsMin - ksMin);
		}
		return fzs;
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
