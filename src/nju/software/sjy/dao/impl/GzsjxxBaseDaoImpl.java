package nju.software.sjy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import nju.software.sjy.dao.GzsjxxBaseDao;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxxBase;

@Component
public class GzsjxxBaseDaoImpl extends BaseDaoImpl implements GzsjxxBaseDao
{

	@Override
	public void save(TGzsjxxBase gztb)
	{
		getHibernateTemplate().save(gztb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBh()
	{
		String hql = "select max(bh) from TGzsjxxBase";
		List<Integer> list = (List<Integer>) getHibernateTemplate().find(hql);
		if (list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		return 0;
	}

	@Override
	public void delete(TGzsjxxBase gztb)
	{
		getHibernateTemplate().delete(gztb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsjxxBase> getGzsjxxByGzsj(TGzsj gzsj)
	{
		String hql = "from TGzsjxxBase where gzsj=?";
		List<TGzsjxxBase> list = (List<TGzsjxxBase>) getHibernateTemplate()
				.find(hql, gzsj);

		return list;
	}

	@Override
	public void update(TGzsjxxBase gztb)
	{
		getHibernateTemplate().update(gztb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TGzsjxxBase getGzsjxxByGzsjbhAndGzxxbh(int gzsjbh, int gzxxbh) 
	{
		String hql = "from TGzsjxxBase where gzsjbh=? and gzxxbh=?";
		List<TGzsjxxBase> list = (List<TGzsjxxBase>)getHibernateTemplate().find(hql, gzsjbh,gzxxbh);
		if(list!=null)
			return list.get(0);
		else
			return null;
	}

}
