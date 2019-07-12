package com.esgi.heretoclean.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;


@Entity
@Transactional
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name")
	@NotNull
	private String name;
	
	@Column(name="description")
	@NotNull
	private String description;
	
	@Column(name="price")
	@NotNull
	private float price;
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Association association;
	
    public Product() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}
}
