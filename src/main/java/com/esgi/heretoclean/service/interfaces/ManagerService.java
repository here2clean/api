package com.esgi.heretoclean.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Manager;

@Service
public interface ManagerService {

	public Manager createManager(Manager manager,String emailAssociation);
	
	public List<Event> findAllEvent(String emailManager);
	
	public Association getOneAssociation(String emailManager);
	
	public Manager findByEmail(String email);
	
	public Manager update(Manager m, String emailManager);
	
	public void deleteByEmail(String email);
}
