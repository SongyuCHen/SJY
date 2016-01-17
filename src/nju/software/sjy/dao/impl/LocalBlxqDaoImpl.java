package nju.software.sjy.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.LocalBlxqDao;
import nju.software.sjy.model.xy.LocalBlxq;
import nju.software.sjy.util.DateUtil;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBlxq> getLocalBlxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj) {
		// TODO Auto-generated method stub
		Date ksrq = DateUtil.getDateByStr(kssj);
		Date jsrq = DateUtil.getDateByStr(jssj);
		DetachedCriteria c = DetachedCriteria.forClass(LocalBlxq.class);
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.or(Restrictions.eq("sjy", yhdm), Restrictions.eq("xzsjy", yhdm)));
		c.add(Restrictions.ge("zzrq", ksrq));
		c.add(Restrictions.le("zzrq", jsrq));
		return getHibernateTemplate().findByCriteria(c);
	}

}
