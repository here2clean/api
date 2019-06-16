package com.esgi.heretoclean.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esgi.heretoclean.configuration.MyProperties;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@RestController
@RequestMapping("/api")
public class Authentification {

	private final MyProperties props;


	@Autowired
	public Authentification(MyProperties props) {
		this.props = props;
	}


	@RequestMapping("/authent")
	public ResponseEntity authentification()throws IOException, InterruptedException, ExecutionException {

//		String token = http.getHeader("token");
//		boolean isValide = FirebaseAuth.getInstance().verifyIdTokenAsync(token).isDone();
//
//		if(token != null || token.isEmpty()|| !isValide ) {
//			return new ResponseEntity<String>("Non authorisé",HttpStatus.FORBIDDEN );
//		}
//
//		User principal = new User(FirebaseAuth.getInstance().verifyIdTokenAsync(token).get().getEmail(), "",null);
//		UsernamePasswordAuthenticationToken u = new UsernamePasswordAuthenticationToken(principal, "") ;
//		u.setAuthenticated(true);


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
		
       Authentication u = SecurityContextHolder.getContext().getAuthentication();

       User principal = (User) u.getPrincipal();

		return new ResponseEntity<String>("Ok",HttpStatus.OK );

	}

	@RequestMapping("/login/test")
	public ResponseEntity test(HttpServletRequest http) throws IOException, InterruptedException, ExecutionException, FirebaseAuthException {


		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add( new SimpleGrantedAuthority("ROLE_USER") );
		String token = http.getHeader("token");
		FirebaseToken isValide = FirebaseAuth.getInstance().verifyIdToken(token);

		if(token == null || token.isEmpty() ) {
			return new ResponseEntity<String>("Non authorisé",HttpStatus.FORBIDDEN );
		}

		User principal = new User(isValide.getEmail(), "", authority);
		UsernamePasswordAuthenticationToken u = new UsernamePasswordAuthenticationToken(principal, "",authority) ;
        SecurityContextHolder.getContext().setAuthentication(u);
	
//		u.setAuthenticated(true);
		//		FirebaseAuthenticationTokenFilter t = new FirebaseAuthenticationTokenFilter();
		//		t.attemptAuthentication(request, response);
		return new ResponseEntity<String>("Ok",HttpStatus.OK );

	}
	
	@RequestMapping("/jean")
	public ResponseEntity test()  {

		return new ResponseEntity<String>("Ok",HttpStatus.OK );

	}



}
