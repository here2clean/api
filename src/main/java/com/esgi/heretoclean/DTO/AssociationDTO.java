package com.esgi.heretoclean.DTO;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.models.Volunteer;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class AssociationDTO {
	
	private Long id;

	private String name;

	private String email;

	private int numberRna;
	
	private String password;
	
	private String description;
	
	private String urlImage;

	private List<GiftDTO> giftDTOs = new ArrayList<GiftDTO>();

	private List<VolunteerDTO> volunteerDTOs = new ArrayList<VolunteerDTO>();

	private List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

	private List<EventDTO> eventDTOs = new ArrayList<EventDTO>();


	public AssociationDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumberRna() {
		return numberRna;
	}

	public void setNumberRna(int numberRna) {
		this.numberRna = numberRna;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<GiftDTO> getGiftDTOs() {
		return giftDTOs;
	}

	public void setGiftDTOs(List<GiftDTO> giftDTOs) {
		this.giftDTOs = giftDTOs;
	}

	public List<VolunteerDTO> getVolunteerDTOs() {
		return volunteerDTOs;
	}

	public void setVolunteerDTOs(List<VolunteerDTO> volunteerDTOs) {
		this.volunteerDTOs = volunteerDTOs;
	}

	public List<ProductDTO> getProductDTOs() {
		return productDTOs;
	}

	public void setProductDTOs(List<ProductDTO> productDTOs) {
		this.productDTOs = productDTOs;
	}

	public List<EventDTO> getEventDTOs() {
		return eventDTOs;
	}

	public void setEventDTOs(List<EventDTO> eventDTOs) {
		this.eventDTOs = eventDTOs;
	}

	public static AssociationDTO AssociationToAssociationDTO(Association asso) {
		
		AssociationDTO assoDTO = new AssociationDTO();
		
		assoDTO.setId(asso.getId());
		assoDTO.setName(asso.getName());
		assoDTO.setEmail(asso.getEmail());
		assoDTO.setNumberRna(asso.getNumberRna());
		assoDTO.setPassword(asso.getPassword());
		assoDTO.setDescription(asso.getDescription());
		assoDTO.setUrlImage(asso.getUrlImage());
//		assoDTO.setEventDTOs(eventDTOs);
//		assoDTO.setVolunteerDTOs(volunteerDTOs);
//		assoDTO.setGiftDTOs(giftDTOs);
//		assoDTO.setProductDTOs(productDTOs);

		
		
		return assoDTO;
	}
	
}


