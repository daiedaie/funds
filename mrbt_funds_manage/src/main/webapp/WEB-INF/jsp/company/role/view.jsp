 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <div id="DivView" class="easyui-dialog" style="width:850px;height:180px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="title:'查看',iconCls: 'icon-add',buttons: '#dlg-buttons'">
			 <form id="ffView" method="post" novalidate="novalidate">
			 	<table id="tblView" class="view">
				 			<input type="hidden" name=id id="vid" />
				 	<tr>
				 			<th>
	                           <label for="rName" width='200'>权限名称</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="rName" id="vrName" readonly="readonly" style="width:200px" ></input>
	                        </td>
				 			<th>
	                           <label for="description" width='200'>描述信息</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="description" id="vdescription" readonly="readonly" style="width:200px" ></input>
	                        </td>
			 		</tr>
				 	<tr>
				 			<th>
	                           <label for="rStatus" width='200'>角色状态</label>
	                        </th>
	                        <td colspan="3">
	                        		<select class="easyui-combobox" name="rStatus" readonly="readonly" id="vrStatus" style="width: 200px" readonly="readonly" >
	                        				<option value="0">有效</option>
	                        				<option value="1">无效</option>
	                        		</select>
	                        </td>
			 		</tr>
			 		<tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivView').dialog('close')">关闭</a>
                        </td>
                    </tr>
			 	</table>
			 </form>
		</div>