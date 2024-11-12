package com.naverrain.store.web;

import com.naverrain.persistence.SetupDataLoader;
import com.naverrain.store.web.security.DefaultAuthFailureHandler;
import com.naverrain.store.web.security.DefaultAuthProvider;
import com.naverrain.store.web.security.DefaultAuthSuccessHandler;
import com.naverrain.store.web.security.DefaultUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/admin/*").hasRole("ADMIN")
                .requestMatchers("/management-fulfilment", "/management-orders")
                .hasAuthority(SetupDataLoader.WRITE_PRIVILEGE)
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/signin")
                .usernameParameter("email")
                .loginProcessingUrl("/perform_login")
                .successHandler(successAuthHandler())
                .failureHandler(failureHandler())
                .and()
                .logout()
                .logoutUrl("/signout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/homepage")
                .and()
                .rememberMe()
                .key("superSecretKey")
                .rememberMeParameter("remember")
                .rememberMeCookieName("rememberlogin");

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successAuthHandler() {
        return new DefaultAuthSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new DefaultAuthFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailsService();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        return new DefaultAuthProvider();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider());
        return authenticationManagerBuilder.build();
    }
}
