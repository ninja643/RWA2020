package rs.ac.ni.pmf.marko.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import rs.ac.ni.pmf.marko.web.config.ApplicationConfiguration;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException {

		final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApplicationConfiguration.class);

		final Dynamic servletRegistration = servletContext.addServlet("dispatcher",
				new DispatcherServlet(applicationContext));
		servletRegistration.addMapping("/services/rest/*");
		servletRegistration.setLoadOnStartup(1);
	}

}
