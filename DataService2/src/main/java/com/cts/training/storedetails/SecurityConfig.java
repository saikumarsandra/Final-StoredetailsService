package com.cts.training.storedetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Autowired
	DataServiceConfig properties;
	
	@Autowired
	AuthEntryPoint entryPoint;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
      	.authorizeRequests()
      	.antMatchers("cloudfoundryapplication/**").permitAll()
      	.anyRequest().authenticated()
//      	.anyRequest().permitAll()
        .and()
        .httpBasic()
        .authenticationEntryPoint(entryPoint);
      
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser(properties.getUsername())
		.password(encoder.encode(properties.getPassword()))
		.roles(properties.getRoles());
	}
	
}


