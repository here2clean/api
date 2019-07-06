package com.esgi.heretoclean.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;


public class FirebaseTokenFilter extends GenericFilterBean{
	
	private FirebaseAuthenticationProvider provider;
	
	public FirebaseTokenFilter(FirebaseAuthenticationProvider provider) {
		this.provider = provider;
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String jwt = getToken(httpServletRequest);
		
		if (StringUtils.hasText(jwt) ) {
            Authentication authentication = this.provider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
		chain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(FirebaseJTWConfig.AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
	}

}
