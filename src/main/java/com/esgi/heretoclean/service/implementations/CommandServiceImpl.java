package com.esgi.heretoclean.service.implementations;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esgi.heretoclean.dao.CommandRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.service.interfaces.CommandService;

@Service
@Transactional
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
	@Transactional(readOnly = true)
	public List<Command> findByDateCommand(Date d) {
		return commandRepo.findByDateCommand(d);
	}

	@Override
	@Transactional(readOnly = true)
	public Command findById(Long id) {
		return commandRepo.getOne(id);
	}

	@Override
	public void addPanier(Long id,Product p, int quantity) {
		Optional<Command> command = Optional.of(commandRepo.getOne(id));
		
//		if(command.isPresent()) {
//			Map m = command.get().getCompoCommand();
//			m.put(p, quantity);
//			command.get().setCompoCommand(m);
//			commandRepo.saveAndFlush(command.get());
//		}
	}
	
	

	@Override
	public List<Command> findByVolunteer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getAmount(Long id) {
		
	Optional<Command> command = Optional.of(commandRepo.getOne(id));
		
//		if(command.isPresent()) {
//			double amount = command.get().getAmount();
//
//			Map m = command.get().getCompoCommand();
//			
//		      Set<Entry<Product, Integer>> setHm = m.entrySet();
//		      Iterator<Entry<Product, Integer>> it = setHm.iterator();
//		      
//		      while(it.hasNext()) {
//		    	  Entry<Product, Integer> e = it.next();
//		    	  amount = amount + e.getKey().getPrice() * e.getValue();
//		      }
//		      
//		      return amount;
//		}
		
		return 0;
	}

}
