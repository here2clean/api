package com.esgi.heretoclean.web;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.service.interfaces.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/register")
	public ResponseEntity registerProduct(@RequestParam("emailAssociation") String emailAssociation, @RequestBody @Valid Product product) throws URISyntaxException {
		productService.addProduct(emailAssociation,product);
		return ResponseEntity.status(HttpStatus.CREATED.value()).build();
	}

	@GetMapping("/all")
	public ResponseEntity getProducts() {
//productService.
		
		return null;

	}



}
