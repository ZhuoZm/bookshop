package org.edu2act.java.bookshop.beans;

import java.io.Serializable;

public class ProductUnit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int count;//商品数量
	private Book product;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Book getProduct() {
		return product;
	}
	public void setProduct(Book product) {
		this.product = product;
	}
	public Double getTotalCost() {
		return product.getBook_price()*count;
	}
	
}
