package com.esgi.heretoclean.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.service.interfaces.CommandService;
import com.esgi.heretoclean.service.interfaces.ProductService;

@RestController
@RequestMapping("/api/command")
public class CommandController {
	
	private final CommandService commandService;
	private final ProductService productService;

	public CommandController(CommandService commandService,ProductService productService) {
		this.commandService = commandService;
		this.productService = productService;
	}
	/*	
	@PostMapping("/addProduct")
	public ResponseEntity addProductInCommand(@RequestBody @Valid Command command, @RequestParam("product_id") Long idProduct , @RequestParam("volunteer_id") Long idVolunteer ) throws HereToCleanException {
		
	Optional<Command> c = Optional.ofNullable(commandService.createCommand(command, idProduct, idVolunteer));
		
		
		if(!c.isPresent() || c.get().getId() == null ) {
			throw new HereToCleanException("L'ajout du produit à une commande a échouché");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED.value()).build();
		
	
	}
	*/
	
	@PostMapping("/create")
	public ResponseEntity create(@RequestParam("volunteer_id") Long idVolunteer,@RequestParam("product_id") Long...idsProduct) {

		List<Product> products = new ArrayList<Product>();
		
		
		for(Long idProduct : idsProduct) {
			Optional<Product> product = Optional.of(productService.findById(idProduct));
			
			if(!product.isPresent() || product.get().getId() == null) {
				continue;
			}
			
			products.add(product.get());
		}
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("/compoCommand")
	public ResponseEntity getCompoCommand(@RequestParam("command_id") Long idCommand) {
		
		Optional<Set<Product>> command = Optional.ofNullable(commandService.getCompoCommand(idCommand));
		
		return ResponseEntity.ok(command.get());
	}
}
