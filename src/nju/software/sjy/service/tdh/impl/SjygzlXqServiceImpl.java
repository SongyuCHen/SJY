package nju.software.sjy.service.tdh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.software.sjy.common.Constants;
import nju.software.sjy.dao.da.DajgDao;
import nju.software.sjy.dao.tdh.EajJzDao;
import nju.software.sjy.dao.tdh.SjygzlAjxxDao;
import nju.software.sjy.dao.tdh.SjygzlBlxqDao;
import nju.software.sjy.dao.tdh.SjygzlKtxqDao;
import nju.software.sjy.dao.tdh.SjygzlSdxqDao;
import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.model.da.ViewDajgSsfzxx;
import nju.software.sjy.model.tdh.EajJz;
import nju.software.sjy.model.tdh.SjygzlAjxx;
import nju.software.sjy.model.tdh.SjygzlBlxq;
import nju.software.sjy.model.tdh.SjygzlBlxqId;
import nju.software.sjy.model.tdh.SjygzlKtxq;
import nju.software.sjy.model.tdh.SjygzlSdxq;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.model.xy.TGzsjxxBase;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.GzsjxxBaseService;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.UserService;
import nju.software.sjy.service.tdh.SjygzlXqService;
import nju.software.sjy.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SjygzlXqServiceImpl implements SjygzlXqService
{

	@Autowired
	SjygzlSdxqDao sdxqDao;

	@Autowired
	SjygzlBlxqDao blxqDao;

	@Autowired
	SjygzlKtxqDao ktxqDao;

	@Autowired
	SjygzlAjxxDao ajxxDao;
	
	@Autowired
	DajgDao dajgDao;

	@Autowired
	EajJzDao eajJzDao;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private GzsjxxService gzsjxxService;
	
	@Autowired
	private GzsjxxBaseService gzsjxxBaseService;
	
	private static final Object sync = new Object();
	
	@Cacheable(value="tdhCache")
	public List<SjygzlBlxq> getBlxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj){
		Date ksrq = DateUtil.getDateByStr(kssj);
		Date jsrq = DateUtil.getDateByStr(jssj);
		List<SjygzlAjxx> ajxxList = ajxxDao.getWGDAjxxByFydmAndYhdm(fjm, yhdm, kssj);
		//List<EajJz> eajJzList = new ArrayList<EajJz>();
		List<SjygzlBlxq> blxqList =  new ArrayList<SjygzlBlxq>();
		for(SjygzlAjxx aj:ajxxList){
			List<EajJz> jzList = eajJzDao.getEajJzByAhdmAndZzsj(aj.getId().getAhdm(), ksrq, jsrq);
			for(EajJz jz:jzList){
				SjygzlBlxq blxq = new SjygzlBlxq();
				SjygzlBlxqId id = new SjygzlBlxqId(fjm,aj.getId().getAhdm(),jz.getMc(),jz.getZzsj());
				blxq.setId(id);
				blxq.setAh(aj.getAh());
				blxq.setSjy(yhdm);
				blxq.setCbbm(aj.getCbbm());
				blxq.setCbbmmc(aj.getCbbmmc());
				blxq.setSjymc(aj.getSjymc());
				blxq.setWjzs(jz.getWjzs());
				blxqList.add(blxq);
			}
					
		}
		return blxqList;
	}
	@Cacheable(value="tdhCache")
	public List<SjygzlBlxq> getBlxqByFyAndYhdm2(String fjm, String yhdm,
			String kssj, String jssj)
	{
		Date ksrq = DateUtil.getDateByStr(kssj);
		Date jsrq = DateUtil.getDateByStr(jssj);
		return blxqDao.getBlxqByFyAndYhdm(fjm, yhdm, ksrq, jsrq);
	}
		
	@Cacheable(value="tdhCache")
	public List<SjygzlKtxq> getKtxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		return ktxqDao.getKtxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
	}
	
	@Cacheable(value="tdhCache")
	public MGzs getBlsByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		Date ksrq = DateUtil.getDateByStr(kssj);
		Date jsrq = DateUtil.getDateByStr(jssj);
		MGzs mGzs= new MGzs();
		int bls = 0;
		List<SjygzlAjxx> ajxxList = ajxxDao.getWGDAjxxByFydmAndYhdm(fjm, yhdm, kssj);
		for(SjygzlAjxx aj:ajxxList){
			List<EajJz> jzList = eajJzDao.getEajJzByAhdmAndZzsj(aj.getId().getAhdm(), ksrq, jsrq);
			for(EajJz jz:jzList){
				bls += jz.getWjzs();
			}
					
		}
		mGzs.setFjm(fjm);
		mGzs.setYhdm(yhdm);
		mGzs.setGzs(bls);
		return mGzs;
	}
	
	@Cacheable(value="tdhCache")
	public MGzs getKtsByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		return ktxqDao.getKtsByFy(fjm, yhdm, kssj, jssj);
	}
	
	@Cacheable(value="tdhCache")
	public MGzs getKtljsjByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		return ktxqDao.getKtljsjByFy(fjm, yhdm, kssj, jssj);
	}

	@Cacheable(value="tdhCache")
	public MGzs getSdsByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		return sdxqDao.getSdsByFy(fjm, yhdm, kssj, jssj);
	}
	@Cacheable(value="tdhCache")
	public MGzs getAjsByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		return ajxxDao.getAjsByFydmAndYhdm(fjm, yhdm, kssj, jssj);
	}
	@Cacheable(value="tdhCache")
	public MGzs getJzsByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		List<SjygzlAjxx> ajxxList = ajxxDao.getAjxxByFydmAndGdjsrq(fjm, yhdm, kssj, jssj);
		MGzs mGzs= new MGzs();
		int gzs = 0;
		if(ajxxList != null)
		{
			mGzs.setFjm(fjm);
			mGzs.setYhdm(yhdm);			
			for(SjygzlAjxx ajxx : ajxxList)
			{
				ViewDajgSsfzxx da = getDaysByAhdm(ajxx.getId().getAhdm());
				if(da != null){
					gzs += (da.getZjys() + da.getFjys());
				}
			}			
		}
		mGzs.setGzs(gzs);
		return mGzs;
	}

	@Cacheable(value="tdhCache")
	public List<SjygzlSdxq> getSdxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		return sdxqDao.getSdxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
	}

	@Cacheable(value="tdhCache")
	public List<SjygzlAjxx> getAjxxByFydmAndGdjsrq(String fydm, String yhdm,
			String kssj, String jssj)
	{
		return ajxxDao.getAjxxByFydmAndGdjsrq(fydm, yhdm, kssj, jssj);
	}


	@Cacheable(value="tdhCache")
	public ViewDajgSsfzxx getDaysByAhdm(String ahdm) {
		// TODO Auto-generated method stub
		return dajgDao.getWjysByAhdm(ahdm);
	}
	
	@Override
	public List<TGzsj> getSjyGzlFromView(Date date, int type) {
		// TODO Auto-generated method stub
		List<TGzsj> gzsjList = new ArrayList<TGzsj>();
		TGypz FYDM = gypzService.getGypzByPzbhLx(1,Constants.FYDM);
		String KSSJ = "";
		String JSSJ = "";
		String TODAY = "";
		String YHDM = "";
		String rq[] = new String[2];
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
		TODAY = DateUtil.getSimpleFormatStr(new Date());
		if(TODAY.compareTo(JSSJ)>0) TODAY=JSSJ;
		TRole sjy = roleService.getRoleByRolename(Constants.SJY);
		List<TUser> useList = userService.getUserByRole(sjy);
		List<TGypz> gypzs = gypzService.getGypzByLx(Constants.GZSJ);
		TGzsj gzsj;
		MGzs mgzs;
		for(TUser user:useList){
			gzsj = new TGzsj();
			YHDM = user.getUserid();
			TGzsj oldGzsj = gzsjService.getGzsjByUserDate(user, yearMonth);
			Map<String, Integer> value = new HashMap<String, Integer>();
			mgzs = getAjsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,JSSJ);
			value.put("案件数", mgzs.getGzs());
			mgzs = getKtsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,TODAY);
			value.put("庭审记录", mgzs.getGzs());
			mgzs = getJzsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,JSSJ);
			value.put("装订卷宗", mgzs.getGzs());
			mgzs = getSdsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,JSSJ);
			value.put("送达数", mgzs.getGzs());
			mgzs = getBlsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,JSSJ);
			value.put("笔录字数", mgzs.getGzs());
			mgzs = getKtljsjByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,TODAY);
			value.put("庭审累计时间", mgzs.getGzs());//开庭时间
			if(oldGzsj == null){
				//书记员工作量不存在，需要插入数据库
				synchronized(sync)
				{
					int bh = gzsjService.getMaxGzsjBh() + 1;
					TGypz zt = gypzService.getGypzByPzbhLx(0, Constants.ZT);
					
					gzsj.setBh(bh);
					gzsj.setUser(user);
					gzsj.setBm(user.getBm());
					gzsj.setRq(yearMonth);
					gzsj.setZt(zt);
					gzsj.setEdit(0);
					gzsjService.saveGzsj(gzsj);
					List<TGzsjxx> tgjsjxxs = new ArrayList<TGzsjxx>();

					for(TGypz gypz:gypzs){
						TGzsjxx tgjsjxx = new TGzsjxx();
						int gzxxbh = gzsjxxService.getMaxBh()+ 1;
						tgjsjxx.setBh(gzxxbh);
						tgjsjxx.setGzsj(gzsj);
						tgjsjxx.setGzxx(gypz);
						tgjsjxx.setSz((int) value.get(gypz.getMc()));
						gzsjxxService.save(tgjsjxx);
						tgjsjxxs.add(tgjsjxx);					
					}
					gzsj.setXxList(tgjsjxxs);
				}
			}
			else//书记员工作量已经存在，需要更新数据库
			{	
				//若该数据已经被用户编辑，则数据更新至基础数据表TGzsjxxBase，否则继续更新至TGzsjxx
				if(oldGzsj.getEdit()==0){
					List<TGzsjxx> tgjsjxxs = gzsjxxService.getGzsjxxByGzsjGzlx(oldGzsj, Constants.GZSJ);
					for(TGzsjxx tgjsjxx:tgjsjxxs){
						TGypz gypz = tgjsjxx.getGzxx();
						tgjsjxx.setSz((int) value.get(gypz.getMc()));
						gzsjxxService.update(tgjsjxx);
					}
					oldGzsj.setXxList(tgjsjxxs);
				}else{
					List<TGzsjxxBase> tgzsjxxBases = gzsjxxBaseService.getGzsjxxBaseByGzsj(oldGzsj);
					for(TGzsjxxBase tgjsjxxBase:tgzsjxxBases){
						//TGypz gypz = gypzService.getGypzByBh(tgjsjxxBase.getGzxx().getBh());
						TGypz gypz = tgjsjxxBase.getGzxx();
						tgjsjxxBase.setSz((int) value.get(gypz.getMc()));
						gzsjxxBaseService.update(tgjsjxxBase);
					}
					
				}
				gzsj = oldGzsj;
				
			}
			gzsjList.add(gzsj);
		}
		
		return gzsjList;
		
	}
}
