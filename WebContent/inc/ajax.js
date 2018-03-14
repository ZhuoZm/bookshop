function InitAjax() {
	var http_request = false;
	var xmlhttp_request;
	if (window.XMLHttpRequest) {
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {
			http_request.overrideMimeType('text/xml');
		}
	} else if (window.ActiveXObject) {
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
	if (!http_request) {
		alert('Giving up :( Cannot create an XMLHTTP instance');
		return false;
	}
	return http_request;
}

//function IDRequest(url) {
//	// 定义收到服务器的响应后需要执行的JavaScript函数
//	var idAndCount = GetText();
//	fullUrl = "http://localhost:8080" + url + "/DoAjax.do";
//	// alert(fullUrl);
//	xmlhttp_request = InitAjax();// 调用创建XMLHttpRequest的函数
//	xmlhttp_request.open("POST", fullUrl, true);
//	xmlhttp_request.setRequestHeader("Content-type",
//			"application/x-www-form-urlencoded");
//	xmlhttp_request.send("idAndCount=" + encodeURI(idAndCount));
//	xmlhttp_request.onreadystatechange = doContents;// 调用doContents函数
//}

function doContents() {
	if (xmlhttp_request.readyState == 4) {
		// 收到完整的服务器响应
		if (xmlhttp_request.status == 200) {
			// HTTP服务器响应的值OK
			var response = xmlhttp_request.responseText;
			document.getElementById("showTotalCost").innerHTML = response;
		}
	}
}

function GetText() {
	var num = document.getElementById("productCartForm").elements.length;
	var id_count = "";
	for ( var i = 0; i < num - 1; i++) {
		id_count = id_count + "______"
				+ document.getElementById("productCartForm").elements[i].value;
	}
	return id_count;
}

// function checkForm(){
// var num = document.getElementById("productCartForm").elements.length;
// for(var i = 0;i<num-1;i++){
// var count = document.getElementById("productCartForm").elements[i].value;
// if(count==null||count==""){
// alert("数量不能为空！");
// document.getElementById("productCartForm").elements[i].focus();
// return false;
// }
//		
// }
// return true;
// }
