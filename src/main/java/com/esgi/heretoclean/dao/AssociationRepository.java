package com.esgi.heretoclean.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgi.heretoclean.models.Association;
import com.esgi.heretoclean.models.CompoCommand;
import com.esgi.heretoclean.models.Event;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long>  {
	
	Optional<Association> findByEmail(String email);
	List<Association> findByNameContaining(String name);
//	List<CompoCommand> findAllCompoCommandByCommandId(Long idCommand);
	
	
	void deleteByName(String name);
	void deleteByEmail(String email);
}
