package com.schoollog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;

import com.schoollog.config.AuthenticationEntryPoint;

import javax.sql.DataSource;

@Configuration 
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint; 
	
	@Autowired
	private DataSource dataSource;
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http.csrf().disable()
	.authorizeRequests().anyRequest().authenticated().and()
	.httpBasic().authenticationEntryPoint(authenticationEntryPoint);

	return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
	AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);

		auth.jdbcAuthentication()
		.usersByUsernameQuery(this.usersQuery)
		.authoritiesByUsernameQuery(this.rolesQuery)
		.dataSource(this.dataSource)
		.passwordEncoder(
		PasswordEncoderFactories.createDelegatingPasswordEncoder()
		);


		return auth.build();
	}

	
}