<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    <%@include file="/commons/page-taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查找密码成功</title>
</head>
<body>
<div style="width:800px;border:0;margin: 0 auto;">
<%@include file="/commons/page-top.jsp" %>
<div align="center">
尊敬的${users.user_username},您的注册信息如下：
您的密码：${users.user_passwd}<br /><br>
您的email：${users.user_email }<br /><br>
您的地址：${users.user_address}<br /><br>
您的电话：${users.user_telephone}<br /><br>
您的注册时间：${users.posttime}<br /><br>
请记住您的注册及注意保护您的账号密码，以防丢失。谢谢合作！<br><br>
</div>
<br><br>
<center> <a href="${pageContext.request.contextPath}/BookAction.do?action=select">返回首页</a></center>
<br><br><br>
 <%@include file="/commons/page-bottom.jsp" %>
 </div>
</body>
</html>