package com.esgi.heretoclean.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="Association_Volunteer")
public class AssociationVolunteer implements Serializable {
	
	@Id
    @ManyToOne
	private Association association;
	
	@Id
    @ManyToOne
    private Volunteer volunteer;

}
