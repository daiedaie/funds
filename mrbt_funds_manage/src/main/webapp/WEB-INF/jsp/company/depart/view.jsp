 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <div id="DivView" class="easyui-dialog" style="width:850px;height:180px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="title:'查看',iconCls: 'icon-add',buttons: '#dlg-buttons'">
			 <form id="ffView" method="post" novalidate="novalidate">
			 	<table id="tblView" class="view">
				 			<input type="hidden" name=id id="vid" />
				 	<tr>
				 			<th>
	                           <label for="dpName" width='200'>部门名称</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="dpName" id="vdpName" readonly="readonly" style="width:200px" ></input>
	                        </td>
				 			<th>
	                           <label for="dpStatus" width='200'>部门状态</label>
	                        </th>
	                        <td >
	                        		<select class="easyui-combobox" name="dpStatus" readonly="readonly" id="vdpStatus" style="width: 200px" readonly="readonly" >
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