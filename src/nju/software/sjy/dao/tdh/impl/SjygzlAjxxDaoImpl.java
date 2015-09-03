package nju.software.sjy.dao.tdh.impl;

import java.util.List;

import nju.software.sjy.dao.tdh.SjygzlAjxxDao;
import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.model.tdh.SjygzlAjxx;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
@Repository
public class SjygzlAjxxDaoImpl extends SjygzlBaseDaoImpl implements SjygzlAjxxDao 
{

	@SuppressWarnings("unchecked")
	@Override
	public List<SjygzlAjxx> getAjxxByFydmAndGdjsrq(String fydm, String yhdm, String kssj, String jssj) 
	{
		DetachedCriteria c = DetachedCriteria.forClass(SjygzlAjxx.class);
		c.add(Restrictions.eq("id.fydm", fydm));
		c.add(Restrictions.eq("sjy", yhdm));
		c.add(Restrictions.ge("gdjsrq", kssj));
		c.add(Restrictions.le("gdjsrq", jssj));
		List<SjygzlAjxx> list = (List<SjygzlAjxx>)getHibernateTemplate().findByCriteria(c);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SjygzlAjxx> getAjxxByFydmAndYhdm(String fydm, String yhdm) {
		// TODO Auto-generated method stub
		DetachedCriteria c = DetachedCriteria.forClass(SjygzlAjxx.class);
		c.add(Restrictions.eq("id.fydm", fydm));
		c.add(Restrictions.eq("sjy", yhdm));
		List<SjygzlAjxx> list = (List<SjygzlAjxx>)getHibernateTemplate().findByCriteria(c);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MGzs getAjsByFydmAndYhdm(String fydm, String yhdm, String kssj,
			String jssj) {
		String hql = "SELECT DISTINCT id.fydm,sjy,COUNT(*) FROM SjygzlAjxx where  id.fydm = '"+fydm+"' and sjy='"+yhdm+"' and jarq>='"+kssj+"' and jarq<= '"+jssj+"' GROUP BY sjy";
		List<MGzs> ajsList =  (List<MGzs>)getHibernateTemplate().find(hql);
		if(ajsList!=null && ajsList.size()>0){
			Object o = ajsList.get(0);
			Object[] i = (Object[]) o;
			MGzs mGzs = new MGzs();
			mGzs.setFjm(i[0].toString());
			mGzs.setYhdm(i[1].toString());
			mGzs.setGzs(new Integer(i[2].toString()));
			return mGzs;
		}
		else 
			return new MGzs(fydm,yhdm,0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SjygzlAjxx> getWGDAjxxByFydmAndYhdm(String fydm, String yhdm,
			String kssj) {
		// TODO Auto-generated method stub

		DetachedCriteria c = DetachedCriteria.forClass(SjygzlAjxx.class);
		c.add(Restrictions.eq("id.fydm", fydm));
		c.add(Restrictions.eq("sjy", yhdm));
		c.add(Restrictions.or(Restrictions.ge("gdjsrq", kssj), Restrictions.or(Restrictions.isNull("gdjsrq"), Restrictions.eq("gdjsrq",""))));
		List<SjygzlAjxx> list = (List<SjygzlAjxx>)getHibernateTemplate().findByCriteria(c);
		return list;
	}

}
