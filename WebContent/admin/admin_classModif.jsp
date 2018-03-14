<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/page-taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>增加类别</title>		
		<link href="${pageContext.request.contextPath}/style/style.css"
			rel="stylesheet" type="text/css">
	</head>
	<body>
		<h2>
		<c:if test="${bookType}!=''">
		修改类别</c:if>
		<c:if test="${bookType}==''">
		增加类别</c:if>			
		</h2>
		<br />
		<br />
		<form
			action="BookClassAction.do?action=modify"
			method="post" name="class_form" id="class_form">
			类别名称:
			<input type="hidden" name="id" id="id" value="${bookType.type_id}">
			<input type="text" name="name" id="name" value="${bookType.type_name}">
			<br />
			<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" name="addButton" id="addButton" value="提交" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" name="return" id="return" value="返回" onclick="history.back();" />
		</form>
	</body>
</html>