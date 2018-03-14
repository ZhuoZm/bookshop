<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/commons/page-taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台展示页面</title>
<link href="${pageContext.request.contextPath}/style/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/lqy_style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	${result}
	<div id="body-wrapper">
		<div id="wrapper"><%@ include file="/commons/page-top.jsp"%><%--page-top--%>
			<!-- middle 中间体 -->
			<div id="middle">
				<!-- left -->
				<div id="left"><%@ include file="/user/enter.jsp"%>
				</div>
				<!-- center -->
				<div id="center">
					<div id="lqy_product">
						<c:if test="${productList==null}">
								无任何商品!
							</c:if>
						<c:if test="${productList!=null}">
							<c:forEach items="${productList}" var="book">

								<table width="380" height="120" border="0" cellpadding="0"
									cellspacing="0" id="lqy_productclass_product">
									<tr>

										<td width="110" rowspan="5" class="lqy_productclass_img">
											<img height="150" width="110" border="90"
											src="${pageContext.request.contextPath}/${book.book_imgurl}"
											alt="欢迎光临webshop"> <input type="hidden" name="id"
											value="${book.book_id}">
										</td>
										<td width="80" class="lqy_productclass_td">商品名称:</td>
										<td class="lqy_productclass_td">&nbsp;${book.book_name}</td>
									</tr>
									<tr>
										<td class="lqy_productclass_td">所属类别:</td>
										<td class="lqy_productclass_td">&nbsp; <a
											href="BookAction.do?action=selectProductByClassId&id=${book.bookType.type_id}">${book.bookType.type_name}</a>
										</td>
									</tr>
									<tr>
										<td class="lqy_productclass_td">商品价格:￥</td>
										<td class="lqy_productclass_td">&nbsp;${book.book_price}
										</td>
									</tr>
									<tr>
										<td class="lqy_productclass_td">附属说明:</td>
										<td class="lqy_productclass_td">
											&nbsp;${book.book_description}</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="right"><a
											href="BookCartAction.do?action=addProduct&id=${book.book_id}">放入购物车&nbsp;&nbsp;</a>
										</td>
									</tr>
								</table>

							</c:forEach>
						</c:if>
					</div>
				</div>
				<!-- right -->

				<div id="right">
					<div id="msy_productclass">
						<div id="lqy_productclass">
							<div id="lqy_productclass_navi">商品类别</div>
							<div id="lqy_productclass_content">
								<ul id="lqy_productclass_ul">
									<c:forEach items="${productclasslist}" var="bookType">

										<li><a
											href="${pageContext.request.contextPath}/BookAction.do?action=selectProductByClassId&id=${bookType.type_id}">${bookType.type_name}</a>
										</li>

									</c:forEach>
								</ul>
								<br style="clear: both;" />
							</div>
						</div>
					</div>
				</div>
				<br style="clear: both;" />

				<div id="lqy_product_showpage">
					共${pageCount.recordCount}条记录&nbsp;&nbsp;共 ${pageCount.count}
					<c:if test="${pageCount.isFirst}">
  &nbsp;首页&nbsp;&nbsp;上一页&nbsp;
</c:if>
					<c:if test="${!(pageCount.isFirst)}">
  &nbsp;<a href='BookAction.do?action=select&pageNum=1'>首页</a>&nbsp;
  &nbsp;<a
							href='BookAction.do?action=select&pageNum=${pageCount.showPage-1}'>上一页</a>&nbsp;
</c:if>
					<c:if test="${pageCount.isLast}">
  &nbsp;尾页&nbsp;&nbsp;下一页&nbsp;
</c:if>
					<c:if test="${!(pageCount.isLast)}">
  &nbsp;<a
							href='BookAction.do?action=select&pageNum=${pageCount.showPage+1}'>下一页</a>&nbsp;
  &nbsp;<a href='BookAction.do?action=select&pageNum=${pageCount.count}'>尾页</a>&nbsp;
</c:if>
					&nbsp;当前页为第 ${pageCount.showPage} 页
				</div>

				<%@ include file="commons/page-bottom.jsp"%><%--page-bottom--%>
			</div>
		</div>
	</div>
</body>
</html>