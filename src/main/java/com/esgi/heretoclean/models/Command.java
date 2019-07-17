package com.esgi.heretoclean.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
/*
@Entity
public class Command {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="dateCommand")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy")
	private LocalDate dateCommand; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn
	@NotNull
	private Volunteer volunteer ;
	
	
	@OneToMany(mappedBy="command",fetch=FetchType.LAZY)
	private List<CompoCommand> compoCommand = new ArrayList<CompoCommand>();
	
	
	public Command() {
		
		this.dateCommand = LocalDate.now();
	}
    
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

	public List<CompoCommand> getCompoCommand() {
		return compoCommand;
	}

	public void setCompoCommand(List<CompoCommand> compoCommand) {
		this.compoCommand = compoCommand;
	}


	
	

	
}
*/