<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivEdit" class="easyui-dialog"
	style="width: 800px; height: 140px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'修改',iconCls: 'icon-edit',buttons: '#dlg-buttons'">
	<form id="ffEdit" method="post" novalidate="novalidate">
		<table id="tblEdit" class="view">
			<tr>
				<th><label for="id" width='200'>ID</label></th>
				<td><input class="easyui-textbox" name="id" style="width: 200px"
					readonly="readonly" data-options="required:true"></input></td>
				<th><label for="name" width='200'>名称</label></th>
				<td><input class="easyui-textbox" name="name"
					style="width: 200px" data-options="required:true"></input></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right; padding-top: 10px"><a
					href="javascript:void(0)" class="easyui-linkbutton" id="btnAddOK"
					iconcls="icon-ok" onclick="update()">确定</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconcls="icon-cancel"
					onclick="javascript:$('#DivEdit').dialog('close')">关闭</a></td>
			</tr>
		</table>
	</form>
</div>