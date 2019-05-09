package com.esgi.heretoclean.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Gift;

@Service
public interface GiftService {

	public Gift createGift(Gift gift, String emailAssociation, String emailVolunteer);
	
	public List<Gift> findByVolunteer(String email);
	
	public Gift findById(Long id);
	
	public List<Gift> findByAssociation(Long idAssociation);
	
	public Gift update(Gift g,String emailAssociation,String emailVolunteer);
	
	public void deleteById(Long id);
	
	
}
