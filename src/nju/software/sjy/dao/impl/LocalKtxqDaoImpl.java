package nju.software.sjy.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.LocalKtxqDao;
import nju.software.sjy.model.xy.LocalKtxq;
@Repository
public class LocalKtxqDaoImpl extends BaseDaoImpl implements LocalKtxqDao {

	@SuppressWarnings("unchecked")
	@Override
	public LocalKtxq getLocalKtxqByFydm(String fjm, String ahdm, String ktrq,
			String kssj) {
		// TODO Auto-generated method stub
		DetachedCriteria c = DetachedCriteria.forClass(LocalKtxq.class);
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.eq("id.ahdm", ahdm));
		c.add(Restrictions.eq("id.ktrq", ktrq));
		c.add(Restrictions.eq("id.kssj", kssj));
		List<LocalKtxq> list = getHibernateTemplate().findByCriteria(c);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public void updateLocalKtxq(LocalKtxq localKtxq) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(localKtxq);
	}

	@Override
	public void saveLocalKtxq(LocalKtxq localKtxq) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(localKtxq);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalKtxq> getLocalKtxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj) {
		// TODO Auto-generated method stub
		DetachedCriteria c = DetachedCriteria.forClass(LocalKtxq.class);
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.or(Restrictions.eq("sjy", yhdm), Restrictions.eq("xzsjy", yhdm)));
		c.add(Restrictions.ge("id.ktrq", kssj));
		c.add(Restrictions.le("id.ktrq", jssj));
		return getHibernateTemplate().findByCriteria(c);
	}

}
