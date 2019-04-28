package com.esgi.heretoclean.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Association registerAssociation(@RequestBody @Valid Association asso) throws URISyntaxException {
    	Association association = associationService.registerAssociation(asso);
    	return association;
    }
    
    @GetMapping("/all")
    public List<Association> getAssociations() {
        return associationService.findAllAssociation();
    }
    
    @GetMapping("/research/email")
    public Optional<Association> getAssociationByEmail(@QueryParam("email") String email) {
        return associationService.findAssociationByEmail(email);
    }
    
    @GetMapping("/research/name")
    public Optional<List<Association>> getAssociationBynalme(@QueryParam("name") String name) {
    	return associationService.findAssociationByName(name);
    }
    
    @PostMapping("/update")
    public Association updateAssociation(@QueryParam("email") String email,@RequestBody Association asso){
    	Optional<Association> optionalAssociation = associationService.findAssociationByEmail(email);

    	if(!optionalAssociation.isPresent()) {
    		return null;
    	}
    	return associationService.updateAssociation(asso);
    }
    
    @DeleteMapping("/delete")
    public Association deleteAssociation(@QueryParam("email") String email) {
    	
    	associationService.deleteAssociationByEmail(email);
    	return null;
    	
    }
    
    
}
