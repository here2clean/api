package com.esgi.heretoclean.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Gift;

@Service
public interface GiftService {
	public Gift createGift(Gift gift, String emailAssociation, String emailVolunteer);
}
