package com.esgi.heretoclean.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esgi.heretoclean.DTO.AssociationDTO;
import com.esgi.heretoclean.DTO.EventDTO;
import com.esgi.heretoclean.DTO.GiftDTO;
import com.esgi.heretoclean.DTO.VolunteerDTO;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.implementations.VolunteerServiceImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {

	private final VolunteerServiceImpl volunteerService;
	private final FirebaseAuth auth;

	@Autowired
	public VolunteerController(VolunteerServiceImpl volunteerService,FirebaseAuth auth) {
		this.volunteerService = volunteerService;
		this.auth = auth;
	}

	@RequestMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createUser(@RequestBody @Valid Volunteer v) throws IOException, FirebaseAuthException {
		//		FirebaseAuth auth = this.initFirebase();
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
	public ResponseEntity update(@RequestParam("email") String email , @Valid @RequestBody Volunteer volunteer){
		volunteerService.update(email,volunteer);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/allEvent")
	public ResponseEntity getEvents(@Email @RequestParam("email") String emailVolunteer) {
		if(volunteerService.findAllEvent(emailVolunteer) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		//    	List<String> s = new 
		//    	VolunteerDTO volunteerDTO = VolunteerDTO.VolunteerToVolunteerDTO(volunteerService.findAllEvent(emailVolunteer));
		List<EventDTO> eventDTOs = new ArrayList<EventDTO>();
		
		for(Event e : volunteerService.findAllEvent(emailVolunteer)) {
			
			eventDTOs.add(EventDTO.EventToEventDTO(e));
			
		}

		return ResponseEntity.ok(eventDTOs);
	}

	@GetMapping("/allAssocaition")
	public ResponseEntity getAssociations(@Email @RequestParam("email") String emailVolunteer) {
		if(volunteerService.findAllAssociation(emailVolunteer) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		List<AssociationDTO> associationDTOs = new ArrayList<AssociationDTO>();
		
		for(Association a : volunteerService.findAllAssociation(emailVolunteer) ) {
			associationDTOs.add(AssociationDTO.AssociationToAssociationDTO(a));
		}
		
		return ResponseEntity.status(HttpStatus.FOUND).body(associationDTOs);
	}

	@GetMapping("/allGift")
	public ResponseEntity getGifs(@Email @RequestParam("email") String emailVolunteer) {

		if(volunteerService.findAllGift(emailVolunteer) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		List<GiftDTO> giftDTOs = new ArrayList<GiftDTO>();
		
		for(Gift g : volunteerService.findAllGift(emailVolunteer)) {
			giftDTOs.add(GiftDTO.GiftToGiftDTO(g));
		}

		return ResponseEntity.ok(giftDTOs);
	}

	@DeleteMapping("/delete")
	public ResponseEntity delete(@Email @RequestParam("email") String email   ) {
		volunteerService.deleteByEmail(email);
		return ResponseEntity.status(HttpStatus.OK.value()).build();
	}

	@GetMapping("/findByEmail")
	public ResponseEntity getVolunteer(@Email @RequestParam("email") String email) {
		Optional<Volunteer> volunteerOptional = volunteerService.findVolunteerByEmail(email);
		if(!volunteerOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		VolunteerDTO volunteerDTO = VolunteerDTO.VolunteerToVolunteerDTO(volunteerOptional.get());

		return ResponseEntity.ok(volunteerDTO);
	}


}
