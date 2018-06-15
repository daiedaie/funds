<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivView" class="easyui-dialog"
	style="width: 800px; height: 260px; padding: 10px 20px" closed="true"
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
				<th><label for="createTime" width='200'>组合名称</label></th>
				<td><input class="easyui-textbox" name="name"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label for="createTime" width='200'>创建时间</label></th>
				<td><input class="easyui-datebox" name="createTime"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>组合特性</label></th>
				<td colspan="3"><input class="easyui-textbox" name="feature"
					readonly="readonly" style="width: 600px;"></input></td>
			</tr>
			<tr>
				<th><label width='200'>配置点评</label></th>
				<td colspan="3"><input class="easyui-textbox"
					name="confAnalyze" readonly="readonly"
					data-options="multiline:true" style="width: 600px; height: 80px;"></input></td>
			</tr>
		</table>
	</form>
</div>