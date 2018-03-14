<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/commons/page-taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/style/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<center><br />
<table width="490" border="1" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="22" colspan="2">
		<div align="left"><span class="STYLE2">图书详细信息显示：</span></div>
		</td>
	</tr>
	<tr>
		<td width="91" height="22">图书编号：</td>
		<td width="393">&nbsp;&nbsp;${book.book_id}</td>
	</tr>
	<tr>
		<td height="25">图书名称：</td>
		<td>&nbsp;&nbsp;${book.book_name}</td>
	</tr>
	<tr>
		<td height="25">图书作者：</td>
		<td>&nbsp;&nbsp;${book.book_auth}</td>
	</tr>
	<tr>
		<td height="25">图书出版社：</td>
		<td>&nbsp;&nbsp;${book.book_publisher}</td>
	</tr>
	<tr>
		<td height="24">图书类别：</td>
		<td>&nbsp;&nbsp;${book.bookType.type_name}</td>
	</tr>
	<tr>
		<td height="25">图书价格：</td>
		<td>&nbsp;&nbsp;${book.book_price}</td>
	</tr>
	<tr>
		<td height="25">产品图片：</td>
		<td>
			<img src="${pageContext.request.contextPath}/${book.book_imgurl}" /></td>
	</tr>

	<tr>
		<td height="73">产品介绍：</td>
		<td>
		<div align="left"><textarea name="textarea" rows="7" cols="50">${book.book_description }</textarea></div>
		</td>
	</tr>
	<tr>
		<td height="28" colspan="2"><input type="button" name="button"
			value="返回产品列表"
			onclick="javascript:document.location='${pageContext.request.contextPath}/BookAction.do?action=selectlist'" />

		</td>
	</tr>
</table>
</center>
</body>
</html>