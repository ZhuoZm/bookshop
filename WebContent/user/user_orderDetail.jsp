<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/commons/page-taglibs.jsp"%>
<html>
<head>
<title>订单详细信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath }/style/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/style/lqy_style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<div id="body-wrapper">
<div id="wrapper"><%@ include file="/commons/page-top.jsp"%><%--page-top--%>
<!-- middle 中间体 -->
<div id="middle"><!-- proudct cart start -->
<div id="lqy_prudctcart"></div>
<!-- proudct cart end --> <!-- center -->
<table align="center" border="0" width="580px" cellpadding="0"
	cellspacing="0">
	<tr class="lqy_productCart_thead">
		<td class="lqy_productCart_thead">订单ID</td>
		<td class="lqy_productCart_thead">${order.order_id} <input
			type="hidden" name="orderid" value="${order.order_id}"></td>
		<td class="lqy_productCart_thead">用户名:</td>
		<td class="lqy_productCart_thead">${order.user.user_username}</td>
	</tr>
	<tr>
		<td class="lqy_productCart_thead">地址:</td>
		<td class="lqy_productCart_thead">${order.user.user_address }</td>
		<td class="lqy_productCart_thead">邮箱:</td>
		<td class="lqy_productCart_thead">${order.user.user_email}</td>
	</tr>
	<tr>
		<td class="lqy_productCart_thead">商品名称</td>
		<td class="lqy_productCart_thead">单价</td>
		<td class="lqy_productCart_thead">数量</td>
		<td class="lqy_productCart_thead">&nbsp;</td>
	</tr>
	<c:forEach items="${orderList}" var="orderDetail">
		<tr>
			<td>${orderDetail.book.book_name}</td>
			<td>￥${orderDetail.book.book_price}</td>
			<td>${orderDetail.count}</td>
			<td>&nbsp;</td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="6" class="lqy_productCart_totalCost"><span
			id="showTotalCost">总价：￥${totalMoney}</span>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td colspan="6" style="text-align: center;"><input
			type="button" onclick="history.back();" name="submit" value="后退" /></td>
	</tr>
</table></div>
</div>
<!-- right -->
<div id="right">
<div id="msy_productclass">
<div id="lqy_productclass"></div>
</div>
</div>
<br style="clear: both;" />
<%@ include file="/commons/page-bottom.jsp"%><%--page-bottom--%>
</div>
</body>
</html>
