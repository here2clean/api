package com.esgi.heretoclean.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgi.heretoclean.dao.CommandRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.service.interfaces.CommandService;

@Service
public class CommandServiceImpl implements CommandService{
	
	private final CommandRepository commandRepo;
	
	private final VolunteerRepository volunteerRepo;
	
	@Autowired
	public CommandServiceImpl(CommandRepository commandRepo, VolunteerRepository volunteerRepo) {
		this.commandRepo = commandRepo;
		this.volunteerRepo = volunteerRepo;
	}

	@Override
	public Command createCommand(Command c) {
		return commandRepo.save(c);
	}

	@Override
	public List<Command> findByDateCommand(Date d) {
		return commandRepo.findByDateCommand(d);
	}

	@Override
	public Command findById(Long id) {
		return commandRepo.getOne(id);
	}

}
