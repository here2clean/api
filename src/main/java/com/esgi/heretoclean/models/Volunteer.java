package com.esgi.heretoclean.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Transactional
public class Volunteer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name="firstName")
	private String firstName;
	
	@NotNull
	@Column(name="lastName")
	private String lastName;
	
	@NotNull
	@Column(name="birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy")
	private Date birthday;
	
	@NotNull
	@Column(name="address")
	private String address;
	
	@NotNull
	@Column(name="city")
	private String city;
	
	@NotNull
	@Column(name="cityCode")
	private int cityCode;

	@Email
	@NotNull
	@Column(unique=true)
	private String email;
	
	@Transient
	private String password;


	@OneToMany(mappedBy="volunteer")
	private List<Command> commands = new ArrayList<Command>();
	
	@OneToMany(mappedBy="volunteer")
	private List<Gift> gifts = new ArrayList<Gift>();
	
	@OneToMany(mappedBy = "volunteer")
	private List<AssociationVolunteer> associationVolunteers;
	
	@ManyToMany(mappedBy="volunteers")
	private List<Event> events = new ArrayList<Event>();
	
	
	public Volunteer() {}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public List<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}

	public List<AssociationVolunteer> getAssociationVolunteers() {
		return associationVolunteers;
	}

	public void setAssociationVolunteers(List<AssociationVolunteer> associationVolunteers) {
		this.associationVolunteers = associationVolunteers;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	
}
