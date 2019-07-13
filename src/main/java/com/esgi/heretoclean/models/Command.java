package com.esgi.heretoclean.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Transactional
public class Command {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="dateCommand")
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dateCommand; 
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Volunteer volunteer ;
	
	
	@JsonManagedReference

	@OneToMany
//	@OneToMany(mappedBy="pk.command")
//	@Valid
	private List<CommandProduct> commandProducts = new ArrayList<CommandProduct>();
    
	
	@Transient
	public double getTotalOrderPrice() {
		double res = 0;
		List<CommandProduct> commandProducts = getCommandProducts();
		for(CommandProduct cp: commandProducts) {
			res += cp.getTotalPrice();
		}
		
		return res;
	}
	
	
	@Transient
	public int getNumberOfProducts() {
		return this.commandProducts.size();
	}
	
	public Command() {}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Volunteer getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

	public LocalDate getDateCommand() {
		return dateCommand;
	}

	public void setDateCommand(LocalDate dateCommand) {
		this.dateCommand = dateCommand;
	}

	public List<CommandProduct> getCommandProducts() {
		return commandProducts;
	}

	public void setCommandProducts(List<CommandProduct> commandProducts) {
		this.commandProducts = commandProducts;
	}

	
	

	
}
