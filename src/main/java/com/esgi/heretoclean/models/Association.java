package com.esgi.heretoclean.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

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
	private String name;

	@Email
	@Column(name="email")
	private String email;

	@Column(name="numberRna")
	private int numberRna;

	//	@OneToMany(mappedBy="association")
	//	private List<Event> events = new ArrayList<Event>();

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


}

