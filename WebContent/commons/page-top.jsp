<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/page-taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<table width="100%" border="0" cellpadding="0" cellspacing="3"
		id="page-header">
		<tr>
			<td valign="top" id="cell-logo">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td rowspan="2">
							<img src="${pageContext.request.contextPath}/images/logo.gif" width="130" height="56"
								alt="欢迎光临webshop">
						</td>
						<td align="right" valign="top" style="padding: 5px 10px;">
							<a href="BookCartAction.do?action=view">查看购物车</a> |
							<c:if test="${user!=null}">
								<a href="OrderAction.do?action=myOrderList&userid=${user.user_id}">我的订单</a>
							</c:if>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td id="cell-nav">
				<ul>
					<li>
						<a
							href="${pageContext.request.contextPath }/BookAction.do?action=select"
							style="color: #ffffff">首页</a>
					</li>
				</ul>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="page-search">
		<tr>
			<td align="right">
				<form id="findBook" name="findBook" method="post" action="BookAction.do?action=findBooksByKey">
					<img src="${pageContext.request.contextPath}/images/icon_search.gif" alt=""
						style="vertical-align: middle;">
					<select name="category" id="category"
						style="vertical-align: middle; width: 75px; height: 18px;">
						<c:forEach items="${productclasslist}" var="bookType">
							<option  value="${bookType.type_id}">
								${bookType.type_name}
							</option>
						</c:forEach>
					</select>
					<input name="keywords" class="textbox" type="text" id="keyword" />
					<input type="image" name="imageField" src="images/btn_go.gif"
						style="vertical-align: middle;" />
				</form>
			</td>
		</tr>
	</table>
</div>