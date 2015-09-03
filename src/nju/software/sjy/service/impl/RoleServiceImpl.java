package nju.software.sjy.service.impl;

import java.util.List;

import nju.software.sjy.dao.RoleDao;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TResource;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TRoleoperation;
import nju.software.sjy.model.xy.TRoleres;
import nju.software.sjy.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService
{
	@Autowired
	private RoleDao roleDao;

	@Cacheable(value="sjyCache")
	public List<TRole> getAllRoles()
	{
		return roleDao.getAllRoles();
	}

	@Cacheable(value="sjyCache")
	public List<TResource> getResByRole(TRole role)
	{
		return roleDao.getResByRole(role);
	}

	@Cacheable(value="sjyCache")
	public TRole getRoleByRolename(String rolename) 
	{
		return roleDao.getRoleByRolename(rolename);
	}

	@Cacheable(value="sjyCache")
	public List<TOperation> getOperationByRole(TRole role) 
	{
		return roleDao.getOperationByRole(role);
	}

	@Override
	public Integer getMaxRoleBh() 
	{
		return roleDao.getMaxRoleBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void saveRole(TRole role) 
	{
		roleDao.saveRole(role);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateRole(TRole role) 
	{
		roleDao.updateRole(role);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteRole(TRole role) 
	{
		roleDao.deleteRole(role);
	}

	@Override
	public Integer getMaxRoleoperationBh() 
	{
		return roleDao.getMaxRoleoperationBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void saveRoleoperation(TRoleoperation ro) 
	{
		roleDao.saveRoleoperation(ro);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateRoleoperation(TRoleoperation ro) 
	{
		roleDao.updateRoleoperation(ro);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteRoleoperation(TRoleoperation ro) 
	{
		roleDao.deleteRoleoperation(ro);
	}

	@Cacheable(value="sjyCache")
	public TRole getRoleByRolebh(int rolebh)
	{
		return roleDao.getRoleByRolebh(rolebh);
	}

	@Cacheable(value="sjyCache")
	public TRoleoperation getRoByRoleOperation(TRole role, TOperation operation)
	{
		return roleDao.getRoByRoleOperation(role, operation);
	}

	@Cacheable(value="sjyCache")
	public List<TRoleoperation> getRoByRole(TRole role)
	{
		return roleDao.getRoByRole(role);
	}

	@Cacheable(value="sjyCache")
	public TResource getResByName(String resName)
	{
		return roleDao.getResByName(resName);
	}

	@Cacheable(value="sjyCache")
	public List<TResource> getResByType(String type)
	{
		return roleDao.getResByType(type);
	}

	@Cacheable(value="sjyCache")
	public List<TResource> getAllResource()
	{
		return roleDao.getAllResource();
	}

	@Cacheable(value="sjyCache")
	public TRoleres getRrByRoleRes(TRole role, TResource res)
	{
		return roleDao.getRrByRoleRes(role, res);
	}

	@Override
	public Integer getMaxRoleResBh()
	{
		return roleDao.getMaxRoleResBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void saveRoleRes(TRoleres roleRes)
	{
		roleDao.saveRoleRes(roleRes);
	}

	@Cacheable(value="sjyCache")
	public List<TRoleres> getRrByRole(TRole role)
	{
		return roleDao.getRrByRole(role);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteRoleRes(TRoleres roleRes)
	{
		roleDao.deleteRoleRes(roleRes);
	}

	@Cacheable(value="sjyCache")
	public List<TOperation> getOperationByRoleOperationname(TRole role,
			String operationname)
	{
		return roleDao.getOperationByRoleOperationname(role, operationname);
	}

	@Cacheable(value="sjyCache")
	public List<TRole> getRoleByRolenamelist(List<String> rolenameList)
	{
		return roleDao.getRoleByRolenamelist(rolenameList);
	}


	
	
}
