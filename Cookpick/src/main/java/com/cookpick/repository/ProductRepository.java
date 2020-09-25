package com.cookpick.repository;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cookpick.entity.ProductEntity;
import com.cookpick.model.Product;
import com.cookpick.model.ProductNotFoundException;

@Repository
public class ProductRepository {
	@PersistenceContext
	private EntityManager entityManager;
	public Integer addProduct(Product product) throws Exception{
		ProductEntity proEnt = new ProductEntity(product.getProductName(),product.getProductVendor(),product.getProductPrice(),product.getProductInStock());
		entityManager.persist(proEnt);
		return proEnt.getProductId();
	}
	
	public List<Product> getProducts(String productName) throws ProductNotFoundException{
//		String name = productName.substring(1,productName.length()-1);
		String sql = "select p from ProductEntity p where p.productName = :productName";
		Query query = entityManager.createQuery(sql);
		query.setParameter("productName", productName);
		List<ProductEntity> prodEntList = query.getResultList();
		List<Product> prodList = new LinkedList<Product>();
		prodEntList.forEach(c->{			
			Product p = new Product(c.getProductId(),c.getProductName(),c.getProductVendor(),c.getProductPrice(),c.getProductInStock());
			prodList.add(p);			
		});
		return prodList;
	}
	
	public Set<Product> getProducts(Map<String,List<String>> map)throws ProductNotFoundException{
		Set<Product> prodSet = new LinkedHashSet<Product>();
		map.keySet().forEach(c->{
			switch(c) {
			case "productName":{
				map.get(c).forEach(e->{
					String sql = "select p from ProductEntity p where p.productName = :productName";
					Query query = entityManager.createQuery(sql);
					query.setParameter("productName", e);
					List<ProductEntity> proEntList = query.getResultList();
					proEntList.forEach(d->{
						Product p = new Product(d.getProductId(),d.getProductName(),d.getProductVendor(),d.getProductPrice(),d.getProductInStock());
						prodSet.add(p);
						
					});
				});
				break;
			}
			case "productVendor":{
				map.get(c).forEach(e->{
					String sql = "select p from ProductEntity p where p.productVendor = :productVendor";
					Query query = entityManager.createQuery(sql);
					query.setParameter("productVendor", e);
					List<ProductEntity> proEntList = query.getResultList();
					proEntList.forEach(d->{
						Product p = new Product(d.getProductId(),d.getProductName(),d.getProductVendor(),d.getProductPrice(),d.getProductInStock());
						prodSet.add(p);
						
					});
				});
				break;
			}
			}
			
		});
		return prodSet;	
	}
	
	public List<Product> getProducts(String productName,String productVendor) throws ProductNotFoundException{
		String sql = "select p from ProductEntity p where p.productName = ?1 and p.productVendor = ?2";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, productName);
		query.setParameter(2, productVendor);
		List<ProductEntity> prodEntList = query.getResultList();
		List<Product> prodList = new LinkedList<Product>();
		prodEntList.forEach(c->{			
			Product p = new Product(c.getProductId(),c.getProductName(),c.getProductVendor(),c.getProductPrice(),c.getProductInStock());
			prodList.add(p);			
		});
		return prodList;
	}
	
	public Byte checkProduct(String productName,String productVendor) throws Exception{
		String sql = "select p from ProductEntity p where p.productName = ?1 and p.productVendor = ?2";
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, productName);
		query.setParameter(2, productVendor);
		List<ProductEntity> proentList = query.getResultList();
		Byte retVal=null;
		if(proentList.isEmpty()) retVal = 1;
		return retVal;
	}
	
	public String deleteProduct(Integer productId) throws ProductNotFoundException{
		String retVal = null;
		ProductEntity p = entityManager.find(ProductEntity.class, productId);
		if(p!=null) {
			retVal = p.getProductName()+"\t With product Id: "+productId+" has been deleted sucessfully";
			entityManager.remove(p);
		}
		return retVal;
	}
}
