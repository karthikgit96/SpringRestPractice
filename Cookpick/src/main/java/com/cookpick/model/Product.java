package com.cookpick.model;

import javax.validation.constraints.Pattern;

public class Product {
	private Integer productId;
	@Pattern(regexp = "[\\w ]{5,10}")
	private String productName;
	private String productVendor;
	private Double productPrice;
	private Boolean productInStock;
	
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Integer productId, String productName, String productVendor, Double productPrice,
			char productInStock) {
		this();
		this.productId = productId;
		this.productName = productName;
		this.productVendor = productVendor;
		this.productPrice = productPrice;
		this.productInStock = (productInStock=='y'|| productInStock=='Y')?true:false;
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
	public Boolean getProductInStock() {
		return productInStock;
	}
	public void setProductInStock(Boolean productInStock) {
		this.productInStock = productInStock;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productVendor=" + productVendor
				+ ", productPrice=" + productPrice + ", productInStock=" + productInStock + "]";
	}
	
	

}
