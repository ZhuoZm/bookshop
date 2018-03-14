<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@include file="/commons/page-taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<%
			Object us = session.getAttribute("user");
			org.edu2act.java.bookshop.beans.User user = (org.edu2act.java.bookshop.beans.User) us;
			if (user != null) {
				String username = user.getUser_username();
				out.print("恭喜您" + username + ",您登录成功,祝您购物愉快!");
		%>
		<br/>
<a href="UserAction.do?action=exsit">退出</a>
<a href="UserAction.do?action=show&name=<%=username%>">用户信息</a><br/>
<a href="<%=request.getContextPath()%>/user/user_ChangePass.jsp">修改密码</a>
<%} else { %>
<form action="UserAction.do?action=login" method="post" name="loginForm">
<table border="0" cellpadding="0" cellspacing="0" align="center"
	background="${pageContext.request.contextPath}/images/login_bg.gif">
	<tr>
		<td><img
			src="${pageContext.request.contextPath}/images/member_login2.jpg"></td>
	</tr>
	<tr>
		<td>账&nbsp;&nbsp;&nbsp;号： <input type="text" name="user"
			id="user" style="width: 140px; height: 16px;" /></td>

	</tr>

	<tr>
		<td>密&nbsp;&nbsp;&nbsp;码： <input type="password" name="pass"
			id="pass" style="width: 140px; height: 16px;" /></td>
	</tr>

	<tr>
		<td valign="bottom">验证码： <input id="captcha" name=captcha
			maxLength=4 style="width: 60px; height: 16px;" /> <img
			src="<%=request.getContextPath()%>/commons/image.jsp" /></td>
	</tr>

	<tr align="center">
		<td><input type="submit" value="登录"
			style="height: 18px; width: 52px;"> &nbsp; <input
			type="reset" value="取消" style="height: 18px; width: 52px;"></td>
	</tr>

	<tr>
		<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp; <a
			href="${pageContext.request.contextPath}/user/register.jsp">注册新会员</a></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp; <a
			href="${pageContext.request.contextPath}/user/findpassword.jsp">您忘记密码了吗</a>
		</td>
	</tr>
</table>
</form>
<%} %>
</body>
</html>