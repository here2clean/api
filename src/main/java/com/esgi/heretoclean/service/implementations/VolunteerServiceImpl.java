package com.esgi.heretoclean.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.interfaces.VolunteerService;
@Transactional
@Service
public class VolunteerServiceImpl implements VolunteerService {
	
	private final VolunteerRepository volunteerRepo;
	
	@Autowired
	public VolunteerServiceImpl(VolunteerRepository volunteerRepo) {
		this.volunteerRepo = volunteerRepo;
	}

	@Override
	@Transactional(readOnly = true)
	public Volunteer findVolunteerById(Long id) {
		return volunteerRepo.getOne(id);
	}

	@Override
	public Volunteer registerVolunteer(Volunteer volunteer) {
		return volunteerRepo.save(volunteer);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Volunteer> findVolunteerByEmail(String email) {
		return volunteerRepo.findOneByEmailIgnoreCase(email);
	}

	@Override
	public Volunteer update(String email,Volunteer v) {
		
		if(!volunteerRepo.findOneByEmailIgnoreCase(email).isPresent()) {
			return null;
		}
		
		Volunteer updateVolunteer = volunteerRepo.findOneByEmailIgnoreCase(email).get();
		updateVolunteer = v;
		return volunteerRepo.saveAndFlush(updateVolunteer);
	}

	@Override
	public void deleteByEmail(String email) {
		volunteerRepo.deleteByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Gift> findAllGift(String email) {
//		return 	volunteerRepo.findOneByEmailIgnoreCase(email).get().getGifts();
		return null;
	}

	@Override
	public List<Association> findAllAssociation(String email) {
//		return volunteerRepo.findOneByEmailIgnoreCase(email).get().getAssociationVolunteers();
		return null;
	}

	@Override
	public List<Event> findAllEvent(String email) {
//		return volunteerRepo.findOneByEmailIgnoreCase(email).get().getEvents();
		return null;
	}

}
