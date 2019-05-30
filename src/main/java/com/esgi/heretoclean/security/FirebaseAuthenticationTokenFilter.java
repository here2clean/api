package com.esgi.heretoclean.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.google.api.client.util.Strings;

public class FirebaseAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
	
	private final static String TOKEN_HEADER = "Firebase-Token";

	protected FirebaseAuthenticationTokenFilter() {
		super("/api/**");
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		final String token = request.getHeader(TOKEN_HEADER);
		
		if(Strings.isNullOrEmpty(token)) {
			throw new RuntimeException("Invalid token");
		}
		
		return getAuthenticationManager().authenticate(new FirebaseAuthenticationToken(token));
	}

}
