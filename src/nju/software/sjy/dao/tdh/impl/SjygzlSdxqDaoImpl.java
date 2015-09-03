package nju.software.sjy.dao.tdh.impl;

import java.util.List;

import nju.software.sjy.dao.tdh.SjygzlSdxqDao;
import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.model.tdh.SjygzlSdxq;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SjygzlSdxqDaoImpl extends SjygzlBaseDaoImpl implements
		SjygzlSdxqDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<SjygzlSdxq> getSdxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		DetachedCriteria c = DetachedCriteria.forClass(SjygzlSdxq.class);
		c.add(Restrictions.or(Restrictions.and(Restrictions.ge("sdrq", kssj), Restrictions.le("sdrq", jssj)), Restrictions.and(Restrictions.ge("ysdrq", kssj), Restrictions.le("ysdrq", jssj))));
//		c.add(Restrictions.ge("sdrq", kssj));
//		c.add(Restrictions.le("sdrq", jssj));
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.eq("sjy", yhdm));
		// String hql =
		// "from SjygzlSdxq where id=? and sjy=? and sdrq>=? and sdrq<=?";
		List<SjygzlSdxq> list = (List<SjygzlSdxq>) getHibernateTemplate()
				.findByCriteria(c);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MGzs getSdsByFy(String fjm, String yhdm, String kssj, String jssj) {
		// TODO Auto-generated method stub
		String hql = "SELECT DISTINCT id.fydm,sjy,COUNT(*) FROM SjygzlSdxq WHERE id.fydm = '"+fjm+"' and sjy = '"+yhdm+"' and ((sdrq >='"+ kssj +"' and sdrq <='"+ jssj +"') OR (ysdrq >='"+ kssj +"' and ysdrq <='"+ jssj +"')) GROUP BY sjy";
		List<MGzs> sdsList =  (List<MGzs>)getHibernateTemplate().find(hql);
		if(sdsList!=null && sdsList.size()>0){
			Object o = sdsList.get(0);
			Object[] i = (Object[]) o;
			MGzs mGzs = new MGzs();
			mGzs.setFjm(i[0].toString());
			mGzs.setYhdm(i[1].toString());
			mGzs.setGzs(new Integer(i[2].toString()));
			return mGzs;
		}else 
			return new MGzs(fjm,yhdm,0);
	}

}
