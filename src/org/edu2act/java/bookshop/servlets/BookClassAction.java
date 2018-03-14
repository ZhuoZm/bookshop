/**
 * 
 */
package org.edu2act.java.bookshop.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edu2act.java.bookshop.beans.BookType;
import org.edu2act.java.bookshop.dao.BookTypeDao;

/**
 * @author Administrator
 * 
 */
public class BookClassAction extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = null;
		String action = request.getParameter("action");
		
		if ("select".equals(action)) {
			result = selectProductClass(request, response);
		} else if ("add".equals(action)) {
			result = addProductClass(request, response);
		}else if("delete".equals(action)){
			result = deleteProductClass(request, response);
		}else if("gotoModify".equals(action)){
			result = gotoModifyProductClass(request, response);
		}else if("modify".equals(action)){
			result = modifyProductClass(request, response);
		}else if("find".equals(action)){
			result = findProductClass(request, response);
		}
		request.getRequestDispatcher(result).forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	


	private String gotoModifyProductClass(HttpServletRequest request,
			HttpServletResponse response) {
		int id = (int)Integer.parseInt(request.getParameter("id"));
		BookType bt = new BookTypeDao().findBookType(id);
		request.setAttribute("bookType", bt);
		return "/admin/admin_classModif.jsp";
	}

	private String findProductClass(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("productClassId"));
		BookTypeDao productClassDao = new BookTypeDao();
		BookType productClass = productClassDao.findBookType(id);
		request.setAttribute("productClass", productClass);
		return "/admin/admin_BookClassUpdate.jsp";
	}

	private String selectProductClass(HttpServletRequest request,
			HttpServletResponse response) {

		BookTypeDao productClassDao = new BookTypeDao();
		ArrayList<BookType> productlist = productClassDao
				.selectProductClass();
		request.setAttribute("productclasslist", productlist);
		return "/admin/admin_ClassList.jsp";
	}

	private String addProductClass(HttpServletRequest request,
			HttpServletResponse response) {
		String bookTypeName = request.getParameter("name");
		BookType bookType = new BookType();
		bookType.setType_name(bookTypeName);
		BookTypeDao bookTypeDao = new BookTypeDao();		
		if(bookTypeDao.isClassExit(bookTypeName)){
			String errormessage = "该类已存在！！";
			request.setAttribute("errorMessage", errormessage);
			return "/Error.jsp";
		}		
		boolean result = bookTypeDao.addProductClass(bookType);
		if (result) {
			return "/BookClassAction.do?action=select";
		} else {
			return "/admin/admin_addClass.jsp";

		}

	}
	
	private String deleteProductClass(HttpServletRequest request, HttpServletResponse response){
		String productclassid = request.getParameter("id");
		int id = Integer.parseInt(productclassid);
		BookType bookType = new BookType();
		bookType.setType_id(id);
		BookTypeDao bookTypeDao = new BookTypeDao();
		boolean result = bookTypeDao.deleteProductClass(bookType);
		
		if (result) {
			return "/BookClassAction.do?action=select";
		} else {
			return "/deleteFiled.jsp";
		}
	}

	private String modifyProductClass(HttpServletRequest request,HttpServletResponse response){		
		String productclassid = request.getParameter("id");
		int id = Integer.parseInt(productclassid);
		String productclassname = request.getParameter("name");
		BookType productClass = new BookType();
		productClass.setType_id(id);
		productClass.setType_name(productclassname);
		
		boolean result = new BookTypeDao().updateProductClass(productClass);
		if (result){
		return "/BookClassAction.do?action=select";	
					//return "/WebShop/ProductClassAction.do?action=select";	
		}else{
			return "/updateFiled.jsp";
		}
	}
}
