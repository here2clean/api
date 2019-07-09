package com.esgi.heretoclean.web;

import java.net.URISyntaxException;
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

import com.esgi.heretoclean.HeretocleanApplication;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.service.interfaces.EventService;

import io.netty.util.internal.StringUtil;

@RestController
@RequestMapping("/api/event")
public class EventController {
    
    private final EventService eventService;
    
    @Autowired
    public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@PostMapping("/register")
    public ResponseEntity registerEvent(@RequestBody @Valid Event event) throws HereToCleanException {
    	int eventCode = eventService.registerEvent(event);
    	
    	if(eventCode == 200) {
    		return ResponseEntity.status(HttpStatus.CREATED.value()).build();    		
    	}
    	
    	throw new HereToCleanException("Évènement non créé");
    }
    
    @GetMapping("/all")
    public ResponseEntity getEvents() {
        return ResponseEntity.status(HttpStatus.OK.value()).body(eventService.findAllEvent());
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
    public ResponseEntity updateEvent(@RequestParam("name") String name, @RequestBody Event event){
    	return ResponseEntity.status(HttpStatus.OK.value()).body(eventService.updateEvent(event));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity deleteEvent(@RequestParam("name") String name) {
    	eventService.delete(name);
    	return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
    
    
}
