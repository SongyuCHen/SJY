package nju.software.sjy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import nju.software.sjy.common.Constants;
import nju.software.sjy.dao.KfqxDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TKfqx;
import nju.software.sjy.model.xy.TUser;

@Repository
public class KfqxDaoImpl extends BaseDaoImpl implements KfqxDao 
{	

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBh() {
		String hql = "select max(bh) from TKfqx";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0) == null ? 0 : list.get(0);
		}
		
		return 0;
	}

	@Override
	public void save(TKfqx kfqx)
	{
		getHibernateTemplate().save(kfqx);
	}

	@Override
	public void update(TKfqx kfqx)
	{
		getHibernateTemplate().update(kfqx);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getAllKfqx() {
		String hql = "from TKfqx";
		List<TKfqx> list = (List<TKfqx>)getHibernateTemplate().find(hql);

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGypz> getAllKflb() {
		String hql = "from TGypz where lx = ?";
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql, Constants.KFLB_STR);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfkfxm> getAllKfxm() {
		String hql = "from TJfkfxm where jfkf = ?";
		List<TJfkfxm> list = (List<TJfkfxm>)getHibernateTemplate().find(hql, Constants.KF_FLAG);

		return list;
	}

	@Override
	public void save(TGypz kflb) {
		getHibernateTemplate().save(kflb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getMaxKflbPzbh(String lbStr) {
		String hql = "select max(pzbh) from TGypz where lx = ?";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql, lbStr);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0) == null ? 0 : list.get(0);
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getMaxKflbBh() {
		String hql = "select max(bh) from TGypz";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0) == null ? 0 : list.get(0);
		}
		
		return 0;
	}

	@Override
	public void deleteKflb(TGypz kflb) {
		getHibernateTemplate().delete(kflb);
	}

	@Override
	public void editKflb(TGypz kflb) {
		getHibernateTemplate().update(kflb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getMaxKfxmBh() {
		String hql = "select max(bh) from TJfkfxm";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0) == null ? 0 : list.get(0);
		}
		
		return 0;
	}

	@Override
	public void addKfxm(TJfkfxm kfxm) {
		getHibernateTemplate().save(kfxm);
	}

	@Override
	public TJfkfxm getKfxmByBh(int bh) {
		return getHibernateTemplate().get(TJfkfxm.class, bh);
	}

	@Override
	public void editKfxm(TJfkfxm kfxm) {
		getHibernateTemplate().update(kfxm);
	}

	@Override
	public void deleteKfxm(TJfkfxm kfxm) {
		getHibernateTemplate().delete(kfxm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getMaxKfqxBh() {
		String hql = "select max(bh) from TKfqx";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0) == null ? 0 : list.get(0);
		}
		
		return 0;
	}

	@Override
	public void addKfqx(TKfqx kfqx) {
		getHibernateTemplate().save(kfqx);
	}

	@Override
	public TKfqx getKfqxByBh(int bh) {
		return getHibernateTemplate().get(TKfqx.class, bh);
	}

	@Override
	public void deleteKfqx(TKfqx kfqx) {
		getHibernateTemplate().delete(kfqx);
	}

	@Override
	public void editKfqx(TKfqx kfqx) {
		getHibernateTemplate().update(kfqx);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGypz> getAllJflb() {
		String hql = "from TGypz where lx = ?";
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql, Constants.JFLB_STR);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfkfxm> getAllJfxm() {
		String hql = "from TJfkfxm where jfkf = ?";
		List<TJfkfxm> list = (List<TJfkfxm>)getHibernateTemplate().find(hql, Constants.JF_FLAG);

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getKfqxByDateRange(String startDatetime,
			String endDatetime) {
		
		String hql = "";
		List<TKfqx> list = null;
		if(!startDatetime.isEmpty() && !endDatetime.isEmpty()){
			hql = "from TKfqx where hdsj >= ? and hdsj <= ?";
			list = (List<TKfqx>)getHibernateTemplate().find(hql,startDatetime,endDatetime);
		}
		if(startDatetime.isEmpty() && !endDatetime.isEmpty()){
			hql = "from TKfqx where hdsj <= ?";
			list = (List<TKfqx>)getHibernateTemplate().find(hql,endDatetime);
		}
		if(!startDatetime.isEmpty() && endDatetime.isEmpty()){
			hql = "from TKfqx where hdsj >= ?";
			list = (List<TKfqx>)getHibernateTemplate().find(hql,startDatetime);
		}
		if(startDatetime.isEmpty() && endDatetime.isEmpty()){
			hql = "from TKfqx";
			list = (List<TKfqx>)getHibernateTemplate().find(hql);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getKfqxByTUser(TUser user)
	{
		String hql = "from TKfqx where ry=?";
		List<TKfqx> list = (List<TKfqx>)getHibernateTemplate().find(hql, user);

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getFsByLbMc(TGypz gypz, String mc)
	{
		String hql = "select fs from TJfkfxm where lb=? and mc=?";
		List<Double> list = (List<Double>)getHibernateTemplate().find(hql, 
				new Object[]{gypz, mc});
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		
		return 0.0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfkfxm> getKfxmByGypz(TGypz gypz)
	{
		String hql = "from TJfkfxm where lb=?";
		List<TJfkfxm> list = (List<TJfkfxm>)getHibernateTemplate().find(hql, gypz);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getKfqxByNfYfAndBmbh(int nf, int yf, int bmbh) {
		String hql = "from TKfqx where nf=? and yf=? and " + (bmbh == 0 ? "0 = ?" : "bm.bmbh = ?");

		List<TKfqx> list = (List<TKfqx>)getHibernateTemplate().find(hql, nf,yf,bmbh);

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getKfqxByRybhDateRangeKfxm(int rybh,
			String startDatetime, String endDatetime, TJfkfxm kfxm)
	{
		String hql = "from TKfqx where rybh=? and hdsj>=? and hdsj<=? and kfxm.lb.bh=?";
		List<TKfqx> list = (List<TKfqx>)getHibernateTemplate().find(hql, 
				new Object[]{rybh, startDatetime, endDatetime, kfxm.getLb().getBh()});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCountByRybhDateRangeKfxm(int rybh, String startTime,
			String endTime, TJfkfxm kfxm)
	{
		String hql = "select count(*) from TKfqx where rybh=? and hdsj>=? and hdsj<=? and kfxm.lb.bh=?";
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{rybh, startTime, endTime, kfxm.getLb().getBh()});
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getKfqxByUserDateRangeKfxm(TUser user, String startTime,
			String endTime, TJfkfxm kfxm)
	{
		String hql = "from TKfqx where ry=? and hdsj>=? and hdsj<=? and kfxm.lb = ?";
		List<TKfqx> list = (List<TKfqx>)getHibernateTemplate().find(hql, 
				new Object[]{user, startTime, endTime, kfxm.getLb()});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCountByUserDateRangeKfxm(TUser user, String startTime,
			String endTime, TJfkfxm kfxm)
	{
		//String hql = "select count(*) from TKfqx where ry=? and hdsj>=? and hdsj<=? and kfxm.mc = ?";
		String hql = "select sum(kfcs) from TKfqx where ry=? and hdsj>=? and hdsj<=? and kfxm.mc = ?";
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{user, startTime, endTime, kfxm.getMc()});
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getKfqxByUserDateRange(TUser user, String startDate,
			String endDate)
	{
		String hql = "from TKfqx where ry=? and hdsj>=? and hdsj<=?";
		List<TKfqx> list = (List<TKfqx>)getHibernateTemplate().find(hql, 
				new Object[]{user, startDate, endDate});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getKfqxByDateRangeExcludeQtbmlx(String startDate,
			String endDate, TGypz qtbmlx)
	{
		String hql = "from TKfqx where hdsj>=? and hdsj<=? and bm.bmlx<>?";
		List<TKfqx> list = (List<TKfqx>)getHibernateTemplate().find(hql, 
				new Object[]{startDate, endDate, qtbmlx});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKfqx> getKfqxByBmDateRange(TBm bm, String startDate,
			String endDate)
	{
		String hql = "from TKfqx where bm=? and hdsj>=? and hdsj<=?";
		List<TKfqx> list = (List<TKfqx>)getHibernateTemplate().find(hql, 
				new Object[]{bm, startDate, endDate});
		
		return list;
	}

}