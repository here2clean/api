package com.esgi.heretoclean.service.implementations;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esgi.heretoclean.configuration.MyProperties;
import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.CompoCommand;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.interfaces.AssociationService;
import com.google.gson.Gson;

@Service("AssociationService")
@Transactional
public class AssociationServiceImpl implements AssociationService{

	private final AssociationRepository assoRepository;
	private final VolunteerRepository volunteerRepo;

	@Autowired
	public AssociationServiceImpl(AssociationRepository assoRepository,VolunteerRepository volunteerRepo) {
		this.assoRepository = assoRepository;
		this.volunteerRepo = volunteerRepo;
	}


	@Override
	public Association registerAssociation(Association asso) throws HereToCleanException {
		return assoRepository.save(asso);
	}


	@Transactional(readOnly = true)
	@Override
	public Optional<Association> findAssociationByEmail(String email) {
		return assoRepository.findByEmail(email);
	}


	@Override
	public Association updateAssociation(Association asso) {
		return assoRepository.saveAndFlush(asso);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Association> findAssociationByName(String name) {
		return assoRepository.findByNameContaining(name);
	}

	@Override
	public void deleteAssociationByName(String name) {
		assoRepository.deleteByName(name);		
	}

	@Override
	public void deleteAssociationByEmail(String email) {
		assoRepository.deleteByEmail(email);	

	}

	@Transactional(readOnly = true)
	@Override
	public List<Association> findAllAssociation() {
		return assoRepository.findAll();
	}


	@Override
	public Association findById(Long id) {
		return assoRepository.getOne(id);
	}


	@Override
	public void addVolunteer(Long idAssociation, Long idVolunteer) throws HereToCleanException {
		
		Optional<Association> asso = Optional.ofNullable(assoRepository.getOne(idAssociation));
    	Optional<Volunteer> volunteer = Optional.ofNullable(volunteerRepo.getOne(idVolunteer));
    	
    	if(!asso.isPresent() || asso.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Association non trouvé");
    	}
    	
    	if(!volunteer.isPresent() || volunteer.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Bénévole non trouvé");
    	}
    	
    	asso.get().getVolunteers().add(volunteer.get());
    	
    	assoRepository.save(asso.get());
	}


	@Override
	public void removeVolunteer(Long idAssociation, Long idVolunteer) throws HereToCleanException {
		Optional<Association> asso = Optional.ofNullable(assoRepository.getOne(idAssociation));
    	Optional<Volunteer> volunteer = Optional.ofNullable(volunteerRepo.getOne(idVolunteer));
    	
    	if(!asso.isPresent() || asso.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Association non trouvé");
    	}
    	
    	if(!volunteer.isPresent() || volunteer.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Bénévole non trouvé");
    	}
    	
    	asso.get().getVolunteers().remove(volunteer.get());
    	
    	assoRepository.save(asso.get());		
	}


	@Override
	public List<Event> getEvents(Long id) throws HereToCleanException {
		
		Optional<Association> asso = Optional.ofNullable(assoRepository.getOne(id));
		if(!asso.isPresent() || asso.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Association non trouvé");
    	}
		
		return asso.get().getEvents();
	}


	@Override
	public List<CompoCommand> getCommands(Long idAssociation) throws HereToCleanException {
		// TODO Auto-generated method stub
		
		Optional<Association> asso = Optional.ofNullable(assoRepository.getOne(idAssociation));
		if(!asso.isPresent() || asso.get().getId() == null ) {
    		throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Association non trouvé");
    	}
		
		return asso.get().getCompoCommand();
	}
	


}
