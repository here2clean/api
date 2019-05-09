package com.esgi.heretoclean.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.models.Volunteer;


@Service
public interface VolunteerService {

	Volunteer registerVolunteer(Volunteer volunteer);
	
	Volunteer findVolunteerById(Long id);
	
	Optional<Volunteer> findVolunteerByEmail(String email);
	
	Volunteer update(Volunteer v);
	
	void deleteByEmail(String eamil);
	
	List<Gift> findAllGift(String email);
	
	List<Association> findAllAssociation(String email);
	
	List<Event> findAllEvent(String email);
}
