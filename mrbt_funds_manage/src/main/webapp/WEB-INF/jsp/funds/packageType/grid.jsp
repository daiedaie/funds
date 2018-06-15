<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="tb" data-options="region:'center'"
	style="padding: 5px; height: auto">
	<table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid"
		data-options="singleSelect:true,toolbar:toolbar,fitColumns:true,pagination:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,url:'/rest/funds/packageType/list',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'id',width:100">组合类型ID</th>
				<th data-options="field:'name',width:100">基金组合名称</th>
				<th data-options="field:'createTime',width:100" formatter="formatTimers">创建日期</th>
				<th data-options="field:'feature',width:100">特点</th>
				<th data-options="field:'confAnalyze',width:100">配置点评</th>
				<th data-options="field:'onLine',width:100" formatter="formatOnline">是否上线</th>
			</tr>
		</thead>
	</table>
</div>
