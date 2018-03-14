package org.edu2act.java.bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.edu2act.java.bookshop.beans.Admin;
import org.edu2act.java.bookshop.db.DB;

public class AdminDao {

	public boolean Login(Admin admin) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from admin where admin_username=? and admin_passwd = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin.getAdmin_username());
			stmt.setString(2, admin.getAdmin_passwd());
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return false;
	}

	public boolean addAdmin(Admin admin) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		String sql = "insert into admin(admin_username,admin_passwd,admin_realname) values(?,?,?);";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin.getAdmin_username());
			stmt.setString(2, admin.getAdmin_passwd());
			stmt.setString(3, admin.getAdmin_realname());
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

	public boolean isExit(String adminUserName) {
		String sql = "select * from admin where admin_username=?";
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, adminUserName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return false;
	}
	public boolean updatePassword(Admin admin) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		String sql = "update admin set admin_passwd=? where admin_username=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin.getAdmin_passwd());
			stmt.setString(2, admin.getAdmin_username());
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
}
