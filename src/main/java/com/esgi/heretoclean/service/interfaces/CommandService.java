package com.esgi.heretoclean.service.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Command;

@Service
public interface CommandService {
	Command createCommand(Command c);
	List<Command> findByDateCommand(Date d);
	Command findById(Long id);
}
