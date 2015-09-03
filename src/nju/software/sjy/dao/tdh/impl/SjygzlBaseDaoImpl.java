package nju.software.sjy.dao.tdh.impl;

import nju.software.sjy.dao.tdh.SjygzlBaseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SjygzlBaseDaoImpl implements SjygzlBaseDao
{
	@Autowired
	private HibernateTemplate hibernateTemplate_tdh;
	
	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate_tdh;
	}
}
