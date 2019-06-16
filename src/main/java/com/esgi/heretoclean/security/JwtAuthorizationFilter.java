package com.esgi.heretoclean.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter  {

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

	UsernamePasswordAuthenticationToken authentication = null ;
	try {
		authentication = getAuthentication(request);
	} catch (FirebaseAuthException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String header = request.getHeader("token");
	
	
	 if (StringUtils.isEmpty(header) ) {
         chain.doFilter(request, response);
         return;
     }
	    SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws FirebaseAuthException {

		String token = request.getHeader("token");
		if(!StringUtils.isEmpty(token)) {
			
			List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
			authority.add( new SimpleGrantedAuthority("ROLE_USER") );
			FirebaseToken isValide = FirebaseAuth.getInstance().verifyIdToken(token);

			User principal = new User(isValide.getEmail(), "", authority);
			return  new UsernamePasswordAuthenticationToken(principal, "",authority) ;
		}
		return null;
	}
}