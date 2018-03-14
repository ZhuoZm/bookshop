<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.edu2act.java.bookshop.servlets.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>增加类别</title>

		<link href="/style/style.css"
			rel="stylesheet" type="text/css">
	</head>
	<body>
		<h2>
			增加类别
		</h2>
		<br />
		<br />
		<form
			action="${pageContext.request.contextPath}/BookClassAction.do?action=add"
			method="post" name="class_form" id="class_form">

			类别名称:
			<input type="text" name="name" id="name" value="">
			<br />
			<br />
			<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit"" name="addButton" id="addButton" value="增加"
				 />

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="return" id="return" value="返回"
				onclick="history.back();" />
		</form>
	</body>
</html>