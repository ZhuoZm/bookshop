/**
 * 
 */
package org.edu2act.java.bookshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		Object adminObject = request.getSession().getAttribute("admin");
		Object userObject = request.getSession().getAttribute("user");
		String requestUrl = request.getRequestURL().toString();
		int indexOf = requestUrl.indexOf("/admin/");
		if (indexOf > 0) {
			if (requestUrl.indexOf("/admin/admin_login.jsp") > 0
					|| requestUrl.indexOf("/AdminAction.do") > 0
					|| adminObject != null) {
				arg2.doFilter(request, response);
			} else {
				request.getRequestDispatcher("/admin/admin_login.jsp").forward(
						arg0, arg1);
			}
		} else if (requestUrl.contains("BookCartAction.do")
				&& userObject == null) {
			request.setAttribute("result",
					"<script>alert('您还没有登录，请先登录');</script>");
			request.getRequestDispatcher("/BookAction.do?action=select")
					.forward(arg0, arg1);
		} else {
			arg2.doFilter(request, response);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
