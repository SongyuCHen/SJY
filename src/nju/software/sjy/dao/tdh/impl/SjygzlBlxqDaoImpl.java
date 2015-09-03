package nju.software.sjy.dao.tdh.impl;

import java.util.Date;
import java.util.List;

import nju.software.sjy.dao.tdh.SjygzlBlxqDao;
import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.model.tdh.SjygzlBlxq;
import nju.software.sjy.util.DateUtil;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SjygzlBlxqDaoImpl extends SjygzlBaseDaoImpl implements
		SjygzlBlxqDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<SjygzlBlxq> getBlxqByFyAndYhdm(String fjm, String yhdm,
			Date kssj, Date jssj)
	{	
		DetachedCriteria c = DetachedCriteria.forClass(SjygzlBlxq.class);
		c.add(Restrictions.ge("id.zzrq", kssj));
		c.add(Restrictions.le("id.zzrq", jssj));
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.eq("sjy", yhdm));
		// String hql =
		// "from SjygzlBlxq where id=? and sjy=? and zzrq>=? and zzrq<=?";
		List<SjygzlBlxq> list = (List<SjygzlBlxq>) getHibernateTemplate()
				.findByCriteria(c);
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public MGzs getBlsByFy(String fjm, String yhdm, Date kssj, Date jssj) {
		// TODO Auto-generated method stub
		String hql = "SELECT DISTINCT id.fydm,sjy,COUNT(wjzs) FROM SjygzlBlxq WHERE id.fydm = '"+fjm+"' and sjy = '"+yhdm+"' and id.zzrq >='"+ DateUtil.getFormatStr(kssj)+"' and id.zzrq <='"+ DateUtil.getFormatStr(jssj)+"' GROUP BY sjy";
		List<MGzs> blsList =  (List<MGzs>)getHibernateTemplate().find(hql);
		if(blsList!=null && blsList.size()>0){
			Object o = blsList.get(0);
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
