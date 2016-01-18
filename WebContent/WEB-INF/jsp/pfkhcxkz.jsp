<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<%@include file="base.jsp"%>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/pfkhcxkz.css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/pfkhcxkz.js"></script>
<title>评分考核查询空值</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal"
				action="${pageContext.request.contextPath}/pfkh/cx">
				<div id="menu_left">
					<label>部门:</label>
					<select class="select-control" name="bm" id="bm">
						<c:forEach items="${bmList }" var="bmmc">
							<option>${bmmc }</option>
						</c:forEach>
					</select>
					
					<label id="xmLabel">姓名:</label> 
					<select class="select-control" name="xm" id="xm">
						<c:forEach items="${xmList }" var="ryxm">
							<option>${ryxm }</option>
						</c:forEach>
					</select>
				</div>
				<div id="menu_right">
					<label id="sj">时间:</label> 
					<select class="select-control" name="nf" id="nf">
						<c:forEach items="${years }" var="year">
							<option>${year }</option>
						</c:forEach>
					</select> 
					<label id="yearLabel">年</label> 
					
					<select class="select-control" name="jd" id="jd">
						<c:forEach items="${quarters }" var="quarter">
							<option>${quarter }</option>
						</c:forEach>
					</select>
					<label id="quarterLabel">季度</label>
					
					<button class="btn btn-success">生成考评</button>
				</div>
			</form>
		</div>
		
		<div id="content">
			<div class="alert alert-info" role="alert">
				<label id="xmbmLabel">
					<%-- <strong>${mpfkh.bmmc }-${mpfkh.ryxm }</strong> --%>
					<strong>有人员在${mpfkh.nf }年${mpfkh.jd }季度的工作实绩信息没有录入！</strong>
				</label>
				
				<div id="gap"></div>
				
				<label id="nfyfLabel">
					<%-- <strong>在${mpfkh.nf }年${mpfkh.yf }月的评分考核尚未生成</strong> --%>
					<strong>暂时不能生成评分考核！</strong>
				</label>
			</div>
		</div>
	</div>
	
	<div id="hiddenValue">
		<input type="text" id="lastBm" value="${mpfkh.bmmc }" />
		<input type="text" id="lastXm" value="${mpfkh.ryxm }" />
		<input type="text" id="lastNf" value="${mpfkh.nf }" />
		<input type="text" id="lastJd" value="${mpfkh.jd }" />
	</div>
</body>
</html>