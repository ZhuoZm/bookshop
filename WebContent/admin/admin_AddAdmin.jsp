<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../style/style.css" rel="stylesheet" type="text/css" />
<title>添加图书</title>

<script type="text/javascript" language="javascript">
function addbt(){
  if(document.getElementById("username").value==""){
   		alert("用户名不能为空！");
   		document.getElementById("username").focus();
   }else if(document.getElementById("pwd").value==""){
   		alert("密码不能为空！");
   		document.getElementById("pwd").focus();
   }
   else if(document.getElementById("pwd").value!=document.getElementById("pwd_d").value){
  		alert("两次输入密码不一样！");
  		document.getElementById("pwd").focus();
  }else{ document.addform.submit();}
    }
   
</script>
</head>
<body>
<center>
<form action="../AdminAction.do?action=add" name="addform" id="addform"
	method="post" ><br />
<br />
<table border="0">
	<tr>
		<td>管理员用户名</td>
		<td><input type="text" name="username" id="username" /></td>
	</tr>
	<tr>
		<td>管理员密码</td>
		<td><input type="password"" name="admin_pwd" id="pwd" /></td>
	</tr>
	<tr>
		<td>管理员密码</td>
		<td><input type="password"" name="admin_pwd_d" id="pwd_d" /></td>
	</tr>
		<tr>
		<td>管理员真实姓名</td>
		<td><input type="text" name="admin_realname" id="admin_realname" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="button""" name="bt1" id="bt1" value="添加管理员" onclick="addbt();" />
		 <input type="reset" name="bt2" id="bt2" value="重置" /> 		
		 </td>
	</tr>
</table>
</form>
</center>
</body>
</html>