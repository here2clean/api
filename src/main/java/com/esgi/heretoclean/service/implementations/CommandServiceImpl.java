package com.esgi.heretoclean.service.implementations;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esgi.heretoclean.configuration.MyProperties;
import com.esgi.heretoclean.dao.CommandRepository;
import com.esgi.heretoclean.dao.CompoCommandRepository;
import com.esgi.heretoclean.dao.ProductRepository;
import com.esgi.heretoclean.dao.VolunteerRepository;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.CompoCommand;
import com.esgi.heretoclean.models.CompoCommandJson;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.models.Volunteer;
import com.esgi.heretoclean.service.interfaces.CommandService;

@Service
@Transactional
public class CommandServiceImpl implements CommandService {

	private final CommandRepository commandRepo;

	private final VolunteerRepository volunteerRepo;

	private final ProductRepository productRepo;

	private final CompoCommandRepository compoCommandRepo;
	
	private final MyProperties myProperties;

	@Autowired
	public CommandServiceImpl(CommandRepository commandRepo, VolunteerRepository volunteerRepo,ProductRepository productRepo, CompoCommandRepository compoCommandRepo,MyProperties myProperties) {
		this.commandRepo = commandRepo;
		this.volunteerRepo = volunteerRepo;
		this.productRepo = productRepo;
		this.compoCommandRepo = compoCommandRepo;
		this.myProperties = myProperties;
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

		for(CompoCommandJson cJson : compoJson){
			CompoCommand compo = new CompoCommand();
			compo.setCommand(c);
			Optional<Product> product = productRepo.findById(cJson.getIdProduct());

			if(!product.isPresent() || product.get().getId() == null ) {
				throw new HereToCleanException(HttpStatus.NOT_FOUND.value(), "Produit non trouvé");
			}

			compo.setProduct(product.get());
			compo.setQuantity(cJson.getQuantity());
			compo.setAssociation(product.get().getAssociation());
//			compo.setCommand(command);
			
			compoCommandRepo.save(compo);

		}



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


	@Override
	public List<CompoCommand> findByCommandId(Long idCommand) {
		// TODO Auto-generated method stub
		return compoCommandRepo.findAllCompoCommandByCommandId(idCommand);
	}


	@Override
	public List<CompoCommand> findCommandByAssociation(Long idAssociation) {
		return compoCommandRepo.findAllCompoCommandByAssociationId(idAssociation);
	}


	@Override
	public void validateCommand(Long idCompoCommand) {
		CompoCommand compoCommand = compoCommandRepo.getOne(idCompoCommand);
		compoCommand.setIsConfirmed(MyProperties.COMMMAND_VALIDED);
		compoCommandRepo.saveAndFlush(compoCommand);
	}



	@Override
	public void commandPassed(Long idCompo) {
		CompoCommand compoCommand = compoCommandRepo.getOne(idCompo);
		compoCommand.setIsConfirmed(MyProperties.COMMMAND_PASSED);
		compoCommandRepo.saveAndFlush(compoCommand);		
	}


	@Override
	public void commandReceive(Long idCompo) {
		CompoCommand compoCommand = compoCommandRepo.getOne(idCompo);
		compoCommand.setIsConfirmed(MyProperties.COMMMAND_RECEIVED);
		compoCommandRepo.saveAndFlush(compoCommand);		
	}
	
	

	


}
