<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<div id="DivDepartPlugingAdd" class="easyui-dialog" style="width:550px;height:450px;padding:10px 20px"
			closed="true" resizable="false" modal="true" data-options="title:'查找部门',iconCls: 'icon-add',buttons: '#departft'">
		<table class="easyui-datagrid" fit='true' id="adGridDepartPlug" 
            data-options="multiSelect:true,method:'post',toolbar:'#departPlugingtb'">
	        <thead>
	            <tr>
	            	 <th data-options="field:'ck',checkbox:true"></th>
				     <th data-options="field:'id',width:100 ">id</th>
				     <th data-options="field:'dpName',width:100 ">部门名称</th>
				     <th data-options="field:'dpStatus',width:100  ,formatter:function(value){if(value==0){return '有效'}else if(value==1){return '无效'}else {return '未知'}}">部门状态</th>
	            </tr>
	        </thead>
	 	</table>
	 </div>
	 <div id="departPlugingtb" style="padding:2px 5px;">
	  	<form id="searchDepartFrm" method="post">
           	<table>
			 	<tr>
		 			<th>
	                	<label for="dpName" width='200'>部门名称:</label>
	                </th>
	                <td>
	                	<input  class="easyui-textbox" name="dpName" id="sdpName" style="width:200px" ></input>
	                </td>
         			<td><a href="javascript:searchDepart()" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-search" >查询</a></td>
         		</tr>
           	</table>
	     </form>
    </div>
    <form id="ffAddDepart" method="post">
    	<input type="hidden" name="u_id" id="setDepartUId"/>
    	<input type="hidden" name="dp_ids" id="setDpIds" />
    </form>
    <div id="departft" style="padding:2px 5px;">
        <a href="javascript:saveDepart()" class="easyui-linkbutton" iconcls="icon-ok">确认</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivDepartPlugingAdd').dialog('close')">关闭</a>
    </div>