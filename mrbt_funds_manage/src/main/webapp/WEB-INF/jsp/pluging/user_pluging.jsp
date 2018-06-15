<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
		<div id="DivUserPlugingAdd" class="easyui-dialog" style="width:850px;height:450px;padding:10px 20px"
			closed="true" resizable="false" modal="true" data-options="title:'查找用户',iconCls: 'icon-add',buttons: '#ft'">
	           <table class="easyui-datagrid" fit='true'
            data-options="singleSelect:true,method:'get',toolbar:'#userPlugingtb'">
			        <thead>
			            <tr>
			            	 <th data-options="field:'ck',checkbox:true"></th>
						     <th data-options="field:'id',width:100 ">id</th>
						     <th data-options="field:'loginName',width:100 ">登录名称</th>
						     <th data-options="field:'loginStatus',width:100  ,formatter:function(value){if(value==0){return '正常'}else if(value==1){return '暂停'}else if(value==2){return '删除'}else {return '未知'}}">状态</th>
						     <th data-options="field:'lastDt',width:100  ,formatter:formatDate">最后登录时间</th>
						     <th data-options="field:'lastIp',width:100 ">最后登录ip</th>
			            </tr>
			        </thead>
		    	</table>
	     </div>
	 <div id="userPlugingtb" style="padding:2px 5px;">
        Date From: <input class="easyui-datebox" style="width:110px">
        To: <input class="easyui-datebox" style="width:110px">
        Language: 
        <select class="easyui-combobox" panelHeight="auto" style="width:100px">
            <option value="java">Java</option>
            <option value="c">C</option>
            <option value="basic">Basic</option>
            <option value="perl">Perl</option>
            <option value="python">Python</option>
        </select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
    </div>
    <div id="ft" style="padding:2px 5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
    </div>