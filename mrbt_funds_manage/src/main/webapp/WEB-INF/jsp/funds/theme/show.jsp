<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基金主题管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/funds/theme/show.js"></script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-panel"
			data-options="region:'north',title:'查询窗口',iconCls:'icon-book'"
			style="padding: 5px; height: 80px">
			<form id="searchFrm">
				<table>

					<tr>
						<th><label for="fundsCode" width='200'>基金主题名称：</label></th>
						<td><input class="easyui-textbox" name="name"
							id="sname" style="width: 200px"></input></td>
						<td><a href="javascript:search()" class="easyui-linkbutton"
							id="btnAddOK" iconcls="icon-search">查询</a></td>
					</tr>

				</table>
			</form>
		</div>
		<!-- 列表层 -->
		<jsp:include page="grid.jsp"></jsp:include>
	</div>
	<!-- 添加的弹出层 -->
	<jsp:include page="add.jsp"></jsp:include>
	<!-- 添加的弹出层     end-->
	<!-- 编辑的弹出层 -->
	<jsp:include page="update.jsp"></jsp:include>
	<!-- 编辑的弹出层    end-->

</body>
</html>