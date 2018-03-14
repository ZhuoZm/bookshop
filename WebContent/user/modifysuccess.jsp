<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改成功</title>
</head>
<body>
<div style="width:800px;border:0;margin: 0 auto;" align="center">
<%@include file="/commons/page-top.jsp" %>

修改成功<br>
<center> <a href="<%=request.getContextPath() %>/BookAction.do?action=select">返回首页</a></center>
 <%@include file="/commons/page-bottom.jsp" %>
 </div>
</body>
</html>