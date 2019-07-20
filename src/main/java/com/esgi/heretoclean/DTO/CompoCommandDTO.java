package com.esgi.heretoclean.DTO;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.esgi.heretoclean.models.Command;
import com.esgi.heretoclean.models.CompoCommand;
import com.esgi.heretoclean.models.Product;

public class CompoCommandDTO {
	private Long id;

	private CommandDTO command;

	private ProductDTO product;
	
	private int quantity;
	

	public CompoCommandDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommandDTO getCommand() {
		return command;
	}

	public void setCommand(CommandDTO command) {
		this.command = command;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public static CompoCommandDTO CompoCommandToCompoCommandDTO(CompoCommand c) {
		CompoCommandDTO compoDTO = new CompoCommandDTO();
		compoDTO.setId(c.getId());
		compoDTO.setQuantity(c.getQuantity());
		
		compoDTO.setCommand(CommandDTO.CommandToCommandDTO(c.getCommand()));
		compoDTO.setProduct(ProductDTO.ProductToPooductDTO(c.getProduct()));
		
		return compoDTO;
	}
	
}
