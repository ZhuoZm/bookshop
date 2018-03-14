<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增管理员</title>
<link href="${pageContext.request.contextPath }/style/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/style/lqy_style.css"
	rel="stylesheet" type="text/css" />
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
<div id="body-wrapper">
<div id="wrapper"><%@ include file="/commons/page-top.jsp"%><%--page-top--%>
<!-- middle 中间体 -->
<div id="middle"><!-- proudct cart start -->
<div id="lqy_prudctcart"></div>
<!-- proudct cart end --> <!-- center -->
<div id="lqy_productCart">
<div id="container">
	<div id="header"></div>
	<div id="content">
	<form action="UserAction.do?action=changePass" method="post" name = "changePass" onsubmit="return checkForm();" >
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
</div>
<!-- right -->
<div id="right">
<div id="msy_productclass">
<div id="lqy_productclass"></div>
</div>
</div>
<br style="clear: both;" />
<%@ include file="/commons/page-bottom.jsp"%><%--page-bottom--%>
</div>
</div>
</div>
</body>
</html>