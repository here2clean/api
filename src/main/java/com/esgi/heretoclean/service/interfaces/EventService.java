package com.esgi.heretoclean.service.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;

@Service
public interface EventService {

	Event registerEvent(Event event);

	Optional<Event> findByName(String name);

	Optional<List<Volunteer>> findVolunteer(Long id);

	Optional<List<Event>> findByDate(LocalDate date);

	Optional<List<Event>> findByLocation(String location);
	
	Optional<List<Event>> findAllEventByZoneManager(Long idZManager);

	List<Event> findAllEvent();

	Event updateEvent(Event event);
	
	Optional<Event> findById(Long id);
	
	int getCountForEvent(String name);
	
	void delete(String email);

}
