package com.esgi.heretoclean.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Volunteer {

	@Id
	@GeneratedValue
	@NotNull
	private Long id;

	@Column(name="firstName")
	@NotNull
	private String firstName;

	@Column(name="lastName")
	@NotNull
	private String lastName;

	@Column(name="birthday")
	@NotNull
	private LocalDate birthday;

	@Column(name="address")
	@NotNull
	private String address;

	@Column(name="city")
	@NotNull
	private String city;

	@Column(name="cityCode")
	@NotNull
	private int cityCode;

	@Column(name="isZoneManager")
	@NotNull
	private boolean isZoneManager;

	@ManyToMany(mappedBy="volunteers")
	private List<Event> events = new ArrayList<Event>();

	@OneToMany(mappedBy = "volunteer")
	private List<AssociationVolunteer> associationVolunteers;

	@OneToMany(mappedBy="volunteer")
	private List<Gift> gifts = new ArrayList<Gift>();

	@OneToMany(mappedBy="volunteer")
	private List<Command> commands = new ArrayList<Command>();

	@OneToOne
    @JoinColumn(unique = true)
	@NotNull
	private Association association;

	public Volunteer() {
	}

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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
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
	public boolean isZoneManager() {
		return isZoneManager;
	}

	public void setZoneManager(boolean isZoneManager) {
		this.isZoneManager = isZoneManager;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<AssociationVolunteer> getAssociationVolunteers() {
		return associationVolunteers;
	}

	public void setAssociationVolunteers(List<AssociationVolunteer> associationVolunteers) {
		this.associationVolunteers = associationVolunteers;
	}

	public List<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}




}
