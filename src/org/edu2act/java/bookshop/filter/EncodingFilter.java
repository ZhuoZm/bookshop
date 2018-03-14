package org.edu2act.java.bookshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private static final String DEFAULT_ENCODING = "UTF-8";

	public EncodingFilter() {
		super();
	}

	private String encoding = DEFAULT_ENCODING;

	
	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {

		request.setCharacterEncoding(this.encoding);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		filterChain.doFilter(request, response);

	}	
	public void init(FilterConfig filterConfig) throws ServletException {
		String encode = null;
		if (filterConfig != null)
			encode = filterConfig.getInitParameter("encode");
		if (encode != null && encode.length() > 0)
			this.encoding = encode;
	}

}
