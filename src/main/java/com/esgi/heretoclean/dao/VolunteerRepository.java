package com.esgi.heretoclean.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgi.heretoclean.models.Volunteer;


@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
	
	Optional<Volunteer> findOneByEmailIgnoreCase(String email);
	void deleteByEmail(String email);
	
}
