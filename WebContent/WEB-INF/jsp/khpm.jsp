<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<%@include file="base.jsp"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/dataTables/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/chosen/css/chosen.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/jgrowl/css/jquery.jgrowl.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/alerts/css/jquery.alerts.custom.css" />

<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/khpm.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css" />

<title>考核排名</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">>
	<jsp:include page="header.jsp" />
	

	  <div id="wrapperinner">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/khpm/cx">
				<div id="menu_left">
					<div id="ok"></div>
					<div id="word">
						<label class="label-control">考核排名</label>
					</div>
				</div>
				
				<div id="menu_right">
					<label class="label-control">考核指标：</label>
					<select class="select-control" name="khzb" id="khzb">
						<c:forEach items="${gzList }" var="gz">
							<option>${gz.mc }</option>
						</c:forEach>
					</select>
				
					<label class="label-control" id="bmlxLabel">部门类型：</label>
					<select class="select-control" name="bmlx" id="bmlx">
						<c:forEach items="${bmlxmcList }" var="bmlxmc">
							<option>${bmlxmc }</option>
						</c:forEach>
					</select>
					
					<label id="sj">时间：</label>
					<select class="select-control" name="nf" id="nf">
						<c:forEach items="${years }" var="year">
							<option>${year }</option>
						</c:forEach>
					</select>
					<label>年</label>
					<select class="select-control" name="jd" id="jd">
						<c:forEach items="${quarters }" var="quarter">
							<option>${quarter }</option>
						</c:forEach>
					</select>
					<label id="quarter">季度</label>
					
					<button type="submit" class="btn btn-primary" id="searchBtn">
						<span class="glyphicon glyphicon-search"></span> 查 询
					</button>
				</div>
			</form>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button id="operationBtn" data-toggle="dropdown" class="btn btn-primary dropdown-toggle">
				操  作 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a href="javascript:void(0)" id="printKhpmLink">打印</a></li>
				<li><a href="javascript:void(0)" id="exportKhpmLink">导出Excel表格</a></li>
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
	</div>
	
	<div id="hiddenValue">
		<input type="hidden" id="curKhzbmc" name="curKhzbmc" value="${curKhzbmc }" />
		<input type="hidden" id="curBmlxmc" name="curBmlxmc" value="${curBmlxmc }" />
		<input type="hidden" id="curYear" name="curYear" value="${curYear }" />
		<input type="hidden" id="curQuarter" name="curQuarter" value="${curQuarter }" />
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
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/khpm.js"></script>
	<script type="text/template" id="khpmTableTemplate">
		<table id="khpmTable" class="table table-bordered">
					<colgroup>
						<col class="con0">
						<col class="con1">
						<col class="con0">
						<col class="con1">
						<col class="con0">
					</colgroup>
					<thead>
						<tr>
							<th class="no-wrap">排名</th>
							<th class="no-wrap">姓名</th>
							<th class="no-wrap">部门</th>
							<th class="no-wrap">时间</th>
							<th class="no-wrap">评分</th>
							
						</tr>
					</thead>
					
					<tbody>
								{{#mPfkhgzList}}
									<tr>
										<td>{{index}}</td>
										<td>{{ryxm}}</td>
										<td>{{bmmc}}</td>
										<td>{{nf}}年{{jd}}季度</td>
										<td>{{gzdf}}</td>
									</tr>
								{{/mPfkhgzList}}
					</tbody>
				</table>
	</script>
	<script type="text/template" id="syzbKhpmTableTemplate">
		<table id="khpmTable" class="table table-bordered">
					<colgroup>
						<col class="con0">
						<col class="con1">
						<col class="con0">
						<col class="con1">
						<col class="con0">
						<col class="con1 no-print">
					</colgroup>
					
					<thead>
						<tr>
							<th class="no-wrap">排名</th>
							<th class="no-wrap">姓名</th>
							<th class="no-wrap">部门</th>
							<th class="no-wrap">时间</th>
							<th class="no-wrap">评分</th>
							<th class="no-wrap no-print">查看详细</th>
						</tr>
					</thead>
					
					<tbody>
							{{#mPfkhList}}
									<tr>
										<td>{{index}}</td>
										<td>{{ryxm}}</td>
										<td>{{bmmc}}</td>
										<td>{{nf}}年{{jd}}季度</td>
										<td>{{jddf}}</td>
										<td class="no-print">
											<a href="javascript:void(0)" class="pfkhgz" data-pfkhgz="{{pfkhgzJsonStr}}">查看详细</a>
										</td>
									</tr>
							{{/mPfkhList}}
					</tbody>
				</table>
	</script>
</body>
</html>