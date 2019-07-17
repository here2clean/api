package com.esgi.heretoclean.service.implementations;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esgi.heretoclean.dao.CompoCommandRepository;
import com.esgi.heretoclean.dao.ProductRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.CompoCommand;
import com.esgi.heretoclean.models.CompoCommandJson;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.models.Volunteer;
/*
@Service
@Transactional
public class CommandServiceImpl implements CommandService {

	private final CommandRepository commandRepo;

	private final VolunteerRepository volunteerRepo;

	private final ProductRepository productRepo;

	private final CompoCommandRepository compoCommandRepo;

	@Autowired
	public CommandServiceImpl(CommandRepository commandRepo, VolunteerRepository volunteerRepo,ProductRepository productRepo, CompoCommandRepository compoCommandRepo) {
		this.commandRepo = commandRepo;
		this.volunteerRepo = volunteerRepo;
		this.productRepo = productRepo;
		this.compoCommandRepo = compoCommandRepo;
	}


	@Override
	public Command createCommand(Long idVolunteer, CompoCommandJson[] compoJson) throws HereToCleanException {

		if(idVolunteer == null) {
			throw new HereToCleanException("Produit non trouvé");
		}
		
		Optional<Volunteer> volunteer = volunteerRepo.findById(idVolunteer);

		if(!volunteer.isPresent() || volunteer.get().getId() == null) {
			throw new HereToCleanException("Bénévole non trouvé");
		}

		Command c = new Command();
		c.setVolunteer(volunteer.get());
		c = commandRepo.save(c);
		CompoCommand compo = new CompoCommand();

		compo.setCommand(c);
		for(CompoCommandJson cJson : compoJson){
			Optional<Product> product = productRepo.findById(cJson.getIdProduct());

			if(!product.isPresent() || product.get().getId() == null ) {
				throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "Produit non trouvé");
			}

			compo.setProduct(product.get());
			compo.setQuantity(cJson.getQuantity());
			//			compo.setCommand(command);
			
			compoCommandRepo.save(compo);

		}



		// TODO Auto-generated method stub
		return null;
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
*/