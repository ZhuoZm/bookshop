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
<div id="body-wrapper">
<div id="wrapper"><%@ include file="/commons/page-top.jsp"%><%--page-top--%>
<!-- middle 中间体 -->
<div id="middle"><!-- proudct cart start -->
<div id="lqy_prudctcart"></div>
<!-- proudct cart end --> <!-- center -->
<div id="lqy_productCart">
<form action="OrderAction.do?action=create" method="post"
	name="productForm" id="productForm"><script
	type="text/javascript">
function sub() {
	var products = document.getElementsByName("productid");
	var names = document.getElementsByName("count");
	var flag=true;
	for(var i=0;i<names.length;i++){
		if(names[i].value==0){
			flag=false;
		}
	}
	if (products.length == 0) {
		alert("没有订单");
	} else if(!flag){
		alert("数量不能为0，如不想购买可删除");
	}else {
		document.productForm.submit();
	}
}
function IDRequest() {
	var names = document.getElementsByName("count");
	var flag=true;
	for(var i=0;i<names.length;i++){
		if(names[i].value==0){
			flag=false;
		}
	}
	if(flag){
		document.productForm.action = "BookCartAction.do?action=setProductCart";
		document.productForm.submit();
	}else{
		alert("数量不能为0，如不想购买可删除");
	}
}
</script>
<table align="left" border="0" width="580px" cellpadding="0"
	cellspacing="0">
	<tr>
		<td class="lqy_productCart_thead">序号</td>
		<td class="lqy_productCart_thead">商品名称</td>
		<td class="lqy_productCart_thead">单价</td>
		<td class="lqy_productCart_thead">数量</td>
		<td class="lqy_productCart_thead">&nbsp;</td>
		<td class="lqy_productCart_thead">操作</td>
	</tr>
	<c:forEach items="${sessionScope.productCart}" var="productUnit">
		<tr>
			<td height="27px">${productUnit.product.book_id}</td>
			<td>${productUnit.product.book_name}</td>
			<td>￥${productUnit.product.book_price}</td>
			<td><input type="hidden" name="productid"
				value="${productUnit.product.book_id}" /><input type="text"
				value="${productUnit.count}" name="count" size="5" maxLength="5"
				onblur="IDRequest();" /></td>
			<td>&nbsp;</td>
			<td><a
				href="BookCartAction.do?action=delete&id=${productUnit.product.book_id}">删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="6" class="lqy_productCart_totalCost"><a
			href="BookAction.do?action=select">继续购买商品.....</a><font color="red"
			size="5">总价:￥${totalcost}</font> </td>
	</tr>
	<tr>
		<td colspan="6" style="text-align: center;"><input type="button"
			value="生成订单" onclick="sub();" /></td>
	</tr>
</table>
</form>
</div>
<!-- right -->
<div id="right">
<div id="msy_productclass">
<div id="lqy_productclass_navi">商品类别</div>
<div id="lqy_productclass_content">
<ul id="lqy_productclass_ul">
	<c:forEach items="${productclasslist}" var="bookType">
		<li><a
			href="${pageContext.request.contextPath}/BookAction.do?action=selectProductByClassId&id=${bookType.type_id }">${bookType.type_name}</a>
		</li>
	</c:forEach>
</ul>
<br style="clear: both;" />
</div>
</div>
</div>
<br style="clear: both;" />
<%@ include file="commons/page-bottom.jsp"%><%--page-bottom--%>
</div>
</div>
</div>
</body>
</html>