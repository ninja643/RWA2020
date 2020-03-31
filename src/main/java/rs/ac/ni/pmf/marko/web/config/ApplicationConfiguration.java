package rs.ac.ni.pmf.marko.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import rs.ac.ni.pmf.marko.web.provider.DataProvider;
import rs.ac.ni.pmf.marko.web.provider.impl.DataMemoryProvider;

@Configuration
@EnableWebMvc
@ComponentScan({ "rs.ac.ni.pmf.marko.web" })
public class ApplicationConfiguration {

	@Bean
	public DataProvider getTicketProvider()
	{
		return new DataMemoryProvider();
	}
}
