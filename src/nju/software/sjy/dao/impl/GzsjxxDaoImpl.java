package nju.software.sjy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.GzsjxxDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.model.xy.TUser;

@Repository
public class GzsjxxDaoImpl extends BaseDaoImpl implements GzsjxxDao
{

	@Override
	public void update(TGzsjxx gztb)
	{
		getHibernateTemplate().update(gztb);
	}

	@Override
	public void save(TGzsjxx gztb)
	{
		getHibernateTemplate().save(gztb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBh()
	{
		String hql = "select max(bh) from TGzsjxx";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		return 0;
	}

	@Override
	public void delete(TGzsjxx gztb)
	{
		getHibernateTemplate().delete(gztb);
	}



	@SuppressWarnings("unchecked")
	@Override
	public TGzsjxx getGzsjxxByGzsjGzxx(TGzsj gzsj, TGypz gzxx)
	{
		String hql = "from TGzsjxx where gzsj=? and gzxx=?";
		List<TGzsjxx> list = (List<TGzsjxx>)getHibernateTemplate().find(hql,
				new Object[]{gzsj, gzxx});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsjxx> getGzsjxxByGzsjGzlx(TGzsj gzsj, String gzlx)
	{
		String hql = "from TGzsjxx where gzsj=? and gzxx.lx = ?";
		List<TGzsjxx> list = (List<TGzsjxx>)getHibernateTemplate().find(hql,
				new Object[]{gzsj, gzlx});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxSzByNfJdGzxx(int nf, int fromMonth, int toMonth,
			TGypz gzxx)
	{
		String hql = "select max(sum(sz)) from TGzsjxx where gzsj.nf=? and gzsj.yf>=? and gzsj.yf<=? and gzxx=? group by gzsj";
		
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{nf, fromMonth, toMonth, gzxx});
		
		if(list != null && !list.isEmpty() && list.get(0)!=null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMinSzByNfJdGzxx(int nf, int fromMonth, int toMonth,
			TGypz gzxx)
	{
		String hql = "select min(sum(sz)) from TGzsjxx where gzsj.nf=? and gzsj.yf>=? and gzsj.yf<=? and gzxx=? group by gzsj";
		
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{nf, fromMonth, toMonth, gzxx});
		
		if(list != null && !list.isEmpty() && list.get(0)!=null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getSzByRybhNfJdGzxx(TUser user, int nf, int fromMonth,
			int toMonth, TGypz gzxx)
	{
		String hql = "select sum(sz) from TGzsjxx where gzsj.user=? and gzsj.nf=? and gzsj.yf>=? and gzsj.yf<=? and gzxx=? group by gzsj";
		
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{user, nf, fromMonth, toMonth, gzxx});
		
		if(list != null && !list.isEmpty() && list.get(0)!=null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getSzByGzsjGzxx(TGzsj gzsj, TGypz gzxx)
	{
		String hql = "select sz from TGzsjxx where gzsj=? and gzxx=?";
		
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql, 
				new Object[]{gzsj, gzxx});
		
		if(list != null && !list.isEmpty() && list.get(0)!=null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxSumByGzxxNfJdZt(TGypz gzxx, int nf, int startMonth,
			int endMonth, TGypz finalZt)
	{
		String hql = "select max(sum(sz)) from TGzsjxx where gzxx=? and gzsj.nf=? and gzsj.yf>=? and gzsj.yf<=? and gzsj.zt=? group by gzsj.user";
		
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{gzxx, nf, startMonth, endMonth, finalZt});
		
		if(list != null && !list.isEmpty() && list.get(0)!=null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMinSumByGzxxNfJdZt(TGypz gzxx, int nf, int startMonth,
			int endMonth, TGypz finalZt)
	{
		String hql = "select min(sum(sz)) from TGzsjxx where gzxx=? and gzsj.nf=? and gzsj.yf>=? and gzsj.yf<=? and gzsj.zt=? group by gzsj.user";
		
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{gzxx, nf, startMonth, endMonth, finalZt});
		
		if(list != null && !list.isEmpty() && list.get(0)!=null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getSzByGzsjlistGzxx(List<TGzsj> tlist, TGypz gzxx)
	{
		if(tlist == null || tlist.isEmpty())
		{
			return null;
		}
		
		String hql = "select g.sz from TGzsjxx g where g.gzxx=? and g.gzsj in (:tlist)";
		
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setParameter(0, gzxx);
		query.setParameterList("tlist", tlist);
		
		return (List<Integer>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsjxx> getGzsjxxByGzsj(TGzsj gzsj)
	{
		String hql = "from TGzsjxx where gzsj=?";
		List<TGzsjxx> list = (List<TGzsjxx>)getHibernateTemplate().find(hql, gzsj);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxSumByGzxxDateRangeZt(TGypz gzxx, String startDate,
			String endDate, TGypz finalZt)
	{
		String hql = "select max(sum(sz)) from TGzsjxx where gzxx=? and gzsj.rq>=? and gzsj.rq<=? and gzsj.zt=? group by gzsj.user";
		
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{gzxx, startDate, endDate, finalZt});
		
		if(list != null && !list.isEmpty() && list.get(0)!=null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMinSumByGzxxDateRangeZt(TGypz gzxx, String startDate,
			String endDate, TGypz finalZt)
	{
		String hql = "select min(sum(sz)) from TGzsjxx where gzxx=? and gzsj.rq>=? and gzsj.rq<=? and gzsj.zt=? group by gzsj.user";
		
		List<Long> list = (List<Long>)getHibernateTemplate().find(hql, 
				new Object[]{gzxx, startDate, endDate, finalZt});
		
		if(list != null && !list.isEmpty() && list.get(0)!=null)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}
}
