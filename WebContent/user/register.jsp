<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
    function checkForm(){
    
    //判断登陆名
    var Username = /[^\w]|[a-zA-Z_0-9]/;
    //判断密码
    var Pwd = /[^\w]|[a-zA-Z_0-9]/;
    //判断地址
   var add = /[^\w]|[a-zA-Z_0-9]/;
    //判断电话号码
   var Mobilephone = new RegExp("[_0-9]{1,20}");
    //判断邮箱
  //var Email_zhz = w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*    
 
    //获得input对象
    var username = document.getElementById("username");
    var password = document.getElementById("userpass");
    var password2 = document.getElementById("repass");
    var address = document.getElementById("address");
    var email = document.getElementById("ename");
    var mobilephone = document.getElementById("mobilephone");
    
     if(username.value == null || username.value == ""){
        alert("用户名不能为空");
         return false;
     }else if(username.value.match(Username)==null){
        alert("您输入的用户名与规定类型不匹配");
        return false;
     } else if(username.value.length < 3 || username.value.length > 10){
        alert("用户名最小不能3位，最大不能超过10位");
         return false;
     } else if(email.value == null || email.value == ""){
        alert("email不能为空");
         return false; 
     } else if(email.value.indexOf("@")<0){
        alert("您输入的邮箱格式不正确");
        return false;
     }else if(password.value == null || password.value == ""){
        alert("密码不能为空");
         return false;
     }else if(password.value.match(Pwd) == null){
        alert("您输入的密码与规定类型不匹配");
        return false;
     }else if(password.value !=password2.value){
        alert("两次密码不能不一致");
         return false;
     }else if(password.value.length < 5 || password.value.length >20){
        alert("密码最小5位 最大不能超过20");
         return false;
     }else if(address.value == null || address.value == ""){
        alert("地址不能为空");
        return false;
     }else if(address.value.match(add) == null){
        alert("您输入的地址有误");
        return false;
     }else if(mobilephone.value == null || mobilephone.vlaue == ""){
        alert("电话号码不能为空");
        return false;
     }else if(mobilephone.value.match(Mobilephone) == null){
        alert("您输入的电话号码无效");
        return false;
    }
     return true;
}
</script>
</head>
<body>
<div style="width: 800px; border: 0; margin: 0 auto;"><%@include
	file="/commons/page-top.jsp"%> <br>
<br>
<form action="../UserAction.do?action=register" name="registerForm"
	method="post" onsubmit="return checkForm();">
<div id="login-news">
<table align="center" border="1">
	<tr>
		<td colspan="2" align="center"><font size="4" color="blue"><b>
		用户注册表</b></font></td>
	</tr>
	<tr>
		<td><font> &nbsp;&nbsp; 用户名:&nbsp; </font></td>
		<td><input type="text" name="username" id="username">用户名长度不得少于3个字(用户名只能由数字,字母,下划线组成)</td>
	</tr>
	<tr>
		<td><font> &nbsp;&nbsp; email:&nbsp; </font></td>
		<td><input type="text" name="ename" id="ename">请输入有效的EMAIL地址
		</td>
	</tr>
	<tr>
		<td><font> &nbsp;&nbsp;&nbsp;密&nbsp;码:&nbsp;&nbsp; </font></td>
		<td><input type="password" name="userpass" id="userpass">密码长度不得少于6位(用户名只能由数字,字母,下划线组成)
		</td>
	</tr>
	<tr>
		<td><font> &nbsp; 确认密码:</font></td>
		<td><input type="password" name="repass" id="repass">请确定上述密码与本次输入密码一致
		</td>
	</tr>
	<tr>
		<td><font> &nbsp; 家庭电话:</font></td>
		<td><input type="text" name="mobilephone" id="mobilephone">请输入有效的电话号码
		</td>
	</tr>
	<tr>
		<td><font> &nbsp; 家庭地址: </font></td>
		<td><input type="text" name="address" id="address">请输入有效的家庭地址</td>
	</tr>
	<tr>
		<td align="center"><input type="submit" name="ok" value="确定">
		<input type="reset" name="no" value="取消"></td>
	</tr>
</table>
</div>
</form>
<br>
<br>
<center><a
	href="<%=request.getContextPath() %>/BookAction.do?action=select">返回首页</a></center>
<br>
<br>
<br>
<br>
<%@ include file="/commons/page-bottom.jsp"%></div>
</body>
</html>