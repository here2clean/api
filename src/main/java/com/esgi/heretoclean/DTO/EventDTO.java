package com.esgi.heretoclean.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;

public class EventDTO {
	
	private Long id;
	private String name;
	private LocalDate beginDate;
	private LocalDate endDate;
	private String description;
	private String location;
	private String urlImage;
	private List<VolunteerDTO> volunteerDTOs = new ArrayList<VolunteerDTO>();
	private AssociationDTO associationDTO;
	private int nbVolunteer;
	
	public EventDTO() {
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

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<VolunteerDTO> getVolunteerDTOs() {
		return volunteerDTOs;
	}

	public void setVolunteerDTOs(List<VolunteerDTO> volunteerDTOs) {
		this.volunteerDTOs = volunteerDTOs;
	}

	public AssociationDTO getAssociationDTO() {
		return associationDTO;
	}

	public void setAssociationDTO(AssociationDTO associationDTO) {
		this.associationDTO = associationDTO;
	}
	
	
	
	
	public int getNbVolunteer() {
		return nbVolunteer;
	}

	public void setNbVolunteer(int nbVolunteer) {
		this.nbVolunteer = nbVolunteer;
	}

	public static EventDTO EventToEventDTO(Event e) {
		EventDTO eventDTO = new EventDTO();
		
		eventDTO.setId(e.getId());
		eventDTO.setLocation(e.getLocation());
		eventDTO.setName(e.getName());
		eventDTO.setBeginDate(e.getBeginDate());
		eventDTO.setEndDate(e.getEndDate());
		eventDTO.setUrlImage(e.getUrlImage());
		eventDTO.setDescription(e.getDescription());
		eventDTO.setNbVolunteer(e.getVolunteers().size());
		
		List<VolunteerDTO> volunteerDTOs = new ArrayList<VolunteerDTO>();
		
		for(Volunteer v : e.getVolunteers()) {
			VolunteerDTO vDTO = VolunteerDTO.VolunteerToVolunteerDTO(v);
			volunteerDTOs.add(vDTO);
		}
		
		eventDTO.setVolunteerDTOs(volunteerDTOs);
		
		
//		eventDTO.seassociationDTO(e.getAssociation());
		return eventDTO;
	}
	
	

}
