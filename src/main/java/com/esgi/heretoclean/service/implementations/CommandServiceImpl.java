package com.esgi.heretoclean.service.implementations;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esgi.heretoclean.dao.CommandRepository;
import com.esgi.heretoclean.dao.ProductRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.interfaces.CommandService;

@Service
@Transactional
public class CommandServiceImpl implements CommandService{

	private final CommandRepository commandRepo;

	private final VolunteerRepository volunteerRepo;

	private final ProductRepository productRepo;

	@Autowired
	public CommandServiceImpl(CommandRepository commandRepo, VolunteerRepository volunteerRepo,ProductRepository productRepo) {
		this.commandRepo = commandRepo;
		this.volunteerRepo = volunteerRepo;
		this.productRepo = productRepo;
	}

	@Override
	public Command createCommand(Command c, Long idVolunteer) {
		Optional<Volunteer> volunteer = volunteerRepo.findById(idVolunteer);

		if(!volunteer.isPresent() && volunteer.get().getId() == null ) {
			return null;
		}
		c.setVolunteer(volunteer.get());
		c.setDateCommand(LocalDate.now());
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
	//
	//	@Override
	//	public Set<Product> getCompoCommand(Long idCommand) {
	//		// TODO Auto-generated method stub
	//		return commandRepo.findById(idCommand).get().getProducts();
	//	}


	@Override
	public void addProductInCommand(Long idProduct) throws HereToCleanException {

		Optional<Product> product = productRepo.findById(idProduct);

		if(!product.isPresent() || product.get().getId() == null ) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Le produit n'a pas été trouvé") ;
		}

	}

	@Override
	public void updateCommand(Command command) {
		this.commandRepo.save(command);		
	}

	@Override
	public Set<Product> getCompoCommand(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
