package nju.software.sjy.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager
{
	private static Logger log = Logger.getLogger(MyAccessDecisionManager.class);
	
	/**
	 * In this method, need to compare authentication with configAttributes.
	 * 1. A object is a URL, a filter was find permission configuration by this URL, and pass to here.
	 * 2. Check authentication has attribute in permission configuration(configAttributes)
	 * 3. If no match corresponding authentication, throw a AccessDeniedException. 
	 */
	@Override
	public void decide(Authentication authentication, Object obj,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException
	{
		FilterInvocation fi = (FilterInvocation)obj;
		String url = fi.getRequestUrl();
		
		log.info("MyAccessDescisionManager.decide(). begin to check whether current user has the authentication to visit " + url);
		
		if(configAttributes == null)
		{
			return;
		}
		
		Iterator<ConfigAttribute> it = configAttributes.iterator();
		while(it.hasNext())
		{
			String needRole = it.next().getAttribute();
			
			for(GrantedAuthority ga : authentication.getAuthorities())
			{
				String role = ga.getAuthority();
				log.info("need role:" + needRole + " ———— actual role:" + role);
				if(needRole.equals(role))
				{
					log.info("MyAccessDescisionManager.decide(). end to check. Authenticate success!");
					return;
				}
			}
		}
		
		log.info("MyAccessDescisionManager.decide(). end to check. Authenticate fail!");
		
		throw new AccessDeniedException("MyAccessDescisionManager.decide(). end to check. Authenticate fail!");
	}

	@Override
	public boolean supports(ConfigAttribute configAttribute)
	{
		log.info("MyAccessDescisionManager.supports(). role name：" + configAttribute.getAttribute());  
        return true;
	}

	@Override
	public boolean supports(Class<?> arg0)
	{
		log.info("MyAccessDescisionManager.supports()");  
        return true;
	}

}
