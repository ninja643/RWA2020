package rs.ac.ni.pmf.marko.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import rs.ac.ni.pmf.marko.web.config.PersistenceConfiguration;

@SuppressWarnings("deprecation")
@Configuration
@ComponentScan(basePackages = "rs.ac.ni.pmf.marko.web.repository")
@Import(value = { PersistenceConfiguration.class })
public class TestDatabaseConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
