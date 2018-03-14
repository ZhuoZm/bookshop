package org.edu2act.java.bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.edu2act.java.bookshop.beans.BookType;
import org.edu2act.java.bookshop.db.DB;

public class BookTypeDao {
	// Statement stat = null;
	// Connection conn = null;

	public BookTypeDao() {
		// conn = DB.getConn();
		// try {
		// stat = conn.createStatement();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public ArrayList<BookType> selectProductClass() {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<BookType> list = new ArrayList<BookType>();
		String sql = "select * from book_type";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				BookType bookType = new BookType();
				bookType.setType_id(rs.getInt("type_id"));
				bookType.setType_name(rs.getString("type_name"));
				list.add(bookType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return list;
	}

	public BookType findBookType(int id) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BookType bookType = null;
		String sql = "select * from book_type where type_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				bookType = new BookType();
				bookType.setType_id(rs.getInt("type_id"));
				bookType.setType_name(rs.getString("type_name"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return bookType;
	}

	public boolean addProductClass(BookType bookType) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		boolean result = true;
		String sql = "insert into book_type(type_name)values(?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bookType.getType_name());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return result;
	}

	public boolean deleteProductClass(BookType productClass) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		boolean result = true;
		String sql = "delete from book_type where type_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productClass.getType_id());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return result;
	}

	public boolean updateProductClass(BookType productClass) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "update book_type set type_name='"
				+ productClass.getType_name() + "' where type_id="
				+ productClass.getType_id();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			result = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return result;
	}

	public boolean isClassExit(String bookTypeName) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from book_type where type_name=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bookTypeName);
			rs = stmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return false;
	}
}
