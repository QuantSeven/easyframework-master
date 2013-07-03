<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>EasyFramework</title>
<link rel="stylesheet" type="text/css" href="css/easyframework.css" />
<script type="text/javascript" src="js/plugins/jquery/jquery-1.10.1.min.js"></script>
<script type="text/javascript">  
    function changeR(node){  
        // 用于点击时产生不同的验证码  
        node.src = "randomcode?time="+new Date().getTime() ;      
    }  
</script> 
</head>
<body>
	<div id="container">
		<div id="header">
			<div class="div1">
				<div class="div2">
					<!-- 放logo -->
				</div>
			</div>
		</div>
		<div id="content">
			<div class="box"
				style="width: 400px; min-height: 300px; margin-top: 40px; margin-left: auto; margin-right: auto;">
				<div class="heading">
					<h1>
						<img src="img/lockscreen.png" alt="" />
						管理系统
					</h1>
				</div>
				<%
				String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
				if(error != null){
				%>
					<div class="alert alert-error input-medium controls">
						<button class="close" data-dismiss="alert">×</button>登录失败，请重试.
					</div>
				<%
				}
				%>
				<div class="content" style="min-height: 150px; overflow: hidden;">
					<form action="login" method="post" id="form" >
						<table style="width: 100%;">
							<tr>
								<td style="text-align: center;" rowspan="4">
									<img src="img/login.png"  />
							    </td>
							</tr>
							<tr>
								<td>
									用户名：
									<br /> 
									<input type="text" name="username" style="margin-top: 4px;" />
									<br />
									<br /> 
									密码：
									<br /> 
									<input type="password" name="password" style="margin-top: 4px;" /> 
									<br />
									<br />
									验证码：<input type="randomcode" name="captcha" style="margin-top: 4px;" /> 
									<br />
									<img alt="random" src="randomcode" onclick="changeR(this)" style="cursor: pointer;"> 
									<br />
									<br />
									<a href="user/forgotten">
										忘记密码
									</a>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td style="text-align: right;">
								<a onclick="$('#form').submit();" class="button" >
										登录
								</a></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>