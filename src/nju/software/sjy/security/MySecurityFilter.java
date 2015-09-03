package nju.software.sjy.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

@Service
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter
{
	private static Logger log = Logger.getLogger(MySecurityFilter.class);
	
	@Autowired
	private MySecurityMetadataSource securityMetadataSource; 
	
	@Autowired
	private MyAccessDecisionManager accessDecisionManager;
	
	@Autowired
	private AuthenticationManager myAuthenticationManager; 
	
	@PostConstruct
	public void init() throws ServletException
	{
		super.setAuthenticationManager(myAuthenticationManager);
		super.setAccessDecisionManager(accessDecisionManager);
	}
	
	@Override
	public Class<?> getSecureObjectClass()
	{
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource()
	{
		return this.securityMetadataSource;
	}
	
	@Override
	public void destroy(){}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		log.info("MyFilterSecurityInterceptor.doFilter(). start to intercepte...");  
		
        FilterInvocation fi = new FilterInvocation(request, response, chain);  
        InterceptorStatusToken token = super.beforeInvocation(fi);
        
        try 
        {  
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
        }
        finally
        {  
            super.afterInvocation(token,null);  
        }  
        log.info("MyFilterSecurityInterceptor.doFilter(). end to intercepte...");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		
	}


}
