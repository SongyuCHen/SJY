package nju.software.sjy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.PfpzDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfpz;

@Repository
public class PfpzDaoImpl extends BaseDaoImpl implements PfpzDao 
{

	@SuppressWarnings("unchecked")
	@Override
	public List<TPfpz> getAllPfpz()
	{
		String hql = "from TPfpz";
		List<TPfpz> list = (List<TPfpz>)getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TPfpz getPfpzByMc(String mc)
	{
		String hql = "from TPfpz where mc=?";
		List<TPfpz> list = (List<TPfpz>)getHibernateTemplate().find(hql, mc);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void update(TPfpz pfpz)
	{
		getHibernateTemplate().update(pfpz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBh() 
	{
		String hql = "select max(bh) from TPfpz";
		
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void save(TPfpz pfpz) 
	{
		getHibernateTemplate().save(pfpz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TPfpz getPfpzByGz(TGypz gz) 
	{
		String hql = "from TPfpz where gz=?";
		
		List<TPfpz> list = (List<TPfpz>)getHibernateTemplate().find(hql, gz);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void delete(TPfpz pfpz) 
	{
		getHibernateTemplate().delete(pfpz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TPfpz getPfpzByBh(int bh) 
	{
		String hql = "from TPfpz where bh=?";
		List<TPfpz> list = (List<TPfpz>)getHibernateTemplate().find(hql, bh);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getFsByGz(TGypz gz)
	{
		String hql = "select fs from TPfpz where gz=?";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql, gz);
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}
	
	
}
