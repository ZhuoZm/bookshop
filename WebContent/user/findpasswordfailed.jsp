<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/commons/page-taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查找用户信息失败</title>
</head>
<body>
<div style="width:800px;border:0;margin: 0 auto;">
<%@include file="/commons/page-top.jsp" %>
<div align="center">
<table >
<tr>
   <td>
     对不起,暂时没有您的信息,请您注册您的用户信息<br>
   </td>
</tr>
<tr>
   <td>
      <a href="${pageContext.request.contextPath}/user/findpassword.jsp">重新找回密码</a>
   </td>
</tr>
</table>
<br>
<a href="${pageContext.request.contextPath}/ProductAction.do?action=select">返回首页</a>
<br>
 <%@include file="/commons/page-bottom.jsp" %>
</div>
</div>
</body>
</html>