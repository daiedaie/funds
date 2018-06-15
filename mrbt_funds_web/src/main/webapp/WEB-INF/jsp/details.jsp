<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html">
	<meta charset="utf-8">
	<title>睿博财富-基金详情</title>
	<%@ include file="/common/head.jsp"%>
	<link rel="stylesheet" href="/css/details/details.css">
	<script type="text/javascript" src="/js/details.js"></script>
</head>
<body>
	<input id='model_code' style="display: none;" value='${code}' />
	<%@ include file="/common/menu.jsp"%>
		<div class="main">     
			<div class="xqTop clearfix">
				<div class="xqTleft">
					<%@ include file="details/net_value.jsp" %>
				</div>
				<div class="xqTright">
					<%@ include file="details/fund_base.jsp" %>
				</div>
			</div>
			<div class="xiangqing">
				<%@ include file="details/fund_desc.jsp" %>
			</div>
			<div class="jjxqTable">
				<div class="jtab clearfix">
					<a class="tba_1 active" href="javascript:void(0);">基金投资</a>
					<a href="javascript:void(0);">基金经理</a>
					<a href="javascript:void(0);">同类基金对比</a>
					<a href="javascript:void(0);">基金评价</a>
					<a href="javascript:void(0);">报告规模变动</a>
					<a href="javascript:void(0);">基金费率</a>
					<a href="javascript:void(0);">基金公告</a>
				</div>
				<div class="xBox">
					<div class="jt_1 jjtz">
						<%@ include file="details/fund_tag1.jsp" %>
					</div>
					<div class="jt_2 jjjl" style="display: none;">
						<%@ include file="details/fund_tag2.jsp" %>
					</div>
					<div class="jt_3 tldb" style="display: none;">
						<%@ include file="details/fund_tag3.jsp" %>
					</div>
					<div class="jt_4 jjpj" style="display: none;">
						<%@ include file="details/fund_tag4.jsp" %>
					</div>
					<div class="jt_5 bggmbd" style="display: none;">
						<%@ include file="details/fund_tag5.jsp" %>
					</div>
					<div class="jt_6 jjfl" style="display: none;">
						<%@ include file="details/fund_tag6.jsp" %>
					</div>
					<div class="jt_7 jjgg" style="display: none;">
						<%@ include file="details/fund_tag7.jsp" %>
					</div>
				</div>
			</div>
		</div>
<%@ include file="/common/bottom.jsp"%>
</body>
</html>
