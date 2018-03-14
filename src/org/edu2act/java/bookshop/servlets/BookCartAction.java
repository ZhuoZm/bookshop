package org.edu2act.java.bookshop.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.edu2act.java.bookshop.beans.Book;
import org.edu2act.java.bookshop.beans.ProductCart;
import org.edu2act.java.bookshop.beans.BookType;
import org.edu2act.java.bookshop.beans.ProductUnit;
import org.edu2act.java.bookshop.dao.BookDao;
import org.edu2act.java.bookshop.dao.BookTypeDao;

public class BookCartAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String result = null;
		if ("addProduct".equals(action)) {
			result = putProductInCart(request, response);
		} else if ("view".equals(action)) {
			result = ViewProductCart(request, response);
		} else if ("delete".equals(action)) {
			result = deleteProductCart(request, response);
		} else if ("setProductCart".equals(action)) {
			result = setProductCart(request, response);
		} else {
			result = "/index.html";
		}
		request.getRequestDispatcher(result).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}

	private String deleteProductCart(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<BookType> productclasslist = new BookTypeDao()
				.selectProductClass();
		request.setAttribute("productclasslist", productclasslist);
		String idString = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			return "/productCart.jsp";
		}
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("productCart");
		if (attribute != null) {
			if (attribute instanceof ProductCart) {
				ProductCart productCart = (ProductCart) attribute;
				productCart.delete(id);
				request.setAttribute("totalcost", productCart.getTotalCost());
				return "/productCart.jsp";
			}
		}
		return "/productCart.jsp";
	}

	private String ViewProductCart(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<BookType> productclasslist = new BookTypeDao()
				.selectProductClass();
		request.setAttribute("productclasslist", productclasslist);
		ProductCart productCart = (ProductCart) (request.getSession())
				.getAttribute("productCart");
		if (productCart != null)
			request.setAttribute("totalcost", productCart.getTotalCost());
		return "/productCart.jsp";
	}

	public String putProductInCart(HttpServletRequest request,
			HttpServletResponse response) {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		ArrayList<BookType> productclasslist = new BookTypeDao()
				.selectProductClass();
		BookDao bookDao = new BookDao();
		Book book = bookDao.selectProductById(id);
		ProductUnit productUnit = new ProductUnit();
		ProductCart productCart = new ProductCart();
		HttpSession session = request.getSession();
		Object cart = session.getAttribute("productCart");
		if (cart instanceof ProductCart) {
			ProductCart reCart = (ProductCart) cart;
			if (!reCart.equals(book)) {
				productUnit.setCount(1);
				productUnit.setProduct(book);
				reCart.add(productUnit);
				session.setAttribute("productCart", reCart);
			}
		} else {
			productUnit.setCount(1);
			productUnit.setProduct(book);
			productCart.add(productUnit);
			session.setAttribute("productCart", productCart);
		}
		productCart = (ProductCart) session.getAttribute("productCart");
		request.setAttribute("totalcost", productCart.getTotalCost());
		request.setAttribute("productclasslist", productclasslist);
		return "/productCart.jsp";
	}

	private String setProductCart(HttpServletRequest request,
			HttpServletResponse response) {
		String[] idArr = request.getParameterValues("productid");
		String[] countArr = request.getParameterValues("count");
		HttpSession session = request.getSession();
		ProductCart productCart = (ProductCart) session
				.getAttribute("productCart");
		for (int i = 0; i < idArr.length; i++) {
			for (int j = 0; j < productCart.size(); j++) {
				ProductUnit productUnit = productCart.get(i);
				if (productUnit.getProduct().getBook_id() == Integer
						.parseInt(idArr[i])) {
					productUnit.setCount(Integer.parseInt(countArr[i]));
				}
			}
		}
		request.setAttribute("totalcost", productCart.getTotalCost());
		return "/productCart.jsp";
	}
}
