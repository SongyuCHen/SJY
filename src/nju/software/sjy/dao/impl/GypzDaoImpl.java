package nju.software.sjy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.GypzDao;
import nju.software.sjy.model.xy.TGypz;

@Repository
public class GypzDaoImpl extends BaseDaoImpl implements GypzDao
{

	@SuppressWarnings("unchecked")
	@Override
	public TGypz getGypzByBh(int bh)
	{
		String hql = "from TGypz where bh=?";
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql, bh);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer getBhByLxAndMc(String lx, String mc)
	{
		String hql = "select bh from TGypz where lx=? and mc=?";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql,
				new Object[]{lx, mc});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGypz> getGypzByLx(String lx)
	{
		String hql = "from TGypz where lx=? order by pzbh";
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql, lx);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TGypz getGypzByLxMc(String lx, String mc)
	{
		String hql = "from TGypz where lx=? and mc=?";
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql, 
				new Object[]{lx, mc});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBh()
	{
		String hql = "select max(bh) from TGypz";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxPzbhByLx(String lx)
	{
		String hql = "select max(pzbh) from TGypz where lx=?";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql, lx);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void save(TGypz gypz)
	{
		getHibernateTemplate().save(gypz);
	}

	@Override
	public void update(TGypz gypz)
	{
		getHibernateTemplate().update(gypz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TGypz getGypzByPzbhLx(int pzbh, String lx)
	{
		String hql = "from TGypz where pzbh=? and lx=?";
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql, 
				new Object[]{pzbh, lx});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void delete(TGypz gypz)
	{
		getHibernateTemplate().delete(gypz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMcByLx(String lx)
	{
		String hql = "select mc from TGypz where lx=?";
		List<String> list = (List<String>)getHibernateTemplate().find(hql, lx);
		
		return list;
	}
	
}
