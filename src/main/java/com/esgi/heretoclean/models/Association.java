package com.esgi.heretoclean.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Association {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name="name")
	@NotNull
	private String name;

	@Email
	@Column(name="email")
	@NotNull
	private String email;

	@Column(name="numberRna")
	@NotNull
	private int numberRna;

	@Transient
	private String password;

	@NotNull
	@Column(name="description")
	private String description;

	@Column(name="image")
	private String urlImage;

	@OneToMany(mappedBy = "association",fetch=FetchType.LAZY)
	private List<Gift> gifts = new ArrayList<Gift>();

	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "association_volunteer",
	joinColumns = @JoinColumn(name = "volunteer_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "association_id", referencedColumnName = "id"))
	private List<Volunteer> volunteers = new ArrayList<Volunteer>();

	@OneToMany(mappedBy="association",fetch=FetchType.LAZY)
	private List<Product> products = new ArrayList<Product>();

	@OneToMany(mappedBy="association",fetch=FetchType.LAZY)
	private List<Event> events = new ArrayList<Event>();
	
	
	@OneToMany(mappedBy="association",fetch=FetchType.LAZY)
	private List<CompoCommand> compoCommand = new ArrayList<CompoCommand>();

	public Association() {
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
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


	public List<Volunteer> getVolunteers() {
		return volunteers;
	}


	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}


	public String getUrlImage() {
		return urlImage;
	}


	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}


	public List<Event> getEvents() {
		return events;
	}


	public void setEvents(List<Event> events) {
		this.events = events;
	}


	public List<CompoCommand> getCompoCommand() {
		return compoCommand;
	}


	public void setCompoCommand(List<CompoCommand> compoCommand) {
		this.compoCommand = compoCommand;
	}


}

