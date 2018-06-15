<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基金超市管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/funds/market/show.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
<script charset="utf-8"
	src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
<script charset="utf-8"
	src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-panel"
			data-options="region:'north',title:'查询窗口',iconCls:'icon-book'"
			style="padding: 5px; height: 80px">
			<form id="searchFrm">
				<table>
					<tr>
						<th><label for="fundsCode" width='200'>基金代码：</label></th>
						<td><input class="easyui-textbox" name="fundsCode"
							id="sfundsCode" style="width: 200px"></input></td>
						<td><a href="javascript:search()" class="easyui-linkbutton"
							id="btnAddOK" iconcls="icon-search">查询</a></td>
					</tr>

				</table>
			</form>
		</div>
		<!-- 列表层 -->
		<jsp:include page="grid.jsp"></jsp:include>
	</div>
	<!-- 设置推荐排序的弹出层 -->
	<jsp:include page="order.jsp"></jsp:include>
	<!-- 设置推荐排序的弹出层    end-->
	<!-- 基金超市下线的弹出层 -->
	<jsp:include page="offline.jsp"></jsp:include>
	<!-- 基金超市下线的弹出层    end-->
	<!-- 设置推荐理由的弹出层 -->
	<jsp:include page="reason.jsp"></jsp:include>
	<!-- 设置推荐理由的弹出层    end-->
	<!-- 设置基金主题的弹出层 -->
	<jsp:include page="theme.jsp"></jsp:include>
	<!-- 设置基金主题的弹出层    end-->

</body>
</html>