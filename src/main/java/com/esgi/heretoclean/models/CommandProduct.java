package com.esgi.heretoclean.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.firebase.database.annotations.NotNull;

@Entity
public class CommandProduct {
	
	
	@EmbeddedId
    @JsonIgnore
	private CommandProductPK pk;
	
	@Column(name="quantity")
	@NotNull
	private int quantity;
	
	public CommandProduct(Command command,Product product , int quantity) {
		pk = new CommandProductPK();
		pk.setCommand(command);
		pk.setProduct(product);
		this.quantity = quantity;
	}
	
	@Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }
 
    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

	public CommandProductPK getPk() {
		return pk;
	}

	public void setPk(CommandProductPK pk) {
		this.pk = pk;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}
