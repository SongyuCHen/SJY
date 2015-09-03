package nju.software.sjy.dao.impl;

import java.util.List;

import nju.software.sjy.dao.GzsjDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TUser;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GzsjDaoImpl extends BaseDaoImpl implements GzsjDao 
{

	@SuppressWarnings("unchecked")
	@Override
	public TGzsj getGzsjByBh(int bh)
	{
		String hql = "from TGzsj where bh=?";
		List<TGzsj> list = (List<TGzsj>)getHibernateTemplate().find(hql, bh);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void saveGzsj(TGzsj gzsj)
	{
		getHibernateTemplate().save(gzsj);
	}

	@Override
	public void updateGzsj(TGzsj gzsj)
	{
		getHibernateTemplate().update(gzsj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxGzsjBh()
	{
		String hql = "select max(bh) from TGzsj";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsj> getAllGzsj()
	{
		String hql = "from TGzsj";
		List<TGzsj> list = (List<TGzsj>)getHibernateTemplate().find(hql);
		
		return list;
	}

	@Override
	public void deleteGzsj(TGzsj gzsj)
	{
		getHibernateTemplate().delete(gzsj);
	}

	@Override
	public void updateAllGzsj(List<TGzsj> tlist)
	{
		getHibernateTemplate().saveOrUpdateAll(tlist);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsj> getGzsjByBhArr(Integer[] bhArr)
	{
		if(bhArr == null || bhArr.length == 0)
		{
			return null;
		}
		
		String hql = "from TGzsj where bh in (:arr)";
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		
		query.setParameterList("arr", bhArr);
		
		return (List<TGzsj>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsj> getGzsjByUyUserNfJdZt(TUser user, int year,
			int startMonth, int endMonth, TGypz zt)
	{
		String hql = "from TGzsj g where g.user=? and g.nf=? and g.yf>=? and g.yf<=? and g.zt=?";
		List<TGzsj> list = (List<TGzsj>)getHibernateTemplate().find(hql, 
				new Object[]{user, year, startMonth, endMonth, zt});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsj> getGzsjByUserBmDateRange(TUser user, TBm bm,
			String startDate, String endDate)
	{
		String hql = "from TGzsj g where g.user=? and g.bm=? and g.rq>=? and g.rq<=?";
		List<TGzsj> list = (List<TGzsj>)getHibernateTemplate().find(hql, 
				new Object[]{user, bm, startDate, endDate});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsj> getGzsjByDateRangeExcludeQtbmlx(String startDate,
			String endDate, TGypz qtbmlx)
	{
		String hql = "from TGzsj where rq>=? and rq<=? and bm.bmlx<>?";
		List<TGzsj> list = (List<TGzsj>)getHibernateTemplate().find(hql, 
				new Object[]{startDate, endDate, qtbmlx});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsj> getGzsjByBmDateRange(TBm bm, String startDate,
			String endDate)
	{
		String hql = "from TGzsj g where g.bm=? and g.rq>=? and g.rq<=?";
		List<TGzsj> list = (List<TGzsj>)getHibernateTemplate().find(hql, 
				new Object[]{bm, startDate, endDate});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TGzsj getGzsjByUserDate(TUser user, String date)
	{
		String hql = "from TGzsj g where g.user=? and g.rq=?";
		List<TGzsj> list = (List<TGzsj>)getHibernateTemplate().find(hql, 
				new Object[]{user, date});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsj> getGzsjByUserDateRangeZt(TUser user, String startDate,
			String endDate, TGypz finalZt)
	{
		String hql = "from TGzsj g where g.user=? and g.rq>=? and g.rq<=? and g.zt=?";
		List<TGzsj> list = (List<TGzsj>)getHibernateTemplate().find(hql, 
				new Object[]{user, startDate, endDate, finalZt});
		
		return list;
	}

	

	
	
	
}
