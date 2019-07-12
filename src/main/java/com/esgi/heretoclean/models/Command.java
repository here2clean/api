package com.esgi.heretoclean.models;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Entity
@Transactional
public class Command {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="dateCommand")
	@NotNull
	private Date dateCommand; 
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Volunteer volunteer ;
	
	@Column(name="quantity")
	private float quantity;
	
	@Column(name="amount")
	private double amount;
	
	@ManyToMany
	@JoinTable(name = "CompoCommand"
	,joinColumns = @JoinColumn(name="product_id", referencedColumnName="id")
	,inverseJoinColumns = @JoinColumn(name="command_id",referencedColumnName="id"))
	private Set<Product> products;
    
    public Command() {}
    
    public Command(float quantity,double amount,Product...products) {
    	this.quantity = quantity;
    	this.amount = amount;
    	this.products = Stream.of(products).collect(Collectors.toSet());
    	this.products.forEach(x->x.getCommands().add(this));
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCommand() {
		return dateCommand;
	}

	public void setDateCommand(Date dateCommand) {
		this.dateCommand = dateCommand;
	}

	public Volunteer getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	

}
