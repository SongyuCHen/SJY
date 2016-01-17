<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gzsj.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<title>工作实绩</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	<jsp:include page="header.jsp" />
	
	
		<div id="wrapperinner">
			<div id="menu">
				<form id="searchForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/gzsj/cx">
					<div id="menu_left">
						<div id="ok"></div>
						<div id="word">
							<label class="label-control">工作实绩</label>
						</div>
					</div>
					<div id="menu_right">
						<div id="bmDiv">
							<div class="float-control" id="bmLabelDiv">
								<label class="label-control" id="bmLabel">部门：</label>
							</div>
							<div  class="float-control">
								<select class="select-control" name="bm" id="bm">
									<c:forEach items="${bmList }" var="bm">
										<option>${bm.bmmc }</option>
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
							<button type="submit" class="btn btn-primary" id="searchBtn">
								<span class="glyphicon glyphicon-search"></span> 查 询
							</button>
						</div>
					</div>
				</form>
			</div>
	
			<div id="operationDiv" class="btn-group">
				<button class="btn btn-primary" id="selectAllBtn">全  选</button>
				<div class="btn-group">
					<button data-toggle="dropdown" class="btn btn-success dropdown-toggle">
						操  作 <span class="caret"></span>
					</button>			
					<ul class="dropdown-menu">
						<c:forEach items="${operationList }" var="operation">
							<li><a href="javascript:void(0)" class="operationLi" id="${operation.alias }">${operation.name }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
	
			<div id="content">
				<div id="table_content">
					<table id="gzsjTable" class="table table-bordered">
						<colgroup>
							<col class="con0 no-print" style="align: center; width: 4%">
							<col class="con1">
							<col class="con0">
							<col class="con1">
							<c:forEach var="x" begin="1" end="${pzSize }">
								<col class="con${(x + 1)%2 }">
							</c:forEach>
							<col class="con${(pzSize + 1)%2 }">
						</colgroup>
						<thead>
							<tr>
								<th class="no-print">
									<!-- <input type="checkbox" name="gzsjBh" id="gzsjBhAll" /> -->
								</th>
								<th class="no-wrap">姓名</th>
								<th class="no-wrap">部门</th>
								<th class="no-wrap">日期</th>
								<c:forEach items="${pzList }" var="pz">
									<th class="no-wrap">${pz.mc }</th>
								</c:forEach>
								<th class="no-wrap">状态</th>
								<th class="no-warp">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${mlist }" var="mgzsj">
								<tr>
									<td class="no-print">
										<input type="checkbox" class="gzsjCheckbox" name="gzsjBh" id="gzsjBh" value="${mgzsj.bh }">
									</td>
									<td class="no-wrap">${mgzsj.xm }</td>
									<td class="no-wrap">${mgzsj.bmmc }</td>
									<td class="no-wrap">${mgzsj.rq }</td>
									<c:forEach items="${mgzsj.szList }" var="sz" varStatus="status">
										<%-- <td class="no-wrap">${sz }</td> --%>
										<td class="no-wrap">
											<a href="javascript:void(0)" class="szHref">${sz }</a>
											<%-- <a type="hidden">${mgzsj.szList2[status.index] }</a> --%>
											<input type="hidden" value="${mgzsj.szList2[status.index] }" />
										</td>
									</c:forEach>
									
									<td class="no-wrap">${mgzsj.zt }</td>
									<td class="no-warp">
										<a href="javascript:void(0)" class="czHref">
											<img src="${pageContext.request.contextPath}/images/book_search.png" />
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	
	
	<div id="hiddenValue">
		<input type="text" id="curBmmc" name="curBmmc" value="${curBmmc }" />
	  	<input type="text" id="curStartDate" name="curStartDate" value="${startDate }" />
	  	<input type="text" id="curEndDate" name="curEndDate" value="${endDate }" />
	  	<input type="text" id="status" name="status" value="${status }" />
	  	<input type="text" id="pzSize" name="pzSize" value="${pzSize }" />
	</div>
	
	<!-- 增加工作实际的modal -->
	<div id="addGzsjModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/gzsj/add" id="addGzsjForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span>
        			</button>
        			<h4 class="modal-title" id="">工作实绩</h4>
				</div>
				
				<div class="modal-body">
					
						<div class="form-group">
							<label class="col-lg-4 control-label validateSuc">部门</label>
							<div class="col-lg-5">
								<select	class="select-control modalSelect borderSuc validateSuc" name="addModalBm" id="addModalBm">
									<c:forEach items="${addBmList }" var="bm">
										<option>${bm.bmmc }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-3">
								<label class="control-label">
									<span class="glyphicon glyphicon-ok validateSuc"></span>
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label validateSuc">姓名</label>
							<div class="col-lg-5">
								<select	class="select-control modalSelect borderSuc validateSuc" name="addModalXm" id="addModalXm">
									<c:forEach items="${userList }" var="user">
										<option>${user.xm }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-3">
								<label class="control-label">
									<span class="glyphicon glyphicon-ok validateSuc"></span>
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-4 control-label" id="addSjLabel">时间</label>
							<div class="col-lg-5 input-group date form_date" id="sjDiv">
								<input type="text" class="form-control" name="addModalSj" id="addModalSj" readonly />
								<span class="input-group-addon" id="addSpan">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
							<div class="col-lg-3" id="validateAddSj">
								
							</div>
						</div>
						
						<c:forEach items="${pzList }" var="pz">
							<div class="form-group">
								<label class="col-lg-4 control-label">${pz.mc }</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="addpz${pz.pzbh }" id="addpz${pz.pzbh }" />
								</div>
								<div class="col-lg-3" id="validateAddpz${pz.pzbh }">
									
								</div>
							</div>
						</c:forEach>
						
				</div>
				
				<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        	<button type="button" class="btn btn-success" onclick="confirmAdd()">确定</button>
      	</div>
			</div>
			</form>
		</div>
	</div>
	
	
	<div class="modal fade" id="editGzsjModal" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span>
        			</button>
        			<h4 class="modal-title" id="">工作实绩</h4>
      			</div>

				<div class="modal-body">
					<div class="leftContent">
						<form action="${pageContext.request.contextPath}/gzsj/edit" id="editGzsjForm"
								method="post" class="form-horizontal">
							<input type="hidden" name="editModalBh" id="editModalBh" />	
							
							<div class="form-group">
								<label class="col-lg-3 control-label validateSuc">部门</label>
								<div class="col-lg-4">
									<input type="text" class="form-control borderSuc validateSuc" name="editModalBm" 
										id="editModalBm" readonly />
								</div>
								<div class="col-lg-5">
									<label class="control-label">
										<span class="glyphicon glyphicon-ok validateSuc"></span>
									</label>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-3 control-label validateSuc">姓名</label>
								<div class="col-lg-4">
									<input type="text" class="form-control borderSuc validateSuc" name="editModalXm"
										id="editModalXm" readonly />
								</div>
								<div class="col-lg-5">
									<label class="control-label">
										<span class="glyphicon glyphicon-ok validateSuc"></span>
									</label>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-3 control-label validateSuc">时间</label>
								<div class="col-lg-4">
									<input type="text" class="form-control borderSuc validateSuc" name="editModalSj"
										id="editModalSj" readonly />
								</div>
								<div class="col-lg-5" id="validateEditSj">
									<label class="control-label">
										<span class="glyphicon glyphicon-ok validateSuc"></span>
									</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-lg-3"></div>
								<div class="col-lg-3">
									<label class="validateSuc">原始值</label>
								</div>
								<div class="col-lg-3">
									<label class="validateInfo">修改值</label>
								</div>
							</div>
							
							<c:forEach items="${pzList }" var="pz">
								<div class="form-group">
									<label class="col-lg-3 control-label validateSuc">${pz.mc }</label>
									<div class="col-lg-3">
										<input type="text" class="form-control borderSuc validateSuc" 
											name="editpz${pz.pzbh }" id="editpz${pz.pzbh }" readonly />
									</div>
									<div class="col-lg-3">
										<input type="text" class="form-control borderInfo validateInfo" 
											name="editpzDst${pz.pzbh }" id="editpzDst${pz.pzbh }" readonly />
									</div>
									<div class="col-lg-3">
										<button type="button" class="btn btn-primary gzsjxxEditBtn" data-bh="${pz.pzbh }">修改</button>
										<button type="button" class="btn btn-success gzsjxxViewBtn" data-bh="${pz.pzbh }">查看</button>
									</div>
								</div>
							</c:forEach>						
						</form>
					</div>
					
					<input type="hidden" id="curGzsjxxBh" value="" />
					
					<div class="rightContent">
						<div class="illustrate">
							<!-- <label class="note">说 明</label> -->
							<h2>说明</h2>
							<p>点击修改按钮，可以修改</p>
							<p>点击查看按钮，可以查看</p>
						</div>
						
						<div class="reason">
							<form action="${pageContext.request.contextPath}/gzsj/edit" id="editGzsjxxForm"
								method="post" class="form-horizontal">
								<div class="form-group">
									<label class="col-lg-3 control-label">修改值</label>
									<div class="col-lg-7">
										<input type="text" class="form-control" name="gzsjxxEditVal" id="gzsjxxEditVal" />
									</div>
									<div class="col-lg-2">
									
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">原因</label>
									<div class="col-lg-7">
										<textarea class="form-control" rows="6" name="gzsjxxEditReason" id="gzsjxxEditReason"></textarea>
									</div>
									<div class="col-lg-2">
									
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3"></div>
									<div class="col-lg-7">
										<button type="button" class="btn btn-success gzsjxxSaveBtn">保存</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="clearFloat"></div>
				</div>
				
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        		<button type="button" class="btn btn-success" onclick="confirmEdit()">确定</button>
	        		<button type="button" class="btn btn-success" onclick="confirmEditSubmit()">提交</button>
      			</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="previousModal" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span>
        			</button>
        			<h4 class="modal-title" id="">工作实绩</h4>
      			</div>

				<div class="modal-body">
					<div id="previousTableContent" class="modalTable">
						
					</div>
				</div>
				
				<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        		<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
      			</div>
			</div>
		</div>
	</div>
	
	<div id="chooseInfo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">警告</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-warning alert-dismissible" role="alert" id="chooseAlertInfo">
						<strong>警告！</strong>您没有选择任何记录进行编辑
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="gzsjSuc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">成功</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-success alert-dismissible" role="alert" id="gzsjSucInfo">
						<strong>编辑成功</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="gzsjError" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">错误</h4>
				</div>
				
				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible" role="alert" id="gzsjErrorInfo">
						<strong>编辑时发生了错误！编辑失败！</strong>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="sjygzlModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="sjygzlTitle">详情</h4>
				</div>
				
				<div class="modal-body">
					<div class="btn-group" style="position:relative">
						<button class="btn btn-primary" id="checkoutBtn">导  出</button>
					</div>
					<input type="hidden" id="sjygzlGzsjbh" value="" />
					<div id="sjygzlTableContent" class="modalTable">
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>

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
	<!-- mustache -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/mustache.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/gzsj.js"></script>
	<script type="text/template" id="sjygzlTableTemplate">
		<table id="sjygzlTable" class="table table-bordered">
			<colgroup>
				<col class="con0">
				<col class="con1">
				<col class="con0">
				<col class="con1">
			</colgroup>
			<thead>
				<tr>
					{{#attrList}}
						<th class="no-wrap">{{.}}</th>
					{{/attrList}}
				</tr>
			</thead>
			<tbody>
				{{#sjygzlList}}
					<tr>
						<td>{{attr1}}</td>
						<td>{{attr2}}</td>
						<td>{{attr3}}</td>
						<td>{{attr4}}</td>
						<td>{{attr5}}</td>
						<td>
							<span class="dbsjy-show dbsjy-span">{{attr6}}</span>
							<select class="hide dbsjy-hide dbsjy-select">
								
							</select>
							<button type="button" class="btn btn-success btn-sm dbsjy-show dbsjy-edit">编辑</button>
							<button type="button" class="btn btn-success btn-sm dbsjy-hide dbsjy-save">保存</button>
						</td>
					</tr>
				{{/sjygzlList}}
			</tbody>
		</table>
	</script>
	
	<script type="text/template" id="previousTableTemplate">
		<table id="previousTable" class="table table-bordered">
			<colgroup>
				<col class="con0">
				<col class="con1">
			</colgroup>
			<tbody>
				{{#gzsj}}
					<tr>
						<td>部门</td>
						<td>{{bmmc}}</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td>{{xm}}</td>
					</tr>
					<tr>
						<td>日期</td>
						<td>{{rq}}</td>
					</tr>
				{{/gzsj}}

				{{#mlist}}
					<tr>
						<td>{{mc}}</td>
						<td>{{sz}}</td>
					</tr>
				{{/mlist}}
			</tbody>
		</table>
	</script>
</body>
</html>