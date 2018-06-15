<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="tb" data-options="region:'center'"
	style="padding: 5px; height: auto">
	<table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid"
		data-options="rownumbers:true,singleSelect:true,toolbar:toolbar,fitColumns:true,pagination:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,url:'/rest/funds/knowledge/list',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'id',width:100,hidden:true">ID</th>
				<th data-options="field:'title',width:100">标题</th>
				<th data-options="field:'createTime',width:100" formatter="formatTimer">创建时间</th>
				<th data-options="field:'author',width:100">作者</th>
				<th data-options="field:'onLine',width:100" formatter="formatOnline">是否在线</th>
			</tr>
		</thead>
	</table>
</div>
