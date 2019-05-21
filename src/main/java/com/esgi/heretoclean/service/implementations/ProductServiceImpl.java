package com.esgi.heretoclean.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgi.heretoclean.dao.AssociationRepository;
import com.esgi.heretoclean.dao.CommandRepository;
import com.esgi.heretoclean.dao.ProductRepository;
import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.service.interfaces.ProductService;

@Service
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
	public Product addProduct(Product p) {
		return productRepo.save(p);
	}

	@Override
	public void deleteByName(String name) {
		productRepo.deleteByName(name);
	}

	@Override
	public Product findByName(String name) {
		return productRepo.findByName(name);
	}

	@Override
	public Product update(Long id, Product p) {
		return productRepo.saveAndFlush(p);
	}

	@Override
	public void addAssociation(String name, String emailAsso) {
		Product p = productRepo.findByName(name);
		Association a = assoRepo.findByEmail(emailAsso).get();
		p.setAssociation(a);
		productRepo.saveAndFlush(p);
		
	}

	@Override
	public void addCommand(String nameProduct, Long idCommand) {
		Command c = commandRepo.findById(idCommand).get();
		Product p = productRepo.findByName(nameProduct);
		p.setCommand(c);
		productRepo.saveAndFlush(p);
	}



}
