<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	        <div id="tb"  data-options="region:'center'" style="padding:5px;height:auto">       
	            <table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid"
		            data-options="singleSelect:true,toolbar:toolbar,fitColumns:true,pagination:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,url:'${listUrl}',method:'post'">
			        <thead>
			            <tr>
			            	<#list grid as s>
						     ${s}
							</#list>
			            </tr>
			        </thead>
		    	</table>
	        </div>
