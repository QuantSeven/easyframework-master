<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>EasyFramework</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap-responsive.min.css" media="screen" />
<script type="text/javascript" src="js/plugins/jquery/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
	function changeR(node) {
		// 用于点击时产生不同的验证码  
		node.src = "randomcode?time=" + new Date().getTime();
	}
</script>
<style type="text/css">
.top-banner {
	height: 56px;
	position: absolute;
	background: url('img/header.png') repeat-x;
	width: 100%;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.15);
	color: #221815;
	overflow: hidden;
	margin: 0px;
	top: 0px;
}

.center-banner {
	min-height: 100%;
	margin-top: 130px;
}

.bottom-banner {
	background: url('img/footer.png') repeat-x;
	height: 56px;
	width: 100%;
	vertical-align: bottom;
	position: absolute;
	bottom: 0px;
}

.login-box {
	background: none repeat scroll 0 0 #FFFFFF;
	border: 1px solid #CCCCCC;
	border-radius: 6px 6px 6px 6px;
	box-shadow: 0 0 6px rgba(0, 0, 0, 0.2);
	margin: 0 auto 24px;
	position: relative;
	width: 480px;
}

.login-box .alert-login {
	margin: 10px;
	width: 85%;
}

.login-box .top-panel {
	background: none repeat scroll 0 0 #E0E0E0;
	border-bottom: 1px solid #CCCCCC;
	border-radius: 6px 6px 0 0;
	font: 100 15px/42px 'PT Sans', sans-serif;
	height: 42px;
	padding: 0 20px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}

.login-box .center-panel {
	margin: 0 auto;
	padding: 10px 0;
	width: 100%;
	height: 100px;
}

.login-box .center {
	text-align: center;
	vertical-align: middle;
}

.login-box .center-panel .left {
	float: left;
	width: 100px;
	margin-left: 60px;
}

.login-box .center-panel .right {
	float: right;
	margin-right: 50px;
	margin-top: 15px;
}

.login-box .bottom-panal {
	background: none repeat scroll 0 0 #F7F7F7;
	border-radius: 0 0 6px 6px;
	border-top: 1px solid #E7E7E7;
	padding: 12px 20px;
	margin-top: 15px;
}

.login-box .link-forgetpass {
	display: block;
	font-size: 11px;
	padding: 5px 0 0;
	cursor: pointer;
}

.login-form {
	margin: 0;
}
</style>

<script type="text/javascript">
	$(function() {
		$('#forgotPass').on('click', function(e) {
			$('#logon-box').fadeOut('slow', function() {
				$('#forrgotpass-box').slideDown("slow");
			});
		});
		$("#backToLogin").on("click", function() {
			$('#forrgotpass-box').slideUp('slow', function() {
				$('#logon-box').fadeIn("slow");
			});
		});
	});
</script>
</head>
<body>
	<div class="top-banner"></div>
	<div class="center-banner">
		<div class="login-box" id="logon-box">
			<form action="${pageContext.request.contextPath}/login" method="post" id="login_form" class="login-form">
				<div class="top-panel">
					<img src="img/lockscreen.png" /> 后台管理系统
				</div>
				<%
					String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
					if (error != null) {
				%>
				<div class="alert alert-info alert-login">登录失败！用户名或密码不正确，请重试。</div>
				<%
					}
				%>
				<div class="center-panel">
					<div class="left">
						<img src="img/login.png" />
					</div>
					<div class="right">
						<div class="form-row">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span><input type="text" id="username" name="username" placeholder="Username" value="qya" />
							</div>
						</div>
						<div class="form-row">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span><input type="password" id="password" name="password" placeholder="Password" value="123" />
							</div>
						</div>
						<div class="form-row clearfix">
							<label class="checkbox"><input type="checkbox" /> Remember me</label>
						</div>
					</div>
				</div>
				<div class="bottom-panal clearfix">
					<button class="btn pull-right" type="submit">
						<i class="icon-hand-right"></i> 登录
					</button>
					<span class="link-forgetpass"><a id="forgotPass">忘记密码?</a></span>
				</div>
			</form>
		</div>
		<div id="forrgotpass-box" class="login-box" style="display: none">
			<div class="top-panel">
				<img src="img/lockscreen.png" alt="" /> 忘记密码
			</div>
			<div class="alert alert-info alert-login">请输入您的邮箱地址，你会收到一封修改新密码的邮件。</div>
			<form method="post" class="login-form">
				<div class="center-panel" style="height: auto">
					<div class="form-row">
						<div class="center">
							<div class="input-prepend">
								<span class="add-on">@</span><input type="text" name="email" placeholder="Your email address" />
							</div>
						</div>
					</div>
				</div>
				<div class="bottom-panal clearfix">
					<button class="btn pull-right" type="submit"><i class="icon-envelope "></i> 找回密码</button>
					<span class="link-forgetpass"><a id="backToLogin">返回登录</a></span>
				</div>
			</form>
		</div>
	</div>
	<div class="bottom-banner"></div>
</body>
</html>
