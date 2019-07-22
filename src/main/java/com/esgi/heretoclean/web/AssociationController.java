package com.esgi.heretoclean.web;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.heretoclean.DTO.AssociationDTO;
import com.esgi.heretoclean.DTO.CompoCommandDTO;
import com.esgi.heretoclean.DTO.EventDTO;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.CompoCommand;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.interfaces.AssociationService;
import com.esgi.heretoclean.service.interfaces.CommandService;
import com.esgi.heretoclean.service.interfaces.CompoCommandService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

import antlr.StringUtils;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;


@RestController
@RequestMapping("/api/association")
public class AssociationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssociationController.class);

	private final AssociationService associationService;
	private final FirebaseAuth auth;
	private final CommandService commandService;

	@Autowired
	public AssociationController(AssociationService associationService, FirebaseAuth auth,CommandService commandService) {
		this.associationService = associationService;
		this.auth = auth;
		this.commandService = commandService;
	}

	@PostMapping("/register")
	public ResponseEntity  registerAssociation(@RequestBody @Valid Association asso) throws HereToCleanException {
		try {
			associationService.registerAssociation(asso);
			String fullName = asso.getName();
			CreateRequest request = new CreateRequest()
					.setEmail(asso.getEmail())
				    .setPassword(asso.getPassword())
				    .setDisplayName(fullName);
			UserRecord userRecord = auth.createUser(request);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HereToCleanException(HttpStatus.BAD_REQUEST.value() , "L'association n'a pas été enregistré");
		}
	}

	@GetMapping("/all")
	public ResponseEntity  getAssociations() throws HereToCleanException {

		List<Association> associations =   associationService.findAllAssociation();
		
		List<AssociationDTO> associationDTOs = new ArrayList<AssociationDTO>();
		
		for(Association a : associations) {
			
			AssociationDTO assoDTO = AssociationDTO.AssociationToAssociationDTO(a);
			associationDTOs.add(assoDTO);
		}

		if(associations.isEmpty()) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value() , "Associations non trouvé");
		}
		return ResponseEntity.ok(associationDTOs);
	}

	@GetMapping("/research/email")
	public ResponseEntity getAssociationByEmail(@RequestParam("email") String email) throws HereToCleanException {

		if(StringUtil.isNullOrEmpty(email) ) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "Il manque un paramètre");
		}

		Optional<Association> asso = associationService.findAssociationByEmail(email);

		if(!asso.isPresent()) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "L'association ayant comme email : " + email + " n'a pas été trouvé");

		}
		
		AssociationDTO assoDTO = AssociationDTO.AssociationToAssociationDTO(asso.get());

		return ResponseEntity.ok(assoDTO);
	}
	
	@GetMapping("/research/id")
	public ResponseEntity getAssociationById(@RequestParam("id") Long id) throws HereToCleanException {

		if(id == null ) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "Il manque un paramètre");
		}

		Optional<Association> asso = Optional.ofNullable(associationService.findById(id));

		if(!asso.isPresent() || asso.get().getId() == null) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "L'association ayant comme id : " + id + " n'a pas été trouvé");

		}
		
		AssociationDTO assoDTO = AssociationDTO.AssociationToAssociationDTO(asso.get());

		return ResponseEntity.ok(assoDTO);
	}

	@GetMapping("/research/name")
	public ResponseEntity getAssociationBynalme(@RequestParam("name") String name)throws HereToCleanException {

		if(StringUtil.isNullOrEmpty(name) ) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "Il manque un paramètre");
		}
		
		
		List<Association> asso = associationService.findAssociationByName(name);

		
		if(asso.isEmpty()) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "L'association " + name + " n'a pas été trouvé");
		}
		
		List<AssociationDTO> assoDTOs = new ArrayList<AssociationDTO>();
		
		for(Association a : asso) {
			AssociationDTO assoDTO = AssociationDTO.AssociationToAssociationDTO(a);
			assoDTOs.add(assoDTO);
		}

		return ResponseEntity.ok(assoDTOs);
	}

	@PostMapping("/update")
	public ResponseEntity updateAssociation(@RequestParam("email") String email,@RequestBody Association asso) throws HereToCleanException {

		if(StringUtil.isNullOrEmpty(email) || asso == null ) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "Des paramètres sont manquants");
		}

		Optional<Association> optionalAssociation = associationService.findAssociationByEmail(email);

		if(!optionalAssociation.isPresent()) {
			//return ResponseEntity.badRequest().build();
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "L'association n'a pas été mis à jour");

		}
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity deleteAssociation(@RequestParam("email") String email) throws HereToCleanException {

		if(StringUtil.isNullOrEmpty(email)  ) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "Veuillez renseigner l'email de votre association");
		}

		associationService.deleteAssociationByEmail(email);
		return ResponseEntity.ok().build();

	}
	
	@PostMapping("/addVolunteer")
    public ResponseEntity addVolunteer(@RequestParam("association_id") Long idAsso, @RequestParam("volunteer_id") Long idVolunteer ) throws HereToCleanException {
    	
    	if(idAsso == null || idVolunteer == null ) {
    		throw new HereToCleanException("La requête est incomplète");
    	}
    	associationService.addVolunteer(idAsso, idVolunteer);
    	return ResponseEntity.ok().build();
    }
    
    
    @PostMapping("/removeVolunteer")
    public ResponseEntity removeVolunteer(@RequestParam("association_id") Long idAsso, @RequestParam("volunteer_id") Long idVolunteer ) throws HereToCleanException {
    	
    	if(idAsso == null || idVolunteer == null ) {
    		throw new HereToCleanException("La requête est incomplète");
    	}
    	associationService.removeVolunteer(idAsso, idVolunteer);
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/allEvent")
    public ResponseEntity findEvents(@RequestParam("association_id") Long idAsso) throws HereToCleanException {
    	

    	if(idAsso == null  ) {
    		throw new HereToCleanException("La requête est incomplète");
    	}
    	
    	Optional<List<Event>> events = Optional.ofNullable(associationService.getEvents(idAsso));
    	
    	if(!events.isPresent() || events.get().isEmpty()) {
    		throw new HereToCleanException("Il n'y a pas d'évènement pour cette association");
    	}
    	
    	List<EventDTO> eventDTOs = new ArrayList<EventDTO>();
    	
    	for(Event e : events.get()) {
    		eventDTOs.add(EventDTO.EventToEventDTO(e));
    	}
    	
    	return ResponseEntity.ok(eventDTOs);
    }

    @GetMapping("/allCommand")
    public ResponseEntity findCommands(@RequestParam("association_id") Long idAsso) throws HereToCleanException {
    	

    	Optional<Association> asso = Optional.of(associationService.findById(idAsso));
		if(!asso.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		List<CompoCommand> compoCommands = commandService.findCommandByAssociation(idAsso);
		
		List<CompoCommandDTO> compoCommandDTOs = new ArrayList<CompoCommandDTO>();
		
		for(CompoCommand compo : compoCommands) {
			compoCommandDTOs.add(CompoCommandDTO.CompoCommandToCompoCommandDTO(compo));
		}

		return ResponseEntity.ok(compoCommandDTOs);
    }
    
    
    @PutMapping("/validateCommand")
    public ResponseEntity validateCommand(@RequestParam("command") Long idCompoCommand) throws HereToCleanException {
    	commandService.validateCommand(idCompoCommand);
    	return ResponseEntity.ok().build();
    }
    
    
    @PutMapping("/receivedCommand")
    public ResponseEntity receivedCommand(@RequestParam("command") Long idCompoCommand) throws HereToCleanException {
    	commandService.commandReceive(idCompoCommand);
    	return ResponseEntity.ok().build();
    }

}
