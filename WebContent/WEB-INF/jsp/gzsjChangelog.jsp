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
<body>
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
				<div class="singleLog">
					<div class="form-group">
						<label class="col-lg-3 control-label">修改前</label>
						<label class="col-lg-3 control-label">${changeLog.sz1 }</label>
						<label class="col-lg-3 control-label">修改后</label>
						<label class="col-lg-3 control-label">${changeLog.sz2 }</label>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">修改人</label>
						<label class="col-lg-3 control-label">${changeLog.xgr }</label>
						<label class="col-lg-3 control-label">修改日期</label>
						<label class="col-lg-3 control-label">${changeLog.xgrq }</label>
					</div>
					<div class="">
						<div class="">
							<label>修改原因</label>
						</div>
						<div class="">
							<p>${changeLog.xgyy }</p>
						</div>
					</div>
				</div>
			</c:forEach>
			
		</div>
	</div>
</body>
</html>