package rs.ac.ni.pmf.marko.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import rs.ac.ni.pmf.marko.web.provider.TicketProvider;
import rs.ac.ni.pmf.marko.web.provider.impl.TicketMemoryProvider;

@Configuration
@EnableWebMvc
@ComponentScan({ "rs.ac.ni.pmf.marko.web" })
public class ApplicationConfiguration {

	@Bean
	public TicketProvider getTicketProvider()
	{
		return new TicketMemoryProvider();
	}
}
