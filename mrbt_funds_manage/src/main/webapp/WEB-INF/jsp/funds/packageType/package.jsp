<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 添加和修改基金组合的弹出层 -->
<div id="DivPackage" class="easyui-dialog"
	style="width: 800px; height: 260px; padding: 10px 20px" closed="true"
	modal="true"
	data-options="title:'基金组合管理',iconCls: 'icon-add',
			buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        saveAndUpdatePackage();
                    }
                },{
                    text:'关闭',
                     iconCls:'icon-cancel',
                    handler:function(){
                        $('#DivPackage').dialog('close');
                    }
                }]">
	<form id="ffPackage" method="post" novalidate="novalidate"
		enctype="multipart/form-data">
		<input type="hidden" name="id"> <input type="hidden"
			name="fundsTypeId" id="fundsTypeId"> <input type="hidden"
			name="fundsCodeInner" id="fundsCodeInner">
		<table class="view">
			<tr>
				<th>组合ID：</th>
				<td><input class="easyui-textbox" style="width: 200px"
					name="typeId" id="typeId" data-options="required:true"></td>
				<th>组合名称：</th>
				<td><input class="easyui-textbox" style="width: 200px"
					name="typeName" id="typeName" data-options="required:true"></td>
			</tr>
			<tr>
				<th>基金代码：</th>
				<td><select class="easyui-combogrid" name="fundsCode"
					id="fundsCode" style="width: 200px"
					data-options="
							            idField: 'fundsCode',
							            editable:false,
							            required:true,
							            loadMsg:'正在加载,请稍后...',
							            textField: 'fundsCode',
							            url: '/rest/funds/market/list?limit=30',
							            method: 'get',
							            columns: [[
							                {field:'fundsCode',title:'基金代码',width:4},
							                {field:'fundsCodeInner',title:'内部基金代码',width:4}
							            ]],
							            fitColumns: true
							        ">
				</select></td>
				<th>基金配置比例（%）：</th>
				<td><input class="easyui-numberbox" style="width: 200px"
					name="ratio"
					data-options="min:0, max:100, precision:2, required:true"></td>
			</tr>
		</table>
	</form>
</div>
