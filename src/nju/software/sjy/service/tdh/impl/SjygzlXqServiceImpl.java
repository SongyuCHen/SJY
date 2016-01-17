package nju.software.sjy.service.tdh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.software.sjy.common.Constants;
import nju.software.sjy.dao.LocalBlxqDao;
import nju.software.sjy.dao.LocalKtxqDao;
import nju.software.sjy.dao.LocalSdxqDao;
import nju.software.sjy.dao.LocalZdjzDao;
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
import nju.software.sjy.util.StringUtil;

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
	private LocalKtxqDao localKtxqDao;
	
	@Autowired
	private LocalBlxqDao localBlxqDao;
	
	@Autowired
	private LocalSdxqDao localSdxqDao;
	
	@Autowired
	private LocalZdjzDao localZdjzDao;
	
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
	
	/**
	 * 对本地的数据库更新放在获取笔录数的每一条记录判断中
	 * @param fjm
	 * @param yhdm
	 * @param kssj
	 * @param jssj
	 * @return
	 */
	@Cacheable(value="tdhCache")
	public MGzs getBlsByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		Date ksrq = DateUtil.getDateByStr(kssj);
		Date jsrq = DateUtil.getDateByStr(jssj);
		MGzs mGzs= new MGzs();
		int bls = 0;			
		LocalBlxq localBlxq;
		TUser user = userService.getUserByUserid(yhdm);
		List<SjygzlAjxx> ajxxList = ajxxDao.getWGDAjxxByFydmAndYhdm(fjm, yhdm, kssj);
		for(SjygzlAjxx aj:ajxxList){
			List<EajJz> jzList = eajJzDao.getEajJzByAhdmAndZzsj(aj.getId().getAhdm(), ksrq, jsrq);

			for(EajJz jz:jzList){
				bls += jz.getWjzs();
				localBlxq =localBlxqDao.getBlxqById(fjm,aj.getId().getAhdm(),jz.getId().getXh());
				if(null==localBlxq){//若本地不存在，则更新到本地 本地不判断笔录的文件字数是否变化
					localBlxq = new LocalBlxq();
					localBlxq.setId(new LocalBlxqId(fjm,aj.getId().getAhdm(),jz.getId().getXh()));
					localBlxq.setAh(aj.getAh());
					localBlxq.setBlmc(jz.getMc());
					localBlxq.setSjy(yhdm);
					localBlxq.setSjymc(user.getXm());
					localBlxq.setXzsjy(yhdm);
					localBlxq.setXzsjymc(user.getXm());
					localBlxq.setWjzs(jz.getWjzs());
					localBlxq.setZzrq(jz.getZzsj());
					localBlxqDao.save(localBlxq);
				}else if(localBlxq.getWjzs()!=jz.getWjzs()){
					localBlxq.setWjzs(jz.getWjzs());
					localBlxqDao.update(localBlxq);
				}
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
	
	public void updateLocalKtxq(String fjm, String yhdm,
			String kssj, String jssj){
		List<SjygzlKtxq> ktxqList = ktxqDao.getKtxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
		LocalKtxq localKtxq;
		TUser user = userService.getUserByUserid(yhdm);
		for(SjygzlKtxq ktxq:ktxqList){
			localKtxq = localKtxqDao.getLocalKtxqByFydm(fjm,ktxq.getId().getAhdm(),ktxq.getId().getKtrq(),ktxq.getId().getKssj());
			if(null == localKtxq){
				localKtxq = new LocalKtxq();
				localKtxq.setId(new LocalKtxqId(fjm,ktxq.getId().getAhdm(),ktxq.getId().getKtrq(),ktxq.getId().getKssj()));
				localKtxq.setAh(ktxq.getAh());
				localKtxq.setDd(ktxq.getDd());
				localKtxq.setJssj(ktxq.getJssj());
				localKtxq.setSjy(yhdm);
				localKtxq.setSjymc(user.getXm());
				localKtxq.setXzsjy(yhdm);
				localKtxq.setXzsjymc(user.getXm());
				localKtxqDao.saveLocalKtxq(localKtxq);
			}else if(!ktxq.getJssj().equals(localKtxq.getJssj())){
				localKtxq.setJssj(ktxq.getJssj());
				localKtxqDao.updateLocalKtxq(localKtxq);
			}
		}
	}
	
	public void updateLocalSdxq(String fjm, String yhdm,
			String kssj, String jssj){
		List<SjygzlSdxq> sdxqList = sdxqDao.getSdxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
		LocalSdxq localSdxq;
		TUser user = userService.getUserByUserid(yhdm);
		for(SjygzlSdxq sdxq:sdxqList){
			if(StringUtil.isNullOrNone(sdxq.getSddsr()) || StringUtil.isNullOrNone(sdxq.getSdrq()))
				continue;
			localSdxq = localSdxqDao.getSdxqById(fjm, sdxq.getId().getAhdm(), sdxq.getSdrq(), sdxq.getSddsr());
			if(null == localSdxq){
				localSdxq = new LocalSdxq();
				localSdxq.setId(new LocalSdxqId(fjm, sdxq.getId().getAhdm(), sdxq.getSdrq(), sdxq.getSddsr()));
				localSdxq.setAh(sdxq.getAh());
				localSdxq.setSddz(sdxq.getSddz());
				localSdxq.setSjy(yhdm);
				localSdxq.setSjymc(user.getXm());
				localSdxq.setXzsjy(yhdm);
				localSdxq.setXzsjymc(user.getXm());
				localSdxq.setYsdrq(sdxq.getYsdrq());
				localSdxqDao.save(localSdxq);
			}
		}
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
			LocalZdjz localZdjz;
			TUser user = userService.getUserByUserid(yhdm);
			for(SjygzlAjxx ajxx : ajxxList)
			{
				ViewDajgSsfzxx da = getDaysByAhdm(ajxx.getId().getAhdm());
				if(da != null){
					gzs += (da.getZjys() + da.getFjys());
					//更新本地卷宗
					localZdjz = localZdjzDao.getZdjzById(fjm, ajxx.getId().getAhdm());
					if(null == localZdjz){
						localZdjz = new LocalZdjz();
						localZdjz.setId(new LocalZdjzId(fjm, ajxx.getId().getAhdm()));
						localZdjz.setAh(ajxx.getAh());
						localZdjz.setSjy(yhdm);
						localZdjz.setSjymc(user.getXm());
						localZdjz.setXzsjy(yhdm);
						localZdjz.setXzsjymc(user.getXm());
						localZdjz.setZjys(da.getZjys());
						localZdjz.setFjys(da.getFjys());
						localZdjz.setZzsj(ajxx.getGdjsrq());
						localZdjzDao.save(localZdjz);
					}else if(localZdjz.getZjys()!=da.getZjys() || localZdjz.getFjys()!=da.getFjys()){
						localZdjz.setZjys(da.getZjys());
						localZdjz.setFjys(da.getFjys());
						localZdjzDao.update(localZdjz);
					}
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
		sjy = roleService.getRoleByRolename(Constants.SJY_NOTKH);//不参加考核的书记员页获取工作量
		useList.addAll(userService.getUserByRole(sjy));
		List<TGypz> gypzs = gypzService.getGypzByLx(Constants.GZSJ);
		TGzsj gzsj;
		MGzs mgzs;

		for(TUser user:useList){
			gzsj = new TGzsj();
			YHDM = user.getUserid();
			TGzsj oldGzsj = gzsjService.getGzsjByUserDate(user, yearMonth);
			//假如用户已经确认，则工作量不再提取 状态处于用户已提交和记录未被退回之间
			if(oldGzsj != null && oldGzsj.getZt().getPzbh()>0 && oldGzsj.getZt().getPzbh()<5)
				continue;
			Map<String, Integer> value = new HashMap<String, Integer>();
			mgzs = getAjsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,JSSJ);      //案件量太多，且案件的书记员不会产生变化
			value.put("案件数", mgzs.getGzs());				
			mgzs = getKtsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,TODAY);
			value.put("庭审记录", mgzs.getGzs());
			updateLocalKtxq(FYDM.getMc(),YHDM,KSSJ,TODAY);				//更新本地庭审记录
			mgzs = getJzsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,JSSJ);      //在获取装订卷宗的时候更新本地
			value.put("装订卷宗", mgzs.getGzs());			
			mgzs = getSdsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,JSSJ);     
			value.put("送达数", mgzs.getGzs());
			updateLocalSdxq(FYDM.getMc(),YHDM,KSSJ,TODAY);             //更新本地送达记录
			mgzs = getBlsByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,JSSJ);     //在获取笔录的时候更新本地
			value.put("笔录字数", mgzs.getGzs());
			mgzs = getKtljsjByFyAndYhdm(FYDM.getMc(),YHDM,KSSJ,TODAY);
			value.put("庭审累计时间", mgzs.getGzs());                      //开庭时间 本地无需另行更新
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
	@Override
	public List<LocalBlxq> getLocalBlxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj) {
		// TODO Auto-generated method stub
		return localBlxqDao.getLocalBlxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
	}
	@Override
	public List<LocalKtxq> getLocalKtxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj) {
		// TODO Auto-generated method stub
		return localKtxqDao.getLocalKtxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
	}
	@Override
	public List<LocalSdxq> getLocalSdxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj) {
		// TODO Auto-generated method stub
		return localSdxqDao.getLocalSdxqByFyAndYhdm(fjm, yhdm, kssj, jssj);
	}
	@Override
	public List<LocalZdjz> getLocalZdjzByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj) {
		// TODO Auto-generated method stub
		return localZdjzDao.getLocalZdjzByFyAndYhdm(fjm, yhdm, kssj, jssj);
	}
	@Override
	public LocalBlxq getLocalBlxqById(LocalBlxqId id) {
		// TODO Auto-generated method stub
		String fjm = id.getFydm();
		String ahdm = id.getAhdm();
		String xh = id.getXh();
		return localBlxqDao.getBlxqById(fjm, ahdm, xh);
	}
	@Override
	public LocalKtxq getLocalKtxqById(LocalKtxqId id) {
		// TODO Auto-generated method stub
		String fjm = id.getFydm();
		String ahdm = id.getAhdm();
		String ktrq = id.getKtrq();
		String kssj = id.getKssj();
		return localKtxqDao.getLocalKtxqByFydm(fjm, ahdm, ktrq, kssj);
	}
	@Override
	public LocalSdxq getLocalSdxqById(LocalSdxqId id) {
		// TODO Auto-generated method stub
		String fjm = id.getFydm();
		String ahdm = id.getAhdm();
		String sdrq = id.getSdrq();
		String sddsr = id.getSddsr();
		return localSdxqDao.getSdxqById(fjm, ahdm, sdrq, sddsr);
	}
	@Override
	public LocalZdjz getLocalZdjzById(LocalZdjzId id) {
		// TODO Auto-generated method stub
		String fjm = id.getFydm();
		String ahdm = id.getAhdm();
		return localZdjzDao.getZdjzById(fjm, ahdm);
	}
	@Override
	public void updateLocalBlxq(LocalBlxq localBlxq) {
		// TODO Auto-generated method stub
		localBlxqDao.update(localBlxq);
	}
	@Override
	public void updateLocalKtxq(LocalKtxq localKtxq) {
		// TODO Auto-generated method stub
		localKtxqDao.updateLocalKtxq(localKtxq);
	}
	@Override
	public void updateLocalSdxq(LocalSdxq localSdxq) {
		// TODO Auto-generated method stub
		localSdxqDao.update(localSdxq);
	}
	@Override
	public void updateLocalZdjz(LocalZdjz localZdjz) {
		// TODO Auto-generated method stub
		localZdjzDao.update(localZdjz);
	}
}
