package com.esgi.heretoclean.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class CompoCommand {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "command_id")
	private Command command;

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
	private Product product;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "association_id")
	private Association association;
	
	private int quantity;

	public CompoCommand() {
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Association getAssociation() {
		return association;
	}


	public void setAssociation(Association association) {
		this.association = association;
	}
	
	
	
	
	

}
