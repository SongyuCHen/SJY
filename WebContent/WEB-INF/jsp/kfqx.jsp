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
		
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jfqx.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>


<title>扣分情形</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	
	<jsp:include page="header.jsp" />
	

	<div id="wrapperinner">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/kfqx/cx">
				<div id="menu_left">
					<div id="ok"></div>
					<div id="word">
						<span>扣分情形</span>
					</div>
				</div>
				<div id="menu_right">
					<div id="bmDiv">
						<div class="float-control" id="bmLabelDiv">
							<label id="bmLabel">部门：</label>
						</div>
						<div  class="float-control">
							<select class="select-control" name="bmbh" id="bm">
								<c:forEach items="${bmList }" var="bm">
									<option value="${bm.bmbh}">${bm.bmmc }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div id="ksrqDiv">
						<div class="float-control" id="ksrqLabelDiv">
							<label class="label-control" id="ksrqLabel">开始日期：</label>
						</div>
						<div class="float-control">
							<div class="input-group date form_date" id="ksrqInputDiv">
								<input type="text" class="form-control" name="startDate" id="startDate" readonly />
								<span class="input-group-addon" id="addSpan">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
					
					<div id="jsrqDiv">
						<div class="float-control" id="jsrqLabelDiv">
							<label class="label-control" id="jsrqLabel">结束日期：</label>
						</div>
						<div class="float-control">
							<div class="input-group date form_date" id="jsrqInputDiv">
								<input type="text" class="form-control" name="endDate" id="endDate" readonly />
								<span class="input-group-addon" id="addSpan">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
					
					<div id="btnDiv">
						<button id="queryGlajBtn" type="submit" class="btn btn-primary">
					    	<span class="glyphicon glyphicon-search"></span> 查询
					    </button>
				    </div>
					
				</div>
			</form>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button class="btn btn-primary" id="selectAllBtn">全  选</button>
			<div class="btn-group">
	          	<button data-toggle="dropdown" class="btn btn-success dropdown-toggle">操 作 <span class="caret"></span></button>
	          	<ul class="dropdown-menu">
		          	<c:forEach items="${operationList }" var="operation">
						<li><a href="javascript:void(0)" class="operationLi" id="${operation.alias }">${operation.name }</a></li>
					</c:forEach>
	          	</ul>
          	</div>
        </div>
		
		<div id="content">
			<div id="table_content">
				<table id="kfqxTable" class="table table-bordered">
					<colgroup>
			            <col class="con0 no-print" style="align: center; width: 4%">
			            <col class="con1">
			            <col class="con0">
			            <col class="con1">
			            <col class="con0">
			            <col class="con1">
			            <col class="con0">
			            <col class="con1">
			            <col class="con0">
		          	</colgroup>
					<thead>
						<tr>
							<th class="no-print">
								<!-- <input type="checkbox" name="kfqxbh" id="kfqxbhAll" /> -->
							</th>
							<th class="no-wrap">姓名</th>
							<th class="no-wrap">部门</th>
							<th class="no-wrap">扣分类别</th>
							<th class="no-wrap">扣分项目名称</th>
							<th class="no-wrap">获得时间</th>
							<th class="no-wrap">分数</th>
							<th class="no-wrap">扣分次数</th>
							<th class="no-wrap">备注</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${kfqxList}" var="kfqx">
							<tr>
								<td class="no-print">
									<input type="checkbox" class="kfqxCheckbox" name="kfqxbh" value="${kfqx.bh}" 
										data-rybh="${kfqx.rybh }" data-bmbh="${kfqx.bmbh }" 
										data-ryxm="${kfqx.ryxm}" data-bmmc="${kfqx.bmmc}"
										data-lbbh="${kfqx.kfxm.kflb.bh }"
										data-xmbh="${kfqx.kfxm.bh }"
										data-fs="${kfqx.kfxm.fs}"
										data-hdsj="${kfqx.hdsj}"
										data-kfcs="${kfqx.kfcs }"
										data-comment="${kfqx.comment }">
								</td>
								<td class="no-wrap">${kfqx.ryxm }</td>
								<td class="no-wrap">${kfqx.bmmc }</td>
								<td class="no-wrap">${kfqx.kfxm.kflb.mc }</td>
								<td>${kfqx.kfxm.mc }</td>
								<td class="no-wrap">${kfqx.hdsj }</td>
								<td class="no-wrap">${kfqx.kfxm.fs }</td>
								<td class="no-warp">${kfqx.kfcs }</td>
								<td class="no-warp">${kfqx.comment }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div id="hiddenValue">
		<input type="text" id="curStartDate" value="${startDate}" />
		<input type="text" id="curEndDate" value="${endDate}" />
		<input type="text" id="curBmbh" value="${curBmbh}" />
	</div>
	
	<div id="addKfqxModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/kfqx/addKfqx" id="addKfqxForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">扣分情形</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">
					<div class="form-group">
						<label class="col-lg-4 control-label ">部门</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalBmbh" name="bmbh"></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">人员</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalRybh" name="rybh"></select>
						</div>
					</div>			
							
					<div class="form-group">
						<label class="col-lg-4 control-label ">类别名称</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalLbbh" name="lbbh"></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">项目名称</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalXmbh" name="xmbh"></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">分数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control  " name="fs" id="modalFs" readonly />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">获得时间</label>
						<div class="col-lg-5 input-group date form_date" id="hdsjDiv">
							<input type="text" class="form-control" name="hdsj" id="modalHdsj" readonly />
							<span class="input-group-addon" id="addSpan">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">扣分次数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addKfcs" id="addKfcs" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">备注</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addComment" id="addComment" />
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

	<div id="editKfqxModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="" id="editKfqxForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">扣分情形</h4>
				</div>
				
				<div class="modal-body" id="modalBodyDiv">
					<input type="hidden" id="modalKfqxbh" name="bh" />					
					<div class="form-group">
						<label class="col-lg-4 control-label ">部门</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" id="modalBmmcInEdit" readonly/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">人员</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" id="modalRyxmInEdit" readonly/>
						</div>
					</div>		
								
					<div class="form-group">
						<label class="col-lg-4 control-label ">类别名称</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalLbbhInEdit" name="lbbh"></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">项目名称</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalXmbhInEdit" name="xmbh"></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">分数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control  " name="fs" id="modalFsInEdit" readonly />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">获得时间</label>
						<div class="col-lg-5 input-group date form_date" id="hdsjDiv">
							<input type="text" class="form-control  " name="hdsj" id="modalHdsjInEdit" readonly />
							<span class="input-group-addon  ">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">扣分次数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editKfcs" id="editKfcs" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">备注</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editComment" id="editComment" />
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/kfqx.js"></script>
</body>
</html>