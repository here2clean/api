package com.esgi.heretoclean.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Volunteer {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="birthday")
	private LocalDate birthday;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;

	@Column(name="cityCode")
	private int cityCode;
	
	@OneToMany(mappedBy="volunteer")
	private List<Event> events = new ArrayList<Event>();
	
	@OneToMany(mappedBy="volunteer")
	private List<Gift> gifts = new ArrayList<Gift>();
	
	@OneToMany(mappedBy="volunteer")
	private List<Command> commands = new ArrayList<Command>();

}
