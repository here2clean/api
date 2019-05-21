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

	private final GiftRepository giftRepo;

	private final VolunteerRepository volunteerRepo;

	private final AssociationRepository associationRepo;
	
	@Autowired
	public GiftServiceImpl(GiftRepository giftRepo, VolunteerRepository volunteerRepo,
			AssociationRepository associationRepo) {
		this.giftRepo = giftRepo;
		this.volunteerRepo = volunteerRepo;
		this.associationRepo = associationRepo;
	}


	@Override
	public Gift createGift(Gift gift,String emailAssociation,String emailVolunteer) {
		gift.setVolunteer(volunteerRepo.findOneByEmailIgnoreCase(emailVolunteer).get());
		gift.setAssociation(associationRepo.findByEmail(emailAssociation).get());
		return giftRepo.save(gift);
	}
}
