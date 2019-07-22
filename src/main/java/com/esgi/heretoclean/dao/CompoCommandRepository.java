package com.esgi.heretoclean.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esgi.heretoclean.models.CompoCommand;
import java.util.List;

@Repository
public interface CompoCommandRepository extends  JpaRepository<CompoCommand, Long> {
	List<CompoCommand> findAllCompoCommandByCommandId(Long idCommand);
	List<CompoCommand> findAllCompoCommandByAssociationId(Long idAssociation);

}


