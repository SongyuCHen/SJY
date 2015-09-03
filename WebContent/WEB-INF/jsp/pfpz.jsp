<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pfpz.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pfpz.js"></script>
<title>评分配置</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div id="wrapper">
		<div id="menu">
			<div id="menu_left">
				<div id="ok"></div>
				<div id="word">
					<label class="label-control">评分配置</label>
				</div>
			</div>
			<div id="menu_right">
			</div>
		</div>
	
		<div id="table_content">
			<table class="table table-bordered" id="pfpzTable">
				<thead>
					<tr>
						<th>编号</th>
						<th>规则名称</th>
						<th>类型</th>
						<th>加权</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="pf">
						<tr>
							<td>${pf.bh }</td>
							<td>${pf.mc }</td>
							<td>${pf.lx }</td>
							<td>${pf.jq }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="alert alert-info" role="alert" id="alertInfo">
			<strong>编辑完成后请记得保存</strong>
		</div>
		
		<div id="button_content">
			<button class="btn btn-success" id="editBtn" onclick="editPfpz()">编 辑</button>
      <button class="btn btn-success" id="saveBtn" onclick="savePfpz()">保 存</button>
		</div>
	</div>
	
	<div class="modal fade" id="editPfpz" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
        	<h4 class="modal-title" id="exampleModalLabel">评分配置</h4>
				</div>

				<div class="modal-body">
					<form action="" method="post" 
							class="form-horizontal" id="pfpzForm">
						<div class="form-group">
						  <div id="pfpzTitle">
							  <label id="pfpzTitleLabel" class="labelSuc">配置各个规则的加权</label>
							  <p>请输入百分数字</p>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label labelSuc" id="gzszLabel">工作实值</label>
							<div class="col-lg-4">
								<input type="text" class="form-control inputTextSuc" name="gzsz" id="gzsz"/>
							</div>
							<div class="col-lg-4" id="validateGzsz">
								<label class="control-label">
									<span class="glyphicon glyphicon-ok spanSuc"></span>
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label labelSuc" id="gzzlLabel">工作质量</label>
							<div class="col-lg-4">
								<input type="text" class="form-control inputTextSuc" name="gzzl" id="gzzl"/>
							</div>
							<div class="col-lg-4" id="validateGzzl">
								<label class="control-label">
									<span class="glyphicon glyphicon-ok spanSuc"></span>
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label labelSuc" id="zfsjLabel">作风守纪</label>
							<div class="col-lg-4">
								<input type="text" class="form-control inputTextSuc" name="zfsj" id="zfsj"/>
							</div>
							<div class="col-lg-4" id="validateZfsj">
								<label class="control-label">
									<span class="glyphicon glyphicon-ok spanSuc"></span>
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label labelSuc" id="jfqxLabel">加分情形</label>
							<div class="col-lg-4">
								<input type="text" class="form-control inputTextSuc" name="jfqx" id="jfqx"/>
							</div>
							<div class="col-lg-4" id="validateJfqx">
								<label class="control-label">
									<span class="glyphicon glyphicon-ok spanSuc"></span>
								</label>
							</div>
						</div>
					</form>
				</div>

				<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        	<button type="button" class="btn btn-success" onclick="confirmEdit()">确定</button>
      	</div>
			</div>
		</div>
	</div>
	
	<div id="saveSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
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
						<strong>保存成功！</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="saveError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
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
	 
</body>
</html>