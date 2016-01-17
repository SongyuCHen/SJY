package nju.software.sjy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import nju.software.sjy.dao.UserDao;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao 
{

	@SuppressWarnings("unchecked")
	@Override
	public String getXmByRybh(int rybh)
	{
		String hql = "select t.xm from TUser t where t.rybh=?";
		List<String> list = (List<String>)getHibernateTemplate().find(hql, rybh);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TUser signIn(TUser user)
	{
		String hql = "from TUser u where u.username=? and u.password=?";
		List<TUser> list = getHibernateTemplate().find(hql, 
				new Object[]{user.getUsername(), user.getPassword()});
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getXmByBmbh(int bmbh)
	{
		String hql = "select xm from TUser where bmbh=?";
		List<String> list = (List<String>)getHibernateTemplate().find(hql, bmbh);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer getRybhByXmAndBmmc(String xm, String bmmc)
	{
		String hql = "select u.rybh from TUser u, TBm b where u.bmbh=b.bmbh and u.xm=? and b.bmmc=?";
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql,
				new Object[]{xm, bmmc});
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getAllRy() {
		String hql = "from TUser";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public TUser getRyByRybh(int rybh) {
		return getHibernateTemplate().get(TUser.class, rybh);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRyxmByBmbh(int bmbh)
	{
		String hql = "select xm from TUser where bmbh=?";
		List<String> list = (List<String>)getHibernateTemplate().find(hql, bmbh);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByBmbh(int bmbh)
	{
		String hql = "from TUser where bmbh=?";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, bmbh);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByBmmc(String bmmc)
	{
		String hql = "from TUser where bm.bmmc = ?";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, bmmc);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TUser getUserByXmAndBmmc(String xm, String bmmc)
	{
		String hql = "from TUser where xm=? and bm.bmmc = ?";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, 
				new Object[]{xm, bmmc});
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByBm(TBm bm)
	{
		String hql = "from TUser where bm=?";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, bm);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getXmByBmmc(String bmmc)
	{
		String hql = "select xm from TUser where bm.bmmc = ?";
		List<String> list = (List<String>)getHibernateTemplate().find(hql, bmmc);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TUser getUserByRybh(int rybh)
	{
		String hql = "from TUser where rybh=?";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, rybh);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TUser getUserByUsername(String username)
	{
		String hql = "from TUser where username=?";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, username);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public List<TRole> getRoleByUser(TUser user)
	{
		//String hql = "seletc role from TUserrole where user=?";
		
		//List<TRole> list = (List<TRole>)getHibernateTemplate().find(hql, user);
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getMaxBh() 
	{
		String hql = "select max(rybh) from TUser";
		
		List<Integer> list = (List<Integer>)getHibernateTemplate().find(hql);
		
		if(list != null && !list.isEmpty() && list.get(0) != null)
		{
			return list.get(0);
		}
		
		return 0;
	}

	@Override
	public void save(TUser user) 
	{
		getHibernateTemplate().save(user);
	}

	@Override
	public void update(TUser user) 
	{
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(TUser user) 
	{
		getHibernateTemplate().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByBmRole(TBm bm, TRole role) 
	{
		String hql = "from TUser where bm=? and role=?";
		
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, 
				new Object[]{bm, role});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TUser getUserByUsernamePassword(String username, String password)
	{
		String hql = "from TUser u where u.username=? and u.password=?";
		List<TUser> list = getHibernateTemplate().find(hql, 
				new Object[]{username, password});
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByBmlxmc(String bmlxmc)
	{
		String hql = "from TUser u where u.bm.bmlx.mc=?";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, bmlxmc);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByRole(TRole role)
	{
		String hql = "from TUser u where u.role=?";
		
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, role);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByRolelist(List<TRole> list)
	{
		if(list == null || list.isEmpty())
		{
			return null;
		}
		
		String hql = "from TUser u where u.role in (:rolelist)";
		
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setParameterList("rolelist", list);
		
		return (List<TUser>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByBmRolelist(TBm bm, List<TRole> list)
	{
		if(list == null || list.isEmpty())
		{
			return null;
		}
		
		String hql = "from TUser u where u.bm=? and u.role in (:rolelist)";
		
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setParameter(0, bm);
		query.setParameterList("rolelist", list);
		
		return (List<TUser>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByBmlistRolelist(List<TBm> bmlist, List<TRole> rolelist)
	{
		if(bmlist == null || bmlist.isEmpty() || rolelist == null || rolelist.isEmpty())
		{
			return null;
		}
		
		String hql = "from TUser u where u.bm in (:bmlist) and u.role in (:rolelist)";
		
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setParameterList("bmlist", bmlist);
		query.setParameterList("rolelist", rolelist);
		
		return (List<TUser>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserByRolelistExcludeQtbmlx(List<TRole> roleList, TGypz qtbmlx)
	{
		if(roleList == null || roleList.isEmpty())
		{
			return null;
		}
		
		String hql = "from TUser u where u.bm.bmlx<>? and u.role in (:roleList)";
		Query query = getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setParameter(0, qtbmlx);
		query.setParameterList("roleList", roleList);
		
		return (List<TUser>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TUser getUserByUserid(String userid)
	{
		String hql = "from TUser where userid=?";
		List<TUser> list = (List<TUser>)getHibernateTemplate().find(hql, userid);
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}


}
