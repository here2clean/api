package com.esgi.heretoclean.web;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.service.interfaces.AssociationService;
import com.esgi.heretoclean.service.interfaces.EventService;
import com.esgi.heretoclean.service.interfaces.GiftService;
import com.esgi.heretoclean.service.interfaces.VolunteerService;

@RestController
@RequestMapping("/api/gift")
public class GiftController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(GiftController.class);
	    
	    @Autowired
	    private GiftService giftService;
	    
	    @PostMapping("/register")
	    public ResponseEntity registerGift(@Email @QueryParam("emailAssociaiton") String emailAssociaiton,@Email @QueryParam("emailVolunteer") String emailVolunteer,@RequestBody @Valid Gift gift ) throws URISyntaxException {
	    	if( emailAssociaiton == null || emailVolunteer == null) {
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
	    	}
	    	Gift newGift = giftService.createGift(gift,emailAssociaiton,emailVolunteer);
	    	return ResponseEntity.status(HttpStatus.CREATED).build();
	    }
	    
	    @PutMapping("/update")
	    public ResponseEntity update(@Email @QueryParam("emailAssociaiton") String emailAssociaiton,@Email @QueryParam("emailVolunteer") String emailVolunteer,@Valid @RequestBody Gift gift){
	    	if( emailAssociaiton == null || emailVolunteer == null) {
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
	    	}
	    	giftService.update(gift,emailAssociaiton,emailVolunteer);
	    	return ResponseEntity.status(HttpStatus.OK.value()).build();
	    }
	    
	    @DeleteMapping("/delete")
	    public ResponseEntity delete(@QueryParam("id") long id) {
	    	
	    	giftService.deleteById(id);
	    	return ResponseEntity.status(HttpStatus.OK.value()).build();
	    }

}
