<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivView" class="easyui-dialog"
	style="width: 800px; height: 480px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'查看',iconCls: 'icon-view',
			buttons: [{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#DivView').dialog('close');
                    }
                }]">
	<form id="ffView" method="post" novalidate="novalidate">
		<input type="hidden" name=id id="vid" />
		<table id="tblView" class="view">
			<tr>
				<th><label for="createTime" width='200'>基金代码</label></th>
				<td><input class="easyui-textbox" name="fundsCode"
					id="vfundsCode" readonly="readonly" style="width: 200px"></input></td>
				<th><label for="createTime" width='200'>创建时间</label></th>
				<td><input class="easyui-datetimebox" name="createTime"
					id="vcreateTime" readonly="readonly" style="width: 200px"></input>
				</td>
			</tr>
		</table>
		<table class="view">
			<tr>
				<th><label for="offlineNotes">下线备注</label></th>
				<td colspan="3"><textarea name="offlineNotes"
						id="vofflineNotes"
						style="width: 500px; height: 320px; visibility: hidden;"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>