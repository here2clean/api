package com.esgi.heretoclean.security;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

@Component
public class FirebaseAuthenticationProvider extends  AbstractUserDetailsAuthenticationProvider {

	//		@Autowired
	private FirebaseAuth firebaseAuth ;

	@Override
	public boolean supports(Class<?> authentication) {
		return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}


	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		final FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
		try {
			this.firebaseAuth = this.initFirebase();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ApiFuture<FirebaseToken> task = firebaseAuth.verifyIdTokenAsync(authenticationToken.getToken());
		try {
			FirebaseToken token = task.get();
			return new FirebaseUserDetails(token.getEmail(), token.getUid());
		} catch (InterruptedException | ExecutionException e) {
			throw new SessionAuthenticationException(e.getMessage());
		}
	}

	private FirebaseAuth initFirebase() throws IOException {
		FileInputStream serviceAccount = new FileInputStream(new File("D://jeand/Documents/Cours2018-2019/S2/PA/API/heretoclean/src/main/resources/heretoclean-config.json"));
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://heretoclean-876f4.firebaseio.com")
				.build();
		FirebaseApp app = FirebaseApp.initializeApp(options);
		return FirebaseAuth.getInstance(app);
	}

}
