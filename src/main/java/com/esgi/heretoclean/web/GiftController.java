package com.esgi.heretoclean.web;

import java.net.URISyntaxException;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.service.interfaces.GiftService;

@RestController
@RequestMapping("/api/gift")
public class GiftController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GiftController.class);

	private final GiftService giftService;

	@Autowired
	public GiftController(GiftService giftService) {
		this.giftService = giftService;
	}

	@PostMapping("/register")
	public ResponseEntity registerGift(@Email @RequestParam("emailAssociaiton") String emailAssociaiton,@Email @RequestParam("emailVolunteer") String emailVolunteer,@RequestBody @Valid Gift gift ) throws URISyntaxException {
		if( emailAssociaiton == null || emailVolunteer == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
		}
		Gift newGift = giftService.createGift(gift,emailAssociaiton,emailVolunteer);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
