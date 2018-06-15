<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="tb" data-options="region:'center'"
	style="padding: 5px; height: auto">
	<table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid"
		data-options="singleSelect:true,toolbar:toolbar,fitColumns:true,pagination:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,url:'/rest/funds/risk/list',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'fundsCode',width:100">基金代码</th>
				<th data-options="field:'riskLevel',width:100">风险级别</th>
				<th data-options="field:'createTime',width:100" formatter="formatTimer">创建时间</th>
			</tr>
		</thead>
	</table>
</div>
