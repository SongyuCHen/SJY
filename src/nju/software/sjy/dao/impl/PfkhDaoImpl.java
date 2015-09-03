package nju.software.sjy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.dao.PfkhDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TUser;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PfkhDaoImpl extends BaseDaoImpl implements PfkhDao 
{

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxPfkhBh()
	{
		String hql = "select max(bh) from TPfkh";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void save(TPfkh tpfkh)
	{
		getHibernateTemplate().save(tpfkh);
	}

	@Override
	public void update(TPfkh tpfkh)
	{
		getHibernateTemplate().update(tpfkh);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TPfkh getPfkhByRybhNfJd(int rybh, int nf, int jd)
	{
		String hql = "from TPfkh where rybh=? and nf=? and jd=?";
		
		List<TPfkh> list = (List<TPfkh>)getHibernateTemplate().find(hql, 
				new Object[]{rybh, nf, jd});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TPfkh> getAllPfkh()
	{
		String hql = "from TPfkh";
		List<TPfkh> list = (List<TPfkh>)getHibernateTemplate().find(hql);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TPfkh getPfkhByUserNfJd(TUser user, int nf, int jd)
	{
		String hql = "from TPfkh where user=? and nf=? and jd=?";
		List<TPfkh> list = (List<TPfkh>)getHibernateTemplate().find(hql, 
				new Object[]{user, nf, jd});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getTopUserByNfJd(int topNum, int nf, int jd) {
		String hql = "from TPfkh where nf=? and jd=?";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql).setParameter(0, nf).setParameter(1, jd);
		query.setFirstResult(0);
		query.setMaxResults(topNum);
		List<TPfkh> list = (List<TPfkh>)query.list();
		List<TUser> users = new ArrayList<TUser>();
		for(TPfkh pfkh:list){
			users.add(pfkh.getUser());
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TPfkh> getPfkhByBmlistNfJd(List<TBm> bmList, int nf, int jd)
	{
		if(bmList == null || bmList.isEmpty())
		{
			return null;
		}
		
		String hql = "from TPfkh p where p.nf=? and p.jd=? and p.user.bm in (:bmList)";
		
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		
		query.setParameter(0, nf);
		query.setParameter(1, jd);
		query.setParameterList("bmList", bmList);
		
		return (List<TPfkh>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TPfkh> getPfkhByUserlistNfJd(List<TUser> userList, int year,
			int quarter)
	{
		if(userList == null || userList.isEmpty())
		{
			return null;
		}
		
		String hql = "from TPfkh p where p.nf=? and p.jd=? and p.user in (:userList)";
		
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		
		query.setParameter(0, year);
		query.setParameter(1, quarter);
		query.setParameterList("userList", userList);
		
		return (List<TPfkh>)query.list();
	}
}
