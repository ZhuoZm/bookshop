<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/commons/page-taglibs.jsp"%>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'admin_orderDetail.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${pageContext.request.contextPath }/style/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/style/lqy_style.css"
	rel="stylesheet" type="text/css" />

</head>

<body>
<br>
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
</table>
</body>
</html>
