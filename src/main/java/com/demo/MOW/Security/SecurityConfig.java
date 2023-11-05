package com.demo.MOW.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.MOW.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http
         .cors()
             .and()
         .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
         .csrf()
             .disable()
         .formLogin()
             .disable()
         .httpBasic()
             .disable()                    
         .authorizeRequests()
             .antMatchers("/",
                 "/error",
                 "/favicon.ico",
                 "/**/*.png",
                 "/**/*.avif",
                 "/**/*.webp",
                 "/**/*.gif",
                 "/**/*.svg",
                 "/**/*.jpg",
                 "/**/*.html",
                 "/**/*.css",
                 "/**/*.js")
                 .permitAll()
             .antMatchers("/api/**").permitAll()
             .antMatchers("/api/meals").permitAll()
             .antMatchers("/api/member/**").hasRole("MEMBER")
             .antMatchers("/api/partner/**").hasRole("PARTNER")
             .antMatchers("/api/volunteer/**").hasRole("VOLUNTEER")
             .antMatchers("/api/caregiver/**").hasRole("CAREGIVER")
             .anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

