package com.cookpick.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cookpick.model.Product;
import com.cookpick.model.ProductNotFoundException;
import com.cookpick.service.ProductService;

@RestController
@RequestMapping(value = "product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "{productName}",params = "version=1")
	public List<Product> getProducts(@PathVariable String productName) throws ProductNotFoundException{
		return productService.getProducts(productName);
	}
	
	@GetMapping(value = "{query}/product",produces = "application/json")
	public Set<Product> getProducts(@MatrixVariable(pathVar = "query") Map<String,List<String>> map) throws ProductNotFoundException{
		return productService.getProducts(map);		
	}
	
	
	@GetMapping
	public List<Product> getProducts(@RequestParam String productName,@RequestParam String productVendor) throws ProductNotFoundException{
		return productService.getProducts(productName,productVendor);
	}
	
	@PostMapping(value = "addproduct")
	public String addProduct(@Valid @RequestBody Product product) throws Exception {
		Integer productId = productService.addProduct(product);
		String message = "Product "+product.getProductName()+" added sucessfully with product id : "+productId;
		return message;
	}
	
	@DeleteMapping(value = "{productId}")
	public String deleteProduct(@PathVariable Integer productId) throws ProductNotFoundException{
		return productService.deleteProduct(productId);
		
	}
}
