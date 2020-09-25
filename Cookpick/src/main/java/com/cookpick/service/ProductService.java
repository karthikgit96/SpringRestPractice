package com.cookpick.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookpick.model.Product;
import com.cookpick.model.ProductNotFoundException;
import com.cookpick.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	

	public List<Product> getProducts(String productName) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		List<Product> prodList = productRepository.getProducts(productName);
		if(prodList.isEmpty()) throw new ProductNotFoundException("ProductService.PRODUCT_NOT_FOUND");
		return prodList;
	}


	public Set<Product> getProducts(Map<String, List<String>> map) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		Set<Product> prodSet = productRepository.getProducts(map);
		if(prodSet.isEmpty()) throw new ProductNotFoundException("ProductService.PRODUCT_NOT_FOUND");
		return prodSet;
	}


	public List<Product> getProducts(String productName, String productVendor) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		List<Product> prodList = productRepository.getProducts(productName, productVendor);
		if(prodList.isEmpty()) throw new ProductNotFoundException("ProductService.PRODUCT_NOT_FOUND");
		return prodList;
	}


	public Integer addProduct( Product product) throws Exception {
		// TODO Auto-generated method stub
		Byte checkVal = productRepository.checkProduct(product.getProductName(), product.getProductVendor());
		Integer retVal = null;
		if(checkVal==null) throw new Exception("ProductService.PRODUCT_ALREADY_PRESENT");
		else retVal = productRepository.addProduct(product);
		return retVal;
	}


	public String deleteProduct(Integer productId) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		String retVal = productRepository.deleteProduct(productId);
		if(retVal == null) throw new ProductNotFoundException("ProductService.PRODUCT_NOT_FOUND");
		return retVal;
	}
	
	

}
