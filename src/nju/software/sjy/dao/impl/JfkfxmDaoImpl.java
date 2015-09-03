package nju.software.sjy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.JfkfxmDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;

@Repository
public class JfkfxmDaoImpl extends BaseDaoImpl implements JfkfxmDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<TJfkfxm> getJfkfxmByLbJfkf(TGypz lb, int jfkf)
	{
		String hql = "from TJfkfxm where lb=? and jfkf=?";
		
		List<TJfkfxm> list = (List<TJfkfxm>)getHibernateTemplate().find(hql,
				new Object[]{lb, jfkf});
		
		return list;
	}

}
