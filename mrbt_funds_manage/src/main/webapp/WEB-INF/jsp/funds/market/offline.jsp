<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivOffline" class="easyui-dialog"
	style="width: 800px; height: 480px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'基金超市下线',iconCls: 'icon-view',
			buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        offlineFromFundsMarket();
                    }
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#DivOffline').dialog('close');
                    }
                }]">
	<form id="ffOffline" method="post" novalidate="novalidate">
		<input type="hidden" name="id" id="vid" />
		<input type="hidden" name="fundsCodeInner" id="vfundsCodeInner" />
		<table id="tblOffline" class="view">
			<tr>
				<th><label for="fundsCode" width='200'>基金代码</label></th>
				<td><input class="easyui-textbox" name="fundsCode"
					id="vfundsCode" readonly="readonly" style="width: 200px"></input></td>
			</tr>
		</table>
		<table class="view">
			<tr>
				<th><label for="offlineNotes">下线备注：</label></th>
				<td colspan="3"><textarea name="offlineNotes"
						id="vofflineNotes"
						style="width: 500px; height: 320px; visibility: hidden;"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>