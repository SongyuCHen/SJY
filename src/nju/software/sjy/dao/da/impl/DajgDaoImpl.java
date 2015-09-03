package nju.software.sjy.dao.da.impl;

import java.util.List;

import nju.software.sjy.dao.da.DajgDao;
import nju.software.sjy.model.da.ViewDajgSsfzxx;

import org.springframework.stereotype.Repository;

@Repository
public class DajgDaoImpl extends DajgBaseDaoImpl implements DajgDao
{

	@SuppressWarnings("unchecked")
	@Override
	public ViewDajgSsfzxx getWjysByAhdm(String ahdm)
	{
		String hql = "from ViewDajgSsfzxx where ahdm=?";
		List<ViewDajgSsfzxx> list = (List<ViewDajgSsfzxx>) getHibernateTemplate()
				.find(hql, ahdm);
		if (list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;

	}

}
