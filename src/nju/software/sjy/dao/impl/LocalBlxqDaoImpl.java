package nju.software.sjy.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.LocalBlxqDao;
import nju.software.sjy.model.xy.LocalBlxq;
@Repository
public class LocalBlxqDaoImpl extends BaseDaoImpl implements LocalBlxqDao {

	@Override
	public void update(LocalBlxq localBlxq) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(localBlxq);
	}

	@Override
	public void save(LocalBlxq localBlxq) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(localBlxq);
	}

	@SuppressWarnings("unchecked")
	@Override
	public LocalBlxq getBlxqById(String fjm, String ahdm, String xh) {
		// TODO Auto-generated method stub
		DetachedCriteria c = DetachedCriteria.forClass(LocalBlxq.class);
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.eq("id.ahdm", ahdm));
		c.add(Restrictions.eq("id.xh", xh));
		List<LocalBlxq> list = getHibernateTemplate().findByCriteria(c);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

}
