<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jsff.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsff.js"></script>
<title>计算方法</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<span>计算方法</span>
				</div>
			</div>
		</div>
		
		<div id="content">
			<div class="leftDesc">
				<div class="jumbotron">
					<h1>方法说明</h1>
					<p>工作量按照每个分项值计算</p>
					<p>分项值最高为100分，最低为60分</p>
					<p>假定分项高值为X，低值为Y，某人分项值为M</p>
					<p>则得分为：(M-Y)/(X-Y)*(100-60)+60</p>
				</div>
			</div>
			
			<div class="rightEdit">
				<form action="${pageContext.request.contextPath}/pfpz/editJsff" id="editJsffForm"
							method="post" class="form-horizontal">
					<div class="form-group">
						<label class="col-lg-4 control-label" id="maxScoreLabel">最高分</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="maxScore" id="maxScore" 
								value="${maxScore }" readonly />
						</div>
						<div class="col-lg-3" id="validateMaxScore">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="minScoreLabel">最低分</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="minScore" id="minScore"
								value="${minScore }" readonly />
						</div>
						<div class="col-lg-3" id="validateMinScore">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-lg-4">
						</div>
						<div class="col-lg-5">
							<button type="button" class="btn btn-primary" id="editJsffBtn">编辑</button>
							<button type="button" class="btn btn-success" id="saveJsffBtn">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>