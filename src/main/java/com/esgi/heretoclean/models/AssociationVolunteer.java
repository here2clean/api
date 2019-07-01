package com.esgi.heretoclean.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Entity(name="Association_Volunteer")
@Transactional
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
