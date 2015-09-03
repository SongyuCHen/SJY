<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="base.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>

<title>登录</title>
</head>
<body>
	<div id="wrapper">
		<div id="title">
			<div id="title_content">
				<div id="log"></div>
				<div id="title_name">
					<div id="title_image"></div>
				</div>
			</div>
		</div>
		<div id="content">
			<div id="login_content">
				<div id="main_content">
					<form action="${pageContext.request.contextPath}/user/signIn" method="post" class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-lg-4 control-label">用户名:</label>
							<div class="col-lg-6">
								<input type="text" class="form-control" id="username" name="username"
									placeholder="请输入用户名">
							</div>
							<div class="col-lg-2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label">密       码:</label>
							<div class="col-lg-6">
								<input type="password" class="form-control" id="password" name="password"
									placeholder="请输入密码">
							</div>
							<div class="col-lg-2">
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-lg-offset-4 col-lg-10">
								<div class="checkbox">
									<label> <input type="checkbox" id="remember"> 记住用户名和密码
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12" id="buttongroup">
								<button type="submit" class="btn btn-success" id="btn_login">登  录</button>
								<button type="reset" class="btn btn-default">重  置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<div id="footer">
			<div id="footContent" class="inline">
				<!-- <p>©版权所有：徐州市中级人民法院。</p> -->
				<p>©2015 版权所有：南京铉盈网络科技有限公司</p>
			</div>
			<%-- <div id="footImg" class="inline">
				<img id="logo" src="${pageContext.request.contextPath}/images/XuanYingLogo.jpg" />
			</div> --%>
			
			
		</div>
		
		<div id="loginRes">
			<input type="text" value="${status }" id="resStatus"/>
		</div>
		
		
	</div>
	
	<div class="modal fade" id="resModal" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">登录失败</h4>
				</div>

				<div class="modal-body">
					<div class="alert alert-warning" role="alert" id="resInfo">
						
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