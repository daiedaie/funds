<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	        <div id="tb"  data-options="region:'center'" style="padding:5px;height:auto">       
	            <table class="easyui-treegrid" title="查询结果" fit='true' id="adGrid"
		            data-options="idField: 'id',treeField: 'dpName',singleSelect:true,toolbar:toolbar,fitColumns:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,url:'/rest/company/depart/list',method:'post'">
			        <thead>
			            <tr>
						     <th data-options="field:'dpName',width:100 ">部门名称</th>
						     <th data-options="field:'dpStatus',width:100  ,formatter:function(value){if(value==0){return '有效'}else if(value==1){return '无效'}else {return '未知'}}">部门状态</th>
			            </tr>
			        </thead>
		    	</table>
	        </div>
