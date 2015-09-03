package nju.software.sjy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.JfpzDao;
import nju.software.sjy.model.xy.TJfpz;

@Repository
public class JfpzDaoImpl extends BaseDaoImpl implements JfpzDao
{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TJfpz> getAllJfpz()
	{
		String hql = "from TJfpz";
		List<TJfpz> list = (List<TJfpz>)getHibernateTemplate().find(hql);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TJfpz getJfpzByBh(int bh)
	{
		String hql = "from TJfpz where bh=?";
		List<TJfpz> list = (List<TJfpz>)getHibernateTemplate().find(hql, bh);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void save(TJfpz jfpz)
	{
		getHibernateTemplate().save(jfpz);
	}

	@Override
	public void update(TJfpz jfpz)
	{
		getHibernateTemplate().update(jfpz);
	}

	@Override
	public void delete(TJfpz jfpz)
	{
		getHibernateTemplate().delete(jfpz);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getMaxBh()
	{
		String hql = "select max(bh) from TJfpz";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getJfzByJb(String jb)
	{
		String hql = "select jfz from TJfpz where jb=?";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql, jb);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return 0;
	}
}
