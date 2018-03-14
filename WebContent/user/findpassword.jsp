<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/commons/page-taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="../style/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找密码页面</title>
<script type="text/javascript">
  function registerUser(){
  document.findPasswordForm.submit();
  }
</script>
</head>
<body>
<div style="width:800px;border:0;margin: 0 auto;">
<%@include file="/commons/page-top.jsp" %>
<p><br><br><br><p>
<form action="${pageContext.request.contextPath}/UserAction.do?action=findpassword" name="findPasswordForm" method="post">
<div id="login-news">
<table align="center" border="1" >
    <tr>
       <td colspan="2" align="center">
           <font size="5" color="blue"><b> 用户找回密码表</b></font>
        </td>      
    </tr>
   <tr>
       <td><font color="#c0c0f0"> 
        &nbsp;&nbsp; 用户名:&nbsp;<input type="text" name="username" id="username">
     </font>  </td>      
    </tr>
    <tr>
       <td><font color="#c0c0f0"> 
        &nbsp;&nbsp; email:&nbsp;  <input type="text" name="ename" id="ename">
     </font>   </td>      
    </tr>
    <tr>
       <td><font color="#c0c0f0"> 
        &nbsp; 家庭电话:<input type="text" name="mobilephone" id="mobilephone">
     </font>   </td>      
    </tr>
    <tr>
       <td><font color="#c0c0f0"> 
        &nbsp; 家庭地址:<input type="text" name="address" id="address">
       </font> </td>      
    </tr>   
    <tr>
       <td align="center">
       <input type="button"  value="确定" onclick="registerUser();"> 
       <input type="reset" value="取消">
       </td>      
    </tr>           
</table>
</div>    
 </form>
 <br><br>
 <center> <a href="<%=request.getContextPath() %>/BookAction.do?action=select">返回首页</a></center>
 <br><br><br><br>
  <%@include file="/commons/page-bottom.jsp" %>
  </div>
</body>
</html>