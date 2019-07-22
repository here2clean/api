package com.esgi.heretoclean.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class MyProperties {
	
	private String urlBase;
	private String apiKey;
	private String secret;
	
	public static int COMMMAND_PASSED = 0;
	public static int COMMMAND_VALIDED = 1;
	public static int COMMMAND_RECEIVED = 2;
	
	
	public MyProperties() {
	}
	
	public String getUrlBase() {
		return urlBase;
	}
	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	

}
