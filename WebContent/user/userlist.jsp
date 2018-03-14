<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="" name="form1" id="form1" method="post">
<center>
<br/><br/>
<table width="70%">
<tr>
<td>用户编号</td>
<td>用户名称</td>
<td>用户密码</td>
<td>用户EMAIL</td>
<td>用户电话</td>
<td>用户地址</td>
<td>操作</td>
</tr>
<c:forEach items="${userList}" var="user">
<tr>
<td>${user.user_id }</td>
<td>${user.user_username }</td>
<td>${user.user_passwd }</td>
<td>${user.user_email }</td>
<td>${user.user_telephone }</td>
<td>${user.user_address }</td>
<td><a href="UserAction.do?name=${user.user_username}&action=delete">删除</a>
|<a href="UserAction.do?name=${user.user_username}&action=initPwd">初始化密码</a></td>
</tr> 
</c:forEach>


</table>

<br/><br/><br/><br/>
</center>
</form>
</body>
</html>