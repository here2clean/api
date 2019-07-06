package com.esgi.heretoclean.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.esgi.heretoclean.security.NewFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final NewFilter corsFilter;


	private final FirebaseAuthenticationProvider tokenProvider;

	public WebSecurityConfig(NewFilter corsFilter, FirebaseAuthenticationProvider tokenProvider) {
		this.corsFilter = corsFilter;
		this.tokenProvider = tokenProvider;
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/test/**")
		.antMatchers(HttpMethod.OPTIONS,"/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilterAfter(corsFilter, UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling()
		.and()
		.csrf()
		.disable()
		.headers()
		.frameOptions()
		.disable()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.antMatchers("/api/association/register").permitAll()
		.antMatchers("/api/volunteer/signUp").permitAll()
		.antMatchers("/api/**").authenticated()
		.and() 
		.apply(securityConfigurerAdapter());

	}

	private FirebaseJTWConfig securityConfigurerAdapter() {
		return new FirebaseJTWConfig(tokenProvider);
	}

	//	  @Bean
	//	    @Override
	//	    public AuthenticationManager authenticationManagerBean() throws Exception {
	//	        return super.authenticationManagerBean();
	//	    }

}