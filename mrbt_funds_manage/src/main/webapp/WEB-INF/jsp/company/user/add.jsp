 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <div id="DivAdd" class="easyui-dialog" style="width:850px;height:150px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="title:'新增',iconCls: 'icon-add',buttons: '#dlg-buttons'">
			 <form id="ffAdd" method="post" novalidate="novalidate">
			 	<table id="tblAdd" class="view">
			 						 	<tr>
				 			<th>
	                           <label for="loginName" width='200'>登录名称</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="loginName" id="aloginName" style="width:200px" data-options="required:true"></input>
	                        </td>
				 			<th>
	                           <label for="loginStatus" width='200'>状态</label>
	                        </th>
	                        <td >
	                        		<select class="easyui-combobox" name="loginStatus" id="aloginStatus" style="width: 200px" data-options="required:true">
	                        				<option value="0">正常</option>
	                        				<option value="1">暂停</option>
	                        				<option value="2">删除</option>
	                        		</select>
	                        </td>
			 		</tr>
			 		<tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-ok" onclick="save()">确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivAdd').dialog('close')">关闭</a>
                        </td>
                    </tr>
			 	</table>
			 </form>
		</div>