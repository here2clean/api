package com.esgi.heretoclean.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgi.heretoclean.models.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {

}
