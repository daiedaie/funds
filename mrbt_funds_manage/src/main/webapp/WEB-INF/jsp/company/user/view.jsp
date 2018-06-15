 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <div id="DivView" class="easyui-dialog"  style="width:850px;height:180px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="title:'查看',iconCls: 'icon-add',buttons: '#dlg-buttons'">
			 <form id="ffView" method="post" novalidate="novalidate">
			 	<table id="tblView" class="view">
			 						 			<input type="hidden" name=id id="vid" />
				 	<tr>
				 			<th>
	                           <label for="loginName" width='200'>登录名称</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="loginName" id="vloginName" readonly="readonly" style="width:200px" ></input>
	                        </td>
				 			<th>
	                           <label for="loginStatus" width='200'>状态</label>
	                        </th>
	                        <td >
	                        		<select class="easyui-combobox" name="loginStatus" readonly="readonly" id="vloginStatus" style="width: 200px" readonly="readonly" >
	                        				<option value="0">正常</option>
	                        				<option value="1">暂停</option>
	                        				<option value="2">删除</option>
	                        		</select>
	                        </td>
			 		</tr>
				 	<tr>
				 			<th>
	                           <label for="lastDt" width='200'>最后登录时间</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-datebox" name="lastDt" id="vlastDt" readonly="readonly" style="width:200px" ></input>
	                        </td>
				 			<th>
	                           <label for="lastIp" width='200'>最后登录ip</label>
	                        </th>
	                        <td >
	                        		<input  class="easyui-textbox" name="lastIp" id="vlastIp" readonly="readonly" style="width:200px" ></input>
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