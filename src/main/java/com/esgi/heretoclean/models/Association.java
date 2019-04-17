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
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Association {
	
	@Id
	@GeneratedValue
	private long id;

	@Column(name="name")
	private String name;
	
	@Email
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="numberRna")
	private int numberRna;
	
	@OneToMany(mappedBy="association")
	private List<Event> events = new ArrayList<Event>();
	
	@OneToMany(mappedBy="association")
	private List<Product> products = new ArrayList<Product>();


}

