<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gztb.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gztb.js"></script>
<title>工作填报</title>
</head>
<body style="background-image:url(../images/background07.jpg); background-size:105%;">
	<jsp:include page="header.jsp" />

	<div id="wrappergztb">
		<c:forEach items="${mResList }" var="mres" varStatus="loop">
			<c:choose>
				<c:when test="${fun:length(mres.childrenList) > 4 }">
					<div class="commonDiv">
						<div class="panelHead">	
			       			<h4 class="panelTitle">${mres.resname }</h4>
						</div>
				      	<div class="panelBody">
					       	<ul>
								<c:forEach items="${mres.childrenList }" var="child">
									<li>
										<a href="${pageContext.request.contextPath}${child.url}">
											<img/>${child.resname}
										</a>
									</li>
								</c:forEach>
							</ul>
					  	</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="floatDiv">
						<div class="panelHead">
							<h4 class="panelTitle">${mres.resname }</h4>
						</div>
						<div class="panelBody">
							<ul>
								<c:forEach items="${mres.childrenList }" var="child">
									<li>
										<a href="${pageContext.request.contextPath}${child.url}">
											<img/>${child.resname}
										</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
	</div>

	<div class="bottom_pic">
	   <span>欢迎您！${USER.xm }</span>
	</div>
	<!-- 修改密码的modal -->
	<div id="modifyPwdModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/user/modifyPwd" id="modifyPwdForm"
							method="post" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span>
        			</button>
        			<h4 class="modal-title" id="">修改密码</h4>
				</div>
				
				<div class="modal-body">
					<div class="form-group">
						<label class="col-lg-4 control-label">旧密码</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="oldPwd" id="oldPwd" />
						</div>
						<div class="col-lg-3" id="validateOldPwd">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">新密码</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="newPwd" id="newPwd" />
						</div>
						<div class="col-lg-3" id="validateNewPwd">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label"></label>
						<div class="col-lg-8">
							<label class="attention">* 密码必须由2-10位数字或字母组成。</label>
						</div>
						<!-- <div class="col-lg-3" id="validateNewPwd">
						</div> -->
					</div>
					
					<div class="form-group">
						<label class="col-lg-4 control-label">重复新密码</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="confirmPwd" id="confirmPwd" />
						</div>
						<div class="col-lg-3" id="validateConfirmPwd">
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
        			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        			<button type="button" class="btn btn-success" onclick="confirmModify()">确定</button>
      			</div>
			</div>
			</form>
		</div>
	</div>
	
</body>
</html>