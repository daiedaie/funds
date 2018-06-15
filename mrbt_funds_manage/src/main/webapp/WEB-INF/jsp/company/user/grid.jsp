<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	        <div id="tb"  data-options="region:'center'" style="padding:5px;height:auto">       
	            <table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid"
		            data-options="singleSelect:true,toolbar:toolbar,fitColumns:true,pagination:true,url:'/rest/company/user/list',iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,method:'post'
		            ,onSelect:function(index,row){
		            	refreshTab();
		            }">
			        <thead>
			            <tr>
						     <th data-options="field:'id',width:100 ">id</th>
						     <th data-options="field:'loginName',width:100 ">登录名称</th>
						     <th data-options="field:'loginStatus',width:100  ,formatter:function(value){if(value==0){return '正常'}else if(value==1){return '暂停'}else if(value==2){return '删除'}else {return '未知'}}">状态</th>
						     <th data-options="field:'lastDt',width:100  ,formatter:formatDate">最后登录时间</th>
						     <th data-options="field:'lastIp',width:100 ">最后登录ip</th>
			            </tr>
			        </thead>
		    	</table>
	        </div>
		