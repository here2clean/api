package com.esgi.heretoclean.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.google.api.client.util.Strings;

public class FirebaseAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter   {

	private final static String TOKEN_HEADER = "token";

	public FirebaseAuthenticationTokenFilter() {
		super("/api/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		final String authToken = request.getHeader(TOKEN_HEADER);
		if (Strings.isNullOrEmpty(authToken)) {
			throw new RuntimeException("Invalid auth token");
		}

		return getAuthenticationManager().authenticate(new FirebaseAuthenticationToken(authToken));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);

		chain.doFilter(request, response);
		
	}

}
