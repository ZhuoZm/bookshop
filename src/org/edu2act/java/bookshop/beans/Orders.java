package org.edu2act.java.bookshop.beans;

import java.sql.Date;
import java.util.List;

import org.edu2act.java.bookshop.dao.OrderDetailDao;

public class Orders {

	private int order_id;
	private User user;
	private String order_state;
	private Date order_time;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int orderId) {
		order_id = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String orderState) {
		order_state = orderState;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date orderTime) {
		order_time = orderTime;
	}

	public double caculMoney() {
		double money = 0;
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		List<OrderDetail> orderDetailList = orderDetailDao
				.getOrderDetailsByOrderId(order_id);
		for (OrderDetail orderDetail : orderDetailList) {
			money += orderDetail.getBook().getBook_price()
					* orderDetail.getCount();
		}
		return money;
	}
}
