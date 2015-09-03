package nju.software.sjy.dao.impl;

import java.util.List;

import nju.software.sjy.dao.BmDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;

import org.springframework.stereotype.Repository;

@Repository
public class BmDaoImpl extends BaseDaoImpl implements BmDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<TBm> getAllBm()
	{
		String hql = "from TBm";
		List<TBm> list = (List<TBm>)getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserXmOfBm(String bmmc)
	{
		String hql = "select u.xm "
				+ "from TBm b, TUser u "
				+ "where b.bmbh=u.bmbh and b.bmmc=?";
		
		List<String> list = (List<String>)getHibernateTemplate().find(hql, bmmc);
		
		return list;
	}

	@Override
	public TBm getBmByBmbh(int bmbh) {
		return getHibernateTemplate().get(TBm.class, bmbh);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TBm getBmByBmmc(String bmmc)
	{
		String hql = "from TBm where bmmc=?";
		List<TBm> list = (List<TBm>)getHibernateTemplate().find(hql, bmmc);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TBm> getBmByBmlx(TGypz bmlx)
	{
		String hql = "from TBm where bmlx=?";
		List<TBm> list = (List<TBm>)getHibernateTemplate().find(hql, bmlx);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBmbh()
	{
		String hql = "select max(bmbh) from TBm";
		
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void saveBm(TBm bm)
	{
		getHibernateTemplate().save(bm);
	}

	@Override
	public void updateBm(TBm bm)
	{
		getHibernateTemplate().update(bm);
	}

	@Override
	public void deleteBm(TBm bm)
	{
		getHibernateTemplate().delete(bm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TBm> getBmExcludeBmlx(TGypz bmlx)
	{
		String hql = "from TBm where bmlx<>?";
		List<TBm> list = (List<TBm>)getHibernateTemplate().find(hql, bmlx);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TBm getBmByBmid(String bmid)
	{
		String hql = "from TBm where bmid=?";
		List<TBm> list = (List<TBm>)getHibernateTemplate().find(hql, bmid);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

}
