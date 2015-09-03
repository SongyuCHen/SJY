<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jump.css"/>
<title>修改成功</title>
</head>
<body>
	<div id="container">
		<h2>密码修改成功，请重新登陆。</h2>
		<h2><span id="time">5</span>秒后返回登陆页面</h2>
		<br/>
		<a href="${pageContext.request.contextPath}/user/login">返回登陆页面</a>
	</div>
</body>

<script type="text/javascript">
	var basePath = getRootPath();
    delayURL();
    function delayURL() { 
        var delay = $("#time").html();
 		var t = setTimeout("delayURL()", 1000);
        if (delay > 0) {
            delay--;
            $("#time").html(delay);
        } 
        else {
     		clearTimeout(t); 
            window.location.href = basePath + "/user/login";
        }        
    } 
</script>
</html>