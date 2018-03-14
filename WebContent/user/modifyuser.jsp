<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改页面</title>
<script type="text/javascript">
	function modifyUser() {
		document.getElementById('modifyForm').submit();
	}
</script>
</head>
<body>
<div style="width: 800px; border: 0; margin: 0 auto;" align="center">
<table>
	<tr>
		<td><%@include file="/commons/page-top.jsp"%>
		</td>
	</tr>
	<tr>
		<td>
		<form action="UserAction.do?action=modify" name="modifyForm"
			method="post">
		<table align="center" width="433" height="231">
			<tr>
				<td>尊敬的用户：${user.user_username }</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" readonly="readonly" name="username"
					value="${user.user_username }"></td>
			</tr>
			<tr>
				<td>用户email地址：</td>
				<td><input type="text" name="email" value="${user.user_email }">
				</td>
			</tr>
			<tr>
				<td>用户联系电话：</td>
				<td><input type="text" name="mobilephone"
					value="${user.user_telephone}"></td>
			</tr>
			<tr>
				<td>用户地址:</td>
				<td><input type="text" name="address" value="${user.user_address }">
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="修改"></td>
				<td><input type="reset" value="取消"></td>
			</tr>
		</table>
		</form>

		</td>
	<tr>
		<td><%@include file="/commons/page-bottom.jsp"%>
		</td>
	</tr>
</table>
</div>
</body>
</html>