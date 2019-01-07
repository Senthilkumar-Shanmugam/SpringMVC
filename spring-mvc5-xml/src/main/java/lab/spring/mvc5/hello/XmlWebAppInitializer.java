package lab.spring.mvc5.hello;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class XmlWebAppInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {
		XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
		rootContext.setConfigLocation("/WEB-INF/app-config.xml");
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		XmlWebApplicationContext webContext = new XmlWebApplicationContext();
		webContext.setConfigLocation("/WEB-INF/web-config.xml");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", 
        		         new DispatcherServlet(webContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
	}

}
