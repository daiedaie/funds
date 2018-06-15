 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <div id="DivAdd" class="easyui-dialog" style="width:850px;height:180px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="title:'添加',iconCls: 'icon-add',buttons: '#dlg-buttons'">
			 <form id="ffAdd" method="post" novalidate="novalidate">
			 	<table id="tblAdd" class="view">
				 	<tr>
				 			<th>
	                           <label for="rName" width='200'>权限名称</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="rName" id="arName" style="width:200px" data-options="required:true"></input>
	                        </td>
				 			<th>
	                           <label for="description" width='200'>描述信息</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="description" id="adescription" style="width:200px" data-options="required:true"></input>
	                        </td>
			 		</tr>
				 	<tr>
				 			<th>
	                           <label for="rStatus" width='200'>角色状态</label>
	                        </th>
	                        <td colspan="3">
	                        		<select class="easyui-combobox" name="rStatus" id="arStatus" style="width: 200px" data-options="required:true">
	                        				<option value="0">有效</option>
	                        				<option value="1">无效</option>
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