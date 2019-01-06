package lab.spring.mvc5.hello.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class AppInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {
       AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
       rootContext.set
	}

}
