package com.esgi.heretoclean.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.esgi.heretoclean.models.Association;
//import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Gift;
import com.esgi.heretoclean.models.Volunteer;
import com.fasterxml.jackson.annotation.JsonFormat;

public class VolunteerDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthday;
	private String address;
	private String city;
	private int cityCode;
	private String email;
	private String password;
//	private List<CommandDTO> commands = new ArrayList<CommandDTO>();
	private List<GiftDTO> gifts = new ArrayList<GiftDTO>();
	private List<AssociationDTO> associations = new ArrayList<AssociationDTO>();
	private List<EventDTO> events = new ArrayList<EventDTO>();
	
	public VolunteerDTO() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
//	public List<CommandDTO> getCommands() {
//		return commands;
//	}
//
//	public void setCommands(List<CommandDTO> commands) {
//		this.commands = commands;
//	}

	public List<GiftDTO> getGifts() {
		return gifts;
	}

	public void setGifts(List<GiftDTO> gifts) {
		this.gifts = gifts;
	}

	public List<AssociationDTO> getAssociations() {
		return associations;
	}

	public void setAssociations(List<AssociationDTO> associations) {
		this.associations = associations;
	}

	public List<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(List<EventDTO> events) {
		this.events = events;
	}

	public static VolunteerDTO VolunteerToVolunteerDTO(Volunteer v) {
		VolunteerDTO volunteerDTO = new VolunteerDTO();
		
		volunteerDTO.setId(v.getId());
		volunteerDTO.setAddress(v.getAddress());
//		volunteerDTO.associations(v.getAssociations());
		volunteerDTO.setBirthday(v.getBirthday());
		volunteerDTO.setCity(v.getCity());
		volunteerDTO.setEmail(v.getEmail());
		volunteerDTO.setCityCode(v.getCityCode());
//		volunteerDTO.setEvents(v.getEvents());
		volunteerDTO.setPassword(v.getPassword());
		volunteerDTO.setLastName(v.getLastName());
		
		List<EventDTO> eventDTOs = new ArrayList<EventDTO>();
		List<AssociationDTO> associationDTOs = new ArrayList<AssociationDTO>();
		
		
		for(Event e: v.getEvents()) {
			EventDTO eventDTO = EventDTO.EventToEventDTO(e);
			eventDTOs.add(eventDTO);
		}
		
//		
//		for(Association a: v.getAssociations()) {
//			AssociationDTO assoDTO = AssociationDTO.AssociationToAssociationDTO(a);
//			associationDTOs.add(assoDTO);
//		}
		volunteerDTO.setEvents(eventDTOs);
//		volunteerDTO.setAssociations(associationDTOs); 
		
		return volunteerDTO;
		
		
	}
}
