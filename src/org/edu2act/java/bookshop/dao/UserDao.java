package org.edu2act.java.bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.edu2act.java.bookshop.beans.User;
import org.edu2act.java.bookshop.db.DB;

public class UserDao {

	public User login(String username, String password) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User us = null;
		try {
			String sql = "select * from users where user_username=? and user_passwd=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				us = new User();
				us.setUser_address(rs.getString("user_address"));
				us.setUser_email(rs.getString("user_email"));
				us.setUser_id(rs.getInt("user_id"));
				us.setUser_passwd(rs.getString("user_passwd"));
				us.setUser_telephone(rs.getString("user_telephone"));
				us.setUser_username(rs.getString("user_username"));
				us.setPosttime(rs.getDate("posttime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(rs);
			DB.close(conn);
		}
		return us;
	}

	public boolean registerUser(User user) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		try {
			String sql = "insert into users(user_username,user_passwd,user_email,user_telephone,user_address,posttime) values(?,?,?,?,?,CURRENT_TIMESTAMP())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUser_username());
			stmt.setString(2, user.getUser_passwd());
			stmt.setString(3, user.getUser_email());
			stmt.setString(4, user.getUser_telephone());
			stmt.setString(5, user.getUser_address());
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

	public User findUserPassword(User user) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User us = null;
		try {
			String sql = "select * from users where user_username=? and user_email=? and user_address=? and user_telephone=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUser_username());
			stmt.setString(2, user.getUser_email());
			stmt.setString(3, user.getUser_address());
			stmt.setString(4, user.getUser_telephone());
			rs = stmt.executeQuery();
			if (rs.next()) {
				us = new User();
				us.setUser_address(rs.getString("user_address"));
				us.setUser_email(rs.getString("user_email"));
				us.setUser_id(rs.getInt("user_id"));
				us.setUser_passwd(rs.getString("user_passwd"));
				us.setUser_telephone(rs.getString("user_telephone"));
				us.setUser_username(rs.getString("user_username"));
				user.setPosttime(rs.getDate(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(rs);
			DB.close(conn);
		}
		return us;
	}

	public boolean modifyuser(String usersname, String email, String telephone,
			String address) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		try {
			String sql = "update users set user_email=?,user_telephone=?,user_address=? where user_username=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, telephone);
			stmt.setString(3, address);
			stmt.setString(4, usersname);
			stmt.executeUpdate();
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

	public User showmodify(String username) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User us = null;
		try {
			String sql = "select * from users where user_username=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				us = new User();
				us.setUser_address(rs.getString("user_address"));
				us.setUser_email(rs.getString("user_email"));
				us.setUser_passwd(rs.getString("user_passwd"));
				us.setUser_telephone(rs.getString("user_telephone"));
				us.setUser_username(rs.getString("user_username"));
				us.setPosttime(rs.getDate(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return us;
	}

	public ArrayList<User> selectalluser() {
		Connection conn = DB.getConn();
		ArrayList<User> list = new ArrayList<User>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from users";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_passwd(rs.getString("user_passwd"));
				user.setUser_telephone(rs.getString("user_telephone"));
				user.setUser_username(rs.getString("user_username"));
				user.setPosttime(rs.getDate(7));
				list.add(user);
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

	public boolean deleteuser(String username) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		try {
			String sql = "delete from users where user_username=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
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

	public User getUserByid(int userid) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			String sql = "select * from users where user_id=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUser_username(rs.getString(2));
				user.setUser_passwd(rs.getString(3));
				user.setUser_email(rs.getString(4));
				user.setUser_telephone(rs.getString(5));
				user.setUser_address(rs.getString(6));
				user.setPosttime(rs.getDate(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return user;
	}

	public boolean initPwd(String username) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		try {
			String sql = "update users set user_passwd='000000' where user_username=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return false;
	}

	public boolean isUserNameUsed(String userName) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from users where user_username=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
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

	public boolean isEmailUsed(String email) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from users where user_email=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
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

	public boolean updatePassword(User user) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		String sql = "update users set user_passwd=? where user_username=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUser_passwd());
			stmt.setString(2, user.getUser_username());
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
