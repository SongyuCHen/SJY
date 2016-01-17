package nju.software.sjy.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.LocalZdjzDao;
import nju.software.sjy.model.xy.LocalZdjz;
@Repository
public class LocalZdjzDaoImpl extends BaseDaoImpl implements LocalZdjzDao {

	@Override
	public void save(LocalZdjz localZdjz) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(localZdjz);
	}

	@Override
	public void update(LocalZdjz localZdjz) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(localZdjz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public LocalZdjz getZdjzById(String fjm, String ahdm) {
		// TODO Auto-generated method stub
		DetachedCriteria c = DetachedCriteria.forClass(LocalZdjz.class);
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.eq("id.ahdm", ahdm));
		List<LocalZdjz> list = getHibernateTemplate().findByCriteria(c);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

}
