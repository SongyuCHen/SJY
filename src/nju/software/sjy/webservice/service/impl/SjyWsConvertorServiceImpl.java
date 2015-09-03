package nju.software.sjy.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.software.sjy.common.Constants;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.model.xy.TGzsjxxBase;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.GzsjxxBaseService;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.webservice.bean.response.SjyGzlResp;
import nju.software.sjy.webservice.service.SjyWsConvertorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SjyWsConvertorServiceImpl implements SjyWsConvertorService
{
	private static final Object sync = new Object();
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private GzsjxxService gzsjxxService;
	
	@Autowired
	private GzsjxxBaseService gzsjxxBaseService;
	
	public List<TGzsj> convertToTGzsjList(List<Object> objList, String yearMonth)
	{
		List<TGzsj> gzsjList = new ArrayList<TGzsj>();
		if(objList != null)
		{
			for(Object obj : objList)
			{
				TGzsj gzsj = convertToTGzsj((SjyGzlResp)obj, yearMonth);
				gzsjList.add(gzsj);
			}
		}
		return gzsjList;
	}
	
	public TGzsj convertToTGzsj(SjyGzlResp resp, String yearMonth)
	{
		//String FYDM = resp.getFYDM();//法院代码
		String BMDM = resp.getBMDM();//部门代码
		String YHDM = resp.getYHDM();//用户代码
		
		Map<String, Integer> value = new HashMap<String, Integer>();
		value.put("案件数", resp.getAJS());
		value.put("庭审记录", resp.getKTS());
		value.put("装订卷宗", resp.getDAYS());
		value.put("送达数", resp.getSDS());
		value.put("笔录字数", resp.getBLZS());
		value.put("庭审累计时间", resp.getKTSJ());//开庭时间

		
		/* 根据用户、年、月获取工作实绩，如果已经存在，则覆盖原来的值；如果不存在，则增加之 */
		
		/* 获取用户 */
		TUser user = userService.getUserByUserid(YHDM);
		TBm bm = bmService.getBmByBmid(BMDM);
		
		/* 获取年、月 */
//		Date date = DateUtil.getDateByStr(KTSJ);
		//int year = nd;
		//int month = yf;
		TGzsj gzsj = gzsjService.getGzsjByUserDate(user, yearMonth);
		//TGzsj gzsj = gzsjService.getGzsjByUserNy(user, year, month);
		List<TGypz> gypzs = gypzService.getGypzByLx(Constants.GZSJ);
		if(gzsj == null)
		{//书记员工作量不存在，需要插入数据库
			synchronized(sync)
			{
				int bh = gzsjService.getMaxGzsjBh() + 1;
				TGypz zt = gypzService.getGypzByPzbhLx(1, Constants.ZT);
				
				TGzsj tgzsj = new TGzsj();
				tgzsj.setBh(bh);
				tgzsj.setUser(user);
				tgzsj.setBm(bm);
				//tgzsj.setNf(year);
				//tgzsj.setYf(month);
				tgzsj.setRq(yearMonth);
				tgzsj.setZt(zt);
				tgzsj.setEdit(0);
				gzsjService.saveGzsj(tgzsj);
				List<TGzsjxx> tgjsjxxs = new ArrayList<TGzsjxx>();

				for(TGypz gypz:gypzs){
					TGzsjxx tgjsjxx = new TGzsjxx();
					int gzxxbh = gzsjxxService.getMaxBh()+ 1;
					tgjsjxx.setBh(gzxxbh);
					tgjsjxx.setGzsj(tgzsj);
					tgjsjxx.setGzxx(gypz);
					tgjsjxx.setSz((int) value.get(gypz.getMc()));
					gzsjxxService.save(tgjsjxx);
					tgjsjxxs.add(tgjsjxx);					
				}
				tgzsj.setXxList(tgjsjxxs);
				
				gzsj = tgzsj;
			}
		}
		else//书记员工作量已经存在，需要更新数据库
		{	
			//若该数据已经被用户编辑，则数据更新至基础数据表TGzsjxxBase，否则继续更新至TGzsjxx
			if(gzsj.getEdit()==0){
				List<TGzsjxx> tgjsjxxs = gzsjxxService.getGzsjxxByGzsjGzlx(gzsj, Constants.GZSJ);
				for(TGzsjxx tgjsjxx:tgjsjxxs){
					TGypz gypz = tgjsjxx.getGzxx();
					tgjsjxx.setSz((int) value.get(gypz.getMc()));
					gzsjxxService.update(tgjsjxx);
				}
				gzsj.setXxList(tgjsjxxs);
			}else{
				List<TGzsjxxBase> tgzsjxxBases = gzsjxxBaseService.getGzsjxxBaseByGzsj(gzsj);
				for(TGzsjxxBase tgjsjxxBase:tgzsjxxBases){
					//TGypz gypz = gypzService.getGypzByBh(tgjsjxxBase.getGzxx().getBh());
					TGypz gypz = tgjsjxxBase.getGzxx();
					tgjsjxxBase.setSz((int) value.get(gypz.getMc()));
					gzsjxxBaseService.update(tgjsjxxBase);
				}
				
			}
			
		}
		
		return gzsj;
	}

	
}
