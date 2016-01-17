package nju.software.sjy.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.LocalSdxqDao;
import nju.software.sjy.model.xy.LocalSdxq;
@Repository
public class LocalSdxqDaoImpl extends BaseDaoImpl implements LocalSdxqDao {

	@SuppressWarnings("unchecked")
	@Override
	public LocalSdxq getSdxqById(String fjm, String ahdm, String sdrq,
			String sdddsr) {
		// TODO Auto-generated method stub
		DetachedCriteria c = DetachedCriteria.forClass(LocalSdxq.class);
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.eq("id.ahdm", ahdm));
		c.add(Restrictions.eq("id.sdrq", sdrq));
		c.add(Restrictions.eq("id.sddsr", sdddsr));
		List<LocalSdxq> list = getHibernateTemplate().findByCriteria(c);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public void save(LocalSdxq localSdxq) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(localSdxq);
	}

	@Override
	public void update(LocalSdxq localSdxq) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(localSdxq);
	}

	@Override
	public List<LocalSdxq> getLocalSdxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj) {
		// TODO Auto-generated method stub
		DetachedCriteria c = DetachedCriteria.forClass(LocalSdxq.class);
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.or(Restrictions.eq("sjy", yhdm), Restrictions.eq("xzsjy", yhdm)));
		c.add(Restrictions.or(Restrictions.and(Restrictions.ge("sdrq", kssj), Restrictions.le("sdrq", jssj)), Restrictions.and(Restrictions.ge("ysdrq", kssj), Restrictions.le("ysdrq", jssj))));
		
		return getHibernateTemplate().findByCriteria(c);
	}

}
