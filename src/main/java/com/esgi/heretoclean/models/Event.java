package com.esgi.heretoclean.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Event {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="beginDate")
	private LocalDate beginDate;
	
	@Column(name="endDate")
	private LocalDate endDate;
	
	@Column(name="description")
	private String description;
	
	
	@Column(name="location")
	private String location;
	
	@Column(name="urlImage")
	private String urlImage;
	
	@ManyToOne
	@JoinColumn
	private Association association;
	
}
