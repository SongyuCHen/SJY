<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="base.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gzsjChangelog.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gzsjChangelog.js"></script>

<title>工作实绩修改记录</title>
</head>
<body  style="background-image:url(../images/background07.jpg); background-size:105%;">
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<span>修改记录</span>
				</div>
			</div>
		</div>
		
		<div class="loglist">
			
			<c:forEach items="${changeLogList }" var="changeLog">
				<div class="single-log">
					<div class="single-line">
						<label class="col-lg-3 label-control">修改前</label>
						<div class="col-lg-2">
							<p class="text-control">${changeLog.sz1 }</p>
						</div>
						<label class="col-lg-2 label-control">修改后</label>
						<div class="col-lg-5">
							<p class="text-control">${changeLog.sz2 }</p>
						</div>
					</div>
					<div class="single-line">
						<label class="col-lg-3 label-control">修改人</label>
						<div class="col-lg-2">
							<p class="text-control">${changeLog.xgr }</p>
						</div>
						<label class="col-lg-2 label-control">修改日期</label>
						<div class="col-lg-5">
							<p class="text-control">${changeLog.xgrq }</p>
						</div>
					</div>
					<div class="multiple-line">
						<label class="col-lg-3 label-control">修改原因</label>
						<div class="col-lg-7">
							<p class="text-control">${changeLog.xgyy }</p>
						</div>
					</div>
				</div>
			</c:forEach>
			
		</div>
	</div>
</body>
</html>