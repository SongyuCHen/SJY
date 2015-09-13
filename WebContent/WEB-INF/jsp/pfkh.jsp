<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp"%>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/pfkh.css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/pfkh.js"></script>
<title>评分考核</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="wrapperouter">
	<div id="wrapperinner">
		<div id="menu">
			<form id="searchForm" method="post" class="form-horizontal"
				action="${pageContext.request.contextPath}/pfkh/cx">
				<div id="menu_left">
					<div id="ok"></div>
					<div id="word">
						<label class="label-control">评分考核</label>
					</div>
				</div>
				
				<div id="menu_right">
					<label>部门:</label> 
					<select class="select-control" name="bm" id="bm">
						<c:forEach items="${bmList }" var="bm">
							<option>${bm.bmmc }</option>
						</c:forEach>
					</select>
					<label id="xmLabel">姓名:</label> 
					<select class="select-control" name="xm" id="xm">
						<c:forEach items="${userList }" var="user">
							<option>${user.xm }</option>
						</c:forEach>
					</select>
					
					<label id="sjLabel">时间:</label> 
					<select class="select-control" name="nf" id="nf">
						<c:forEach items="${years }" var="year">
							<option>${year }</option>
						</c:forEach>
					</select> 
					<label id="yearLabel">年</label> 
					<select class="select-control" name="jd" id="jd">
						<c:forEach items="${quarters }" var="quarter">
							<option>${quarter }</option>
						</c:forEach>
					</select> 
					<label id="quarterLabel">季度</label>
					<button class="btn btn-primary">生成考评</button>
				</div>
			</form>
		</div>


		<div id="content">
			<div id="table_content">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>规则</th>
							<th colspan="2">规则细项</th>
							<th>得分</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mpfkh.pfkhgzList }" var="pfkhgz">
							<tr>
								<td rowspan="${fn:length(pfkhgz.gzxxList) }">${pfkhgz.gzmc }</td><!-- 规则名称 如：工作实值 -->
								<c:forEach items="${pfkhgz.gzxxList }" var="gypz" begin="0" end="0">
									<td class="gypz-mc">
										<div>
											${gypz.mc }
										</div>
									</td><!-- 规则细项  如：装订卷宗 -->
									<td class="gypz-sz">
										<div>
											${gypz.sz }
										</div>
									</td><!-- 规则细项的数值   如：20 -->
								</c:forEach>
								<td rowspan="${fn:length(pfkhgz.gzxxList) }">${pfkhgz.gzdf }</td>
							</tr>
							<c:forEach items="${pfkhgz.gzxxList }" var="gypza" begin="1" end="${fn:length(pfkhgz.gzxxList) }">
								<tr>
									<td class="gypz-mc">			
										<div>
										${gypza.mc }
										</div>										
									</td>
									<td class="gypz-sz">
										<div>
										${gypza.sz }
										</div>
									</td>
								</tr>
							</c:forEach>	
						</c:forEach>
						<tr>
							<th colspan="3">总分</th>
							<th>${mpfkh.jddf }</th>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="button_content">
				<button class="btn btn-success" id="editBtn">编 辑</button>
				<button class="btn btn-success" id="saveBtn">保 存</button>
			</div>
		</div>
	</div>
	</div>
	<div id="hiddenValue">
		<input type="text" id="curBm" value="${mpfkh.bmmc }" />
		<input type="text" id="curXm" value="${mpfkh.ryxm }" />
		<input type="text" id="curNf" value="${mpfkh.nf }" /> 
		<input type="text" id="curJd" value="${mpfkh.jd }" />
	</div>
	
</body>
</html>