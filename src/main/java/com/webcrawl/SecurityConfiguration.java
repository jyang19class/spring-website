package com.webcrawl;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {  
        http.antMatcher("/**")  
            .authorizeRequests()  
	            .antMatchers("/", "/login**","/css/**", "/images/**", "/webjars/**").permitAll()  
		            .anyRequest().authenticated()  
		            .and()
		    .logout().logoutSuccessUrl("/").permitAll()
		     	.and()
		    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		    	.and()
            .oauth2Login(); 
    }  
}