package org.edu2act.java.bookshop.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.edu2act.java.bookshop.beans.Book;
import org.edu2act.java.bookshop.beans.BookType;
import org.edu2act.java.bookshop.commons.PageCount;
import org.edu2act.java.bookshop.dao.BookDao;
import org.edu2act.java.bookshop.dao.BookTypeDao;

public class BookAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = null;
		String action = request.getParameter("action");
		if ("select".equals(action)) {
			result = selectProduct(request, response);
		} else if ("selectlist".equals(action)) {
			result = adminSelectProduct(request, response);
		} else if ("add".equals(action)) {
			result = addProduct(request, response);
		} else if ("delete".equals(action)) {
			result = deleteProduct(request, response);
		} else if ("updateSelect".equals(action)) {
			result = updateSelect(request, response);
		} else if ("uploadImage".equals(action)) {
			result = uploadImage(request, response);
		} else if ("uploadsubmit".equals(action)) {
			result = uploadSubmit(request, response);
		}else if ("update".equals(action)) {
			result = updateBook(request, response);
		} else if ("detailed".equals(action)) {
			result = detailedProduct(request, response);
		} else if ("selectProductByClassId".equals(action)) {
			result = selectProductByClass(request, response);
		} else if ("findBooksByKey".equals(action)) {
			result = findBooksByKey(request, response);
		}
		request.getRequestDispatcher(result).forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

	private String uploadSubmit(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String idString = request.getParameter("id");
		String realPath = this.getServletConfig().getServletContext()
				.getRealPath("/");
//		String contextPath = request.getContextPath();
		String encoding = request.getCharacterEncoding();
		ServletFileUpload.isMultipartContent(request);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding(encoding);
		List fileItems = null;
		// String imgurl = null;
		try {
			fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// String name = null;
				// name = item.getFieldName();
				// imgurl = item.getName();
				File uploadFile = new File(realPath + "/images/book/"
						+ idString + ".JPG");
				item.write(uploadFile);
				BookDao bookDao = new BookDao();
				bookDao.updateImage(Integer.parseInt(idString), "/images/book/"
						+ idString + ".JPG");
				// bookDao.updateImage(Integer.parseInt(idString), realPath
				// + "/images/book/" + idString + ".JPG");
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("id", idString);
		return "/BookAction.do?action=selectlist";
	}

	private String uploadImage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String idString = request.getParameter("id");
		request.setAttribute("id", idString);
		return "/admin/admin_uploadImage.jsp";
	}

	private String findBooksByKey(HttpServletRequest request,
			HttpServletResponse response) {
		int typeID = Integer.parseInt(request.getParameter("category"));
		String keyWords = request.getParameter("keywords");
		BookDao bookDao = new BookDao();

		ArrayList<Book> productList = bookDao.getBooksByKey(typeID, keyWords
				.trim());
		ArrayList<BookType> productclasslist = new BookTypeDao()
				.selectProductClass();

		request.setAttribute("productclasslist", productclasslist);

		int pageNum;
		String pageNumStr = request.getParameter("pageNum");
		if (pageNumStr == null || pageNumStr.equals("")) {
			pageNumStr = "1";
		}
		try {
			pageNum = Integer.parseInt(pageNumStr);
		} catch (NumberFormatException e) {
			pageNum = 1;
		}
		if (pageNum < 1)
			pageNum = 1;
		PageCount pageCount = new PageCount();// 创建分页对象
		pageCount.setPageSize(3);
		pageCount.setRecordCount(productList.size());
		pageCount.setCount(pageCount.getRecordCount(), pageCount.getPageSize());
		pageCount.setShowPage(1);
		if (pageNum == 0 || pageNum < -1) {
			pageCount.setShowPage(1);
		} else if (pageNum == -1 || pageNum > pageCount.getCount()) {
			pageCount.setShowPage(pageCount.getCount());
		} else {
			pageCount.setShowPage(pageNum);
		}
		request.setAttribute("pageCount", pageCount);

		int showPage = pageCount.getShowPage();
		// int lastPage = pageCount.getCount();
		int pageSize = pageCount.getPageSize();

		int begin, end;
		if (showPage <= 1) {
			begin = 0;
		} else if (showPage > pageCount.getCount()) {
			begin = (pageCount.getCount() - 1) * pageSize;
		} else {
			begin = (showPage - 1) * pageSize;
		}
		if (showPage * pageSize > pageCount.getRecordCount()) {
			end = pageCount.getRecordCount();
		} else {
			end = showPage * pageSize;
		}

		List<Book> subList = new ArrayList<Book>();
		subList = productList.subList(begin, end);
		request.setAttribute("productList", subList.isEmpty() ? null : subList);
		return "/index.jsp";
	}

	private String updateBook(HttpServletRequest request,
			HttpServletResponse response) {
		Book book = new Book();
		String book_id = request.getParameter("id");
		String book_name = request.getParameter("product");
		String book_price = request.getParameter("price");
		String classid = request.getParameter("classid");
		String description = request.getParameter("description");
		String bookAuth = request.getParameter("author");
		String bookPublisher = request.getParameter("publisher");
		book.setBook_id(Integer.parseInt(book_id));
		book.setBook_name(book_name);
		book.setBook_price(Double.parseDouble(book_price));
		book.setBook_auth(bookAuth);
		book.setBook_publisher(bookPublisher);
		book.setBook_description(description);
		BookType bookType = new BookType();
		bookType.setType_id(Integer.parseInt(classid));
		book.setBookType(bookType);
		boolean result = new BookDao().updateProduct(book);
		if (result) {
			return "BookAction.do?action=selectlist";
		} else {
			return "/admin_addProduct.jsp";
		}
	}

	private String updateSelect(HttpServletRequest request,
			HttpServletResponse response) {
		String prodid = request.getParameter("id");
		int productid = Integer.parseInt(prodid);
		Book book = new BookDao().selectProductById(productid);
		ArrayList<BookType> bookTypeList = new BookTypeDao()
				.selectProductClass();
		request.setAttribute("book", book);
		request.setAttribute("typeList", bookTypeList);
		return "/admin/admin_updateBook.jsp";
	}

	private String detailedProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String book_id = request.getParameter("id");
		int productid = Integer.parseInt(book_id);
		Book book = new BookDao().selectProductById(productid);
		request.setAttribute("book", book);
		return "/admin/admin_detailedBook.jsp";
	}

	private String deleteProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String Productid = request.getParameter("id");
		int id = Integer.parseInt(Productid);
		BookDao bookDao = new BookDao();
		Book book = bookDao.selectProductById(id);
		File file = new File(this.getServletContext().getRealPath("/")
				+ book.getBook_imgurl());
		file.delete();
		boolean deleresult = bookDao.deleteProduct(id);
		if (deleresult) {
			return "/BookAction.do?action=selectlist";
		} else {
			return "/deleteFiled.jsp";
		}

	}

	private String addProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String book_name = request.getParameter("product");
		String book_price = request.getParameter("price");
		String classid = request.getParameter("classid");
		String description = request.getParameter("description");
		String bookAuth = request.getParameter("author");
		String bookPublisher = request.getParameter("publisher");
		Book book = new Book();
		book.setBook_name(book_name);
		book.setBook_price(Double.parseDouble(book_price));
		// book.setBook_imgurl(imgurl);
		book.setBook_auth(bookAuth);
		book.setBook_publisher(bookPublisher);
		book.setBook_description(description);
		BookType bookType = new BookType();
		bookType.setType_id(Integer.parseInt(classid));
		book.setBookType(bookType);
		boolean result = new BookDao().addProduct(book);
		if (result) {
			return "/BookAction.do?action=selectlist";
		} else {
			return "/admin/admin_addBook.jsp";
		}
	}

	private String selectProductByClass(HttpServletRequest request,
			HttpServletResponse response) {

		String idString = request.getParameter("id");
		int i;
		try {
			i = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			i = 1;
		}
		ArrayList<Book> productList = new BookDao().selectProduct(i);

		ArrayList<BookType> productclass = new BookTypeDao()
				.selectProductClass();

		request.setAttribute("productclasslist", productclass);
		int pageNum;
		String pageNumStr = request.getParameter("pageNum");
		if (pageNumStr == null || pageNumStr.equals("")) {
			pageNumStr = "1";
		}
		try {
			pageNum = Integer.parseInt(pageNumStr);
		} catch (NumberFormatException e) {
			pageNum = 1;
		}
		PageCount pageCount = new PageCount();// 创建分页对象
		pageCount.setPageSize(5);
		pageCount.setRecordCount(productList.size());
		pageCount.setCount(pageCount.getRecordCount(), pageCount.getPageSize());
		pageCount.setShowPage(1);
		if (pageNum == 0 || pageNum < -1) {
			pageCount.setShowPage(1);
		} else if (pageNum == -1 || pageNum > pageCount.getCount()) {
			pageCount.setShowPage(pageCount.getCount());
		} else {
			pageCount.setShowPage(pageNum);
		}
		request.setAttribute("pageCount", pageCount);
		int showPage = pageCount.getShowPage();
		int lastPage = pageCount.getCount();
		int pageSize = pageCount.getPageSize();

		int begin, end;
		if (showPage <= 1) {
			begin = 0;
		} else if (showPage > pageCount.getCount()) {
			begin = (lastPage - 1) * pageSize;
		} else {
			begin = (showPage - 1) * pageSize;
		}
		if (showPage * pageSize > pageCount.getRecordCount()) {
			end = pageCount.getRecordCount();
		} else {
			end = showPage * pageSize;
		}
		List subList = new ArrayList();
		subList = productList.subList(begin, end);
		request.setAttribute("productList", subList.isEmpty() ? null : subList);
		return "/index.jsp";
		// return "/productbyclassid.jsp";
	}

	private String selectProduct(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Book> productList = new BookDao().selectProduct();
		ArrayList<BookType> productclasslist = new BookTypeDao()
				.selectProductClass();
		request.getSession().setAttribute("productclasslist", productclasslist);
		int pageNum;
		String pageNumStr = request.getParameter("pageNum");
		if (pageNumStr == null || pageNumStr.equals("")) {
			pageNumStr = "1";
		}
		try {
			pageNum = Integer.parseInt(pageNumStr);
		} catch (NumberFormatException e) {
			pageNum = 1;
		}
		if (pageNum < 1)
			pageNum = 1;
		PageCount pageCount = new PageCount();// 创建分页对象
		pageCount.setPageSize(3);
		pageCount.setRecordCount(productList.size());
		pageCount.setCount(pageCount.getRecordCount(), pageCount.getPageSize());
		pageCount.setShowPage(1);
		if (pageNum == 0 || pageNum < -1) {
			pageCount.setShowPage(1);
		} else if (pageNum == -1 || pageNum > pageCount.getCount()) {
			pageCount.setShowPage(pageCount.getCount());
		} else {
			pageCount.setShowPage(pageNum);
		}
		request.setAttribute("pageCount", pageCount);
		int showPage = pageCount.getShowPage();
		// int lastPage = pageCount.getCount();
		int pageSize = pageCount.getPageSize();

		int begin, end;
		if (showPage <= 1) {
			begin = 0;
		} else if (showPage > pageCount.getCount()) {
			begin = (pageCount.getCount() - 1) * pageSize;
		} else {
			begin = (showPage - 1) * pageSize;
		}
		if (showPage * pageSize > pageCount.getRecordCount()) {
			end = pageCount.getRecordCount();
		} else {
			end = showPage * pageSize;
		}

		List subList = new ArrayList();
		subList = productList.subList(begin, end);
		request.setAttribute("productList", subList.isEmpty() ? null : subList);
		return "/index.jsp";
	}

	private String adminSelectProduct(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Book> productList = new BookDao().selectProduct();
		ArrayList<BookType> productclasslist = new BookTypeDao()
				.selectProductClass();
		request.setAttribute("productclasslist", productclasslist);
		int pageNum;
		String pageNumStr = request.getParameter("pageNum");
		if (pageNumStr == null || pageNumStr.equals("")) {
			pageNumStr = "1";
		}
		try {
			pageNum = Integer.parseInt(pageNumStr);
		} catch (NumberFormatException e) {
			pageNum = 1;
		}
		if (pageNum < 1)
			pageNum = 1;
		PageCount pageCount = new PageCount();// 创建分页对象
		pageCount.setPageSize(15);
		pageCount.setRecordCount(productList.size());
		pageCount.setCount(pageCount.getRecordCount(), pageCount.getPageSize());
		pageCount.setShowPage(1);
		if (pageNum == 0 || pageNum < -1) {
			pageCount.setShowPage(1);
		} else if (pageNum == -1 || pageNum > pageCount.getCount()) {
			pageCount.setShowPage(pageCount.getCount());
		} else {
			pageCount.setShowPage(pageNum);
		}
		request.setAttribute("pageCount", pageCount);
		int showPage = pageCount.getShowPage();
		int pageSize = pageCount.getPageSize();

		int begin, end;
		if (showPage <= 1) {
			begin = 0;
		} else if (showPage > pageCount.getCount()) {
			begin = (pageCount.getCount() - 1) * pageSize;
		} else {
			begin = (showPage - 1) * pageSize;
		}
		if (showPage * pageSize > pageCount.getRecordCount()) {
			end = pageCount.getRecordCount();
		} else {
			end = showPage * pageSize;
		}
		List subList = new ArrayList();
		subList = productList.subList(begin, end);
		request.setAttribute("productList", subList.isEmpty() ? null : subList);
		return "/admin/admin_BookList.jsp";

	}
}
