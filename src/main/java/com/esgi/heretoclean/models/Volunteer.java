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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
public class Volunteer extends User {
	
	@Email
	@NotNull
	@Column(unique=true)
	private String email;
	
	@Transient
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(mappedBy="volunteer")
	private List<Command> commands = new ArrayList<Command>();
	
	@OneToMany(mappedBy="volunteer")
	private List<Gift> gifts = new ArrayList<Gift>();
	
	@OneToMany(mappedBy = "volunteer")
	private List<AssociationVolunteer> associationVolunteers;
	
	@ManyToMany(mappedBy="volunteers")
	private List<Event> events = new ArrayList<Event>();
	
	
	public Volunteer() {}

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
	
}
