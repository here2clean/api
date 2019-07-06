package com.esgi.heretoclean.security;

import javax.servlet.Filter;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class FirebaseJTWConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	 public static final String AUTHORIZATION_HEADER = "Authorization";

	    private FirebaseAuthenticationProvider tokenProvider;

	    public FirebaseJTWConfig(FirebaseAuthenticationProvider tokenProvider) {
	        this.tokenProvider = tokenProvider;
	    }

	    @Override
	    public void configure(HttpSecurity http) {
	    	FirebaseTokenFilter customFilter = new FirebaseTokenFilter(tokenProvider);
	        http.addFilterBefore(customFilter,  UsernamePasswordAuthenticationFilter.class);
	    }

	
}
