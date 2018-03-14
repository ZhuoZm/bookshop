package org.edu2act.java.bookshop.beans;

import java.io.Serializable;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int book_id;
	private String book_name;
	private String book_auth;
	private String book_publisher;
	private double book_price;
	private String book_imgurl;
	private String book_description;
	private BookType bookType;

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int bookId) {
		book_id = bookId;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String bookName) {
		book_name = bookName;
	}

	public String getBook_auth() {
		return book_auth;
	}

	public void setBook_auth(String bookAuth) {
		book_auth = bookAuth;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String bookPublisher) {
		book_publisher = bookPublisher;
	}

	public double getBook_price() {
		return book_price;
	}

	public void setBook_price(double bookPrice) {
		book_price = bookPrice;
	}

	public String getBook_imgurl() {
		return book_imgurl;
	}

	public void setBook_imgurl(String bookImgurl) {
		book_imgurl = bookImgurl;
	}

	public String getBook_description() {
		return book_description;
	}

	public void setBook_description(String bookDescription) {
		book_description = bookDescription;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

}
