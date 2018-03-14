package org.edu2act.java.bookshop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.edu2act.java.bookshop.beans.Admin;
import org.edu2act.java.bookshop.dao.AdminDao;

public class AdminAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = null;
		String action = request.getParameter("action");
		if ("add".equals(action)) {
			result = addAction(request, response);
		} else if ("login".equals(action)) {
			result = loginAction(request, response);
		} else if ("change".equals(action)) {
			result = changePass(request, response);
		} else if ("logout".equals(action)) {
			result = logout(request, response);
		}

		request.getRequestDispatcher(result).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	private String loginAction(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rand = request.getParameter("captcha");
		String msg = "";
		if (username == null || username.equals("")) {
			msg = "<script>alert('用户名不能为空!');</script>";
			request.setAttribute("msg", msg);
			return "/admin/admin_login.jsp";
		}
		if (password == null || password.equals("")) {
			msg = "<script>alert('密码不能为空!');</script>";
			request.setAttribute("msg", msg);
			return "/admin/admin_login.jsp";
		}
		if (rand == null || rand.equals("")) {
			msg = "<script>alert('验证码错误!');</script>";
			request.setAttribute("msg", msg);
			return "/admin/admin_login.jsp";
		}
		Admin admin = new Admin();
		admin.setAdmin_username(username.trim());
		admin.setAdmin_passwd(password);
		AdminDao adminDao = new AdminDao();
		boolean isSuccess = adminDao.Login(admin);
		HttpSession session = request.getSession();
		String srand = (String) session.getAttribute("rand");
		if (srand.equals(rand.trim())) {
			if (isSuccess) {
				session.setAttribute("admin", admin);
				return "admin/admin_main.jsp";
			} else {
				msg = "<script>alert('用户名或密码错误!');</script>";
				request.setAttribute("msg", msg);
				return "/admin/admin_login.jsp";
			}
		} else {
			msg = "<script>alert('验证码错误!');</script>";
			request.setAttribute("msg", msg);
			return "/admin/admin_login.jsp";
		}
	}

	private String logout(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		return "/admin/admin_login.jsp";
	}

	private String addAction(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String adminUserName = request.getParameter("username");
		String adminPwd = request.getParameter("admin_pwd");
		String adminRealname = request.getParameter("admin_realname");
		// MD5 md5 = new MD5();
		// adminPwd = md5.getMD5ofStr(adminPwd);
		Admin admin = new Admin();
		admin.setAdmin_username(adminUserName);
		admin.setAdmin_passwd(adminPwd);
		admin.setAdmin_realname(adminRealname);
		AdminDao adminDao = new AdminDao();
		if (adminDao.isExit(adminUserName)) {
			String errorStr = "用户名已存在！！";
			request.setAttribute("errorMessage", errorStr);
			return "/Error.jsp";
		}
		if (adminDao.addAdmin(admin)) {
			String msg = "<script>alert('添加管理员成功！');</script>";
			request.setAttribute("msg", msg);
		} else {
			String errorStr = "数据库操作失败！！";
			request.setAttribute("errorMessage", errorStr);
			return "/Error.jsp";
		}
		return "/admin/admin_listAdmin.jsp";
	}

	private String changePass(HttpServletRequest request,
			HttpServletResponse response) {
		String msg = new String("");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String re_newpassword = request.getParameter("re_newpassword");
		if (!newpassword.equals(re_newpassword)) {
			// 注意这种形式不好
			msg = "<script>alert('密码不一致');</script>";
			request.setAttribute("errorMessage", msg);
			return "/admin/admin_ChangePass.jsp";
		}
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin.getAdmin_passwd().equals(oldpassword)) {
			admin.setAdmin_passwd(newpassword);
			AdminDao adminDao = new AdminDao();
			if (adminDao.updatePassword(admin)) {
				msg = "<script>alert('修改成功');</script>";
			} else {
				msg = "<script>alert('出错啦！');</script>";
			}
		} else {
			msg = "<script>alert('旧密码有错！');</script>";
		}
		request.setAttribute("errorMessage", msg);
		return "/admin/admin_ChangePass.jsp";
	}
}
