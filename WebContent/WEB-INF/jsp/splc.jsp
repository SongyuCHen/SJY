<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/splc.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/splc.js"></script>

<title>审批流程</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<span>审批流程</span>
				</div>
			</div>
		</div>
		
		<div id="content">
			<form id="splcForm" method="post" class="form-horizontal" 
					action="${pageContext.request.contextPath}/xtgl/splcpz">
				
				<div class="form-group" id="splcDiv">
					<div class="splc-inline">
						<div>
							<label class="splc-label">用户提交</label>
						</div>
						<div class="splc-check">
							<span class="glyphicon glyphicon-check" aria-hidden="true"></span>
						</div>
					</div>
				
					<div class="splc-inline splc-arrow">
						<span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
					</div>
				
					<c:forEach items="${mlist }" var="mop">
						<div class="splc-inline">
							<div>
								<label class="splc-label">${mop.range }${mop.name }</label>
							</div>
							<div class="splc-check">
								<c:choose>
									<c:when test="${mop.status=='1' }">
										<div class="hiddenDiv">
											<input type="checkbox" name="splcbh" id="splcbh" value="${mop.bh }" checked="checked" />
										</div>
										<div class="checkDiv">
											<span class="glyphicon glyphicon-check" aria-hidden="true" id="checkIcon"></span>
										</div>
									</c:when>
									<c:otherwise>
										<div class="hiddenDiv">
											<input type="checkbox" name="splcbh" id="splcbh" value="${mop.bh }" />
										</div>
										<div class="checkDiv">
											<span class="glyphicon glyphicon-unchecked" aria-hidden="true" id="checkIcon"></span>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						
						<div class="splc-inline splc-arrow">
							<span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
						</div>
					</c:forEach>
				
					<div class="splc-inline">
						<div>
							<label class="splc-label">审批结束</label>
						</div>
						<div class="splc-check">
							<span class="glyphicon glyphicon-check" aria-hidden="true"></span>
						</div>
					</div>
				</div>
				
				
				<div id="buttonGroup" class="form-group">
					<button type="submit" class="btn btn-success" id="saveBtn">保 存</button>
				</div>
			
			</form>
		</div>
		
		
		
	</div>
	
</body>
</html>