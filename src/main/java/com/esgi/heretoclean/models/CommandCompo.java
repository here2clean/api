package com.esgi.heretoclean.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity(name="CommandCompo")
public class CommandCompo implements Serializable {
	
	@Column(name="quantity")
	private int quantity;
	
	@Id
    @ManyToOne
    @JoinColumn
    @NotNull
	private Command command;
    
    @Id
    @ManyToOne
    @JoinColumn
    @NotNull
    private Product product;

}
