package com.esgi.heretoclean.security;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final FirebaseAuthenticationProvider authenticationProvider;

<<<<<<< HEAD
	private final AuthenticationManagerBuilder authenticationManagerBuilder;



=======
	@Autowired
>>>>>>> Update security
	public WebSecurityConfig(FirebaseAuthenticationProvider authenticationProvider,	AuthenticationManagerBuilder authenticationManagerBuilder) {
		this.authenticationProvider = authenticationProvider;
	}

<<<<<<< HEAD
=======
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return new ProviderManager(Arrays.asList(authenticationProvider));
	}


	public FirebaseAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		FirebaseAuthenticationTokenFilter authenticationTokenFilter = new FirebaseAuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManager());
		authenticationTokenFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {});
		return authenticationTokenFilter;
	}


>>>>>>> Update security
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(HttpMethod.OPTIONS);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.cors()
		.and()
		.csrf().disable()
		//.addFilterBefore(authentification, beforeFilter)
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.antMatchers("/api/login/test").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user")
//		.password(passwordEncoder().encode("password"))
//		.authorities("ROLE_USER");
//	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}