package com.esgi.heretoclean.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;

@Service
public interface AssociationService {
	
	 Association registerAssociation(Association asso) throws Exception;
	
	 Optional<Association> findAssociationByEmail(String email);
	
	 Optional<Association> findAssociationByName(String name);
	 
	 List<Association> findAllAssociation();
	
	 void deleteAssociationByName(String name);
	
	 Association updateAssociation(Association asso);
	 
//	 Optional<List<Event>> findAllEvent(String nameAssociation);
//
//	 Optional<List<Volunteer>> findAllVolunteer(String nameAssociation);
	 
	 void deleteAssociationByEmail(String email);
	
	 
}
