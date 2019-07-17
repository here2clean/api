package com.esgi.heretoclean.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Product;

@Service
public interface ProductService {
	Product addProduct(String emailAssociation,Product p);
	void deleteByName(String name);
	Optional<Product> findByName(String name);
	Product update(Long id, Product p);
	List<Product> findAll();
	List<Product> findAllByAssociation(String name) throws Exception;
	List<Product> findAllByAssociationId(Long id);
	void addAssociation(String name , String emailAsso) throws Exception;
	void addCommand(String nameProduct, Long idCommand) throws Exception;
	Product findById(Long id);
}
