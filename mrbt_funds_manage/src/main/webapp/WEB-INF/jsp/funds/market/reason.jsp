<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivReason" class="easyui-dialog"
	style="width: 800px; height: 480px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'设置推荐理由',iconCls: 'icon-ok',
			buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        setRecommReason();
                    }
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#DivReason').dialog('close');
                    }
                }]">
	<form id="ffReason" method="post" novalidate="novalidate">
		<table id="tblReason" class="view">
			<tr>
				<th><label for="fundsCode" width='200'>基金代码</label></th>
				<td><input class="easyui-textbox" name="fundsCode"
					id="rfundsCode" readonly="readonly" style="width: 200px"></input></td>
			</tr>
		</table>
		<table class="view">
			<tr>
				<th><label for="recommReason">推荐理由：</label></th>
				<td colspan="3"><textarea name="recommReason"
						id="rrecommReason"
						style="width: 500px; height: 320px; visibility: hidden;"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>