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


<title>加分项目</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<span>加分项目</span>
				</div>
			</div>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button class="btn btn-primary" id="selectAllBtn">全  选</button>
			<div class="btn-group">
                <button data-toggle="dropdown" class="btn btn-success dropdown-toggle">操 作 <span class="caret"></span></button>
                <ul class="dropdown-menu">
	                <li><a href="#addJfxmModal" data-toggle="modal">新增</a></li>
	                <li><a href="#" id="editJfxmLink">编辑</a></li>
	                <li><a href="#" id="deleteJfxmLink">删除</a></li>
                </ul>
            </div>
        </div>
		
		<div id="content">
			<div id="table_content">
				<table id="jfxmTable" class="table table-bordered">
					<colgroup>
			            <col class="con0" style="align: center; width: 4%">
			            <col class="con1">
			            <col class="con0">
			            <col class="con1">
		          	</colgroup>
					<thead>
						<tr>
							<th>
								<!-- <input type="checkbox" name="jfxmbh" id="jfxmbhAll" /> -->
							</th>
							<th>类别名称</th>
							<th>等级</th>
							<th>分数</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${jfxmList}" var="jfxm">
							<tr>
								<td>
									<input type="checkbox" class="jfxmCheckbox" name="jfxmbh" value="${jfxm.bh}" data-mc="${jfxm.mc }" data-fs="${jfxm.fs }" data-lbbh="${jfxm.kflb.bh }">
								</td>
								<td>${jfxm.kflb.mc }</td>
								<td>${jfxm.mc }</td>
								<td>${jfxm.fs }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div id="addJfxmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/jfqx/addJfxm" id="addJfxmForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">加分项目</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<div class="form-group">
						<label class="col-lg-4 control-label">类别名称</label>
						<div class="col-lg-6">
							<select class="form-control" id="modalLbbh" name="lbbh"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">等级</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="mc" id="modalMc"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">分数</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="fs" id="modalFs"/>
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

	<div id="editJfxmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/jfqx/editJfxm" id="editJfxmForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">加分项目</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">
					<input type="hidden" id="modalJfxmbh" name="bh" />					
					<div class="form-group">
						<label class="col-lg-4 control-label">类别名称</label>
						<div class="col-lg-6">
							<select class="form-control" id="modalLbbhInEdit" name="lbbh"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">等级</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="mc" id="modalMcInEdit"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">分数</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="fs" id="modalFsInEdit"/>
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
	
	<div id="yhglSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">成功</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-success alert-dismissible" role="alert" id="yhglSucInfo">
						<strong>编辑成功</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="yhglError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">编辑错误</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible" role="alert" id="yhglErrorInfo">
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jfxm.js"></script>
</body>
</html>