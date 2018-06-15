<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<div id="DivRolePlugingAdd" class="easyui-dialog" style="width:550px;height:450px;padding:10px 20px"
			closed="true" resizable="false" modal="true" data-options="title:'查找权限',iconCls: 'icon-add',buttons: '#ft'">
		<table class="easyui-datagrid" fit='true' id="adGridRolePlug" 
            data-options="multiSelect:true,method:'post',url:'/rest/pluging/role_pluging/listByCheckUid',toolbar:'#rolePlugingtb'">
	        <thead>
	            <tr>
	            	 <th data-options="field:'ck',checkbox:true"></th>
				     <th data-options="field:'id',width:100 ">id</th>
				     <th data-options="field:'rName',width:100 ">权限名称</th>
				     <th data-options="field:'description',width:100 ">描述信息</th>
				     <th data-options="field:'rStatus',width:100  ,formatter:function(value){if(value==0){return '有效'}else if(value==1){return '无效'}else {return '未知'}}">角色状态</th>
	            </tr>
	        </thead>
	 	</table>
	 </div>
	 <div id="rolePlugingtb" style="padding:2px 5px;">
	  	<form id="searchRoleFrm" method="post">
           	<table>
			 	<tr>
		 			<th>
	                	<label for="loginName" width='200'>权限名称:</label>
	                </th>
	                <td>
	                	<input  class="easyui-textbox" name="rName" id="srName" style="width:200px" ></input>
	                </td>
         			<td><a href="javascript:searchRole()" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-search" >查询</a></td>
         		</tr>
           	</table>
	     </form>
    </div>
    <form id="ffAddRole" method="post">
    	<input type="hidden" name="u_id" id="setUId"/>
    	<input type="hidden" name="r_ids" id="setRIds" />
    </form>
    <div id="ft" style="padding:2px 5px;">
        <a href="javascript:saveRole()" class="easyui-linkbutton" iconcls="icon-ok">确认</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivRolePlugingAdd').dialog('close')">关闭</a>
    </div>