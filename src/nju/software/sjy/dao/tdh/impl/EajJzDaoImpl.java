package nju.software.sjy.dao.tdh.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.tdh.EajJzDao;
import nju.software.sjy.model.tdh.EajJz;
import nju.software.sjy.util.DateUtil;

@Repository
public class EajJzDaoImpl extends SjygzlBaseDaoImpl implements EajJzDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EajJz>  getEajJzByAhdmAndZzsj(String ahdm, Date kssj, Date jssj) {
		// TODO Auto-generated method stub
		DetachedCriteria c = DetachedCriteria.forClass(EajJz.class);
		c.add(Restrictions.eq("id.ahdm", ahdm));
		c.add(Restrictions.ge("zzsj", kssj));
		Date jssj_nextday = DateUtil.getNextDay(jssj);
		c.add(Restrictions.lt("zzsj", jssj_nextday));
		c.add(Restrictions.in("wjgs",new Object[]{"doc","docx","rtf","DOC","Doc","wps"}));
		c.add(Restrictions.like("mc", "%笔录%"));
		List<EajJz> list = (List<EajJz>) getHibernateTemplate()
				.findByCriteria(c);
		return list;

	}

}
