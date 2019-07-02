package com.esgi.heretoclean.service.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Product;

@Service
public interface CommandService {
	Command createCommand(Command c);
	List<Command> findByDateCommand(Date d);
	Command findById(Long id);
	void addPanier(Long id,Product p,int quantity);
	List<Command> findByVolunteer(String name);
	double getAmount(Long id);
	
}
