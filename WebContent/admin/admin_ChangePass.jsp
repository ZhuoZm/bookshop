<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<script type="text/javascript">
	function checkForm(){
		if(document.getElementById("oldpassword").value==""){
			alert("旧密码不能为空！");
			document.getElementById("oldpassword").focus();
			return false;
		}
		if(document.getElementById("newpassword").value==""){
			alert("旧密码不能为空！");
			document.getElementById("newpassword").focus();
			return false;
		}
		if(document.getElementById("re_newpassword").value==""){
			alert("旧密码不能为空！");
			document.getElementById("re_newpassword").focus();
			return false;
		}
	}
</script>
</head>
<body>
<div id="container">
	<div id="header"></div>
	<div id="content">
	<form action="../UserAction.do?action=changePass" method="post" name = "changePass" onsubmit="return checkForm();" >
	${errorMessage}<br/>
	<ul>
	<li>旧密码：<input type="password" name="oldpassword" id="oldpassword" /></li>
	<li>新密码：<input type="password" name="newpassword" id="newpassword" /></li>
	<li>新密码：<input type="password" name="re_newpassword" id="re_newpassword" /></li>
	<li><input type="submit" value="修改" name="submit" id="submit" ></li>
	</ul>
	</form>
	</div>
</div>
</body>
</html>