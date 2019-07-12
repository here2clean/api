package com.esgi.heretoclean.DTO;

import com.esgi.heretoclean.models.Product;

public class ProductDTO {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private float price;
	
	private AssociationDTO associationDTO;
	
	public ProductDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public AssociationDTO getAssociationDTO() {
		return associationDTO;
	}

	public void setAssociationDTO(AssociationDTO associationDTO) {
		this.associationDTO = associationDTO;
	}
	
	
	public static ProductDTO ProductToPooductDTO(Product p) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(p.getId());
		productDTO.setName(p.getName());
		productDTO.setPrice(p.getPrice());
		productDTO.setDescription(p.getDescription());
		
		AssociationDTO associationDTO = AssociationDTO.AssociationToAssociationDTO(p.getAssociation());
		
		productDTO.setAssociationDTO(associationDTO);
		
		return productDTO;
	}
	
}
