package com.esgi.heretoclean.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.dao.ManagerRepository;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Manager;
import com.esgi.heretoclean.service.interfaces.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerRepository managerRepo;
	
	@Autowired
	private AssociationRepository assoRepo;

	@Override
	public Manager createManager(Manager manager, String emailAssociation) {
		Association a = assoRepo.findByEmail(emailAssociation).get();
		manager.setAssociation(a);
		return managerRepo.save(manager);
	}

	@Override
	public List<Event> findAllEvent(String emailManager) {
		return managerRepo.findByEmail(emailManager).getEvents();
	}

	@Override
	public Association getOneAssociation(String emailManager) {
		return managerRepo.findByEmail(emailManager).getAssociation();
	}

	@Override
	public Manager update(Manager m, String emailAssociation) {
		m.setAssociation(assoRepo.findByEmail(emailAssociation).get());
		return managerRepo.saveAndFlush(m);
	}

	@Override
	public void deleteByEmail(String email) {
		managerRepo.deleteByEmail(email);
	}

	@Override
	public Manager findByEmail(String email) {
		return managerRepo.findByEmail(email);
	}
}
