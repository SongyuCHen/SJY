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


<title>加分情形</title>
</head>
<body>
	
	<jsp:include page="header.jsp" />
	
	<div id="wrapperouter">
		<div id="wrapperinner">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/jfqx/cx">
				<div id="menu_left">
					<div id="ok"></div>
					<div id="word">
						<span>加分情形</span>
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
			            <col class="con1">
		          	</colgroup>
					<thead>
						<tr>
							<th class="no-print">
								<!-- <input type="checkbox" name="jfqxbh" id="jfqxbhAll" /> -->
							</th>
							<th class="no-wrap">姓名</th>
							<th class="no-wrap">部门</th>
							<th class="no-wrap">奖励类型</th>
							<th class="no-wrap">级别</th>
							<th class="no-wrap">获得时间</th>
							<th class="no-wrap">加分值</th>
							<th class="no-warp">加分次数</th>
							<th class="no-wrap">备注</th>
							<th class="no-wrap">状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${jfqxList }" var="jfqx">
							<tr>
								<td class="no-print">
									<input type="checkbox" class="jfqxCheckbox" name="jfqxbh" value="${jfqx.bh}" 
										data-rybh="${jfqx.rybh }" data-bmbh="${jfqx.bmbh }" 
										data-ryxm="${jfqx.ryxm}" data-bmmc="${jfqx.bmmc}"
										data-lbbh="${jfqx.jfxm.lb.bh }"
										data-xmbh="${jfqx.jfxm.bh }"
										data-fs="${jfqx.jfxm.fs}"
										data-hdsj="${jfqx.hdsj}"
										data-jfcs="${jfqx.jfcs }"
										data-comment="${jfqx.comment }">
								</td>
								<td class="no-wrap">${jfqx.ryxm }</td>
								<td class="no-wrap">${jfqx.bmmc }</td>
								<td class="no-wrap">${jfqx.jfxm.lb.mc }</td>
								<td>${jfqx.jfxm.mc }</td>
								<td class="no-wrap">${jfqx.hdsj }</td>
								<td class="no-wrap">${jfqx.jfxm.fs }</td>
								<td class="no-warp">${jfqx.jfcs }</td>
								<td class="no-warp">${jfqx.comment }</td>
								<td class="no-wrap">${jfqx.zt }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</div>
	<div id="hiddenValue">
		<input type="text" id="curBmbh" value="${curBmbh}" />
		<input type="text" id="curStartDate" value="${startDate}" />
		<input type="text" id="curEndDate" value="${endDate}" />
	</div>
	
	<div id="addKfqxModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/jfqx/addJfqx" id="addJfqxForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">加分情形</h4>
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
						<label class="col-lg-4 control-label ">奖励类型</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalLbbh" name="lbbh"></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">级别</label>
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
						<label class="col-lg-4 control-label">加分次数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="addJfcs" id="addJfcs" />
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
			<form action="" id="editJfqxForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">加分情形</h4>
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
						<label class="col-lg-4 control-label ">奖励类型</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalLbbhInEdit" name="lbbh"></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">级别</label>
						<div class="col-lg-5">
							<select class="select-control modalSelect  " id="modalXmbhInEdit" name="xmbh"></select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">分数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="fs" id="modalFsInEdit" readonly />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label ">获得时间</label>
						<div class="col-lg-5 input-group date form_date" id="hdsjDiv">
							<input type="text" class="form-control  " name="hdsj" id="modalHdsjInEdit" readonly />
							<span class="input-group-addon  " id="editSpan">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">加分次数</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="editJfcs" id="editJfcs" />
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
	
	<div id="jfqxSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
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
					<div class="alert alert-danger alert-dismissible" role="alert" id="jfqxErrorInfo">
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jfqx.js"></script>
</body>
</html>