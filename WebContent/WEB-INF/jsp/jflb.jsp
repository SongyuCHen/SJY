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
		
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jfqx.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>


<title>加分类别</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<span>加分类别</span>
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
					<li><a href="#addJflbModal" data-toggle="modal">新增</a></li>
					<li><a href="#" id="editJflbLink">编辑</a></li>
					<li><a href="#" id="deleteJflbLink">删除</a></li>
				</ul>
			</div>
		</div>
		
		<div id="content">
			<div id="table_content">
				<table id="jflbTable" class="table table-bordered">
					<colgroup>
			            <col class="con0" style="align: center; width: 4%">
			            <col class="con1">
			            <col class="con0">
		          	</colgroup>
					<thead>
						<tr>
							<th>
								<!-- <input type="checkbox" name="jflbbh" id="jflbbhAll" /> -->
							</th>
							<th>编号</th>
							<th>类别名称</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${jflbList}" var="jflb">
							<tr>
								<td>
									<input type="checkbox" class="jflbCheckbox" name="jflbbh" value="${jflb.bh}" data-mc="${jflb.mc }">
								</td>
								<td>${jflb.pzbh }</td>
								<td>${jflb.mc }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div id="addJflbModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/jfqx/addJflb" id="addJflbForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">加分类别</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<div class="form-group">
						<label class="col-lg-4 control-label">类别名称</label>
						<div class="col-lg-4">
							<input type="text" class="form-control" name="modalMc" id="modalMc"/>
						</div>
						<div class="col-lg-4">
							<label class="control-label">
								
							</label>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-success">确定</button>
				</div>
			</div>
			</form>
		</div>
	</div>

	<div id="editJflbModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/jfqx/editJflb" id="editJflbForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">加分类别</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">
					<input type="hidden" id="modalJflbbh" name="jflbbh" />					
					<div class="form-group">
						<label class="col-lg-4 control-label">类别名称</label>
						<div class="col-lg-4">
							<input type="text" class="form-control" name="mc" id="modalMc"/>
						</div>
						<div class="col-lg-4">
							<label class="control-label">
								
							</label>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-success">确定</button>
				</div>
			</div>
			</form>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jflb.js"></script>
</body>
</html>