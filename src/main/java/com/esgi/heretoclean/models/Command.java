package com.esgi.heretoclean.models;

import java.time.LocalDate;
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
public class Command {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="orderStatus")
	private String orderStatus;
	
	@Column(name="dateCommand")
	private LocalDate dateCommand; 
	
	@ManyToOne
	@JoinColumn
	private Volunteer volunteer ;
	
	@ManyToMany(targetEntity=Product.class)
	private List<Product> products = new ArrayList<Product>();
	
    @OneToMany(mappedBy = "command")
	private List<CommandCompo> commandCompos = new ArrayList<CommandCompo>();
	

}
