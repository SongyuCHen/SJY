<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<%@include file="base.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/dataTables/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/chosen/css/chosen.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/jgrowl/css/jquery.jgrowl.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/alerts/css/jquery.alerts.custom.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yhgl.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<title>用户管理</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	<jsp:include page="header.jsp" />

	<div id="wrapperinner">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/user/cx">
				<div id="menu_left">
					<div id="ok"></div>
					<div id="word">
						<span>用户管理</span>
					</div>
				</div>
				
				<div id="menu_right">
					<label id="bmLabel">部门</label>
					<select class="select-control" id="bm" name="bm">
						<c:forEach items="${searchBmList }" var="bm">
							<option>${bm.bmmc }</option>
						</c:forEach>
					</select>
					
					<label id="roleLabel">角色</label>
					<select class="select-control" id="role" name="role">
						<c:forEach items="${searchRoleList }" var="role">
							<option>${role.rolename }</option>
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
					<li><a href="#" id="addYhglLink">增加</a></li>
					<li><a href="#" id="editYhglLink">编辑</a></li>
					<li><a href="#" id="deleteYhglLink">删除</a></li>
				</ul>
			</div>
		</div>
		
		<div id="content">
			<div id="table_content">
				<table id="yhglTable" class="table table-bordered">
					<colgroup>
						<col class="con0" style="align: center; width: 4%">
						<col class="con1">
						<col class="con0">
						<col class="con1">
						<col class="con0">
						<col class="con1">
					</colgroup>
					
					<thead>
						<tr>
							<th>
								<!-- <input type="checkbox" name="rybh" id="rybhAll" /> -->
							</th>
							<th>姓名</th>
							<th>部门</th>
							<th>角色</th>
							<th>用户口令</th>
							<th>密码</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${userList}" var="user">
							<tr>
								<td>
									<input type="checkbox" class="ryCheckbox" name="rybh" value="${user.rybh}" id="rybh">
								</td>
								<td>${user.xm }</td>
								<td>${user.bmmc }</td>
								<td>${user.rolename }</td>
								<td>${user.username}</td>
								<td>
									********
									<input type="hidden" name="password" value="${user.password}" id="password" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div id="hiddenValue">
		<input type="text" id="lastBm" name="lastBm" value="${lastUser.bmmc }" />
	  	<input type="text" id="lastRole" name="lastRole" value="${lastUser.rolename }" />
	</div>
	

	<div id="addUserModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/user/addUser" id="addUserForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">增加用户</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<div class="form-group">
						<label class="col-lg-4 control-label">部门</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect" name="addBm" id="addBm">
								<c:forEach items="${addBmList }" var="bm">
									<option>${bm.bmmc }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-3">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addXmLabel">姓名</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addXm" id="addXm"/>
						</div>
						<div class="col-lg-3" id="validateAddXm">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">角色</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect" id="addRole" name="addRole">
								<c:forEach items="${addRoleList }" var="role">
									<option>${role.rolename }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-3">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addUsernameLabel">用户口令</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addUsername" id="addUsername"/>
						</div>
						<div class="col-lg-3" id="validateAddUsername">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addPasswordLabel">密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="addPassword" id="addPassword"/>
						</div>
						<div class="col-lg-3" id="validateAddPassword">
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
	
	
	<div id="editUserModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/user/cx" id="editUserForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">编辑用户</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">	
					<input type="hidden" id="editBh" name="editBh" />
				
					<div class="form-group">
						<label class="col-lg-4 control-label">部门</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect" name="editBm" id="editBm">
								<c:forEach items="${addBmList }" var="bm">
									<option>${bm.bmmc }</option>
								</c:forEach>
							</select>
						</div>
						
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">姓名</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editXm" id="editXm"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">角色</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect" id="editRole" name="editRole">
								<c:forEach items="${addRoleList }" var="role">
									<option>${role.rolename }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">用户口令</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editUsername" id="editUsername"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="editPassword" id="editPassword"/>
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
	<!-- dataTables���bootstrap�ı���� -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/dataTables.bootstrap.js"></script>
	<!-- uniform -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/uniform/js/jquery.uniform.min.js"></script>
	<!-- chosen -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/chosen/js/chosen.jquery.min.js"></script>
	<!-- jGrowl消息提示 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jgrowl/js/jquery.jgrowl.min.js"></script>
	<!-- Alerts弹出对话框 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/alerts/js/jquery.alerts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/yhgl.js"></script>
</body>	
</html>