<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/dataTables/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/chosen/css/chosen.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/jgrowl/css/jquery.jgrowl.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/alerts/css/jquery.alerts.custom.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gzlb.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css"/>

<title>规则类别</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<span>规则类别</span>
				</div>
			</div>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button class="btn btn-primary" id="selectAllBtn">全  选</button>
			<div class="btn-group">
				<button data-toggle="dropdown" class="btn btn-success dropdown-toggle">
					操 作 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#" id="addGzlbLink">新增</a></li>
					<li><a href="#" id="editGzlbLink">编辑</a></li>
					<li><a href="#" id="deleteGzlbLink">删除</a></li>
				</ul>
			</div>
		</div>
		
		<div id="content">
			<div id="table_content">
				<table id="gzlbTable" class="table table-bordered">
					<colgroup>
			            <col class="con0" style="align: center; width: 4%">
			            <col class="con1">
			            <col class="con0">
		          	</colgroup>
					<thead>
						<tr>
							<th>
								<!-- <input type="checkbox" name="gzbh" id="gzbhAll" /> -->
							</th>
							<th>编号</th>
							<th>规则名称</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${gzList}" var="gz">
							<tr>
								<td>
									<input type="checkbox" class="gzCheckbox" name="gzbh" id="gzbh" value="${gz.bh}" data-mc="${gz.mc }">
								</td>
								<td>${gz.pzbh }</td>
								<td>${gz.mc }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	
	</div>
	
	<!-- 增加规则 -->
	<div id="addGzlbModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/pfpz/addGzlb" id="addGzlbForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">规则</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addMcLabel">规则名称</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addMc" id="addMc"/>
						</div>
						<div class="col-lg-3" id="validateAddMc">
							<label class="control-label">
								
							</label>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-success" onclick="confirmAdd()">确定</button>
				</div>
			</div>
			</form>
		</div>
	</div>

	<div id="editGzlbModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/pfpz/editGzlb" id="editGzlbForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">规则</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">
					<input type="hidden" id="editGzbh" name="editGzbh" />
					<!-- <input type="hidden" id="editGzpzbh" name="editGzpzbh" />	 -->				
					<div class="form-group">
						<label class="col-lg-4 control-label" id="editMcLabel">规则名称</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editMc" id="editMc"/>
						</div>
						<div class="col-lg-3" id="validateEditMc">
							<label class="control-label">
								
							</label>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-success" onclick="confirmEdit()">确定</button>
				</div>
			</div>
			</form>
		</div>
	</div>
	
	<div id="gzlbSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">成功</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-success alert-dismissible" role="alert" id="gzlbSucInfo">
						<strong>编辑成功</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="gzlbError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">编辑失败</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible" role="alert" id="gzlbErrorInfo">
						<strong>编辑时发生了错误</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- dataTables -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/jquery.dataTables.min.js"></script>
	<!-- dataTables针对bootstrap的表格风格 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/dataTables.bootstrap.js"></script>
	<!-- uniform -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/uniform/js/jquery.uniform.min.js"></script>
	<!-- chosen -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/chosen/js/chosen.jquery.min.js"></script>
	<!-- jGrowl消息提示 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jgrowl/js/jquery.jgrowl.min.js"></script>
	<!-- Alerts弹出对话框 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/alerts/js/jquery.alerts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/gzlb.js"></script>
</body>
</html>