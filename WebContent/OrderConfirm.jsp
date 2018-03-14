<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/page-taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.edu2act.java.bookshop.commons.PageCount"%>
<%@page import="org.edu2act.java.bookshop.beans.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.edu2act.java.bookshop.beans.BookType"%>
<%@page import="org.edu2act.java.bookshop.beans.ProductCart"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link href="${pageContext.request.contextPath }/style/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/style/lqy_style.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/inc/ajax.js"></script>
<div id="body-wrapper">
<div id="wrapper"><%@ include file="/commons/page-top.jsp"%><%--page-top--%>
<!-- middle 中间体 -->
<div id="middle"><!-- proudct cart start -->
<div id="lqy_prudctcart"></div>
<!-- proudct cart end --> <!-- center -->
<div id="lqy_productCart">
<form action="OrderAction.do?action=confirm" method="post"
	name="productCartForm" id="productCartForm"
	onsubmit="return checkForm();">
<table align="left" border="0" width="580px" cellpadding="0"
	cellspacing="0">
	<tr>
		<td class="lqy_productCart_thead">订单ID</td>
		<td class="lqy_productCart_thead">${order.order_id} <input
			type="hidden" name="orderid" value="${order.order_id}"></td>
		<td class="lqy_productCart_thead">用户名:</td>
		<td class="lqy_productCart_thead">${user.user_username }</td>
	</tr>
	<tr>
		<td class="lqy_productCart_thead">地址:</td>
		<td class="lqy_productCart_thead" colspan="3">${user.user_address
		}</td>
	</tr>
	<tr>

		<td class="lqy_productCart_thead">商品名称</td>
		<td class="lqy_productCart_thead">单价</td>
		<td class="lqy_productCart_thead">数量</td>
		<td class="lqy_productCart_thead"></td>

	</tr>
	<c:forEach items="${orderDetailList}" var="orderDetail">
		<tr>
			<td>${orderDetail.book.book_name}</td>
			<td>￥${orderDetail.book.book_price}</td>
			<td>${orderDetail.count}</td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="6" class="lqy_productCart_totalCost"><a
			href="BookAction.do?action=select">继续购买商品.....</a><span
			id="showTotalCost">总价：￥${totalMoney}</span>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td colspan="6" style="text-align: center;"><input
			type="submit" name="submit" value="确认订单" /></td>
	</tr>
</table>
</form>
</div>
<!-- right -->
<div id="right">
<div id="msy_productclass">
<div id="lqy_productclass"></div>
</div>
</div>
<br style="clear: both;" />
<%@ include file="commons/page-bottom.jsp"%><%--page-bottom--%>
</div>
</div>
</div>
</body>
</html>