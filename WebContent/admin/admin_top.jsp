<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link href="../images/admin_images/general.css" rel="stylesheet"
			type="text/css"/>

		<style type="text/css">
#header-div {
	background: #278296;
	border-bottom: 1px solid #FFF;
}

#logo-div {
	height: 50px;
	float: left;
}

#submenu-div {
	height: 50px;
}

#submenu-div ul {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

#submenu-div li {
	float: right;
	padding: 0 10px;
	margin: 3px 0;
	border-left: 1px solid #FFF;
}

#submenu-div a:visited,#submenu-div a:link {
	color: #FFF;
	text-decoration: none;
}

#submenu-div a:hover {
	color: #F5C29A;
}

#loading-div {
	clear: right;
	text-align: right;
	display: block;
}

#menu-div {
	background: #80BDCB;
	font-weight: bold;
	height: 24px;
	line-height: 24px;
}

#menu-div ul {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

#menu-div li {
	float: left;
	border-right: 1px solid #192E32;
	border-left: 1px solid #BBDDE5;
}

#menu-div a:visited,#menu-div a:link {
	display: block;
	padding: 0 20px;
	text-decoration: none;
	color: #335B64;
	background: #9CCBD6;
}

#menu-div a:hover {
	color: #000;
	background: #80BDCB;
}

#submenu-div a.fix-submenu {
	clear: both;
	margin-left: 5px;
	padding: 1px 5px; *
	padding: 3px 5px 5px;
	background: #DDEEF2;
	color: #278296;
}

#submenu-div a.fix-submenu:hover {
	padding: 1px 5px; *
	padding: 3px 5px 5px;
	background: #FFF;
	color: #278296;
}

#menu-div li.fix-spacel {
	width: 30px;
	border-left: none;
}

#menu-div li.fix-spacer {
	border-right: none;
}
</style>

<script type="text/javascript">
function modalDialog(url, name, width, height)
{
  if (width == undefined)
  {
    width = 400;
  }
  if (height == undefined)
  {
    height = 300;
  }

  if (window.showModalDialog)
  {
    window.showModalDialog(url, name, 'dialogWidth=' + (width) + 'px; dialogHeight=' + (height+5) + 'px; status=off');
  }
  else
  {
    x = (window.screen.width - width) / 2;
    y = (window.screen.height - height) / 2;

    window.open(url, name, 'height='+height+', width='+width+', left='+x+', top='+y+', toolbar=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, modal=yes');
  }
}

function ShowToDoList()
{
  try
  {
    var mainFrame = window.top.frames['main-frame'];
    mainFrame.window.showTodoList(adminId);
  }
  catch (ex)
  {
  }
}
var adminId = "1"; 
</script>
	</head>
	<body style="cursor: auto;">
		<div id="header-div">
			<div id="logo-div">
				<img src="../images/webshop.gif"
					alt="WEBSHOP - power for e-commerce" width="130" height="50" />
			</div>
			<div id="submenu-div">
				<ul>
					<li>
						<a href="" target="main-frame"></a></li></ul>
				<div id="send_info"
					style="padding: 5px 10px 0pt 0pt; clear: right; text-align: right; color: rgb(255, 153, 0); width: 40%; float: right;">
					<span id="send_msg"></span>
					<a href="javascript:;" onclick="Javascript:switcher()"
						id="lnkSwitch"
						style="margin-right: 10px; color: rgb(255, 153, 0); text-decoration: underline; display: none;">暂停</a>
					
					<a href="../AdminAction.do?action=logout" target="_top" class="fix-submenu">退出</a>
				</div>

				<div id="load-div"
					style="padding: 5px 10px 0pt 0pt; text-align: right; color: rgb(255, 153, 0); display: none; width: 40%; float: right;">
					<img src="index_data/top_loader.gif" alt="正在处理您的请求..."
						style="vertical-align: middle;" height="16" width="16" />
					正在处理您的请求...
				</div>
			</div>
		</div>
		<div id="menu-div">
			<br class="clear" />
		</div>
	</body>
</html>