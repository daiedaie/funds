<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 上传excel的弹出层-->
<div id="DivAdd" class="easyui-dialog"
	style="width: 450px; height: 135px; padding: 10px 20px" closed="true"
	modal="true"
	data-options="title:'导入基金风险数据',iconCls: 'icon-add',
			buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        upload();
                    }
                },{
                    text:'重置',
                    iconCls:'icon-clear',
                    handler:function(){
                        $('#ffAdd').form('clear');;
                    }
                },{
                    text:'关闭',
                     iconCls:'icon-cancel',
                    handler:function(){
                        $('#DivAdd').dialog('close');
                    }
                }]">
	<form id="ffAdd" method="post" novalidate="novalidate"
		enctype="multipart/form-data">
		<input type="hidden" name="id">
		<table class="view">
			<tr>
				<th>上传excel：</th>
				<td><input class="easyui-filebox" name="excelFile" id="excelFile"
					style="width: 225px" data-options="required:true,prompt:'请上传excel文件'"></input></td>
			</tr>
		</table>
	</form>
</div>
