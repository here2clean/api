package com.esgi.heretoclean.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.Event;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long>  {
	
	Optional<Association> findByEmail(String email);
	Optional<List<Association>> findByName(String name);
	
	void deleteByName(String name);
	void deleteByEmail(String email);
}
