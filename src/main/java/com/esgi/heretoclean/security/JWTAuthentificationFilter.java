package com.esgi.heretoclean.security;

import java.util.concurrent.ExecutionException;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

public class JWTAuthentificationFilter  {
	
	
	public void test(String idToken) throws InterruptedException, ExecutionException {
		FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
		final String email = decodedToken.getEmail();
	}

}
