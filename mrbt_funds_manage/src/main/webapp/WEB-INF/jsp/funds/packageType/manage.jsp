<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 添加和修改的弹出层 -->
<div id="DivManage" class="easyui-dialog"
	style="width: 800px; height: 260px; padding: 10px 20px" closed="true"
	modal="true"
	data-options="title:'基金组合类型管理',iconCls: 'icon-add',
			buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        saveAndUpdate();
                    }
                },{
                    text:'重置',
                    iconCls:'icon-clear',
                    handler:function(){
                        $('#ffManage').form('clear');;
                    }
                },{
                    text:'关闭',
                     iconCls:'icon-cancel',
                    handler:function(){
                        $('#DivManage').dialog('close');
                    }
                }]">
	<form id="ffManage" method="post" novalidate="novalidate"
		enctype="multipart/form-data">
		<input type="hidden" name="id">
		<table class="view">
			<tr>
				<th>组合名称：</th>
				<td><input class="easyui-textbox" style="width: 200px"
					name="name" data-options="required:true"></td>
			</tr>
			<tr>
				<th>组合特性：</th>
				<td><input class="easyui-textbox" style="width: 600px"
					name="feature" data-options="required:true"></td>
			</tr>
			<tr>
				<th>配置点评：</th>
				<td><input class="easyui-textbox"
					style="width: 600px; height: 80px;" name="confAnalyze"
					data-options="multiline:true,required:true"></td>
			</tr>
		</table>
	</form>
</div>
