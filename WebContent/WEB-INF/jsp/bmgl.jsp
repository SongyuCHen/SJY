<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/dataTables/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/chosen/css/chosen.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/jgrowl/css/jquery.jgrowl.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/alerts/css/jquery.alerts.custom.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bmgl.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<title>部门管理</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/bm/cx">
				<div id="menu_left">
					<div id="ok"></div>
					<div id="word">
						<span>部门管理</span>
					</div>
				</div>
				
				<div id="menu_right">
					<label class="label-control">部门类型：</label>
					<select class="select-control" name="bmlx" id="bmlx">
						<c:forEach items="${bmlxList }" var="bmlx">
							<option>${bmlx.mc }</option>
						</c:forEach>
					</select>
					
					<button type="submit" class="btn btn-primary" id="searchBtn">
						<span class="glyphicon glyphicon-search"></span> 查 询
					</button>
				</div>
			</form>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button class="btn btn-primary" id="selectAllBtn">全  选</button>
			<div class="btn-group">
				<button data-toggle="dropdown" class="btn btn-success dropdown-toggle">操 作 <span class="caret"></span></button>
				<ul class="dropdown-menu">
					<li><a href="#" id="addBmglLink">增加</a></li>
					<li><a href="#" id="editBmglLink">编辑</a></li>
					<li><a href="#" id="deleteBmglLink">删除</a></li>
				</ul>
			</div>
		</div>
		
		<div id="content">
			<div id="table_content">
				<table id="bmglTable" class="table table-bordered">
					<colgroup>
						<col class="con0" style="align: center; width: 4%">
						<col class="con1">
						<col class="con0">
					</colgroup>
					
					<thead>
						<tr>
							<th>
								<!-- <input type="checkbox" name="bmbh" id="bmbhAll" /> -->
							</th>
							<th>部门名称</th>
							<th>部门分类</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${mlist}" var="bm">
							<tr>
								<td>
									<input type="checkbox" class="bmCheckbox" name="bmbh" value="${bm.bmbh}" id="bmbh"
										data-bmid="${bm.bmid }">
								</td>
								<td>${bm.bmmc }</td>
								<%-- <td>${bm.bmid }</td> --%>
								<td>${bm.bmlx}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div id="hiddenValue">
		<input type="text" id="lastBmlx" name="lastBmlx" value="${lastBmlx }" />
	</div>
	

	<div id="addBmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/bm/addBm" id="addBmForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">增加部门</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addBmmcLabel">部门名称</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addBmmc" id="addBmmc" />
						</div>
						<div class="col-lg-3" id="validateAddBmmc">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addBmidLabel">部门标识</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addBmid" id="addBmid"/>
						</div>
						<div class="col-lg-3" id="validateAddBmid">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addBmlxLabel">部门类型</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect" id="addBmlx" name="addBmlx">
								<c:forEach items="${editBmlxList }" var="bmlx">
									<option>${bmlx.mc }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-3">
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
	
	
	<div id="editBmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="" id="editBmForm" method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">编辑用户</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">	
					<input type="hidden" id="editBmbh" name="editBmbh" />
				
					<div class="form-group">
						<label class="col-lg-4 control-label">部门名称</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editBmmc" id="editBmmc" />
						</div>
						<div class="col-lg-3" id="validateEditBmmc">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">部门标识</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editBmid" id="editBmid"/>
						</div>
						<div class="col-lg-3" id="validateEditBmid">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">部门类型</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect" id="editBmlx" name="editBmlx">
								<c:forEach items="${editBmlxList }" var="bmlx">
									<option>${bmlx.mc }</option>
								</c:forEach>
							</select>
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
	
	
	<div id="bmglSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">成功</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-success alert-dismissible" role="alert" id="bmglSucInfo">
						<strong>编辑成功</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="bmglError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">编辑错误</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible" role="alert" id="bmglErrorInfo">
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
	<!-- dataTables -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/dataTables.bootstrap.js"></script>
	<!-- uniform -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/uniform/js/jquery.uniform.min.js"></script>
	<!-- chosen -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/chosen/js/chosen.jquery.min.js"></script>
	<!-- jGrowl消息提示 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jgrowl/js/jquery.jgrowl.min.js"></script>
	<!-- Alerts弹出对话框 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/alerts/js/jquery.alerts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bmgl.js"></script>
</body>	
</html>