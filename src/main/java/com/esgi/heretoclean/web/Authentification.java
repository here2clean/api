package com.esgi.heretoclean.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.heretoclean.configuration.MyProperties;
import com.google.auth.oauth2.GoogleCredentials;
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


	@RequestMapping("/authentification")
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
	
	@RequestMapping("/test")
	public ResponseEntity test() throws IOException {


			return new ResponseEntity<String>("Ok",HttpStatus.OK );

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
