package com.esgi.heretoclean.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgi.heretoclean.models.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
	
	List<Command> findByDateCommand(Date command);
	

}
