package com.esgi.heretoclean.service.interfaces;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.Product;

@Service
public interface ProductService {
	Product addProduct(Product p);
	void deleteByName(String name);
	Product findByName(String name);
	Product update(Long id, Product p);
	void addAssociation(String name , String emailAsso);
	void addCommand(String nameProduct, Long idCommand);
}
