<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
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

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/qxgl.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<title>权限管理</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	<jsp:include page="header.jsp" />

	<div id="wrapper">

		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<span>权限管理</span>
				</div>
			</div>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button class="btn btn-primary" id="selectAllBtn">全  选</button>
			<div class="btn-group">
				<button data-toggle="dropdown" class="btn btn-success dropdown-toggle">操 作 <span class="caret"></span></button>
				<ul class="dropdown-menu">
					<li><a href="#" id="addQxglLink">增加</a></li>
					<li><a href="#" id="editQxglLink">编辑</a></li>
					<li><a href="#" id="deleteQxglLink">删除</a></li>
				</ul>
			</div>
		</div>
		
		<div id="content">
			<div id="table_content">
				<table id="qxglTable" class="table table-bordered">
					<colgroup>
						<col class="con0" style="align: center; width: 4%">
						<col class="con1">
						<col class="con0">
						<col class="con1">
					</colgroup>
					
					<thead>
						<tr>
							<th>
								<!-- <input type="checkbox" name="rolebh" id="rolebhAll" /> -->
							</th>
							<th>角色</th>
							<th>操作</th>
							<th>资源</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${mlist}" var="role">
							<tr>
								<td>
									<input type="checkbox" class="roleCheckbox" name="rolebh" value="${role.rolebh}" id="rolebh">
								</td>
								<td>${role.rolename }</td>
								<td>${role.operations }</td>
								<td>
									${role.simpleReses }
									<input type="hidden" name="detailReses" id="detailReses" value="${role.detailReses }" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div id="addRoleModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<form action="${pageContext.request.contextPath}/role/addRole" id="addRoleForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">增加角色权限</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					
					<div class="form-group">
						<label class="col-lg-2 control-label" id="addRolenameLabel">角色</label>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="addRolename" id="addRolename"/>
						</div>
						<div class="col-lg-7" id="validateAddRolename">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label" id="addOperationLabel">操作</label>
						<div class="col-lg-9">
							<c:forEach items="${mOplist}" var="operation" varStatus="status">
								<li class="operationLi">
									<label for="operation-${operation.bh}-add">
										<input id="operation-${operation.bh}-add" type="checkbox" name="addOperation"
											value="${operation.name }_${status.index}" />${operation.name}
										<c:if test="${fun:length(operation.rangeList) > 0}">
        									<select class="select-control modalSelect" name="addRange${status.index }" id="addRange${status.index }">
												<c:forEach items="${operation.rangeList }" var="range">
													<option>${range }</option>
												</c:forEach>
											</select>
 										</c:if>
									</label>
								</li>
							</c:forEach>
						</div>
						<div class="col-lg-1" id="validateAddOperation">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label" id="addResLabel">资源</label>
						<div class="col-lg-9 resDiv">
							<!-- 一级checkbox -->
							<c:forEach items="${mReslist }" var="res" varStatus="status">
								<li class="oneResLi">
									<input type="checkbox" class="t_parent" name="addRes" id="addRes${status.index }" 
										value="${res.resname }_${status.index }" />
									<label for="addRes${status.index }">${res.resname }</label>
										
									<!-- 二级 -->
									<ul>
										<c:forEach items="${res.childrenList }" var="child">
											<li class="twoResLi">
												<input type="checkbox" class="t_child" id="addChildRes${status.index }" 
													name="addChildRes${status.index }" value="${child.resname }" />
												<label for="addChildRes${status.index }">${child.resname }</label>
											</li>
										</c:forEach>
									</ul>
								</li>
								
							</c:forEach>
						</div>
						<div class="col-lg-1">
						
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
	
	<div id="editRoleModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<form action="${pageContext.request.contextPath}/role/editRole" id="editRoleForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">编辑角色权限</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<input type="hidden" name="editBh" id="editBh" />
					<div class="form-group">
						<label class="col-lg-2 control-label" id="editRolenameLabel">角色</label>
						<div class="col-lg-3">
							<input type="text" class="form-control" name="editRolename" id="editRolename" readonly />
						</div>
						<div class="col-lg-7" id="validateEditRolename">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label" id="editOperationLabel">操作</label>
						<div class="col-lg-9 resDiv">
							<c:forEach items="${mOplist}" var="operation" varStatus="status">
								<li class="operationLi">
									<label for="operation-${operation.bh}-edit">
										<input id="operation-${operation.bh}-edit" type="checkbox" name="editOperation"
											value="${operation.name }_${status.index}" data-operation="${operation.name}" />${operation.name}
										<c:if test="${fun:length(operation.rangeList) > 0}">
											<select class="select-control modalSelect" name="editRange${status.index }" id="editRange${status.index }">
												<c:forEach items="${operation.rangeList }" var="range">
													<option>${range }</option>
												</c:forEach>
											</select>
										</c:if>
									</label>
								</li>
							</c:forEach>
						</div>
						<div class="col-lg-1" id="validateEditOperation">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label" id="editResLabel">资源</label>
						<div class="col-lg-9 resDiv">
							<!-- 一级checkbox -->
							<c:forEach items="${mReslist }" var="res" varStatus="status">
								<li class="oneResLi">
									<input type="checkbox" class="t_parent" name="editRes" id="editRes${status.index }" 
										value="${res.resname }_${status.index }" data-res="${res.resname }" />
									<label for="editRes${status.index }">${res.resname }</label>
										
									<!-- 二级 -->
									<ul>
										<c:forEach items="${res.childrenList }" var="child">
											<li class="twoResLi">
												<input type="checkbox" class="t_child" id="editChildRes${status.index }" 
													name="editChildRes${status.index }" value="${child.resname }" 
													data-parent="${res.resname }" />
												<label for="editChildRes${status.index }">${child.resname }</label>
											</li>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</div>
						<div class="col-lg-1">
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
	
	
	
	<div id="qxglSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">成功</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-success alert-dismissible" role="alert" id="qxglSucInfo">
						<strong>编辑成功</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="qxglError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">编辑失败</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible" role="alert" id="qxglErrorInfo">
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/dataTables.bootstrap.js"></script>
	<!-- uniform -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/uniform/js/jquery.uniform.min.js"></script>
	<!-- chosen -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/chosen/js/chosen.jquery.min.js"></script>
	<!-- jGrowl消息提示 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jgrowl/js/jquery.jgrowl.min.js"></script>
	<!-- Alerts弹出对话框 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/alerts/js/jquery.alerts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/qxgl.js"></script>

</body>	
</html>