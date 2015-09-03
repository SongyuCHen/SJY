package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TResource;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TRoleoperation;
import nju.software.sjy.model.xy.TRoleres;

public interface RoleDao
{
	List<TRole> getAllRoles();
	
	List<TResource> getResByRole(TRole role);
	
	TRole getRoleByRolename(String rolename);
	
	List<TOperation> getOperationByRole(TRole role);
	
	Integer getMaxRoleBh();
	
	void saveRole(TRole role);
	
	void updateRole(TRole role);
	
	void deleteRole(TRole role);
	
	Integer getMaxRoleoperationBh();
	
	void saveRoleoperation(TRoleoperation ro);
	
	void updateRoleoperation(TRoleoperation ro);
	
	void deleteRoleoperation(TRoleoperation ro);
	
	List<TOperation> getAllOperation();
	
	TOperation getOperationByName(String name);
	
	TRole getRoleByRolebh(int rolebh);
	
	TRoleoperation getRoByRoleOperation(TRole role, TOperation operation);
	
	List<TRoleoperation> getRoByRole(TRole role);
	
	TResource getResByName(String resName);
	
	List<TRole> getRoleByResource(TResource res);
	
	TOperation getOperationByNameRange(String name, String range);
	
	List<TResource> getResByType(String type);
	
	List<TResource> getAllResource();
	
	TRoleres getRrByRoleRes(TRole role, TResource res);
	
	Integer getMaxRoleResBh();
	
	void saveRoleRes(TRoleres roleRes);
	
	List<TRoleres> getRrByRole(TRole role);
	
	void deleteRoleRes(TRoleres roleRes);
	
	List<TOperation> getActiveOperation();
	
	List<TOperation> getOperationByRoleOperationname(TRole role, String operationname);
	
	List<TRole> getRoleByRolenamelist(List<String> rolenameList);
}
