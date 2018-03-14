package org.edu2act.java.bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.edu2act.java.bookshop.beans.*;
import org.edu2act.java.bookshop.db.DB;

public class OrderDetailDao {

	public void saveOrderDetail(OrderDetail orderDetail) {
		String sql = "insert into order_detail(order_id,book_id,count) values(?, ?, ?)";
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderDetail.getOrders().getOrder_id());
			stmt.setInt(2, orderDetail.getBook().getBook_id());
			stmt.setInt(3, orderDetail.getCount());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
	}

	public List<OrderDetail> getOrderDetailsByOrderId(int orderid) {
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		String sql = "select * from order_detail where order_id=?;";
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder_detail_id(rs.getInt(1));
				BookDao bookDao = new BookDao();
				Book book = bookDao.selectProductById(rs.getInt(3));
				orderDetail.setBook(book);
				Orders order = new Orders();
				order.setOrder_id(orderid);
				orderDetail.setOrders(order);
				orderDetail.setCount(rs.getInt(4));
				orderDetailList.add(orderDetail);
			}
			return orderDetailList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}

		return null;
	}

	public void deleteOdDetailsByOrderId(int orderid) {
		Connection conn = DB.getConn();
		PreparedStatement stmt = null;
		String sql = "delete from order_detail where order_id=?;";
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
}
