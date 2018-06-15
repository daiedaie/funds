<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	        <div id="tb"  data-options="region:'center'" style="padding:5px;height:auto">       
	            <table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid"
		            data-options="singleSelect:true,toolbar:'#toolbar',fitColumns:true,pagination:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,url:'/rest/company/role/list',method:'post'">
			        <thead>
			            <tr>
						     <th data-options="field:'id',width:100 ">id</th>
						     <th data-options="field:'rName',width:100 ">权限名称</th>
						     <th data-options="field:'description',width:100 ">描述信息</th>
						     <th data-options="field:'rStatus',width:100  ,formatter:function(value){if(value==0){return '有效'}else if(value==1){return '无效'}else {return '未知'}}">角色状态</th>
			            </tr>
			        </thead>
		    	</table>
	        </div>
