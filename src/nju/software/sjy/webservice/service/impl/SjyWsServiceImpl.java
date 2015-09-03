package nju.software.sjy.webservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.ws.Holder;























import nju.software.sjy.common.Constants;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.util.DateUtil;
import nju.software.sjy.webservice.bean.request.RyReq;
import nju.software.sjy.webservice.bean.request.SjyGzlReq;
import nju.software.sjy.webservice.bean.request.ZzjgReq;
import nju.software.sjy.webservice.convertor.RequestConvertor;
import nju.software.sjy.webservice.convertor.ResponseConvertor;
import nju.software.sjy.webservice.cxf.SzftWebService;
import nju.software.sjy.webservice.cxf.SzftWebServiceService;
import nju.software.sjy.webservice.service.SjyWsConvertorService;
import nju.software.sjy.webservice.service.SjyWsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SjyWsServiceImpl implements SjyWsService
{
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SjyWsConvertorService convert;
	
	@Override
	public List<TGzsj> getSjyGzl(Date date, int type)
	{
		/* 根据date获取开始时间和结束时间 */
		//Date preMonth = DateUtil.getPreMonthDate(date);
		
		TGypz FJM = gypzService.getGypzByPzbhLx(1,Constants.FJM);
		TGypz UID = gypzService.getGypzByPzbhLx(1,"UID");
		TGypz PWD = gypzService.getGypzByPzbhLx(1,"PWD");
		String KSSJ = "";
		String JSSJ = "";
		String YHDM = "";
		String suid = UID.getMc();
		String spass = PWD.getMc();
		String rq[] = new String[2];
		List<TGzsj> gzsj = new ArrayList<TGzsj>();
		String yearMonth = null;
		//int nd,yf;
		//nd = DateUtil.getYear(date);
		//yf = DateUtil.getMonth(date);
		if(type==0)//手工
		{
			rq = DateUtil.getStartEndDateStr(date);
			yearMonth = DateUtil.getYearMonth(date);
		}
		else if(type==1)//定时任务自动
		{
			if(DateUtil.getMonth(date)==1)
			{
				rq = DateUtil.getStartEndDateStr(DateUtil.getYear(date)-1, 12);
				//nd = DateUtil.getYear(date)-1;
				//yf = 12;
			}
			else
			{
				rq = DateUtil.getStartEndDateStr(DateUtil.getYear(date), DateUtil.getMonth(date)-1);
				//yf = DateUtil.getMonth(date) - 1;
			}
			yearMonth = DateUtil.getLastMonthDateStr(date);
		}
		
		KSSJ = rq[0];
		JSSJ = rq[1];
		TRole sjy = roleService.getRoleByRolename(Constants.SJY);
		List<TUser> useList = userService.getUserByRole(sjy);
		/* 调用webservice */
		SzftWebServiceService swss = new SzftWebServiceService();
		SzftWebService sws = swss.getSzftWebServicePort();
		for(TUser user:useList){
			YHDM = user.getUserid();
			SjyGzlReq req = new SjyGzlReq(FJM.getMc(), YHDM, KSSJ, JSSJ);
			String requestXML = RequestConvertor.convertToXML(req);
			
			Holder<String> holder = new Holder<String>(requestXML);
			sws.getSjyGzl(suid, spass, holder);	
			String result = holder.value;

			List<Object> respList = ResponseConvertor.convertToObj(result);
			gzsj.addAll(convert.convertToTGzsjList(respList, yearMonth));

		}
		
		
		return gzsj;
	}

	@Override
	public List<TUser> getAllRy() {
		TGypz FJM = gypzService.getGypzByPzbhLx(1,Constants.FJM);
		TGypz UID = gypzService.getGypzByPzbhLx(1,"UID");
		TGypz PWD = gypzService.getGypzByPzbhLx(1,"PWD");
		String suid = UID.getMc();
		String spass = PWD.getMc();
		RyReq req = new RyReq(FJM.getMc());
		String requestXML = RequestConvertor.convertToXML(req);
		
		/* 调用webservice */
		SzftWebServiceService swss = new SzftWebServiceService();
		SzftWebService sws = swss.getSzftWebServicePort();
		String result = sws.getRy(suid, spass, requestXML);	
		List<Object> respList = ResponseConvertor.convertToObj(result);
		respList.isEmpty();
		return null;
	}

	@Override
	public List<TBm> getAllZzjg() {
		TGypz FJM = gypzService.getGypzByPzbhLx(1,Constants.FJM);
		TGypz UID = gypzService.getGypzByPzbhLx(1,"UID");
		TGypz PWD = gypzService.getGypzByPzbhLx(1,"PWD");
		String suid = UID.getMc();
		String spass = PWD.getMc();
		ZzjgReq req = new ZzjgReq(FJM.getMc());
		String requestXML = RequestConvertor.convertToXML(req);
		
		
		/* 调用webservice */
		SzftWebServiceService swss = new SzftWebServiceService();
		SzftWebService sws = swss.getSzftWebServicePort();
		String result = sws.getZzjg(suid, spass, requestXML);	
		List<Object> respList = ResponseConvertor.convertToObj(result);
		respList.isEmpty();
		return null;
	}
	
	
}
