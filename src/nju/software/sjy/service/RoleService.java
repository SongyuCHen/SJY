package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TResource;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TRoleoperation;
import nju.software.sjy.model.xy.TRoleres;

public interface RoleService
{
	/**
	 * 获取所有的角色
	 */
	List<TRole> getAllRoles();
	
	/**
	 * 获取角色的所有资源
	 */
	List<TResource> getResByRole(TRole role);
	
	/**
	 * 根据角色名获取角色
	 */
	TRole getRoleByRolename(String rolename);
	
	/**
	 * 获取角色的所有操作
	 */
	List<TOperation> getOperationByRole(TRole role);
	
	/**
	 * 获取最大的角色编号
	 */
	Integer getMaxRoleBh();
	
	void saveRole(TRole role);
	
	void updateRole(TRole role);
	
	void deleteRole(TRole role);
	
	/**
	 * 获取Roleoperation表中的最大编号
	 */
	Integer getMaxRoleoperationBh();
	
	void saveRoleoperation(TRoleoperation ro);
	
	void updateRoleoperation(TRoleoperation ro);
	
	void deleteRoleoperation(TRoleoperation ro);
	
	/**
	 * 根据角色编号获取角色
	 */
	TRole getRoleByRolebh(int rolebh);
	
	/**
	 * 根据角色和操作到TRoleoperation表中查询相应的记录
	 */
	TRoleoperation getRoByRoleOperation(TRole role, TOperation operation);
	
	/**
	 * 根据角色查询TRoleoperation
	 */
	List<TRoleoperation> getRoByRole(TRole role);
	
	TResource getResByName(String resName);
	
	List<TResource> getResByType(String type);
	
	List<TResource> getAllResource();
	
	/**
	 * 根据角色和资源查询TRoleres记录
	 */
	TRoleres getRrByRoleRes(TRole role, TResource res);
	
	Integer getMaxRoleResBh();
	
	void saveRoleRes(TRoleres roleRes);
	
	List<TRoleres> getRrByRole(TRole role);
	
	void deleteRoleRes(TRoleres roleRes);
	
	List<TOperation> getOperationByRoleOperationname(TRole role, String operationname);
	
	List<TRole> getRoleByRolenamelist(List<String> rolenameList);
}
