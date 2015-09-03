<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/dataTables/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/chosen/css/chosen.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/jgrowl/css/jquery.jgrowl.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/alerts/css/jquery.alerts.custom.css" />

<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/plkh.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css" />

<title>批量考评</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal"
				action="${pageContext.request.contextPath}/pfkh/scplkh">
				<div id="menu_left">
					<div id="ok"></div>
					<div id="word">
						<label class="label-control">批量考评</label>
					</div>
				</div>
				
				<div id="menu_right">
					<label>部门:</label> 
					<select class="select-control" name="bmmc" id="bmmc">
						<c:forEach items="${bmList }" var="bm">
							<option>${bm.bmmc }</option>
						</c:forEach>
					</select>
					
					<label id="sjLabel">时间:</label> 
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
					<button class="btn btn-primary" id="searchBtn">生成考评</button>
				</div>
			</form>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button id="operationBtn" data-toggle="dropdown" class="btn btn-primary dropdown-toggle">
				操  作 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a href="javascript:void(0)" id="printPlkhLink">打印</a></li>
				<li><a href="javascript:void(0)" id="exportPlkhLink">导出Excel表格</a></li>
			</ul>
		</div>
		
		<div id="loading-indicator">
			<h4>数据处理中，请稍后</h4>
			<img src="../images/Preloader_3.gif"/>
		</div>
		
		<div id="content">
			<div id="table_content">
			</div>
		</div>
		
		<div id="hiddenValue">
			<input type="hidden" id="curBmmc" name="curBmmc" value="${curBmmc }" />
			<input type="hidden" id="curYear" name="curYear" value="${curYear }" />
			<input type="hidden" id="curQuarter" name="curQuarter" value="${curQuarter }" />
		</div>
	</div>
	
	<!-- the modal for showing the detail pfkh -->
	<div id="showDetailModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">评分考核详细内容</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<div id="modalTable">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>规则</th>
									<th>得分</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/jquery.dataTables.min.js"></script>
	<!-- dataTables针对bootstrap的表格风格 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/dataTables.bootstrap.js"></script>
	<!-- uniform -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/uniform/js/jquery.uniform.min.js"></script>
	<!-- mustache -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/mustache.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/plkh.js"></script>
	<script type="text/template" id="plkhTableTemplate">
		<table id="plkhTable" class="table table-bordered">
					<colgroup>
						<col class="con0 no-print">
						<col class="con1">
						<col class="con0">
						<col class="con1">
						<col class="con0">
						<col class="con1">
					</colgroup>
				
					<thead>
						<tr>
							<th class="no-wrap no-print"></th>
							<th class="no-wrap">姓名</th>
							<th class="no-wrap">部门</th>
							<th class="no-wrap">时间</th>
							<th class="no-wrap">总分</th>
							<th class="no-wrap no-print">查看详细</th>
						</tr>
					</thead>
					
					<tbody>
						{{#mlist}}
							<tr>
								<td class="no-print">
									<input type="checkbox" name="pfkhbh" id="pfkhbh" value="{{bh}}" />
								</td>
								<td>{{ryxm}}</td>
								<td>{{bmmc}}</td>
								<td>{{nf}}年{{jd}}季度</td>
								<td>{{jddf}}</td>
								<td class="no-print">
									<a href="javascript:void(0)" class="pfkhgz" data-pfkhgz="{{pfkhgzJsonStr}}">查看详细</a>
								</td>
							</tr>
						{{/mlist}}
					</tbody>
				</table>
	</script>
</body>
</html>