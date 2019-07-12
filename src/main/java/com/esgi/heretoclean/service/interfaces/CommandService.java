package com.esgi.heretoclean.service.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Product;

@Service
public interface CommandService {
	Command createCommand(Command c,Long idProduct,Long idVolunteer);
	List<Command> findByDateCommand(Date d);
	Command findById(Long id);
	List<Command> findByVolunteer(String name);
	
	
	double getAmount(Long id);
	
}
