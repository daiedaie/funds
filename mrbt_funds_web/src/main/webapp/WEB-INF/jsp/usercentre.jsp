<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html">
	<meta charset="utf-8">
	<title>睿博财富-用户登录</title>
	<%@ include file="/common/head.jsp"%>
	<script type="text/javascript" src="/js/user/usercentre.js"></script>
	<link rel="stylesheet" href="/css/user/usercentre.css">
</head>
<body onload="setMenuClass('${menu}', '${index}')">
	<%@ include file="/common/menu.jsp"%>
	
	<div class="main clearfix">
		<div class="leftNav">
			<%@ include file="usercentres/uc_menu.jsp"%>
		</div>
		<div id='right_main' class="right_main">
			<c:if test="${index == '1'}"><%@ include file="usercentres/uc_zl.jsp"%></c:if>
			<c:if test="${index == '2'}"><%@ include file="usercentres/uc_jjzh.jsp"%></c:if>
			<c:if test="${index == '3'}"><%@ include file="usercentres/uc_msg.jsp"%></c:if>
			<c:if test="${index == '4'}"><%@ include file="usercentres/uc_aqsz.jsp"%></c:if>
			<c:if test="${index == '201'}"><%@ include file="usercentres/uc_201.jsp"%></c:if>
			<c:if test="${index == '202'}"><%@ include file="usercentres/uc_202.jsp"%></c:if>
			<c:if test="${index == '203'}"><%@ include file="usercentres/uc_203.jsp"%></c:if>
			<c:if test="${index == '204'}"><%@ include file="usercentres/uc_204.jsp"%></c:if>
			<c:if test="${index == '205'}"><%@ include file="usercentres/uc_205.jsp"%></c:if>
			<c:if test="${index == '206'}"><%@ include file="usercentres/uc_206.jsp"%></c:if>
		</div>
	</div>
	
	<%@ include file="/common/bottom.jsp"%>
</body>