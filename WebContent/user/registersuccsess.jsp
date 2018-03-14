<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
</head>
<body>
<div style="width:800px;border:0;margin: 0 auto;">
<%@include file="/commons/page-top.jsp" %>
<br><br><br><br>
<div align="center">
您注册的信息如下，请记住您的注册信息<br />
您的姓名：${users.user_username }<br />
您的密码：${users.user_passwd}<br />
您的email：${users.user_email }<br />
您的地址：${users.user_address}<br />
您的电话：${users.user_telephone}<br />
<a href="<%=request.getContextPath() %>/index.html">进入购物空间</a>
<br><br><br>
 <%@include file="/commons/page-bottom.jsp" %>
 </div>
 </div>
</body>
</html>