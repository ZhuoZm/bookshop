<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/page-taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<div id="body-wrapper">
<div id="wrapper">
<%@ include file="/commons/page-top.jsp"%><%--page-top--%>
<!-- middle 中间体 -->
<div id="middle"><!-- proudct cart start -->
<div id="lqy_prudctcart"></div>
<!-- proudct cart end --> <!-- center -->
<div id="lqy_productCart">
<table align="center" border="1" width="80%" cellpadding="0"
	cellspacing="0">
	<tr>
		<td colspan="7" align="center">订单列表</td>
	</tr>
	<tr>
		<td height="27px" style="border-color: #F6F6F6;">订单ID</td>
		<td style="border-color: #F6F6F6;">客户</td>
		<td style="border-color: #F6F6F6;">下单时间</td>
		<td style="border-color: #F6F6F6;">订单状态</td>
		<td style="border-color: #F6F6F6;">操作</td>
	</tr>

	<c:forEach items="${orderList}" var="order">
		<tr>
			<td height="27px" style="border-color: #F6F6F6;">${order.order_id}</td>
			<td style="border-color: #F6F6F6;">${order.user.user_username}&nbsp;</td>
			<td style="border-color: #F6F6F6;">${order.order_time}</td>
			<td style="border-color: #F6F6F6;">${order.order_state}</td>
			<td style="border-color: #F6F6F6;"><a
				href="${pageContext.request.contextPath}/OrderAction.do?action=userdetailed&id=${order.order_id}">详细</a>|
			<c:if test="${order.order_state=='未处理'}">
				<a
					href="${pageContext.request.contextPath}/OrderAction.do?action=delete&id=${order.order_id}">删除</a>|
							<a
					href="${pageContext.request.contextPath}/OrderAction.do?action=confirm&orderid=${order.order_id}">确认订单</a>|</c:if></td>
		</tr>
	</c:forEach>
</table>
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
</div>
</div>
</body>
</html>