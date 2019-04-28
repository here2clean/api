package com.esgi.heretoclean.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Association;

@Service
public interface AssociationService {
	
	 Association registerAssociation(Association asso);
	
	 Optional<Association> findAssociationByEmail(String email);
	
	 Optional<List<Association>> findAssociationByName(String name);
	 
	 List<Association> findAllAssociation();
	
	 void deleteAssociationByName(String name);
	
	 Association updateAssociation(Association asso);
	 
	 void deleteAssociationByEmail(String email);
	
	 
}
