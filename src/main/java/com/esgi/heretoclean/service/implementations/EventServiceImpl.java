package com.esgi.heretoclean.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.esgi.heretoclean.dao.EventRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.interfaces.EventService;

@Service("EventService")
@Transactional
public class EventServiceImpl implements EventService {
	private final EventRepository eventRepository;
	private final VolunteerRepository volunteerRepo;
	
	@Autowired
	public EventServiceImpl(EventRepository eventRepository, VolunteerRepository volunteerRepo) {
		this.eventRepository = eventRepository;
		this.volunteerRepo = volunteerRepo;
	}
	

	@Override
	public int registerEvent(Event event) {
		Event e = eventRepository.save(event);
		if(e != null) {
			return 201;
		}
		return 400;
	}


	@Transactional(readOnly = true)
	@Override
	public Optional<Event> findByName(String name) {
		return eventRepository.findByName(name);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<List<Event>> findByDate(LocalDate date) {
		return eventRepository.findByBeginDate(date);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<List<Event>> findByLocation(String location) {
		return eventRepository.findByLocation(location);
	}

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	@Override
	public Optional<Event> findById(Long id) {
		return eventRepository.findById((id));
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<List<Volunteer>> findVolunteer(Long idEvent) {
		Event event = eventRepository.getOne(idEvent);
		return Optional.ofNullable(event.getVolunteers());
	}


	@Override
	public void addVolunteer(String nameEvent,String emailVolunteer) {
		// TODO Auto-generated method stub
		
		Optional<Event> event = eventRepository.findByName(nameEvent);
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//User principal = (User) auth.getPrincipal();
		Optional<Volunteer> volunteer = volunteerRepo.findOneByEmailIgnoreCase(emailVolunteer);
		
		if(event.isPresent() && volunteer.isPresent()) {
			event.get().getVolunteers().add(volunteer.get());
			eventRepository.saveAndFlush(event.get());
		}
		
	}

}
