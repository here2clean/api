package com.esgi.heretoclean.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name="Association_Volunteer")
public class AssociationVolunteer implements Serializable {
	
	@Id
    @ManyToOne
    @NotNull
	private Association association;
	
	@Id
    @ManyToOne
    @NotNull
    private Volunteer volunteer;

}
