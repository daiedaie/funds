<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 添加和修改的弹出层 -->
<div id="DivManage" class="easyui-dialog"
	style="width: 850px; height: 80px; padding: 10px 20px" closed="true"
	modal="true"
	data-options="title:'小知识学堂管理',iconCls: 'icon-add',
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
				<th>标题：</th>
				<td><input class="easyui-textbox" style="width: 225px"
					name="title" data-options="required:true"></td>
				<th>作者：</th>
				<td><input class="easyui-textbox" style="width: 225px"
					name="author" data-options="required:true"></td>
			</tr>
			<tr>
				<th>图片：</th>
				<td><input class="easyui-filebox" name="pictureImg"
					id="pictureImg" style="width: 225px" data-options="prompt:'请上传图片'"></input></td>
				<th>内容（pdf文件）：</th>
				<td><input class="easyui-filebox" name="textPdf" id="textPdf"
					style="width: 225px" data-options="prompt:'请上传pdf文件'"></input></td>
			</tr>
			<tr id="tr">
				<th>原图片预览：</th>
				<td><a id="picture" target="_blank" style="width: 225px"></a></td>
				<th>原pdf文件预览：</th>
				<td><a id="text" target="_blank" style="width: 225px"></a></td>
			</tr>
		</table>
	</form>
</div>
