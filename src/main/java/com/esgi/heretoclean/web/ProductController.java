package com.esgi.heretoclean.web;


import javax.validation.Valid;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.esgi.heretoclean.DTO.ProductDTO;
import com.esgi.heretoclean.exception.HereToCleanException;
import com.esgi.heretoclean.models.Product;
import com.esgi.heretoclean.service.interfaces.ProductService;

import antlr.StringUtils;

import java.util.Optional;

import io.netty.util.internal.StringUtil;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/register")
	public ResponseEntity registerProduct(@RequestParam("emailAssociation") String emailAssociation, @RequestBody @Valid Product product) throws HereToCleanException {

		if(StringUtil.isNullOrEmpty(emailAssociation) || product == null) {
			throw new HereToCleanException(HttpStatus.BAD_REQUEST.value(), "Certains paramètres sont manquants dans la requête");
		}

		productService.addProduct(emailAssociation,product);
		return ResponseEntity.status(HttpStatus.CREATED.value()).build();
	}

	@GetMapping("/all")
	public ResponseEntity getProducts() throws HereToCleanException {
		List<Product> products = productService.findAll();

		if(products.isEmpty()) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Il n'y a pas de produit ");
		}

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();


		for(Product p : products) {
			ProductDTO productDTO = ProductDTO.ProductToPooductDTO(p);

			productDTOs.add(productDTO);
		}


		return ResponseEntity.ok(productDTOs);
	}

	@GetMapping("/reaserch/name")
	public ResponseEntity findByName(@RequestParam("name") String name) throws HereToCleanException {

		if(StringUtil.isNullOrEmpty(name)) {
			throw new HereToCleanException(HttpStatus.BAD_REQUEST.value(),"Renseignez le nom du produit svp");
		}

		Optional<Product> product = productService.findByName(name);

		if(!product.isPresent()) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Produit non trouvé");
		}
		return ResponseEntity.ok(product.get());
	}

	@DeleteMapping("/delete/name")
	public ResponseEntity deleteByName(@RequestParam("name") String name) throws HereToCleanException {
		if(StringUtil.isNullOrEmpty(name)) {
			throw new HereToCleanException(HttpStatus.BAD_REQUEST.value(),"Renseignez le nom du produit svp");
		}

		productService.deleteByName(name);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/update/name")
	public ResponseEntity updateProduct(@RequestParam("name") String name, @Valid Product newProduct) throws HereToCleanException {

		if(StringUtil.isNullOrEmpty(name) || newProduct == null ) {
			throw new HereToCleanException(HttpStatus.BAD_REQUEST.value(),"Tous les paramètres ne sont pas renseignés");
		}

		Optional<Product> oldProduct = productService.findByName(name);

		if(! oldProduct.isPresent()) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Produit non trouvé");
		}

		productService.update(oldProduct.get().getId(), newProduct);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/findById")
	public ResponseEntity findById(@RequestParam("id") Long id) throws HereToCleanException {

		if(id == null) {
			throw new HereToCleanException(HttpStatus.BAD_REQUEST.value(),"Renseignez l'id du produit svp");
		}

		Optional<Product> product = Optional.of(productService.findById(id));


		if(!product.isPresent() || product.get().getId() == null) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Produit non trouvé");
		}
		return ResponseEntity.ok(product.get());
	}

	@GetMapping("/findByAssociationId")
	public ResponseEntity findByAssociationId(@RequestParam("id") Long id) throws HereToCleanException {

		if(id == null) {
			throw new HereToCleanException(HttpStatus.BAD_REQUEST.value(),"Renseignez l'id de l'association svp");
		}

		Optional<List<Product>> products = Optional.of(productService.findAllByAssociationId(id));

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for(Product p : products.get()) {
			ProductDTO productDTO = ProductDTO.ProductToPooductDTO(p);
 
			productDTOs.add(productDTO);
		}


		if(!products.isPresent() && productDTOs.isEmpty()) {
			throw new HereToCleanException(HttpStatus.NOT_FOUND.value(),"Il n'y a aucun produit");
		}
		return ResponseEntity.ok(productDTOs);

	}	
}
