 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <div id="DivEdit" class="easyui-dialog" style="width:850px;height:180px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="title:'修改',iconCls: 'icon-add',buttons: '#dlg-buttons'">
			 <form id="ffEdit" method="post" novalidate="novalidate">
			 	<table id="tblEdit" class="view">
				 			<input type="hidden" name=id id="eid" />
				 	<tr>
				 			<th>
	                           <label for="dpName" width='200'>部门名称</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="dpName" id="edpName" style="width:200px" data-options="required:true"></input>
	                        </td>
				 			<th>
	                           <label for="dpStatus" width='200'>部门状态</label>
	                        </th>
	                        <td >
	                        		<select class="easyui-combobox" name="dpStatus" id="edpStatus" style="width: 200px" data-options="required:true">
	                        				<option value="0">有效</option>
	                        				<option value="1">无效</option>
	                        		</select>
	                        </td>
			 		</tr>
			 		<tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-ok" onclick="update()">确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEdit').dialog('close')">关闭</a>
                        </td>
                    </tr>
			 	</table>
			 </form>
		</div>