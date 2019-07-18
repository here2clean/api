package com.esgi.heretoclean;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;


@SpringBootApplication
public class HeretocleanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeretocleanApplication.class, args);
	}
	

	@Bean
	public FirebaseAuth firebaseAuth() throws IOException {
		
		FileInputStream serviceAccount = new FileInputStream(
				"/home/heretoclean-config.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://heretoclean-876f4.firebaseio.com")
				.build();

		FirebaseApp.initializeApp(options);
		return FirebaseAuth.getInstance();
	}

}
