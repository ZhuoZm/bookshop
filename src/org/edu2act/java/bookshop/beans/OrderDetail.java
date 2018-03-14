package org.edu2act.java.bookshop.beans;

public class OrderDetail {

	private int order_detail_id;
	private Book book;
	private int count;
	private Orders orders;

	public int getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(int orderDetailId) {
		order_detail_id = orderDetailId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}
}
