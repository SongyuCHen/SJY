package nju.software.sjy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.OperationDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TZtoperation;

@Repository
public class OperationDaoImpl extends BaseDaoImpl implements OperationDao
{

	@SuppressWarnings("unchecked")
	@Override
	public int getMaxBh()
	{
		String hql = "select max(bh) from TOperation";
		
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TOperation> getOperationByName(String name)
	{
		String hql = "from TOperation where name=?";
		
		List<TOperation> list = (List<TOperation>)getHibernateTemplate().find(hql, name);
		
		return list;
	}

	@Override
	public void saveOperation(TOperation operation)
	{
		getHibernateTemplate().save(operation);
	}

	@Override
	public void updateOperation(TOperation operation)
	{
		getHibernateTemplate().update(operation);
	}

	@Override
	public void deleteOperation(TOperation operation)
	{
		getHibernateTemplate().delete(operation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TOperation getOperationByBh(int bh)
	{
		String hql = "from TOperation where bh=?";
		
		List<TOperation> list = (List<TOperation>)getHibernateTemplate().find(hql, bh);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TOperation getOperationByNameRange(String name, TGypz range)
	{
		String hql = "from TOperation where name=? and range=?";
		List<TOperation> list = (List<TOperation>)getHibernateTemplate().find(hql, 
				new Object[]{name, range});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TOperation> getActiveOperation()
	{
		String hql = "from TOperation where status=1";
		
		List<TOperation> list = (List<TOperation>)getHibernateTemplate().find(hql);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TZtoperation getActiveZtoperationByZt(TGypz zt)
	{
		String hql = "from TZtoperation where status=1 and zt=?";
		
		List<TZtoperation> list = (List<TZtoperation>)getHibernateTemplate().find(hql, zt);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TZtoperation> getAllZtoperation()
	{
		String hql = "from TZtoperation";
		
		List<TZtoperation> list = (List<TZtoperation>)getHibernateTemplate().find(hql);
		
		return list;
	}

	@Override
	public void updateZtoperation(List<TZtoperation> tlist)
	{
		getHibernateTemplate().saveOrUpdateAll(tlist);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TZtoperation getZtoperationByOperation(TOperation operation)
	{
		String hql = "from TZtoperation where operation=?";
		
		List<TZtoperation> list = (List<TZtoperation>)getHibernateTemplate().find(hql, operation);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void updateZtoperation(TZtoperation operation)
	{
		getHibernateTemplate().update(operation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TZtoperation getZtoperationByZt(TGypz zt)
	{
		String hql = "from TZtoperation where zt=?";
		
		List<TZtoperation> list = (List<TZtoperation>)getHibernateTemplate().find(hql, zt);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TGypz getFinalZt()
	{
		String hql = "select z1.nextZt from TZtoperation z1 "
				+ "where z1.status=1 and z1.zobh=(select max(z2.zobh) from TZtoperation z2 where z2.status=1)";
		
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TGypz getZtByOperation(TOperation operation)
	{
		String hql = "select zt from TZtoperation where operation=?";
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql, operation);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TGypz getNextZt(TGypz zt)
	{
		String hql = "select z.nextZt from TZtoperation z where z.zt=?";
		List<TGypz> list = (List<TGypz>)getHibernateTemplate().find(hql, zt);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

}
