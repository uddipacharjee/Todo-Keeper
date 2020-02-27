package com.tutorial.security;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {




    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        List<UserDetails> users=new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder().username("uddip").password("123").roles("USER").build());
        return new InMemoryUserDetailsManager(users);
    }

    //    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)throws Exception{
//        auth.inMemoryAuthentication().withUser("uddip").password("{noop}123").roles("USER","ADMIN");
//
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/","/*todo*/**").access("hasRole('USER')").and().formLogin();
//    }

}
