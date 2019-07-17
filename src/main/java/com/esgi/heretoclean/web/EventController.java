package com.esgi.heretoclean.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.esgi.heretoclean.DTO.EventDTO;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.service.interfaces.AssociationService;
import com.esgi.heretoclean.service.interfaces.EventService;
import com.esgi.heretoclean.service.interfaces.VolunteerService;

import io.netty.util.internal.StringUtil;

@RestController
@RequestMapping("/api/event")
public class EventController {

	private final EventService eventService;
	private final VolunteerService volunteerService;
	private final AssociationService assoService;

	@Autowired
	public EventController(EventService eventService,VolunteerService volunteerService,AssociationService assoService) {
		this.eventService = eventService;
		this.volunteerService = volunteerService;
		this.assoService = assoService;

	}

	@PostMapping("/register")
	public ResponseEntity registerEvent(@RequestBody @Valid Event event, @RequestParam("association_id") Long id) throws HereToCleanException {

		if(id == null) {
			throw new HereToCleanException("La requête est incomplète");
		}

		Optional<Association> asso = Optional.ofNullable(assoService.findById(id));

		if(!asso.isPresent() || asso.get().getId() == null) {
			throw new HereToCleanException("L'association n'existe pas");
		}
		event.setAssociation(asso.get());
		eventService.registerEvent(event);


		return ResponseEntity.ok().build();   		


	}

	@GetMapping("/all")
	public ResponseEntity getEvents() {
		List<EventDTO> eventDTOs = new ArrayList<EventDTO>();

		for(Event e : eventService.findAllEvent()) {
			EventDTO eventDTO = EventDTO.EventToEventDTO(e);
			eventDTOs.add(eventDTO);
		}

		return ResponseEntity.ok(eventDTOs); 
	}

	@GetMapping("/research/name")
	public ResponseEntity getEventByName(@RequestParam("name") String name)throws HereToCleanException {

		if(StringUtil.isNullOrEmpty(name)) {
			throw new HereToCleanException(HttpStatus.BAD_GATEWAY.value(),"Il manque un paramètre");
		}


		Optional<List<Event>> optionalEvent = eventService.findByName(name);
		if(!optionalEvent.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND.value()).body(optionalEvent);
	}

	@PutMapping("/update")
	public ResponseEntity updateEvent(@RequestBody Event event){
		return ResponseEntity.status(HttpStatus.OK.value()).body(eventService.updateEvent(event));
	}

	@DeleteMapping("/delete")
	public ResponseEntity deleteEvent(@RequestParam("id") Long id) {
		eventService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK.value()).build();
	}

	@PostMapping("/addVolunteer")
	public ResponseEntity addVolunteer(@RequestParam("event_id") Long idEvent, @RequestParam("volunteer_id") Long idVolunteer ) throws HereToCleanException {

		if(idEvent == null || idVolunteer == null ) {
			throw new HereToCleanException("La requête est incomplète");
		}
		eventService.addVolunteer(idEvent, idVolunteer);
		return ResponseEntity.ok().build();
	}


	@PostMapping("/removeVolunteer")
	public ResponseEntity removeVolunteer(@RequestParam("event_id") Long idEvent, @RequestParam("volunteer_id") Long idVolunteer ) throws HereToCleanException {

		if(idEvent == null || idVolunteer == null ) {
			throw new HereToCleanException("La requête est incomplète");
		}
		eventService.removeVolunteer(idEvent, idVolunteer);
		return ResponseEntity.ok().build();
	}


	@GetMapping("allByAssocation")
	public ResponseEntity getAllByAssociation(@RequestParam("association_id") Long idAsso) throws HereToCleanException {

		if(idAsso == null  ) {
			throw new HereToCleanException("La requête est incomplète");
		}

		Optional<List<Event>> events = Optional.ofNullable(assoService.getEvents(idAsso));

		if(!events.isPresent() || events.get().isEmpty()) {
			throw new HereToCleanException("Il n'y a pas d'évènement pour cette association");

		}
		
		List<EventDTO> eventDTOs = new ArrayList<EventDTO>();
		
		for(Event e : events.get()) {
			eventDTOs.add(EventDTO.EventToEventDTO(e));
		}

		return ResponseEntity.ok(eventDTOs);
	}
}
