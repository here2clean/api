package com.esgi.heretoclean.configuration;

public enum EnumStatusOrder {
	orderValidated ("Commande validée"),
	inProgress ("En cours de préparation"),
	orderReady ("Commande prête"),
	orderCancel ("Commande annulée");

	private String name;

	EnumStatusOrder(String name){
		this.name = name;
	}

	public String toString(){
		return name;
	}
}
