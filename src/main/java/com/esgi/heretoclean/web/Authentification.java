package com.esgi.heretoclean.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapstruct.BeforeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esgi.heretoclean.configuration.MyProperties;
import com.esgi.heretoclean.security.FirebaseAuthenticationTokenFilter;
import com.google.api.client.http.HttpRequest;
import com.google.auth.oauth2.ClientId;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.TokenStore;
import com.google.auth.oauth2.UserAuthorizer;
import com.google.auth.oauth2.UserCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api")
public class Authentification {

	private final MyProperties props;


	@Autowired
	public Authentification(MyProperties props) {
		this.props = props;
	}


	@RequestMapping("/authent")
	public ResponseEntity authentification()throws IOException {



		//		try {
		//			FirebaseAuth auth = this.initFirebase();
		//
		//			String email = auth.getInstance().verifyIdToken(token).getEmail();
		//			
		//			Claims claims = Jwts
		//					.parser()
		//					.setSigningKey(props.getSecret())
		//					.parseClaimsJws(token)
		//					.getBody();
		//			
		//			Collection<? extends GrantedAuthority> authories = 
		//					Arrays.stream(claims.get("auth").toString().split(","))
		//					.map(SimpleGrantedAuthority::new)
		//					.collect(Collectors.toList());
		//			
		//					User principal = new User(email, "", authories);
		//			UsernamePasswordAuthenticationToken u = new UsernamePasswordAuthenticationToken(principal, token);
		//			u.setAuthenticated(true);
		//
		//		} catch (FirebaseAuthException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//			return new ResponseEntity<String>("Token has expired", HttpStatus.UNAUTHORIZED);
		//		}


		return new ResponseEntity<String>("Ok",HttpStatus.OK );

	}

	@RequestMapping("/login/test")
	public ResponseEntity test(HttpServletRequest request, HttpServletResponse response) throws IOException {

		FirebaseAuthenticationTokenFilter t = new FirebaseAuthenticationTokenFilter();
		t.attemptAuthentication(request, response);
		return new ResponseEntity<String>("Ok",HttpStatus.OK );

	}



}
