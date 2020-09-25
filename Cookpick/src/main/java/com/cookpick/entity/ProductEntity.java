package com.cookpick.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity {
	@Id
	@SequenceGenerator(name = "productsequence",sequenceName = "PRODUCT_SEQUENCE",allocationSize = 1)
	@GeneratedValue(generator = "productsequence",strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private String productVendor;
	private Double productPrice;
	private Character productInStock;
	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductEntity( String productName, String productVendor, Double productPrice,
			Boolean productInStock) {
		this();
		this.productName = productName;
		this.productVendor = productVendor;
		this.productPrice = productPrice;
		this.productInStock = (productInStock)?'Y':'N';
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductVendor() {
		return productVendor;
	}
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Character getProductInStock() {
		return productInStock;
	}
	public void setProductInStock(Character productInStock) {
		this.productInStock = productInStock;
	}
	
	
	
	

}
