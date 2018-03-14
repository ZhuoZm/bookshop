package org.edu2act.java.bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.edu2act.java.bookshop.beans.*;
import org.edu2act.java.bookshop.db.DB;

public class BookDao {

	public ArrayList<Book> selectProduct() {
		Connection conn = DB.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Book> productList = new ArrayList<Book>();
		try {
			String sql = "select * from book";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setBook_name(rs.getString("book_name"));
				book.setBook_price(rs.getDouble("book_price"));
				book.setBook_auth(rs.getString("book_auth"));
				book.setBook_publisher(rs.getString("book_publisher"));
				int int1 = rs.getInt("type_id");
				BookType bookType = new BookTypeDao().findBookType(int1);
				book.setBookType(bookType);
				String desc = rs.getString("book_description");
				if (desc == null)
					desc = "";
				if (desc.length() > 10) {
					book.setBook_description(desc.substring(0, 10) + "....");
				} else {
					book.setBook_description(desc);
				}
				book.setBook_imgurl(rs.getString("book_imgurl"));
				productList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return productList;
	}

	public ArrayList<Book> selectProduct(int type_id) {
		Connection conn = DB.getConn();
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {
			String sql = "select * from book where type_id = " + type_id;
			BookType bookType = null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setBook_name(rs.getString("book_name"));
				book.setBook_price(rs.getDouble("book_price"));
				book.setBook_auth(rs.getString("book_auth"));
				book.setBook_publisher(rs.getString("book_publisher"));
				bookType = new BookTypeDao().findBookType(type_id);
				book.setBookType(bookType);
				book.setBook_description(rs.getString("book_description"));
				book.setBook_imgurl(rs.getString("book_imgurl"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return bookList;
	}

	public boolean addProduct(Book product) {

		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		String sql = "insert into book(book_name,book_price,book_auth,book_publisher,book_description,type_id)values(?,?,?,?,?,?);";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getBook_name());
			stmt.setDouble(2, product.getBook_price());
			stmt.setString(3, product.getBook_auth());
			stmt.setString(4, product.getBook_publisher());
			stmt.setString(5, product.getBook_description());
			stmt.setInt(6, product.getBookType().getType_id());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return false;
	}

	public List<Book> SelectProductByIdDO(int[] id) {
		Connection conn = DB.getConn();
		List<Book> bookList = new ArrayList<Book>();
		ResultSet rs = null;
		Statement stmt = null;
		String ids = id[0] + "";
		for (int i = 1; i < id.length; i++) {
			ids = ids + "," + id[i];
		}
		String sql = "select * from product where book_id in( " + ids + ")";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setBook_name(rs.getString("book_name"));
				book.setBook_price(rs.getDouble("book_price"));
				book.setBook_auth(rs.getString("book_auth"));
				book.setBook_publisher(rs.getString("book_publisher"));
				book.setBook_description(rs.getString("book_description"));
				book.setBook_imgurl(rs.getString("book_imgurl"));
				BookType bookType = new BookTypeDao().findBookType(rs
						.getInt("type_id"));
				book.setBookType(bookType);
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return bookList;
	}

	public Book selectProductById(int book_id) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Book book = null;
		String sql = "select * from book where book_id =?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,book_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setBook_name(rs.getString("book_name"));
				book.setBook_price(rs.getDouble("book_price"));
				book.setBook_description(rs.getString("book_description"));
				book.setBook_auth(rs.getString("book_auth"));
				book.setBook_publisher(rs.getString("book_publisher"));
				book.setBook_imgurl(rs.getString("book_imgurl"));
				BookType bookType = new BookTypeDao().findBookType(rs
						.getInt("type_id"));
				book.setBookType(bookType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return book;
	}

	// public boolean UpdateProductDo(List<Book> list) {
	// Connection conn = DB.getConn();
	// Statement stmt = null;
	// boolean result = false;
	// for (int i = 0; i < list.size(); i++) {
	// Book book = new Book();
	// book = list.get(i);
	// String sql = "update book set book_name = '" + book.getBook_name()
	// + "',book_price = " + book.getBook_price()
	// + "',book_auth = " + book.getBook_auth()
	// + "',book_publisher = " + book.getBook_publisher()
	// + ",book_description = '" + book.getBook_description()
	// + "',book_imgurl = '" + book.getBook_imgurl()
	// + "',type_id = " + book.getBookType().getType_id()
	// + " where book_id = " + book.getBook_id();
	//
	// try {
	// stmt = conn.createStatement();
	// result = stmt.execute(sql);
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// DB.close(stmt);
	// DB.close(conn);
	// return result;
	// }

	public boolean deleteProduct(int book_id) {
		Connection conn = DB.getConn();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "delete from book where book_id=" + book_id;
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}

		return false;
	}

	public List<Book> detailedProduct(int book_id) {
		Connection conn = DB.getConn();
		ResultSet rs = null;
		Statement stmt = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			String sql = "select * from book where book_id=" + book_id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setBook_name(rs.getString("book_name"));
				book.setBook_price(rs.getDouble("book_price"));
				book.setBook_description(rs.getString("book_description"));
				book.setBook_auth(rs.getString("book_auth"));
				book.setBook_description(rs.getString("Book_description"));
				book.setBook_imgurl(rs.getString("Book_imgurl"));
				BookType bookType = new BookTypeDao().findBookType(rs
						.getInt("type_id"));
				book.setBookType(bookType);
				bookList.add(book);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return bookList;
	}

	public boolean updateProduct(Book book) {
		Connection conn = DB.getConn();
		String sql = "update book SET book_name=?, book_price=?, book_auth=?, book_publisher=?, book_description=?, type_id=? WHERE book_id=?";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, book.getBook_name());
			prep.setDouble(2, book.getBook_price());
			prep.setString(3, book.getBook_auth());
			prep.setString(4, book.getBook_publisher());
			prep.setString(5, book.getBook_description());
			prep.setInt(6, book.getBookType().getType_id());
			prep.setInt(7, book.getBook_id());
			prep.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(prep);
			DB.close(conn);
		}
		return false;
	}

	public boolean updateImage(int id, String imgurl) {
		Connection conn = DB.getConn();
		String sql = "update book SET book_imgurl=? WHERE book_id=?";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, imgurl);
			prep.setInt(2, id);
			prep.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(prep);
			DB.close(conn);
		}
		return false;
	}

	public ArrayList<Book> getBooksByKey(int type_id, String keyWords) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Book> productList = new ArrayList<Book>();
		try {
			String sql = "select * from book where  type_id=? and book_name like ? ;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, type_id);
			stmt.setString(2, "%" + keyWords + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getInt("book_id"));
				book.setBook_name(rs.getString("book_name"));
				book.setBook_price(rs.getDouble("book_price"));
				book.setBook_auth(rs.getString("book_auth"));
				book.setBook_publisher(rs.getString("book_publisher"));
				book.setBook_description(rs.getString("book_description"));
				book.setBook_imgurl(rs.getString("book_imgurl"));
				BookType bookType = new BookTypeDao().findBookType(rs
						.getInt("type_id"));
				book.setBookType(bookType);
				productList.add(book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return productList;
	}

}
