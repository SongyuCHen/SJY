package nju.software.sjy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.JfqxDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TUser;

@Repository
public class JfqxDaoImpl extends BaseDaoImpl implements JfqxDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfqx> getJfqxByBmNY(TJfqx jfqx)
	{
		String hql = "from TJfqx t where t.bmbh=? and t.nf=? and t.yf=?";
		List<TJfqx> list = (List<TJfqx>)getHibernateTemplate().find(hql, 
				new Object[]{jfqx.getUser().getBm().getBmbh(), jfqx.getNf(), jfqx.getYf()});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBh()
	{
		String hql = "select max(bh) from TJfqx";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0) == null ? 0 : list.get(0);
		}
		
		return 0;
	}

	@Override
	public void save(TJfqx jfqx)
	{
		getHibernateTemplate().save(jfqx);
	}

	@Override
	public void update(TJfqx jfqx)
	{
		getHibernateTemplate().update(jfqx);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TJfqx getJfqxByBh(int bh)
	{
		String hql = "from TJfqx where bh=?";
		List<TJfqx> list = (List<TJfqx>)getHibernateTemplate().find(hql, bh);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfqx> getAllJfqx() {
		String hql = "from TJfqx";
		List<TJfqx> list = (List<TJfqx>)getHibernateTemplate().find(hql);

		return list;
	}

	@Override
	public void delete(TJfqx jfqx) 
	{
		getHibernateTemplate().delete(jfqx);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfqx> getJfqxByRybhDateRangeKfxm(int rybh,
			String startDatetime, String endDatetime, TJfkfxm kfxm)
	{
		String hql = "from TJfqx where rybh=? and hdsj>=? and hdsj<=? and kfxm.lb.bh=?";
		List<TJfqx> list = (List<TJfqx>)getHibernateTemplate().find(hql, 
				new Object[]{rybh, startDatetime, endDatetime, kfxm.getLb().getBh()});
		
		return list;
	}

	@Override
	public void updateAllJfqx(List<TJfqx> tlist)
	{
		getHibernateTemplate().saveOrUpdateAll(tlist);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfqx> getJfqxByBhArr(Integer[] bhArr)
	{
		if(bhArr == null || bhArr.length == 0)
		{
			return null;
		}
		
		String hql = "from TJfqx where bh in (:arr)";
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		
		query.setParameterList("arr", bhArr);
		
		return (List<TJfqx>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfqx> getJfqxByUserDateRangeJfxmZt(TUser user,
			String startTime, String endTime, TJfkfxm jfxm, TGypz zt)
	{
		String hql = "from TJfqx where user=? and hdsj>=? and hdsj<=? and jfxm.lb = ? and zt=?";
		List<TJfqx> list = (List<TJfqx>)getHibernateTemplate().find(hql, 
				new Object[]{user, startTime, endTime, jfxm.getLb(), zt});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCountOfJfqxByUserNfJdJfxmZt(TUser user, int nf,
			int startYf, int endYf, TJfkfxm jfxm, TGypz zt)
	{
		//String hql = "select count(*) from TJfqx j where j.user=? and j.nf=? and j.yf>=? and j.yf<=? and j.jfxm.lb=? and j.zt=?";
		String hql = "select sum(j.jfcs) from TJfqx j where j.user=? and j.nf=? and j.yf>=? and j.yf<=? and j.jfxm.lb=? and j.zt=?";
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{user, nf, startYf, endYf, jfxm.getLb(), zt});
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfqx> getJfqxByDateRangeExcludeQtbmlx(String startDate,
			String endDate, TGypz qtbmlx)
	{
		String hql = "from TJfqx j where j.hdsj>=? and j.hdsj<=? and j.bm.bmlx<>?";
		List<TJfqx> list = (List<TJfqx>)getHibernateTemplate().find(hql, 
				new Object[]{startDate, endDate, qtbmlx});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfqx> getJfqxByBmDateRange(TBm bm, String startDate,
			String endDate)
	{
		String hql = "from TJfqx j where j.bm=? and j.hdsj>=? and j.hdsj<=?";
		List<TJfqx> list = (List<TJfqx>)getHibernateTemplate().find(hql,
				new Object[]{bm, startDate, endDate});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfqx> getJfqxByUserDateRange(TUser user, String startDate,
			String endDate)
	{
		String hql = "from TJfqx j where j.user=? and j.hdsj>=? and j.hdsj<=?";
		List<TJfqx> list = (List<TJfqx>)getHibernateTemplate().find(hql,
				new Object[]{user, startDate, endDate});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCountOfJfqxByUserDateRangeJfxmZt(TUser user,
			String startDate, String endDate, TJfkfxm jfxm, TGypz zt)
	{
		String hql = "select sum(j.jfcs) from TJfqx j where j.user=? and j.hdsj>=? and j.hdsj<=? and j.jfxm.lb=? and j.zt=?";
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{user, startDate, endDate, jfxm.getLb(), zt});
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}
	
	
}
