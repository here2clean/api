package com.esgi.heretoclean.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class CommandProductPK implements Serializable {

	@JsonBackReference
	@ManyToOne
	private Command command;
	
	@ManyToOne
	private Product product;
	
	
	public CommandProductPK() {}


	public Command getCommand() {
		return command;
	}


	public void setCommand(Command command) {
		this.command = command;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}
