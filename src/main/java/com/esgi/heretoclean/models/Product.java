package com.esgi.heretoclean.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private float price;
	
	@ManyToOne
	@JoinColumn
	private Association association;
	
	@ManyToOne
	@JoinColumn
	private Command command;
	
	@ManyToMany(mappedBy = "products")
	private List<Command> commands = new ArrayList<Command>();
	
    @OneToMany(mappedBy = "product")
	private List<CommandCompo> commandCompo = new ArrayList<CommandCompo>();
}
