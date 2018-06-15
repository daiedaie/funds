<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivOrder" class="easyui-dialog"
	style="width: 810px; height: 140px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'设置推荐排序',iconCls: 'icon-view',buttons: '#dlg-buttons'">
	<form id="ffOrder" method="post" novalidate="novalidate">
		<input type="hidden" name=isRecomm id="visRecomm" />
		<table id="tblOrder" class="view">
			<tr>
				<th><label for="fundsCode" width='200'>基金代码</label></th>
				<td><input class="easyui-textbox" name="fundsCode"
					id="ofundsCode" readonly="readonly" style="width: 200px"></input></td>
				<th><label for="recommOrder" width='200'>推荐排序</label></th>
				<td><select class="easyui-combobox" name="recommOrder"
					id="orecommOrder" style="width: 200px"
					data-options="editable:false,required:true">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="999">999</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right; padding-top: 10px"><a
					href="javascript:void(0)" class="easyui-linkbutton" id="btnAddOK"
					iconcls="icon-ok" onclick="setRecommOrder()">确定</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconcls="icon-cancel"
					onclick="javascript:$('#DivOrder').dialog('close')">关闭</a></td>
			</tr>
		</table>
	</form>
</div>