package rs.ac.ni.pmf.marko.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import rs.ac.ni.pmf.marko.web.config.PersistenceConfiguration;

@Configuration
@Import(value = { PersistenceConfiguration.class })
@ComponentScan(basePackages = { "rs.ac.ni.pmf.marko.web.repository" })
public class TestDatabaseConfiguration {

}
