package com.esgi.heretoclean.models;

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
public class Gift {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="amount")
	private float amount;
	
	@ManyToOne
	@JoinColumn
	private Volunteer volunteer;

	
}
