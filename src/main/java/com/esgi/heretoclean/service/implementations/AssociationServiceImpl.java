package com.esgi.heretoclean.service.implementations;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.interfaces.AssociationService;

@Service("AssociationService")
@Transactional
public class AssociationServiceImpl implements AssociationService{

	private final AssociationRepository assoRepository;

	@Autowired
	public AssociationServiceImpl(AssociationRepository assoRepository) {
		this.assoRepository = assoRepository;
	}

	@Override
	public Association registerAssociation(Association asso) {
		return assoRepository.save(asso);
	}


	@Override
	public Optional<Association> findAssociationByEmail(String email) {
		return assoRepository.findByEmail(email);
	}


	@Override
	public Association updateAssociation(Association asso) {
		return assoRepository.saveAndFlush(asso);
	}

	@Override
	public Optional<List<Association>> findAssociationByName(String name) {
		return assoRepository.findByName(name);
	}

	@Override
	public void deleteAssociationByName(String name) {
		assoRepository.deleteByName(name);		
	}

	@Override
	public void deleteAssociationByEmail(String email) {
		assoRepository.deleteByEmail(email);	}

	@Override
	public List<Association> findAllAssociation() {
		return assoRepository.findAll();
	}


}
