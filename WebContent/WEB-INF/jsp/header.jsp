<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

	<div id="header">
		<div id="picture">
			<div id="title_left"></div>
			<div id="title_text"></div>
			<div id="title_right"></div>
		</div>
	
		<div id="navigation">
			<div id="navWrapper">
				<div id="btnGroup">
				<ul class="nav nav-pills">
					<c:forEach items="${mResList }" var="mres">
						<c:choose>
							<c:when test="${fun:length(mres.childrenList) > 0 }">
								<li id="" role="presentation" class="dropdown">
									<a href="${pageContext.request.contextPath}${mres.url }"
										class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" 
										data-delay="100" data-close-others="false">${mres.resname }
									</a>
									<ul class="dropdown-menu" role="menu">
										<c:forEach items="${mres.childrenList }" var="child">
											<li><a href="${pageContext.request.contextPath}${child.url}">${child.resname}</a></li>
										</c:forEach>
									</ul>
								</li>
							</c:when>
							<c:otherwise>
								<li id="" role="presentation"><a href="${pageContext.request.contextPath}${mres.url}">${mres.resname }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li id="" class="dropdown">
					  <a href="${pageContext.request.contextPath}/user/tcdl">退出登录</a>
					</li>
					<li id="" class="dropdown">
					<a href="javascript:void(0)" onclick="modifyPwd()">修改密码</a>
					</li>
				</ul>
				</div>
				
				 <div id="userInfo">
					<%-- <span>欢迎您！</span>
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${USER.xm } --%>
					<span id="logoutSpan">
						<%-- <a href="${pageContext.request.contextPath}/user/tcdl">[退出]</a> --%>
						<a href="${pageContext.request.contextPath}/user/fhsy">[首页]</a>
						<!-- <a href="javascript:void(0)" onclick="modifyPwd()">[修改密码]</a> -->
					</span>
				</div> 
			</div>
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
		
	</div>