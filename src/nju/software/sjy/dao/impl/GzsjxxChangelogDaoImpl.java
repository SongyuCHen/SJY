package nju.software.sjy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.GzsjxxChangelogDao;
import nju.software.sjy.model.xy.TGzsjChangelog;
import nju.software.sjy.model.xy.TGzsjxx;
@Repository
public class GzsjxxChangelogDaoImpl extends BaseDaoImpl implements
		GzsjxxChangelogDao {

	@Override
	public void save(TGzsjChangelog changelog) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(changelog);
	}

	@Override
	public void update(TGzsjChangelog changelog) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(changelog);
	}

	@Override
	public void delete(TGzsjChangelog changelog) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(changelog);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBh() {
		// TODO Auto-generated method stub
		String hql = "select max(bh) from TGzsjxxChangelog";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGzsjChangelog> getGzsjxxChangelogByGzsjxx(TGzsjxx gzsjxx) {
		// TODO Auto-generated method stub
		String hql = "select sz from TGzsjxxChangelog where gzsjxx=? ";
		
		List<TGzsjChangelog> list = (List<TGzsjChangelog>)getHibernateTemplate().find(hql, gzsjxx);
		
		return list;
	}

}
