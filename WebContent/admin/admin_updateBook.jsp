<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/commons/page-taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>更新图书信息</title>
<link href="${pageContext.request.contextPath}/style/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<center><br />
<form action="BookAction.do?action=update" name="addform" id="addform"
	method="post"><br />
<br />
<table border="0">
	<tr>
		<td>图书名称</td>
		<td><input type="hidden" name="id" id="id" value="${book.book_id}" />
		<input type="text" name="product" id="product"
			value="${book.book_name}" /></td>
	</tr>
	<tr>
		<td>图书价格</td>
		<td><input type="text" name="price" id="price"
			value="${book.book_price }" /></td>
	</tr>
	<tr>
		<td>所属类别</td>
		<td><select id="classid" name="classid">
			<c:forEach items="${typeList}" var="type">
				<option value="${type.type_id}"
					<c:if test="${type.type_id==book.bookType.type_id}">selected="selected" </c:if>>
				${type.type_name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>图书作者</td>
		<td><input type="text" name="author" id="author"
			value="${book.book_auth}" /></td>
	</tr>	
	<tr>
		<td>图书出版社</td>
		<td><input type="text" name="publisher" id="publisher" value="${book.book_publisher}"/></td>
	</tr>
	<tr>
		<td>图书描述</td>
		<td><textarea rows="5" cols="50" name="description"
			id="description" style="overflow: hidden;">${book.book_description }</textarea>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" name="bt1" id="bt1" value="提交" /> <input
			type="reset" name="bt2" id="bt2" value="重置" /> <input type="button"
			name="button" value="返回产品列表"
			onclick="javascript:document.location='${pageContext.request.contextPath}/BookAction.do?action=selectlist'" />
		</td>
	</tr>

</table>
</form>

</center>
</body>
</html>