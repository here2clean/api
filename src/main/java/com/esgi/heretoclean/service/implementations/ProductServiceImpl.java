package com.esgi.heretoclean.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.dao.CommandRepository;
import com.esgi.heretoclean.dao.ProductRepository;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.service.interfaces.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepo;
	
	private final AssociationRepository assoRepo;
	
	private final CommandRepository commandRepo;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepo, AssociationRepository assoRepo,
			CommandRepository commandRepo) {
		this.productRepo = productRepo;
		this.assoRepo = assoRepo;
		this.commandRepo = commandRepo;
	}

	@Override
	public Product addProduct(String emailAssociation,Product p) {
		 Optional<Association> associationOp = assoRepo.findByEmail(emailAssociation);
		 
		 if(!associationOp.isPresent()) {
			 return null;
		 }
		p.setAssociation(associationOp.get());
		return productRepo.save(p);
	}

	@Override
	public void deleteByName(String name) {
		productRepo.deleteByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findByName(String name) {
		return productRepo.findByName(name);
	}

	@Override
	public Product update(Long id, Product p) {
		return productRepo.saveAndFlush(p);
	}

	@Override
	public void addAssociation(String name, String emailAsso) throws Exception {
		Optional<Product> productOp = productRepo.findByName(name);
		if(!productOp.isPresent()) {
			 throw new Exception("Product not find");
		}
		Optional<Association> associationOp = assoRepo.findByEmail(emailAsso);
		
		if(!associationOp.isPresent()) {
			 throw new Exception("Association not find");
		}
		Product p = productOp.get();
		Association a = associationOp.get();
		
		p.setAssociation(a);
		productRepo.saveAndFlush(p);
		
	}

	@Override
	public void addCommand(String nameProduct, Long idCommand) throws Exception {
		Optional<Command> commandOp = commandRepo.findById(idCommand);
		
		if(!commandOp.isPresent()) {
			 throw new Exception("Command not find");
		}
		
		
		Optional<Product> productOp = productRepo.findByName(nameProduct);
		
		if(!productOp.isPresent()) {
			throw new Exception("Product not find");
		}
		Command c = commandOp.get();
		Product p = productOp.get();
		productRepo.saveAndFlush(p);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAllByAssociation(String name) throws Exception {
		
		List<Association> associationOp = assoRepo.findByNameContaining(name);
		
		if(associationOp.isEmpty()) {
			 throw new Exception("Association not find");
		}
		List<Product> products = productRepo.findByAssociation(associationOp.get(0));
		return products;
	}

	@Override
	public Product findById(Long id) {
		return productRepo.getOne(id);
	}

	@Override
	public List<Product> findAllByAssociationId(Long id) {

		
		Optional<Association> asso = assoRepo.findById(id);
		
		if(asso.isPresent()) {
			List<Product> products = asso.get().getProducts();
			return products;
		}
		
		return null;
	}



}
