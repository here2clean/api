package com.esgi.heretoclean.models;

import java.io.Serializable;

public class CompoCommandJson implements Serializable{

	private Long idProduct;
	
	private int quantity;

	public CompoCommandJson() {}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
