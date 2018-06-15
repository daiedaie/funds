<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivTheme" class="easyui-dialog"
	style="width: 800px; height: 170px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'设置基金主题',iconCls: 'icon-key',
			buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        setFundsTheme();
                    }
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#DivTheme').dialog('close');
                    }
                }]">
	<form id="ffTheme" method="post" novalidate="novalidate">
		<table id="tblTheme" class="view">
			<tr>
				<th><label for="fundsCode" width='200'>基金代码</label></th>
				<td><input class="easyui-textbox" name="fundsCode"
					id="tfundsCode" readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label for="fundsCode" width='200'>基金主题</label></th>
				<td colspan="3"><select multiple="multiple" class="easyui-combogrid"
					id="tfundsTheme" name="fundsTheme" style="width: 600px"
					data-options="
							            panelWidth: 600,
							            idField: 'name',
							            required:true,
							            editable:false,
							            loadMsg:'正在加载,请稍后...',
							            textField: 'name',
							            url: '/rest/funds/theme/list', 
							            method: 'get',
							            remote:'remote',
							            columns: [[
							                {field:'id',title:'基金主题id',width:1},
							                {field:'name',title:'基金主题名称',width:3}
							            ]],
							            fitColumns: true
							        ">
				</select></td>
			</tr>
		</table>
	</form>
</div>