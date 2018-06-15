<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html">
	<meta charset="utf-8">
	<title>睿博财富-用户登录</title>
	<%@ include file="/common/head.jsp"%>
	<script type="text/javascript" src="/js/user/regis.js"></script>
	<link rel="stylesheet" href="/css/user/regis.css">
</head>
<body>
	<%@ include file="/common/menu.jsp"%>
	
	<div class="mainbox">
	<!--主内容 开始-->
		<div class="main clearfix">
			<div class="regisBox">
				<h3>免费注册账户</h3>
				<div class="username">
					<input type="text" placeholder="手机号/账号/邮箱"> 
				</div>
				<div class="password">
					<input type="password" placeholder="密码(建议使用数字+字母)">
				</div>
				<div class="password">
					<input type="password" placeholder="密码(建议使用数字+字母)">
				</div>
				<div class="tel">
					<input type="text" placeholder="请输入正确手机号"> 
				</div>
				<div class="code clearfix">
					<input type="text" placeholder="请输入验证码">
					<a href="javascript:void(0);">获取验证码</a>
				</div>
				<div class="tuijian">
					<a href="javascript:void(0);">推荐人推荐代码（选填）</a>
					<input type="text" placeholder="推荐人推荐代码(选填)">
				</div>
				<div class="xieyi clearfix">
					<a class="agre" href="javascript:void(0);">我已阅读并同意</a>
					<a class="xy" href="javascript:void(0);">《睿博财富网络服务使用协议》</a>
				</div>
				<div class="registButton">
					<a href="javascript:void(0);">注册</a>
				</div>
				<p>已有账号？<a href="/user/login">立即登录</a></p>
			</div>
		</div>
	</div>
	
	<%@ include file="/common/bottom.jsp"%>
</body>