<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gzldj.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kfqx.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<title>工作量对接</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	<jsp:include page="header.jsp" />
	<div id="wrapperouter">
	<div id="wrapperinner">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/gzldj/fetch">
				<div id="menu_left">
					<div id="ok"></div>
					<div id="word">
						<label class="label-control">工作量对接</label>
					</div>
				</div>
				
				<div id="menu_right">
					
					<label id="sjLabel">时间：</label>
					<select class="select-control" name="nf" id="nf">
						<c:forEach items="${years }" var="year">
							<option>${year }</option>
						</c:forEach>
					</select>
					<label>年</label>
					<select class="select-control" class="yf" name="yf" id="yf">
						<c:forEach items="${months }" var="month">
							<option>${month }</option>
						</c:forEach>
					</select>
					<label id="yfLabel">月</label>
					
					<button type="submit" class="btn btn-primary" id="searchBtn">
						<span class="glyphicon glyphicon-search"></span> 获取工作实绩
					</button>
				</div>
			</form>
		</div>
		
		<div id="operationDiv" class="btn-group">
			<button id="operationBtn" data-toggle="dropdown" class="btn btn-primary dropdown-toggle">
				操  作 <span class="caret"></span>
			</button>			
			<ul class="dropdown-menu">
				<c:forEach items="${operationList }" var="operation">
					<li><a href="javascript:void(0)" class="operationLi" id="${operation.alias }">${operation.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="loading-indicator">
			<h4>数据处理中，请稍后</h4>
			<img src="${pageContext.request.contextPath}/images/Preloader_3.gif"/>
		</div>
		
		<div id="content">
			<div id="table_content">
			</div>
		</div>
	</div>
	</div>
	<div id="hiddenValue">
	  	<input type="text" id="curYear" name="curYear" value="${curYear }" />
	  	<input type="text" id="curMonth" name="curMonth" value="${curMonth }" />
	  	<input type="text" id="pzSize" name="pzSize" value="${pzSize }" />
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/jquery.dataTables.min.js"></script>
	<!-- dataTables针对bootstrap的表格风格 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/dataTables/js/dataTables.bootstrap.js"></script>
	<!-- uniform -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/uniform/js/jquery.uniform.min.js"></script>
	<!-- mustache -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/mustache.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/gzldj.js"></script>
	
	<script type="text/template" id="gzldjTableTemplate">
		<h3>获取完毕，共计获取了{{gzsjSize}}条工作实绩记录</h3>
    </script>
	
	<!-- 
	<script type="text/template" id="gzldjTableTemplate">
		<table id="gzldjTable" class="table table-bordered">
			<colgroup>
				<col class="con0">
				<col class="con1">
				<col class="con0">

				{{#pzList}}
					<col class="con0">
				{{/pzList}}
				
				<col class="con0">
			</colgroup>
			<thread>
				<tr>
					<th class="no-wrap no-print"></th>
					<th class="no-wrap">姓名</th>
					<th class="no-wrap">部门</th>
					{{#pzList}}
						<th class="no-wrap">{{mc}}</th>
					{{/pzList}}
					<th class="no-wrap">状态</th>
				</tr>
			</thread>
			<tbody>
				{{#mlist}}
					<tr>
						<td class="no-print">
							<input type="checkbox" name="gzsjBh" id="gzsjBh" value="{{bh}}" />
						</td>
						<td class="no-wrap">{{xm}}</td>
						<td class="no-wrap">{{bmmc}}</td>
						
						{{#szList}}
							<td class="no-wrap">{{.}}</td>
						{{/szList}}
								
						<td class="no-wrap">{{zt}}</td>
					</tr>
				{{/mlist}}
			</tbody>
		</table>
	</script>
	 -->
</body>
</html>