package com.esgi.heretoclean.web;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
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

import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.service.interfaces.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
    
    private final EventService eventService;
    
    @Autowired
    public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@PostMapping("/register")
    public ResponseEntity registerEvent(@RequestBody @Valid Event event) throws URISyntaxException {
    	Event newEevent = eventService.registerEvent(event);
    	return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }
    
    @GetMapping("/all")
    public ResponseEntity getEvents() {
        return ResponseEntity.status(HttpStatus.OK.value()).body(eventService.findAllEvent());
    }
    
//    @GetMapping("/volunteer/all")
//    public ResponseEntity getVolunteers(@QueryParam("idEvent") Long idEvent) {
//        return ResponseEntity.status(HttpStatus.OK.value()).body(eventService.findVolunteer(idEvent));
//    }
    
    @GetMapping("/research/name")
    public ResponseEntity getEventByName(@QueryParam("name") String name) {
    	Optional<Event> optionalEvent = eventService.findByName(name);
    	if(!optionalEvent.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();
    	}
    	return ResponseEntity.status(HttpStatus.FOUND.value()).body(optionalEvent.get());
    }
    
    @PutMapping("/update")
    public ResponseEntity updateEvent(@RequestBody Event event){
    	return ResponseEntity.status(HttpStatus.OK.value()).body(eventService.updateEvent(event));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity deleteEvent(@RequestBody @Valid Event event) {
    	eventService.delete(event);
    	return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
    
}
