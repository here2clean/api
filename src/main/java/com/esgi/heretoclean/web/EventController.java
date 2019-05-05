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
    
    @Autowired
    private EventService eventService;
    
    @PostMapping("/register")
    public Response registerEvent(@RequestBody @Valid Event event) throws URISyntaxException {
    	Event newEevent = eventService.registerEvent(event);
    	return Response.status(HttpStatus.CREATED.value()).entity(newEevent).build();
    }
    
    @GetMapping("/all")
    public Response getEvents() {
        return Response.status(HttpStatus.OK.value()).entity(eventService.findAllEvent()).build();
    }
    
    @GetMapping("/volunteer/all")
    public Response getVolunteers(@QueryParam("idEvent") Long idEvent) {
        return Response.status(HttpStatus.OK.value()).entity(eventService.findVolunteer(idEvent)).build();
    }
    
    @GetMapping("/research/name")
    public Response getEventByName(@QueryParam("name") String name) {
    	Optional<Event> optionalEvent = eventService.findByName(name);
    	if(!optionalEvent.isPresent()) {
            return Response.status(HttpStatus.NOT_FOUND.value()).build();
    	}
    	return Response.status(HttpStatus.FOUND.value()).entity(optionalEvent.get()).build();
    }
    
    @PutMapping("/update")
    public Response updateEvent(@RequestBody Event event){
    	return Response.status(HttpStatus.OK.value()).entity(eventService.updateEvent(event)).build();
    }
    
    @DeleteMapping("/delete")
    public Response deleteEvent(@RequestBody @Valid Event event) {
    	eventService.delete(event);
    	return Response.status(HttpStatus.OK.value()).build();
    }
    
}
