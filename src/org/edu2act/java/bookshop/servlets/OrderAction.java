package org.edu2act.java.bookshop.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.edu2act.java.bookshop.beans.*;
import org.edu2act.java.bookshop.dao.*;

public class OrderAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = null;
		String action = request.getParameter("action");
		if ("create".equals(action)) {
			result = createOrder(request, response);
		} else if ("confirm".equals(action)) {
			result = confirmOrder(request, response);
		} else if ("listAll".equals(action)) {
			result = listAll(request, response);
		} else if ("listUnConf".equals(action)) {
			result = listUnConf(request, response);
		} else if ("listHasConf".equals(action)) {
			result = listHasConf(request, response);
		} else if ("listComplete".equals(action)) {
			result = listComplete(request, response);
		} else if ("detailed".equals(action)) {
			result = detailedOrder(request, response);
		}else if ("userdetailed".equals(action)) {
			detailedOrder(request, response);
			result="/user/user_orderDetail.jsp";
		}  else if ("delete".equals(action)) {
			result = deleteOrder(request, response);
		} else if ("endTheOrder".equals(action)) {
			result = endTheOrder(request, response);
		} else if ("myOrderList".equals(action)) {
			result = myOrderList(request, response);
		}
		request.getRequestDispatcher(result).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	private String myOrderList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int userid = (int) Integer.parseInt(request.getParameter("userid"));
		OrdersDao orderDao = new OrdersDao();
		List<Orders> myOrderList = orderDao.getMyOrderList(userid);
		request.setAttribute("orderList", myOrderList);
		return "/user/myOrderList.jsp";
	}

	private String endTheOrder(HttpServletRequest request,
			HttpServletResponse response) {
		int orderid = (int) Integer.parseInt(request.getParameter("id"));
		OrdersDao orderDao = new OrdersDao();
		orderDao.endTheOrderById(orderid);
		List<Orders> orderList = orderDao.listHasConfOrder();
		request.setAttribute("orderList", orderList);
		return "/admin/admin_OrderList.jsp";
	}

	private String deleteOrder(HttpServletRequest request,
			HttpServletResponse response) {
		int orderid = (int) Integer.parseInt(request.getParameter("id"));
		OrdersDao orderDao = new OrdersDao();
		orderDao.deleteOrderById(orderid);
		User user = (User) request.getSession().getAttribute("user");
		return "OrderAction.do?action=myOrderList&userid=" + user.getUser_id();
	}

	private String detailedOrder(HttpServletRequest request,
			HttpServletResponse response) {
		int orderid = (int) Integer.parseInt(request.getParameter("id"));
		OrdersDao orderDao = new OrdersDao();
		Orders order = orderDao.getOrderById(orderid);
		// User user = new UserDao().getUserByid(order.getUser().getUser_id());
		// request.setAttribute("user", user);
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		List<OrderDetail> orderList = orderDetailDao
				.getOrderDetailsByOrderId(order.getOrder_id());
		request.setAttribute("orderList", orderList);
		request.setAttribute("order", order);
		request.setAttribute("totalMoney", order.caculMoney());
		return "/admin/admin_orderDetail.jsp";
	}

	private String listComplete(HttpServletRequest request,
			HttpServletResponse response) {
		OrdersDao orderDao = new OrdersDao();
		List<Orders> orderList = orderDao.listCompleteOrder();
		request.setAttribute("orderList", orderList);
		return "/admin/admin_OrderList.jsp";
	}
	private String listHasConf(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		OrdersDao orderDao = new OrdersDao();
		List<Orders> orderList = orderDao.listHasConfOrder();
		request.setAttribute("orderList", orderList);
		return "/admin/admin_OrderList.jsp";
	}

	private String listUnConf(HttpServletRequest request,
			HttpServletResponse response) {
		OrdersDao orderDao = new OrdersDao();
		List<Orders> orderList = orderDao.listUnConfOrder();
		request.setAttribute("orderList", orderList);
		return "/admin/admin_OrderList.jsp";
	}

	private String listAll(HttpServletRequest request,
			HttpServletResponse response) {
		OrdersDao orderDao = new OrdersDao();
		List<Orders> orderList = orderDao.listAll();
		request.setAttribute("orderList", orderList);
		return "/admin/admin_OrderList.jsp";
	}

	private String confirmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int orderid = (int) Integer.parseInt(request.getParameter("orderid"));
		OrdersDao orderDao = new OrdersDao();
		orderDao.confirmOrderById(orderid);
		return "BookAction.do?action=select";
	}

	private String createOrder(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			String errormessage = "请先登录";
			request.setAttribute("errorMessage", errormessage);
			return "/Error.jsp";
		}
		String[] idArr = request.getParameterValues("productid");
		String[] countArr = request.getParameterValues("count");

		Orders order = new Orders();
		order.setUser(user);
		order.setOrder_state("未处理");

		// List<OrderDetail> orderDetailList = order.getOrderDetailList();
		// for (int i = 0; i < idArr.length; i++) {
		// orderDetailList.add(new OrderDetail(idArr[i], countArr[i]));
		// }
		OrdersDao orderDao = new OrdersDao();
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		int genId = orderDao.createOrder(order);
		order.setOrder_id(genId);
		for (int i = 0; i < idArr.length; i++) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setCount(Integer.parseInt(countArr[i]));
			Book book = new Book();
			book.setBook_id(Integer.parseInt(idArr[i]));
			orderDetail.setBook(book);
			orderDetail.setOrders(order);
			orderDetailDao.saveOrderDetail(orderDetail);
			// orderDetailList.add(orderDetail);
		}
		// for (OrderDetail orderDetail : orderDetailList) {
		// orderDetail.setOrders(order);
		// orderDetailDao.saveOrderDetail(orderDetail);
		// }
		// 清除购物车
		ProductCart prodCart = (ProductCart) request.getSession().getAttribute(
				"productCart");
		prodCart.clear();
		double totalMoney = order.caculMoney();
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("order", orderDao
				.getOrderById(order.getOrder_id()));
		request.setAttribute("orderDetailList", orderDetailDao
				.getOrderDetailsByOrderId(order.getOrder_id()));
		return "/OrderConfirm.jsp";
	}

}
