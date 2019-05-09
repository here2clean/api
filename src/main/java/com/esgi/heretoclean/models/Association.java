package com.esgi.heretoclean.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
	
    @OneToMany(mappedBy = "association")
	private List<Gift> gifts = new ArrayList<Gift>();
    
    @OneToOne(mappedBy="association")
    private Manager manager;

    @OneToMany(mappedBy = "association")
	private List<AssociationVolunteer> associationVolunteers;
    
	@OneToMany(mappedBy="association")
	private List<Product> products = new ArrayList<Product>();
	
	public Association() {
	}

	public Association(Long id, String name, @Email String email, int numberRna) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.numberRna = numberRna;
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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<AssociationVolunteer> getAssociationVolunteers() {
		return associationVolunteers;
	}

	public void setAssociationVolunteers(List<AssociationVolunteer> associationVolunteers) {
		this.associationVolunteers = associationVolunteers;
	}

	
}

