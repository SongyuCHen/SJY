package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.UserDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserDao userDao;

	@Cacheable(value="sjyCache")
	public TUser signIn(TUser user)
	{
		return userDao.signIn(user);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getAllRy() 
	{
		return userDao.getAllRy();
	}

	@Cacheable(value="sjyCache")
	public TUser getRyByRybh(int rybh) 
	{
		return userDao.getRyByRybh(rybh);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getUserByBmmc(String bmmc)
	{
		return userDao.getUserByBmmc(bmmc);
	}

	@Cacheable(value="sjyCache")
	public TUser getUserByXmAndBmmc(String xm, String bmmc)
	{
		return userDao.getUserByXmAndBmmc(xm, bmmc);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getUserByBm(TBm bm)
	{
		return userDao.getUserByBm(bm);
	}

	@Cacheable(value="sjyCache")
	public List<String> getXmByBmmc(String bmmc)
	{
		return userDao.getXmByBmmc(bmmc);
	}

	@Cacheable(value="sjyCache")
	public TUser getUserByRybh(int rybh)
	{
		return userDao.getUserByRybh(rybh);
	}

	@Cacheable(value="sjyCache")
	public TUser getUserByUsername(String username)
	{
		return userDao.getUserByUsername(username);
	}

	@Cacheable(value="sjyCache")
	public List<TRole> getRoleByUser(TUser user)
	{
		return userDao.getRoleByUser(user);
	}

	@Override
	public Integer getMaxBh() 
	{
		return userDao.getMaxBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TUser user) 
	{
		userDao.save(user);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TUser user) 
	{
		userDao.update(user);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void delete(TUser user) 
	{
		userDao.delete(user);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getUserByBmRole(TBm bm, TRole role) 
	{
		return userDao.getUserByBmRole(bm, role);
	}

	@Cacheable(value="sjyCache")
	public TUser getUserByUsernamePassword(String username, String password)
	{
		return userDao.getUserByUsernamePassword(username, password);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getUserByRole(TRole role)
	{
		return userDao.getUserByRole(role);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getUserByRolelist(List<TRole> list)
	{
		return userDao.getUserByRolelist(list);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getUserByBmRolelist(TBm bm, List<TRole> list)
	{
		return userDao.getUserByBmRolelist(bm, list);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getUserByBmlistRolelist(List<TBm> bmlist, List<TRole> rolelist)
	{
		return userDao.getUserByBmlistRolelist(bmlist, rolelist);
	}

	@Cacheable(value="sjyCache")
	public List<TUser> getUserByRolelistExcludeQtbmlx(List<TRole> roleList, TGypz qtbmlx)
	{
		return userDao.getUserByRolelistExcludeQtbmlx(roleList, qtbmlx);
	}

	@Cacheable(value="sjyCache")
	public TUser getUserByUserid(String userid)
	{
		return userDao.getUserByUserid(userid);
	}
}
