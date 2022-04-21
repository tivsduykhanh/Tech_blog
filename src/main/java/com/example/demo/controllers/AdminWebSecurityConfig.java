package com.example.demo.controllers;

import com.example.demo.Services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Order(1)
public class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = 
          PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	auth
          .inMemoryAuthentication()
          .withUser("admin")
          .password(encoder.encode("admin"))
          .roles("ADMIN", "USER");
    }


	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	// 	PasswordEncoder encoder = 
    //          PasswordEncoderFactories.createDelegatingPasswordEncoder();
	// 	auth.inMemoryAuthentication().withUser("admin")
    //         .password(encoder.encode("admin")).roles("ADMIN");

	//      auth.inMemoryAuthentication().withUser("user").password(encoder.encode("admin")).roles("USER");
	// }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin*")
          .authorizeRequests()
          .anyRequest()
          .hasRole("ADMIN")
          
          .and()
          .formLogin()
          .loginPage("/admin/login")
          .loginProcessingUrl("/admin_login")
          .failureUrl("/admin/login?error")
          .defaultSuccessUrl("/admin")
          
          .and()
          .logout()
          .logoutUrl("/admin_logout")
          .logoutSuccessUrl("/admin/login?logout")
          .deleteCookies("JSESSIONID")
          
          .and()
          .exceptionHandling()
          .accessDeniedPage("/403")
          
          .and()
          .csrf().disable();
        ;
    }
    
}