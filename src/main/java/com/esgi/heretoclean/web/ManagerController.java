package com.esgi.heretoclean.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.models.Manager;
import com.esgi.heretoclean.service.implementations.ManagerServiceImpl;
import com.esgi.heretoclean.service.implementations.VolunteerServiceImpl;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	
	
	private final ManagerServiceImpl managerService;
	
	@Autowired
    public ManagerController(ManagerServiceImpl managerService) {
		this.managerService = managerService;
	}

	@PostMapping("/signUp")
	public ResponseEntity createUser(@Email @RequestParam("email") String email, @RequestBody @Valid Manager m) throws IOException, FirebaseAuthException {
		FirebaseAuth auth = this.initFirebase();
		String fullName = m.getFirstName() + " " + m.getLastName();
		CreateRequest request = new CreateRequest()
				.setEmail(m.getEmail())
				.setEmailVerified(false)
			    .setPassword(m.getPassword())
			    .setDisplayName(fullName);
		
		UserRecord userRecord = auth.createUser(request);
		
		if(userRecord != null) {
			managerService.createManager(m, email);
		}
		
		System.out.println("Successfully created new user: " + userRecord.getUid());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
    
    @PutMapping("/update")
    public ResponseEntity update(@Email @QueryParam("emailAssociaiton") String emailAssociaiton,@Valid @RequestBody Manager manager){
    	if( emailAssociaiton == null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
    	}
    	managerService.update(manager,emailAssociaiton);
    	return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity delete(@Email @QueryParam("email") String email   ) {
    	managerService.deleteByEmail(email);
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
