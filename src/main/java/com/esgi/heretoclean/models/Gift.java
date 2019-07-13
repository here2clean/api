package com.esgi.heretoclean.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Transactional
public class Gift {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="amount")
	@NotNull
	private float amount;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn
	private Volunteer volunteer;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn
    private Association association;
	
	public Gift() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Volunteer getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}
	
	
}
