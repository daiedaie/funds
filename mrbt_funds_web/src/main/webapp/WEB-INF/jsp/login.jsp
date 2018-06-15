<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html">
	<meta charset="utf-8">
	<title>睿博财富-用户登录</title>
	<%@ include file="/common/head.jsp"%>
	<script type="text/javascript" src="/js/user/login.js"></script>
	<link rel="stylesheet" href="/css/user/login.css">
</head>
<body>
	<%@ include file="/common/menu.jsp"%>
	
	<div class="mainbox">
	<!--主内容 开始-->
		<div class="main clearfix">
			<div class="loginBox">
				<div class="username">
					<input type="text" placeholder="手机号/账号/邮箱"> 
				</div>
				<div class="password">
					<input type="text" placeholder="密码(建议使用数字+字母)">
				</div>
				<div class="reme clearfix">
					<a class="remember" href="javascript:void(0);">记住账号</a>
					<a class="forgot" href="javascript:void(0)">忘记密码？</a>
				</div>
				<div class="loginButton">
					<a href="javascript:void(0);">登录</a>
				</div>
				<p>没有账号？<a href="/user/regis">立即注册</a></p>
			</div>
		</div>
	</div>
	
	<%@ include file="/common/bottom.jsp"%>
</body>