package com.esgi.heretoclean.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.esgi.heretoclean.models.Event;
import com.esgi.heretoclean.models.Volunteer;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	Optional<List<Event>> findByNameContaining(String name);
	Optional<List<Event>> findByLocation(String location);
	Optional<List<Event>> findByBeginDate(LocalDate date);
	void deleteByName(String name);
}
