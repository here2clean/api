package com.esgi.heretoclean.service.implementations;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esgi.heretoclean.configuration.MyProperties;
import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.service.interfaces.AssociationService;
import com.google.gson.Gson;

@Service("AssociationService")
@Transactional
public class AssociationServiceImpl implements AssociationService{

	private final AssociationRepository assoRepository;
	private final MyProperties props;

	@Autowired
	public AssociationServiceImpl(AssociationRepository assoRepository, MyProperties props) {
		this.assoRepository = assoRepository;
		this.props = props;
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


}
