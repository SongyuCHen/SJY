package nju.software.sjy.dao.tdh.impl;

import java.util.List;

import nju.software.sjy.dao.tdh.SjygzlKtxqDao;
import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.mapper.MKtjl;
import nju.software.sjy.model.tdh.SjygzlKtxq;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SjygzlKtxqDaoImpl extends SjygzlBaseDaoImpl implements
		SjygzlKtxqDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<SjygzlKtxq> getKtxqByFyAndYhdm(String fjm, String yhdm,
			String kssj, String jssj)
	{
		DetachedCriteria c = DetachedCriteria.forClass(SjygzlKtxq.class);
		c.add(Restrictions.ge("id.ktrq", kssj));
		c.add(Restrictions.le("id.ktrq", jssj));
		c.add(Restrictions.eq("id.fydm", fjm));
		c.add(Restrictions.eq("sjy", yhdm));

		List<SjygzlKtxq> list = (List<SjygzlKtxq>) getHibernateTemplate()
				.findByCriteria(c);
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public MGzs getKtsByFy(String fjm, String yhdm, String kssj, String jssj) {
		// TODO Auto-generated method stub
		String hql = "SELECT DISTINCT id.fydm,sjy,COUNT(*) FROM SjygzlKtxq where  id.fydm = '"+fjm+"' and sjy='"+yhdm+"' and id.ktrq>='"+kssj+"' and id.ktrq<= '"+jssj+"' GROUP BY sjy";
		List<MGzs> ktsList =  (List<MGzs>)getHibernateTemplate().find(hql);
		if(ktsList!=null && ktsList.size()>0){
			Object o = ktsList.get(0);
			Object[] i = (Object[]) o;
			MGzs mGzs = new MGzs();
			mGzs.setFjm(i[0].toString());
			mGzs.setYhdm(i[1].toString());
			mGzs.setGzs(new Integer(i[2].toString()));
			return mGzs;
		}else 
			return new MGzs(fjm,yhdm,0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MGzs getKtljsjByFy(String fjm, String yhdm, String kssj, String jssj) {
		// TODO Auto-generated method stub
		String hql = "SELECT id.kssj,jssj FROM SjygzlKtxq where  id.fydm = '"+fjm+"' and sjy='"+yhdm+"' and id.ktrq>='"+kssj+"' and id.ktrq<= '"+jssj+"'";
		List<MKtjl> ktjlList = (List<MKtjl>)getHibernateTemplate().find(hql);
		String ks="",js="";
		int fzs = 0,ksHour,ksMin,jsHour,jsMin;
		String ksSplit[],jsSplit[];
		for(Object o:ktjlList){
			Object[] i = (Object[]) o;
			ks = i[0].toString();
			js = i[1].toString();
			if(!ks.trim().equals("") && !js.trim().equals("")){
				ksSplit = ks.split(":");
				jsSplit = js.split(":");
				if(ksSplit[0].trim().equals(""))
					ksHour = 0;
				else
					ksHour = Integer.valueOf(ksSplit[0]);
				
				if(jsSplit[0].trim().equals(""))
					jsHour = 0;
				else
					jsHour = Integer.valueOf(jsSplit[0]);
				
				if(ksSplit[1].trim().equals(""))
					ksMin = 0;
				else
					ksMin = Integer.valueOf(ksSplit[1]);
				
				if(jsSplit[1].trim().equals(""))
					jsMin = 0;
				else
					jsMin = Integer.valueOf(jsSplit[1]);

				fzs += (jsHour-ksHour) * 60 + (jsMin - ksMin);
			}			
		}
		MGzs ktljsj = new MGzs(fjm,yhdm,fzs);
		return ktljsj;
	}


}
