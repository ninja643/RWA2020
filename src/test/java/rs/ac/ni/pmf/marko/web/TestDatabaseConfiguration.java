package rs.ac.ni.pmf.marko.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import rs.ac.ni.pmf.marko.web.config.PersistenceConfiguration;

@Configuration
@ComponentScan(basePackages = "rs.ac.ni.pmf.marko.web.repository")
@Import(value = { PersistenceConfiguration.class })
public class TestDatabaseConfiguration {

}
