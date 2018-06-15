<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 设置组合基金-->
<div id="DivSetting" class="easyui-dialog"
	style="width: 860px; height: 300px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'设置组合基金',iconCls: 'icon-setting',buttons: '#dlg-buttons'">
	<table class="easyui-datagrid" fit='true' id="settingGrid"
		data-options="rownumbers:true,singleSelect:true,toolbar:toolbars,fitColumns:true,loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,method:'post'">
		<thead>
			<tr>
				<th data-options="field:'typeId',width:100">组合类型ID</th>
				<th data-options="field:'typeName',width:100">基金组合名称</th>
				<th data-options="field:'fundsCode',width:100">基金代码</th>
				<th data-options="field:'fundsCodeInner',width:100">内部基金代码</th>
				<th data-options="field:'ratio',width:100">基金配置比例（%）</th>
				<th data-options="field:'fundsTypeId',width:100">基金所属类型ID</th>
			</tr>
		</thead>
	</table>
</div>