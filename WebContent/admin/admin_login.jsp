<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>ECSHOP 管理中心</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="<%=request.getContextPath() %>/images/admin_images/general.css" type=text/css rel=stylesheet>
<LINK href="<%=request.getContextPath() %>/images/admin_images/main.css" type=text/css rel=stylesheet>
<STYLE type=text/css>
BODY {
	COLOR: white
}
</STYLE>

<SCRIPT src="<%=request.getContextPath() %>/images/utils.js" type=text/javascript></SCRIPT>
<SCRIPT src="<%=request.getContextPath() %>/images/validator.js" type=text/javascript></SCRIPT>
<SCRIPT language=JavaScript>
// 这里把JS用到的所有语言都赋值到这里
var process_request = "正在处理您的请求...";
var todolist_caption = "记事本";
var todolist_autosave = "自动保存";
var todolist_save = "保存";
var todolist_clear = "清除";
var todolist_confirm_save = "是否将更改保存到记事本？";
var todolist_confirm_clear = "是否清空内容？";
var user_name_empty = "管理员用户名不能为空!";
var password_invaild = "密码必须同时包含字母及数字且长度不能小于于6!";
var email_empty = "Email地址不能为空!";
var email_error = "Email地址格式不正确!";
var password_error = "两次输入的密码不一致!";
var captcha_empty = "您没有输入验证码!";

if (window.parent != window)
{
  window.top.location.href = location.href;
}
</SCRIPT>

<META content="MSHTML 6.00.3790.1830" name=GENERATOR>
</HEAD>
<BODY style="BACKGROUND: rgb(39, 130, 150) 0% 50%; moz-background-clip: -moz-initial; moz-background-origin: -moz-initial; moz-background-inline-policy: -moz-initial" >
<FORM action="${pageContext.request.contextPath}/AdminAction.do?action=login" method="post" onsubmit="return validate();">
<TABLE style="MARGIN-TOP: 100px" cellSpacing=0 cellPadding=0
	align=center>
	<TBODY>
		<TR>
			<TD>WebShop</TD>
			<TD style="PADDING-LEFT: 50px">
			<TABLE>
				<TBODY>
					<TR>
						<TD>管理员姓名：</TD>
						<TD><INPUT name=username  id=username/></TD>
					</TR>
					<TR>
						<TD>管理员密码：</TD>
						<TD><INPUT type=password name=password id=password /></TD>
					</TR>
					<TR>
						<TD>验证码：</TD>
						<TD><INPUT class=capital name=captcha id=captcha /></TD>
					</TR>
					<TR>
						<TD align=right colSpan=2><img src="<%=request.getContextPath() %>/commons/image.jsp"  /></TD>
					</TR>
					<TR>
						<TD colSpan=2><LABEL><INPUT type=checkbox value=1
							name=remember>请保存我这次的登录信息。</LABEL></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
						<TD><INPUT class=button type=submit value=进入管理中心 /></TD>
					</TR>
					<TR>
						<TD align=right colSpan=2>» <A style="COLOR: white"
							href="./">返回首页</A> » </TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<INPUT type=hidden value=signin name=act />
</FORM>
<SCRIPT language=JavaScript>
  document.forms['theForm'].elements['username'].focus();
  /**
   * 检查表单输入的内容
   */
  function validate()
  {
    var validator = new Validator('theForm');
    validator.required('username', user_name_empty);
    //validator.required('password', password_empty);
    if (document.forms['theForm'].elements['captcha'])
    {
      validator.required('captcha', captcha_empty);
    }
    return validator.passed();
  }
</SCRIPT>

<%
if((request.getAttribute("msg")==null)||(request.getAttribute("msg").equals(""))){
}else{
	out.print(request.getAttribute("msg"));
}
%>
</BODY>
</HTML>
