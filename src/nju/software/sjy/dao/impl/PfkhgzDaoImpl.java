package nju.software.sjy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.PfkhgzDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TPfkhgz;

@Repository
public class PfkhgzDaoImpl extends BaseDaoImpl implements PfkhgzDao
{

	@SuppressWarnings("unchecked")
	@Override
	public int getMaxbh()
	{
		String hql = "select max(bh) from TPfkhgz";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TPfkhgz getPfkhgzByPfkhGz(TPfkh pfkh, TGypz gz)
	{
		String hql = "from TPfkhgz where pfkh=? and gz=?";
		List<TPfkhgz> list = (List<TPfkhgz>)getHibernateTemplate().find(hql,
				new Object[]{pfkh, gz});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void savePfkhgz(TPfkhgz pfkhgz)
	{
		getHibernateTemplate().save(pfkhgz);
	}

	@Override
	public void updatePfkhgz(TPfkhgz pfkhgz)
	{
		getHibernateTemplate().update(pfkhgz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TPfkhgz> getPfkhgzByPfkhlistGz(List<TPfkh> pfkhList, TGypz gz)
	{
		if(pfkhList == null || pfkhList.isEmpty())
		{
			return null;
		}
		String hql = "from TPfkhgz where gz=? and pfkh in (:pfkhList)";
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setParameter(0, gz);
		query.setParameterList("pfkhList", pfkhList);
		
		return (List<TPfkhgz>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TPfkhgz> getPfkhgzByPfkh(TPfkh pfkh)
	{
		String hql = "from TPfkhgz where pfkh=?";
		List<TPfkhgz> list = (List<TPfkhgz>)getHibernateTemplate().find(hql, pfkh);
		
		return list;
	}

}
