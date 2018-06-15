<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基金基本信息管理</title>
<style type="text/css">
.rowOverrideClass {
	background-color: #A8D6F6;
}
</style>

<jsp:include page="/common/head.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/source/ths035/show.js"></script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-panel"
			data-options="region:'north',title:'查询窗口',iconCls:'icon-book'"
			style="padding: 5px; height: 100px">
			<form id="searchFrm">
				<table>
					<tr>
						<th><label for="rName" width='200'>真实基金代码：</label></th>
						<td><input class="easyui-textbox" name="f002Ths001"
							id="sf002Ths001" style="width: 200px"></input></td>
						<th><label for="rName" width='200'>基金简称：</label></th>
						<td><input class="easyui-textbox" name="f003Ths001"
							id="sf003Ths001" style="width: 200px"></input></td>
						<th><label for="rName" width='200'>基金全称：</label></th>
						<td><input class="easyui-textbox" name="f005Ths001"
							id="sf005Ths001" style="width: 200px"></input></td>
					</tr>
					<tr>
						<th><label for="rName" width='200'>内部基金代码：</label></th>
						<td><input class="easyui-textbox" name="f001Ths001"
							id="sf001Ths001" style="width: 200px"></input></td>
						<th><label for="rName" width='200'>类型：</label></th>
						<td><select class="easyui-combogrid" name="isRecomm"
							id="isRecomm" style="width: 200px"
							data-options="
							            idField: 'id',
							            editable:false,
							            loadMsg:'正在加载,请稍后...',
							            textField: 'name',
							            url: '/resource/json/isRecommType.json',
							            method: 'get',
							            columns: [[
							                {field:'id',title:'id',width:1},
							                {field:'name',title:'名称',width:4},
							            ]],
							            fitColumns: true
							        ">
						</select></td>
						<td colspan="2" align="right"><a href="javascript:search()"
							class="easyui-linkbutton" id="btnAddOK" iconcls="icon-search">查询</a></td>
					</tr>

				</table>
			</form>
		</div>
		<!-- 列表层 -->
		<jsp:include page="grid.jsp"></jsp:include>
	</div>
	<!-- 显示的弹出层 -->
	<jsp:include page="view.jsp"></jsp:include>
	<!-- 编辑的弹出层    end-->

</body>
</html>