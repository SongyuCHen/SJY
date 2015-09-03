<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jfpz.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jfpz.js"></script>
<title>加分配置</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<label class="label-control">加分配置</label>
				</div>
			</div>
			<div id="menu_right">
				<button type="button" class="btn btn-success" id="addBtn" onclick="showAddJfpz()">增  加</button>
				<button type="button" class="btn btn-success" id="editBtn" onclick="showEditJfpz()">编  辑</button>
			</div>
		</div>
		
		<div id="table_content">
			<table class="table table-bordered" id="pfpzTable">
				<thead>
					<tr>
						<th></th>
						<th>级别</th>
						<th>加分值</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<c:forEach items="${list }" var="jfpz">
						<tr>
							<td>
								<input type="checkbox" />
								<input type="hidden" id="hiddenBh" name="hiddenBh" value="jfpz.bh" />
							</td>
							<td>${jfpz.jb }</td>
							<td>${jfpz.jfz }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<div id="addJfpz" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">加分配置</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">
					<form action="${pageContext.request.contextPath}/jfpz/add" id="addJfpzForm"
							method="post" class="form-horizontal">
						<div class="form-group">
							<label class="col-lg-4 control-label">级别</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" name="addModalJb" id="addModalJb" />
							</div>
							<div class="col-lg-4">
								<label class="control-label">
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label">加分值</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" name="addModalJfz" id="addModalJfz" />
							</div>
							<div class="col-lg-4">
								<label class="control-label">
								</label>
							</div>
						</div>
					</form>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-success" onclick="addJfpz()">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="editJfpz" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">加分配置</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">
					<form action="${pageContext.request.contextPath}/jfpz/edit" id="editJfpzForm"
							method="post" class="form-horizontal">
						<div class="form-group">
							<input type="hidden" id="editModalBh" name="editModalBh" />
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label">级别</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" name="editModalJb" id="editModalJb" readonly />
							</div>
							<div class="col-lg-4">
								<label class="control-label">
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label">加分值</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" name="editModalJfz" id="editModalJfz" />
							</div>
							<div class="col-lg-4">
								<label class="control-label">
								</label>
							</div>
						</div>
					</form>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-success" onclick="editJfpz()">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="addJfpzSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">成功</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-success" role="alert">
						<strong>添加成功！</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="jfqxError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">错误</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-success" role="alert">
						<strong>发生了错误！</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="chooseError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">警告</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-warning alert-dismissible" role="alert" id="chooseErrorInfo">
						<strong>警告！您没有选择任何记录进行编辑!</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>