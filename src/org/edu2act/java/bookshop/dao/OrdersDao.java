package org.edu2act.java.bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.edu2act.java.bookshop.beans.Orders;
import org.edu2act.java.bookshop.beans.User;
import org.edu2act.java.bookshop.db.DB;

public class OrdersDao {

	public int createOrder(Orders order) {
		String sql = "insert into orders(user_id,order_time,order_state) values(?,CURRENT_TIMESTAMP() ,?)";
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, order.getUser().getUser_id());
			stmt.setString(2, order.getOrder_state());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return -1;

	}

	public boolean confirmOrderById(int orderid) {
		// TODO Auto-generated method stub
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		String sql = "UPDATE orders SET order_state='已确认' WHERE order_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderid);
			if (stmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return false;

	}

	public List<Orders> listAll() {
		return listProductOrderByState(null);
	}

	public List<Orders> listUnConfOrder() {
		return listProductOrderByState("未处理");
	}

	public List<Orders> listHasConfOrder() {
		return listProductOrderByState("已确认");
	}

	public List<Orders> listCompleteOrder() {
		return listProductOrderByState("已完成");
	}

	public List<Orders> listProductOrderByState(String state) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Orders> orderList = new ArrayList<Orders>();
		Orders tmpOrder = null;
		try {

			if (state == null) {
				String sql = "select * from orders";
				stmt = conn.prepareStatement(sql);
			} else {
				String sql = "select * from orders where order_state= ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, state);
			}
			rs = stmt.executeQuery();
			UserDao userDao = new UserDao();
			while (rs.next()) {
				tmpOrder = new Orders();
				tmpOrder.setOrder_id(rs.getInt(1));
				User user = userDao.getUserByid(rs.getInt(2));
				tmpOrder.setUser(user);
				tmpOrder.setOrder_time(rs.getDate(3));
				tmpOrder.setOrder_state(rs.getString(4));
				// odList = new
				// OrderDetailDao().getOrderDetailsByOrderId(tmpOrder
				// .getId());
				// tmpOrder.setOrderDetailList(odList);
				orderList.add(tmpOrder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return orderList;
	}

	public void deleteOrderById(int orderid) {
		OrderDetailDao odDao = new OrderDetailDao();
		odDao.deleteOdDetailsByOrderId(orderid);
		String sql = "delete from orders where order_id=?;";
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderid);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}

	}

	public Orders getOrderById(int orderid) {
		// TODO Auto-generated method stub
		// List<OrderDetail> odList = new OrderDetailDao()
		// .getOrderDetailsByOrderId(orderid);
		// order.setOrderDetailList(odList);
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Orders order = null;
		String sql = "select * from orders where order_id=?;";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				order = new Orders();
				order.setOrder_id(orderid);
				UserDao userDao = new UserDao();
				User user = userDao.getUserByid(rs.getInt(2));
				order.setUser(user);
				order.setOrder_time(rs.getDate(3));
				order.setOrder_state(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return order;
	}

	public boolean endTheOrderById(int orderid) {
		// TODO Auto-generated method stub
		String sql = "UPDATE orders SET order_state='已完成' WHERE order_id=?";
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderid);
			if (stmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return false;
	}

	public List<Orders> getMyOrderList(int userid) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Orders> orderList = new ArrayList<Orders>();
		String sql = "select * from orders where user_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userid);
			rs = stmt.executeQuery();
			Orders tmpOrder = null;
			UserDao userDao = new UserDao();
			User user = userDao.getUserByid(userid);
			// List<OrderDetail> odList = null;
			while (rs.next()) {
				tmpOrder = new Orders();
				tmpOrder.setOrder_id(rs.getInt(1));
				user.setUser_id(rs.getInt(2));
				tmpOrder.setUser(user);
				tmpOrder.setOrder_time(rs.getDate(3));
				tmpOrder.setOrder_state(rs.getString(4));
				orderList.add(tmpOrder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return orderList;
	}
}
