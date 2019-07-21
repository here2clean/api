package com.esgi.heretoclean.DTO;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.models.Volunteer;

public class GiftDTO {

	private Long id;
	private float amount;

	private Volunteer volunteer;
	private Association association;
	
	
	public GiftDTO() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public Volunteer getVolunteer() {
		return volunteer;
	}


	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}


	public Association getAssociation() {
		return association;
	}


	public void setAssociation(Association association) {
		this.association = association;
	}
	
	public static GiftDTO GiftToGiftDTO(Gift g) {
		GiftDTO giftDTO = new GiftDTO();
		giftDTO.setId(g.getId());
		giftDTO.setAmount(g.getAmount());
		return giftDTO;
	}
	
	
	
	

}
