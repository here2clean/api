package com.esgi.heretoclean.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.dao.EventRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.interfaces.EventService;

@Service("EventService")
@Transactional
public class EventServiceImpl implements EventService {
	private final EventRepository eventRepository;
	private final AssociationRepository assoRepo;
	private final VolunteerRepository volunteerRepo;
	
	@Autowired
	public EventServiceImpl(EventRepository eventRepository, AssociationRepository assoRepo,
			VolunteerRepository volunteerRepo) {
		this.eventRepository = eventRepository;
		this.assoRepo = assoRepo;
		this.volunteerRepo = volunteerRepo;
	}
	

	@Override
	public Event registerEvent(Event event) {
		return eventRepository.save(event);
	}


	@Override
	public Optional<Event> findByName(String name) {
		return eventRepository.findByName(name);
	}

	@Override
	public Optional<List<Event>> findByDate(LocalDate date) {
		return eventRepository.findByBeginDate(date);
	}

	@Override
	public Optional<List<Event>> findByLocation(String location) {
		return eventRepository.findByLocation(location);
	}

	@Override
	public List<Event> findAllEvent() {
		return eventRepository.findAll();
	}

	@Override
	public Event updateEvent(Event event) {
		return eventRepository.saveAndFlush(event);
	}

	@Override
	public void delete(String name) {
		eventRepository.deleteByName(name);
	}

	@Override
	public Optional<Event> findById(Long id) {
		return eventRepository.findById((id));
	}

	@Override
	public Optional<List<Volunteer>> findVolunteer(Long idEvent) {
		Event event = eventRepository.getOne(idEvent);
		return Optional.ofNullable(event.getVolunteers());
	}

	@Override
	public Optional<List<Event>> findAllEventByZoneManager(Long idZManager) {
		return null;
	}

	@Override
	public void addVolunteer(String nameEvent) {
		// TODO Auto-generated method stub
		
		Optional<Event> event = eventRepository.findByName(nameEvent);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) auth.getPrincipal();
		Optional<Volunteer> volunteer = volunteerRepo.findOneByEmailIgnoreCase(principal.getUsername());
		
		if(event.isPresent() && volunteer.isPresent()) {
			event.get().getVolunteers().add(volunteer.get());
			eventRepository.saveAndFlush(event.get());
		}
		
	}

}
