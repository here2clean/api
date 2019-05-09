package com.esgi.heretoclean.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.heretoclean.models.Manager;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.implementations.VolunteerServiceImpl;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {
	
	@Autowired
	private VolunteerServiceImpl volunteerService;

    @GetMapping("/user/signUp")
	public ResponseEntity createUser(@RequestBody @Valid Volunteer v) throws IOException, FirebaseAuthException {
		FirebaseAuth auth = this.initFirebase();
		String fullName = v.getFirstName() + " " + v.getLastName();
		CreateRequest request = new CreateRequest()
				.setEmail(v.getEmail())
				.setEmailVerified(false)
			    .setPassword(v.getPassword())
			    .setDisplayName(fullName);
		
		UserRecord userRecord = auth.createUser(request);
		
		if(userRecord != null) {
			volunteerService.registerVolunteer(v);
		}
		
		System.out.println("Successfully created new user: " + userRecord.getUid());
		return ResponseEntity.status(HttpStatus.CREATED.value()).build();
	}
    
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Volunteer volunteer){
    
    	volunteerService.update(volunteer);
    	return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
    
    @GetMapping("/allEvent")
    public ResponseEntity getEvents(@Email @QueryParam("email") String emailVolunteer) {
    	
    	if(volunteerService.findAllEvent(emailVolunteer) == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	
    	return ResponseEntity.status(HttpStatus.FOUND).body(volunteerService.findAllEvent(emailVolunteer));
    }
    
    @GetMapping("/allEvent")
    public ResponseEntity getAssociations(@Email @QueryParam("email") String emailVolunteer) {
    	if(volunteerService.findAllAssociation(emailVolunteer) == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	return ResponseEntity.status(HttpStatus.FOUND).body(volunteerService.findAllEvent(emailVolunteer));
    }
    
    @GetMapping("/allEvent")
    public ResponseEntity getGifs(@Email @QueryParam("email") String emailVolunteer) {
    	
    	if(volunteerService.findAllGift(emailVolunteer) == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	
    	return ResponseEntity.status(HttpStatus.FOUND).body(volunteerService.findAllEvent(emailVolunteer));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity delete(@Email @QueryParam("email") String email   ) {
    	volunteerService.deleteByEmail(email);
    	return ResponseEntity.status(HttpStatus.OK.value()).build();
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
