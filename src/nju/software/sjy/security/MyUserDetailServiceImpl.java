package nju.software.sjy.security;

import java.util.HashSet;
import java.util.Set;

import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService
{
	private static Logger log = Logger.getLogger(MyUserDetailServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		log.info("MyUserDetailServiceImpl.loadUserByUsername. username:" + username);
		
		TUser user = userService.getUserByUsername(username);
		
		boolean enabled = true;				   //是否可用
		boolean accountNonExpired = true;      //是否过期
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		/**
		 * 如果你使用资源和权限配置在xml文件中，如：<intercept-url pattern="/user/admin" access="hasRole('ROLE_ADMIN')"/>
		 * 并且也不想用数据库存储，所有用户都具有相同的权限的话，你可以手动保存角色(如：预订网站)。
		 * authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		 */
		TRole role = user.getRole();

		GrantedAuthority ga = new SimpleGrantedAuthority(role.getRolename());
		authorities.add(ga);
		
		org.springframework.security.core.userdetails.User orgUser = new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, 
				accountNonLocked, authorities);
		
		return orgUser;
	}
	
}
