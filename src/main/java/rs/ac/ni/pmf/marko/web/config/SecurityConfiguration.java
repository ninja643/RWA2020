package rs.ac.ni.pmf.marko.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
//@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureSecurity(final AuthenticationManagerBuilder auth/* , final PasswordEncoder passwordEncoder */) throws Exception {
		auth.inMemoryAuthentication()
//				.passwordEncoder(passwordEncoder)
				.withUser("user")
				.password("user")
//				.password(passwordEncoder.encode("user"))
				.roles("USER", "ADMIN");
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(HttpMethod.GET);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET).anonymous()
				
				.and().httpBasic();

		/*
		 * ().antMatchers("/**").hasRole("USER").and().formLogin();
		 */
	}

}
