package com.esgi.heretoclean.DTO;

import java.time.LocalDate;

import com.esgi.heretoclean.models.Command;

public class CommandDTO {

	private Long id;

	private LocalDate dateCommand; 
	
	private VolunteerDTO volunteerDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateCommand() {
		return dateCommand;
	}

	public void setDateCommand(LocalDate dateCommand) {
		this.dateCommand = dateCommand;
	}
	
	public VolunteerDTO getVolunteerDTO() {
		return volunteerDTO;
	}

	public void setVolunteerDTO(VolunteerDTO volunteerDTO) {
		this.volunteerDTO = volunteerDTO;
	}

	public static CommandDTO CommandToCommandDTO(Command c) {
		CommandDTO commandDTO = new CommandDTO();
		
		commandDTO.setId(c.getId());
		commandDTO.setDateCommand(c.getDateCommand());
		
		VolunteerDTO volunteerDTO = VolunteerDTO.VolunteerToVolunteerDTO(c.getVolunteer());
		
		commandDTO.setVolunteerDTO(volunteerDTO);
		
		return commandDTO;
	}

}

