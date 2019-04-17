package com.esgi.heretoclean.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private long id;
	
	@Column(name="orderStatus")
	private String orderStatus;
	
	@Column(name="dateCommand")
	private LocalDate dateCommand; 
	
//	private ProductOrder productOrder;
	
	
	

}
