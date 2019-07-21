package com.esgi.heretoclean.service.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;

@Service
public interface EventService {

	int registerEvent(Event event);

	Optional<List<Event>> findByName(String name);

	Optional<List<Volunteer>> findVolunteer(Long id);

	Optional<List<Event>> findByDate(LocalDate date);

	Optional<List<Event>> findByLocation(String location);

	List<Event> findAllEvent();

	Event updateEvent(Event event);
	
	Optional<Event> findById(Long id);
	
	void deleteById(Long id);
	
	void addVolunteer(Long idEvent,Long idVolunteer) throws HereToCleanException;
	
	void removeVolunteer(Long idEvent,Long idVolunteer) throws HereToCleanException;
	
	

}
