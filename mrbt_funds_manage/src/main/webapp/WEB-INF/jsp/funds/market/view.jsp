<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivView" class="easyui-dialog"
	style="width: 850px; height: 180px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'查看',iconCls: 'icon-add',buttons: '#dlg-buttons'">
	<form id="ffView" method="post" novalidate="novalidate">
		<input type="hidden" name=id id="vid" />
		<table id="tblView" class="view">
			<tr>
				<th><label for="f001Ths035" width='200'>基金代码</label></th>
				<td><input class="easyui-textbox" name="f001Ths035"
					id="vf001Ths035" readonly="readonly" style="width: 200px"></input></td>
				<th><label for="f002Ths035" width='200'>基金简称</label></th>
				<td><input class="easyui-textbox" name="f002Ths035"
					id="vf002Ths035" readonly="readonly" style="width: 200px"></input>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right; padding-top: 10px"><a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconcls="icon-cancel"
					onclick="javascript:$('#DivView').dialog('close')">关闭</a></td>
			</tr>
		</table>
	</form>
</div>