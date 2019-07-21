package com.esgi.heretoclean.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;
import com.esgi.heretoclean.dao.EventRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.exception.HereToCleanException;
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
	public Optional<List<Event>> findByName(String name) {
		return eventRepository.findByNameContaining(name);
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
	public void deleteById(Long id) {
		eventRepository.deleteById(id);
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
	public void addVolunteer(Long idEvent,Long idVolunteer) throws HereToCleanException {
		// TODO Auto-generated method stub
		
		Optional<Event> event = Optional.ofNullable(eventRepository.getOne(idEvent));
    	Optional<Volunteer> volunteer = Optional.ofNullable(volunteerRepo.getOne(idVolunteer));
    	
    	if(!event.isPresent() || event.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Évènement non trouvé");
    	}
    	
    	if(!volunteer.isPresent() || volunteer.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Bénévole non trouvé");
    	}
    	
    	
    	event.get().getVolunteers().add(volunteer.get());
    	
    	eventRepository.save(event.get());
	}


	@Override
	public void removeVolunteer(Long idEvent, Long idVolunteer) throws HereToCleanException {
		
		Optional<Event> event = Optional.ofNullable(eventRepository.getOne(idEvent));
    	Optional<Volunteer> volunteer = Optional.ofNullable(volunteerRepo.getOne(idVolunteer));
    	
    	if(!event.isPresent() || event.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Évènement non trouvé");
    	}
    	
    	if(!volunteer.isPresent() || volunteer.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Bénévole non trouvé");
    	}
    	
    	event.get().getVolunteers().remove(volunteer.get());
	}

}
