<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="tb" data-options="region:'center'"
	style="padding: 5px; height: auto">
	<table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid"
		data-options="singleSelect:true,toolbar:toolbar,fitColumns:true,pagination:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,url:'/rest/source/ths035/listCombo',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'f002Ths001',width:100 ">真实基金代码</th>
				<th data-options="field:'f003Ths001',width:100 ">基金简称</th>
				<th data-options="field:'f005Ths001',width:100 ">基金名称</th>
				<th data-options="field:'isRecomm',width:100,hidden:true">是否推荐</th>
				<th data-options="field:'f001Ths001',width:100 ">内部基金代码</th>
			</tr>
		</thead>
	</table>
</div>
