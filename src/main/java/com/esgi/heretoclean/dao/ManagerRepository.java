package com.esgi.heretoclean.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esgi.heretoclean.models.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
	Manager findByEmail(String email);
	void deleteByEmail(String email);
}
