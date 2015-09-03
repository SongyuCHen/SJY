package nju.software.sjy.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.LogDao;
import nju.software.sjy.model.xy.TLog;

@Repository
public class LogDaoImpl extends BaseDaoImpl implements LogDao{

	@Override
	public Integer getMaxBh() {
		// TODO Auto-generated method stub
		String hql = "select max(bh) from TLog";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void saveLog(TLog log) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(log);		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TLog> getLogBySj(Date sj) {
		// TODO Auto-generated method stub
		String hql = "from TLog where sj =?";
		List<TLog> logs = (List<TLog>)getHibernateTemplate().find(hql,sj);
		return logs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TLog> getAllLog() {
		// TODO Auto-generated method stub
		String hql = "from TLog";
		List<TLog> logs = (List<TLog>)getHibernateTemplate().find(hql);
		return logs;
	}

}
