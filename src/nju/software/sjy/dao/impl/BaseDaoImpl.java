package nju.software.sjy.dao.impl;

import nju.software.sjy.dao.BaseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDaoImpl implements BaseDao
{
	@Autowired
	private HibernateTemplate hibernateTemplate_xy;
	
	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate_xy;
	}

}