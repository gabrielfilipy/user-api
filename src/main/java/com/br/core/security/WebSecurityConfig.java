package com.br.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component

public class WebSecurityConfig {

//	@Value("${auth.username}")
//    private String username;
//
//    @Value("${auth.password}")
//    private String password;
	
	@Autowired
	private UserDatailServiceImpl userDatailService;
	
	@Autowired
	private AuthenticationEntryPointImpl authenticationEntryPoint;
	
	@Autowired
	private AuthenticationJwFilter authenticationJwFilter;
	
	@Autowired
	private UserDatailServiceImpl userDatailServiceImpl;
	
	
	
	
    private static  final String[] AUTH_WIHTELIST = {
            "/v1/auth/**",
            "/v1/user/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
        		.authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WIHTELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .addFilterBefore(authenticationJwFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin();
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager( 
    	AuthenticationConfiguration authenticationConfiguratio) throws Exception {
        return authenticationConfiguratio.getAuthenticationManager();
    }
    @Autowired
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDatailService)
    			.passwordEncoder(passwordEncoder());	
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
