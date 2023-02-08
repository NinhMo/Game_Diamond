package com.game_diamond.config;

import com.game_diamond.jwt.JwtAuthenticationEntryPoint;
import com.game_diamond.jwt.JwtRequestFilter;
import com.game_diamond.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
//
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(jwtUserDetailsService);
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                 .csrf().disable()
                 .cors().and()
                 .authorizeHttpRequests()
                 .antMatchers("/api/user/login").permitAll()
                 .anyRequest()
                 .authenticated()
                 .and().exceptionHandling()
                 .authenticationEntryPoint(jwtAuthenticationEntryPoint);

         httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
