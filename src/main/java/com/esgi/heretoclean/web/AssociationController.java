package com.esgi.heretoclean.web;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.service.interfaces.AssociationService;


@RestController
@RequestMapping("/api/association")
public class AssociationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssociationController.class);

    private final AssociationService associationService;

    @Autowired
	public AssociationController(AssociationService associationService) {
		this.associationService = associationService;
	}
    
    @PostMapping("/register")
    public ResponseEntity  registerAssociation(@RequestBody @Valid Association asso) throws URISyntaxException {
    	associationService.registerAssociation(asso);
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/all")
    public ResponseEntity  getAssociations() {
        return ResponseEntity.ok(associationService.findAllAssociation());
    }
    
    @GetMapping("/research/email")
    public ResponseEntity getAssociationByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(associationService.findAssociationByEmail(email));
    }
    
    @GetMapping("/research/name")
    public ResponseEntity getAssociationBynalme(@RequestParam("name") String name) {
    	return ResponseEntity.ok(associationService.findAssociationByName(name));
    }
    
    @PostMapping("/update")
    public ResponseEntity updateAssociation(@RequestParam("email") String email,@RequestBody Association asso){
    	Optional<Association> optionalAssociation = associationService.findAssociationByEmail(email);

    	if(!optionalAssociation.isPresent()) {
    		return ResponseEntity.badRequest().build();
    	}
    	return ResponseEntity.ok(associationService.updateAssociation(asso));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity deleteAssociation(@RequestParam("email") String email) {
    	associationService.deleteAssociationByEmail(email);
    	return ResponseEntity.ok().build();
    	
    }
    
    
}
