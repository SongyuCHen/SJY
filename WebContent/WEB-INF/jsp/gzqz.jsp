<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<%@include file="base.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/dataTables/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/chosen/css/chosen.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/jgrowl/css/jquery.jgrowl.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/alerts/css/jquery.alerts.custom.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gzqz.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css"/>

<title>规则权重</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	<jsp:include page="header.jsp" />
	

	  <div id="wrapperinner">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<span>规则权重</span>
				</div>
			</div>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button class="btn btn-primary" id="selectAllBtn">全  选</button>
			<div class="btn-group">
				<button data-toggle="dropdown" class="btn btn-success dropdown-toggle">操 作 <span class="caret"></span></button>
				<ul class="dropdown-menu">
					<li><a href="#" id="addGzqzLink">增加</a></li>
					<li><a href="#" id="editGzqzLink">编辑</a></li>
					<li><a href="#" id="deleteGzqzLink">删除</a></li>
				</ul>
			</div>
		</div>
		
		<div id="content">
			<div id="table_content">
				<table id="gzqzTable" class="table table-bordered">
					<colgroup>
						<col class="con0" style="align: center; width: 4%">
						<col class="con1">
						<col class="con0">
						<col class="con1">
					</colgroup>
					
					<thead>
						<tr>
							<th>
								<!-- <input type="checkbox" name="pfbh" id="pfbhAll" /> -->
							</th>
							<th>规则名称</th>
							<th>类型</th>
							<th>分数</th>
							<th>配置</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${mlist}" var="pfpz">
							<tr>
								<td>
									<input type="checkbox" class="pfCheckbox" name="pfbh" id="pfbh" value="${pfpz.bh}" data-mc="${pfpz.mc }">
								</td>
								<td>${pfpz.mc }</td>
								<td>${pfpz.lx }</td>
								<td>${pfpz.fs }</td>
								<td>
									<c:if test="${pfpz.mc=='工作实绩' }">
										<button type="button" class="btn btn-success btn-xs" id="gzsjConfigBtn" onclick="editGzsjGzqz()">
											<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
										</button>
									</c:if>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		</div>

	
	<input type="hidden" id="gzxxLen" value="${gzxxLen }" />
	
	<div id="addGzqzModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/pfpz/addGzqz" id="addGzqzForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">增加规则分数</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addLxLabel">类型</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect" name="addLx" id="addLx">
								<c:forEach items="${lxList }" var="lx">
									<option>${lx }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-3" id="validateAddLx">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addMcLabel">规则名称</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addMc" id="addMc"/>
						</div>
						<div class="col-lg-3" id="validateAddMc">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="addFsLabel">分数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addFs" id="addFs"/>
						</div>
						<div class="col-lg-3" id="validateAddFs">
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
	
	<div id="editGzqzModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/pfpz/editGzqz" id="editGzqzForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">编辑规则分数</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">					
					<input type="hidden" id="editPfbh" name="editPfbh" />
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="editLxLabel">类型</label>
						<div class="col-lg-5">
							<select class="form-control modalSelect" name="editLx" id="editLx">
								<c:forEach items="${lxList }" var="lx">
									<option>${lx }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-3" id="validateEditLx">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="editMcLabel">规则名称</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editMc" id="editMc"/>
						</div>
						<div class="col-lg-3" id="validateEditMc">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label" id="editFsLabel">分数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editFs" id="editFs"/>
						</div>
						<div class="col-lg-3" id="validateEditFs">
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
	
	<div id="gzqzSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">成功</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-success alert-dismissible" role="alert" id="gzqzSucInfo">
						<strong>编辑成功</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="gzqzError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">编辑失败</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible" role="alert" id="gzqzErrorInfo">
						<strong>编辑时发生了错误</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="editGzsjGzqz" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${pageContext.request.contextPath}/pfpz/editGsjGzqz" id="editGzsjGzqzForm"
							method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">工作实绩总分: <font id="gzsjTotal"></font></h4>
					</div>
					
					<div class="modal-body">
						<div class="leftContent">
							
								<c:forEach items="${gzsjlist}" var="gzsjgzqz" varStatus="status">
									<div class="form-group">
										<label class="col-lg-3 control-label" id="">${gzsjgzqz.mc }</label>
										<div class="col-lg-5">
											<input type="text" class="form-control" id="gzxx${status.index }" 
												name="gzxx${status.index }" data-id="${gzsjgzqz.bh }" value="${gzsjgzqz.fs }" />
										</div>
										<div class="col-lg-4"></div>
									</div>
								</c:forEach>
								
								<div class="form-group">
									<div class="col-lg-3"></div>
									<div class="col-lg-9" id="gzsjValValidate">
										
									</div>
								</div>
						</div>
						
					</div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-success" onclick="configGzsjGzqz()">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- dataTables -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/jquery.dataTables.min.js"></script>
	<!-- dataTables-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/dataTables.bootstrap.js"></script>
	<!-- uniform -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/uniform/js/jquery.uniform.min.js"></script>
	<!-- chosen -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/chosen/js/chosen.jquery.min.js"></script>
	<!-- jGrowl消息提示 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jgrowl/js/jquery.jgrowl.min.js"></script>
	<!-- Alerts弹出对话框 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/alerts/js/jquery.alerts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/gzqz.js"></script>
</body>
</html>