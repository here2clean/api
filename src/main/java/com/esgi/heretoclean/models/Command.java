package com.esgi.heretoclean.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Transactional
public class Command {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="orderStatus")
	@NotNull
	private String orderStatus;
	
	@Column(name="dateCommand")
	@NotNull
	private Date dateCommand; 
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Volunteer volunteer ;
	
	@ManyToMany(targetEntity=Product.class)
	private List<Product> products = new ArrayList<Product>();
	
	@Column(name="amount")
	private double amount;
	
//    @OneToMany(mappedBy = "command")
//	private List<CommandCompo> commandCompos = new ArrayList<CommandCompo>();
    @Column(name="Compo")
    private Map<Product,Integer> compoCommand = new HashMap<Product, Integer>();
    
    public Command() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Map<Product, Integer> getCompoCommand() {
		return compoCommand;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCompoCommand(Map<Product, Integer> compoCommand) {
		this.compoCommand = compoCommand;
	}
	
	
	

//	public List<CommandCompo> getCommandCompos() {
//		return commandCompos;
//	}
//
//	public void setCommandCompos(List<CommandCompo> commandCompos) {
//		this.commandCompos = commandCompos;
//	}
	
	
    
    
	

}
