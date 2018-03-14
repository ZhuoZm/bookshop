<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.edu2act.java.bookshop.servlets.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加商品类别</title>
<link href="../style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript">
function sub(){
	var imgurl = document.getElementById('imgurl');
	if(imgurl.value=="" || imgurl.value==null){
          alert("上传图片不能是空！");
	}else{
         document.addform.submit();
	}
}
</script>
<h2>更新商品图片</h2>
<form action="BookAction.do?action=uploadsubmit&id=${id}" method="post"
	name="addform" enctype="multipart/form-data">上传图片:<input
	type="file" value="" name="imgurl" id="imgurl" /><br />
<input type="button" name="addButton" id="addButton" value="增加"
	onclick="sub();" /><input
	type="button" name="return" id="return" value="返回"
	onclick="history.back();"/></form>
</body>
</html>