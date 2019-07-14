package com.esgi.heretoclean.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Association;

@Service
public interface AssociationService {
	
	 Association registerAssociation(Association asso) throws Exception;
	
	 Optional<Association> findAssociationByEmail(String email);
	
	 List<Association> findAssociationByName(String name);
	 
	List<Association> findAllAssociation();
	
	Association findById(Long id);
	
	 void deleteAssociationByName(String name);
	
	 Association updateAssociation(Association asso);
	 
//	 Optional<List<Event>> findAllEvent(String nameAssociation);
//
//	 Optional<List<Volunteer>> findAllVolunteer(String nameAssociation);
	 
	 void deleteAssociationByEmail(String email);
	 
	 void addVolunteer(Long idAssociation,Long idVolunteer) throws HereToCleanException;
	 
	 void removeVolunteer(Long idAssociation,Long idVolunteer);
	
	 
}
