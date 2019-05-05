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
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Product {

	@Id
	@GeneratedValue
	@NotNull
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
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Command command;
	
	@ManyToMany(mappedBy = "products")
	private List<Command> commands = new ArrayList<Command>();
	
    @OneToMany(mappedBy = "product")
	private List<CommandCompo> commandCompo = new ArrayList<CommandCompo>();
    
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

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public List<CommandCompo> getCommandCompo() {
		return commandCompo;
	}

	public void setCommandCompo(List<CommandCompo> commandCompo) {
		this.commandCompo = commandCompo;
	}
    
    
}
