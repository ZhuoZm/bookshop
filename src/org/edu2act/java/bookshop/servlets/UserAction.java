package org.edu2act.java.bookshop.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.edu2act.java.bookshop.beans.User;
import org.edu2act.java.bookshop.dao.UserDao;

/**
 * Servlet implementation class for Servlet: UserAction
 * 
 */
public class UserAction extends HttpServlet {
	static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String result = null;
		if ("login".equals(action)) {
			result = login(request, response);
		} else if ("register".equals(action)) {
			result = doRegister(request, response);
		} else if ("exsit".equals(action)) {
			result = doExsit(request, response);
		} else if ("modify".equals(action)) {
			result = doModify(request, response);
		} else if ("show".equals(action)) {
			result = doShow(request, response);
		} else if ("findpassword".equals(action)) {
			result = doFindpassword(request, response);
		} else if ("list".equals(action)) {
			result = doList(request, response);
		} else if ("delete".equals(action)) {
			result = doDeleteUser(request, response);
		} else if ("initPwd".equals(action)) {
			result = initPwd(request, response);
		} else if ("changePass".equals(action)) {
			result = changePass(request, response);
		}

		request.getRequestDispatcher(result).forward(request, response);

	}

	private String changePass(HttpServletRequest request,
			HttpServletResponse response) {
		String msg = new String("");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String re_newpassword = request.getParameter("re_newpassword");
		request.getParameter("id");
		if (!newpassword.equals(re_newpassword)) {
			// 注意这种形式不好
			msg = "<script>alert('密码不一致');</script>";
			request.setAttribute("errorMessage", msg);
			return "/user/user_ChangePass.jsp";
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user.getUser_passwd().equals(oldpassword)) {
			user.setUser_passwd(newpassword);
			UserDao userDao = new UserDao();
			if (userDao.updatePassword(user)) {
				msg = "<script>alert('修改成功');</script>";
			} else {
				msg = "<script>alert('出错啦！');</script>";
			}
		} else {
			msg = "<script>alert('旧密码有错！');</script>";
		}
		request.setAttribute("errorMessage", msg);
		return "/user/user_ChangePass.jsp";
	}

	private String initPwd(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		userDao.initPwd(name);
		return "/UserAction.do?action=list";
	}

	private String doDeleteUser(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		userDao.deleteuser(name);
		return "/UserAction.do?action=list";

	}

	private String doList(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<User> userlist = userDao.selectalluser();
		request.setAttribute("userList", userlist);
		return "/user/userlist.jsp";
	}

	private String doShow(HttpServletRequest request,
			HttpServletResponse response) {
		String us = request.getParameter("name");
		User user1 = userDao.showmodify(us);
		// String password = user1.getUser_passwd();
		HttpSession session = request.getSession();
		session.setAttribute("user", user1);
		return "/user/modifyuser.jsp";
	}

	private String doModify(HttpServletRequest request,
			HttpServletResponse response) {

		String usersname = request.getParameter("username");
		String email = request.getParameter("email");
		String telephone = request.getParameter("mobilephone");
		String address = request.getParameter("address");
		if (userDao.modifyuser(usersname, email, telephone, address)) {
			return "/BookAction.do?action=select";
		} else {
			return "/UserAction.do?action=show&name=" + usersname;
		}
	}

	private String doExsit(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "/BookAction.do?action=select";
	}

	private String doRegister(HttpServletRequest request,
			HttpServletResponse response) {
		User user = new User();
		user.setUser_username(request.getParameter("username"));
		user.setUser_email(request.getParameter("ename"));
		user.setUser_passwd(request.getParameter("userpass"));
		// String repass = request.getParameter("repass");
		user.setUser_telephone(request.getParameter("mobilephone"));
		user.setUser_address(request.getParameter("address"));

		if (userDao.isUserNameUsed(request.getParameter("username"))) {
			request.setAttribute("errorMessage", "该用户名已被占用！请换一个！！");
			return "/Error.jsp";
		}
		if (userDao.isEmailUsed(request.getParameter("ename"))) {
			request.setAttribute("errorMessage", "该邮箱已被注册！请换一个！！");
			return "/Error.jsp";
		}
		boolean result = userDao.registerUser(user);
		request.setAttribute("users", user);
		if (result) {
			return "/user/registersuccsess.jsp";
		} else
			return "";
	}

	private String login(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("user");
		String pass = request.getParameter("pass");
		String rand = request.getParameter("captcha");
		if (rand == null || rand.equals("")) {
			request.setAttribute("result", "<script>alert('验证码错误!');</script>");
			return "/BookAction.do?action=select";
		}
		User user = userDao.login(username, pass);
		HttpSession session = request.getSession();
		if (user == null) {
			request
					.setAttribute("result",
							"<script>alert('用户名密码错误');</script>");
		}
		session.setAttribute("user", user);
		return "/BookAction.do?action=select";

	}

	private String doFindpassword(HttpServletRequest request,
			HttpServletResponse response) {
		// boolean result = false;
		User user = new User();
		user.setUser_username(request.getParameter("username"));
		user.setUser_email(request.getParameter("ename"));
		user.setUser_telephone(request.getParameter("mobilephone"));
		user.setUser_address(request.getParameter("address"));
		User us = userDao.findUserPassword(user);
		request.setAttribute("users", us);
		if (us != null) {
			return "/user/findpasswordsuccess.jsp";
		} else {
			return "/user/findpasswordfailed.jsp";
		}
	}
}
