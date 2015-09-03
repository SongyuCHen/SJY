package nju.software.sjy.dao.impl;

import java.util.List;

import nju.software.sjy.dao.RoleDao;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TResource;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TRoleoperation;
import nju.software.sjy.model.xy.TRoleres;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<TRole> getAllRoles()
	{
		String hql = "from TRole order by levels asc";
		
		List<TRole> list = (List<TRole>)getHibernateTemplate().find(hql);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TResource> getResByRole(TRole role)
	{
		String hql = "select rr.res from TRoleres rr where rr.role=?";
		
		List<TResource> list = (List<TResource>)getHibernateTemplate().find(hql, role);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TRole getRoleByRolename(String rolename) 
	{
		String hql = "from TRole where rolename=?";
		List<TRole> list = (List<TRole>)getHibernateTemplate().find(hql, rolename);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TOperation> getOperationByRole(TRole role) 
	{
		String hql = "select ro.operation from TRoleoperation ro where ro.role=?";
		
		List<TOperation> list = (List<TOperation>)getHibernateTemplate().find(hql, role);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxRoleBh() 
	{
		String hql = "select max(rolebh) from TRole";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void saveRole(TRole role) 
	{
		getHibernateTemplate().save(role);
	}

	@Override
	public void updateRole(TRole role) 
	{
		getHibernateTemplate().update(role);
	}

	@Override
	public void deleteRole(TRole role) 
	{
		getHibernateTemplate().delete(role);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxRoleoperationBh() 
	{
		String hql = "select max(robh) from TRoleoperation";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void saveRoleoperation(TRoleoperation ro) 
	{
		getHibernateTemplate().save(ro);
	}

	@Override
	public void updateRoleoperation(TRoleoperation ro) 
	{
		getHibernateTemplate().update(ro);
	}

	@Override
	public void deleteRoleoperation(TRoleoperation ro) 
	{
		getHibernateTemplate().delete(ro);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TOperation> getAllOperation()
	{
		String hql = "from TOperation";
		
		List<TOperation> list = (List<TOperation>)getHibernateTemplate().find(hql);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TOperation getOperationByName(String name)
	{
		String hql = "from TOperation where name=?";
		
		List<TOperation> list = (List<TOperation>)getHibernateTemplate().find(hql, name);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TRole getRoleByRolebh(int rolebh)
	{
		String hql = "from TRole where rolebh=?";
		List<TRole> list = (List<TRole>)getHibernateTemplate().find(hql, rolebh);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TRoleoperation getRoByRoleOperation(TRole role, TOperation operation)
	{
		String hql = "from TRoleoperation ro where ro.role=? and ro.operation=?";
		
		List<TRoleoperation> list = (List<TRoleoperation>)getHibernateTemplate().find(hql, 
				new Object[]{role ,operation});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TRoleoperation> getRoByRole(TRole role)
	{
		String hql = "from TRoleoperation ro where ro.role=?";
		List<TRoleoperation> list = (List<TRoleoperation>)getHibernateTemplate().find(hql, role);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TResource getResByName(String resName)
	{
		String hql = "from TResource where resname=?";
		List<TResource> list = (List<TResource>)getHibernateTemplate().find(hql, resName);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TRole> getRoleByResource(TResource res)
	{
		String hql = "select rr.role from TRoleres rr where rr.res=?";
		
		List<TRole> list = (List<TRole>)getHibernateTemplate().find(hql, res);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TOperation getOperationByNameRange(String name, String range)
	{
		String hql = "from TOperation where name=? and range.mc=?";
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
	public List<TResource> getResByType(String type)
	{
		String hql = "from TResource where type=?";
		
		List<TResource> list = (List<TResource>)getHibernateTemplate().find(hql, type);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TResource> getAllResource()
	{
		String hql = "from TResource";
		
		List<TResource> list = (List<TResource>)getHibernateTemplate().find(hql);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TRoleres getRrByRoleRes(TRole role, TResource res)
	{
		String hql = "from TRoleres where role=? and res=?";
		
		List<TRoleres> list = (List<TRoleres>)getHibernateTemplate().find(hql, 
				new Object[]{role, res});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxRoleResBh()
	{
		String hql = "select max(rrbh) from TRoleres";
		
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void saveRoleRes(TRoleres roleRes)
	{
		getHibernateTemplate().save(roleRes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TRoleres> getRrByRole(TRole role)
	{
		String hql = "from TRoleres rr where rr.role=?";
		
		List<TRoleres> list = (List<TRoleres>)getHibernateTemplate().find(hql, role);
		
		return list;
	}

	@Override
	public void deleteRoleRes(TRoleres roleRes)
	{
		getHibernateTemplate().delete(roleRes);
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
	public List<TOperation> getOperationByRoleOperationname(TRole role,
			String operationname)
	{
		String hql = "select ro.operation from TRoleoperation ro where ro.role = ? and ro.operation.name=?";
		
		List<TOperation> list = (List<TOperation>)getHibernateTemplate().find(hql, 
				new Object[]{role, operationname});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TRole> getRoleByRolenamelist(List<String> rolenameList)
	{
		if(rolenameList == null || rolenameList.isEmpty())
		{
			return null;
		}
		
		String hql = "from TRole r where r.rolename in (:rolenameList)";
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setParameterList("rolenameList", rolenameList);
		
		return (List<TRole>)query.list();
	}
	
	
	
	
	
}
