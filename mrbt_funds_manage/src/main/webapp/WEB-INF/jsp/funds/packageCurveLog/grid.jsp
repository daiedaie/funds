<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="tb" data-options="region:'center'"
	style="padding: 5px; height: auto">
	<table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid"
		data-options="singleSelect:true,fitColumns:true,pagination:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,url:'/rest/funds/packageCurveLog/list',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'typeId',width:100">基金组合类型id</th>
				<th data-options="field:'cDate',width:100" formatter="formatTimer">计算日期</th>
				<th data-options="field:'cData',width:100">曲线值</th>
				<th data-options="field:'createTime',width:100" formatter="formatTimers">操作时间</th>
				<th data-options="field:'operateStyle',width:100" formatter="formatStyle">操作方式</th>
			</tr>
		</thead>
	</table>
</div>
