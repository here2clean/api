package com.esgi.heretoclean.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByName(String name);
	List<Product> findByAssociation(Association association);
	void deleteByName(String name);
}
