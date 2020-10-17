package com.csc540.ups;

import com.csc540.ups.service.impl.UPSDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.anonymous().principal("guest").authorities("guest")
				.and()
			.authorizeRequests()
				.antMatchers("/", "/home", "/login", "/user").permitAll()
				.and()
			.authorizeRequests()
//				.antMatchers("/admin").permitAll()
//				.antMatchers("/resources/**", "/signup", "/about").permitAll()
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				.anyRequest().authenticated()
				.and()
			.formLogin()
//				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();

	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("pwd")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
		return new UPSDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}
}
