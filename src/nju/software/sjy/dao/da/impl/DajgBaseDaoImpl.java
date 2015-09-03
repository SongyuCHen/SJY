package nju.software.sjy.dao.da.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import nju.software.sjy.dao.da.DajgBaseDao;

public class DajgBaseDaoImpl implements DajgBaseDao
{
	@Autowired
	private HibernateTemplate hibernateTemplate_da;
	
	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate_da;
	}
}
