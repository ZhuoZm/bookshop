package org.edu2act.java.bookshop.beans;

import java.util.ArrayList;

public class ProductCart extends ArrayList<ProductUnit> {
	private static final long serialVersionUID = 1L;
	public boolean add(ProductUnit productUnit) {
		super.add(productUnit);
		return true;
	}

	public ProductUnit remove(int index) {
		super.remove(index);
		return super.get(index);
	}

	public ProductUnit get(int index) {
		return super.get(index);
	}

	public int size() {
		return super.size();
	}

	public boolean equals(Book product) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getProduct().getBook_id() == product.getBook_id()) {
				this.get(i).setCount(this.get(i).getCount() + 1);
				// System.out.println(i);
				return true;
			}
		}
		return false;
	}

	public void delete(int id) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getProduct().getBook_id() == id) {
				super.remove(i);
			}
		}
	}

	public double getTotalCost() {
		double totalCost = 0;
		for (int i = 0; i < this.size(); i++) {
			totalCost = totalCost + this.get(i).getTotalCost();
		}
		return totalCost;
	}
}
