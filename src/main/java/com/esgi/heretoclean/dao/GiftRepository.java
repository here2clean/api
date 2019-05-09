package com.esgi.heretoclean.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgi.heretoclean.models.Gift;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
	
	List<Gift> findByAmount(Float amount);
	
	List<Gift> findByVolunteer(String email);
	
	List<Gift> findByAssociation(Long idAssociation);
}
