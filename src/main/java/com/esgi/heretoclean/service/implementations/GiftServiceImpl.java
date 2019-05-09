package com.esgi.heretoclean.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.dao.GiftRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.service.interfaces.GiftService;

@Service
public class GiftServiceImpl implements GiftService {

	@Autowired
	private GiftRepository giftRepo;

	@Autowired
	private VolunteerRepository volunteerRepo;

	@Autowired
	private AssociationRepository associationRepo;

	@Override
	public Gift createGift(Gift gift,String emailAssociation,String emailVolunteer) {
		gift.setVolunteer(volunteerRepo.findOneByEmailIgnoreCase(emailVolunteer).get());
		gift.setAssociation(associationRepo.findByEmail(emailAssociation).get());
		return giftRepo.save(gift);
	}

	@Override
	public List<Gift> findByVolunteer(String email) {
		return giftRepo.findByVolunteer(email);
	}


	@Override
	public Gift findById(Long id) {
		return giftRepo.getOne(id);
	}

	@Override
	public List<Gift> findByAssociation(Long idAssociation) {
		return giftRepo.findByAssociation(idAssociation);
	}


	@Override
	public void deleteById(Long id) {
		giftRepo.deleteById(id);
	}

	@Override
	public Gift update(Gift g, String emailAssociation, String emailVolunteer) {
		g.setVolunteer(volunteerRepo.findOneByEmailIgnoreCase(emailVolunteer).get());
		g.setAssociation(associationRepo.findByEmail(emailAssociation).get());
		return giftRepo.saveAndFlush(g);	
	}

}
