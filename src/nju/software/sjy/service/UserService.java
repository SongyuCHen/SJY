package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.mapper.MSjy;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;

public interface UserService 
{
	
	TUser signIn(TUser user);

	List<TUser> getAllRy();

	TUser getRyByRybh(int rybh);
	
	List<TUser> getUserByBmmc(String bmmc);
	
	TUser getUserByXmAndBmmc(String xm, String bmmc);
	
	List<TUser> getUserByBm(TBm bm);
	
	List<String> getXmByBmmc(String bmmc);
	
	TUser getUserByRybh(int rybh);
	
	TUser getUserByUsername(String username);
	
	List<TRole> getRoleByUser(TUser user);
	
	Integer getMaxBh();
	
	void save(TUser user);
	
	void update(TUser user);
	
	void delete(TUser user);
	
	List<TUser> getUserByBmRole(TBm bm, TRole role);
	
	TUser getUserByUsernamePassword(String username, String password);
	
	List<TUser> getUserByRole(TRole role);
	
	List<TUser> getUserByRolelist(List<TRole> list);
	
	List<TUser> getUserByBmRolelist(TBm bm, List<TRole> list);
	
	List<TUser> getUserByBmlistRolelist(List<TBm> bmlist, List<TRole> rolelist);
	
	List<TUser> getUserByRolelistExcludeQtbmlx(List<TRole> roleList, TGypz qtbmlx);
	
	TUser getUserByUserid(String userid);
	
	List<MSjy> getAllSjy();
}
