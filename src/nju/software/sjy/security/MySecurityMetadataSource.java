package nju.software.sjy.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import nju.software.sjy.model.xy.TResource;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.service.RoleService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

/**
 * 这个类在初始化的时候，应该获取到所有的资源及其对应角色的定义
 *
 */
@Service
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
	private static Logger log = Logger.getLogger(MySecurityMetadataSource.class);
	
	@Autowired
	private RoleService roleService;
	
	/* 保存资源和权限的对应关系  key表示资源url  value表示权限 */
	private Map<String, Collection<ConfigAttribute>> resMap = null;
	
	private AntPathMatcher urlMatcher = new AntPathMatcher();
	
	public MySecurityMetadataSource() 
	{  
		
    }
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes()
	{
		return null;
	}
	
	@PostConstruct
	public void loadResourcesDefine()
	{
		resMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		log.info("MySecurityMetadataSource.loadResourcesDefine()——start to load resources");
		
		List<TRole> roles = roleService.getAllRoles();
		for(TRole role : roles)
		{
			ConfigAttribute configAttribute = new SecurityConfig(role.getRolename());
			List<TResource> resources = roleService.getResByRole(role);
			
			for(TResource res : resources)
			{
				Collection<ConfigAttribute> configAttributes = null;
				
				if(resMap.containsKey(res.getUrl()))
				{
					configAttributes = resMap.get(res.getUrl());
					configAttributes.add(configAttribute);
				}
				else
				{
					configAttributes = new ArrayList<ConfigAttribute>();
					configAttributes.add(configAttribute);
					
					resMap.put(res.getUrl(), configAttributes);
				}
			}
		}
		
		log.info("MySecurityMetadataSource.loadResourcesDefine()——end to load resources");
	}
	

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException
	{
		String url = ((FilterInvocation)obj).getRequestUrl();
		log.info("MySecurityMetadataSource:getAttributes().start. the url you requested is: "+url);
		int firstQuestionMarkIndex = url.indexOf("?");
		if(firstQuestionMarkIndex != -1)
		{
			url = url.substring(0, firstQuestionMarkIndex);
		}
		
		Iterator<String> it = resMap.keySet().iterator();
		while(it.hasNext())
		{
			String resUrl = it.next();
			if(resUrl.indexOf("?")!=-1)
			{
				resUrl = resUrl.substring(0, resUrl.indexOf("?"));
			}
			
			if(urlMatcher.match(url, resUrl))
			{
				log.info("MySecurityMetadataSource:getAttributes().end. return the permitted roles:" + resMap.get(resUrl));
				return resMap.get(resUrl);
			}
		}
		
		log.info("MySecurityMetadataSource:getAttributes().end. free to visit");
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0)
	{
		log.info("MySecurityMetadataSource.supports(Class<?> arg0)");  
        return true;
	}
	
	public void clear()
	{
		resMap.clear();
	}
}
