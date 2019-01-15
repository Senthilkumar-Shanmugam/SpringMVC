package lab.springsecurity.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * in Spring security is implemented in DelegatingFilterProxy. To register it in spring container using java configuration
 * use AbstractSecurityWebApplicationInitializer.
 * 
 * The spring will detect the instance of this class during application startup, and register the DelegatingFilterProxy to use the
 *  springSecurityFilterChain before any other registered Filter. It also register a ContextLoaderListener.
 * @author seshshan
 *
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	
	

}
