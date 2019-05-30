package com.esgi.heretoclean.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class FirebaseAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private final String token;

	public FirebaseAuthenticationToken(final String token) {
		super(null,null);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
