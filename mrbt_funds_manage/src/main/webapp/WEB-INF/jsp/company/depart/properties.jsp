 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="tb_proper" data-options="region:'south',hideCollapsedContent:false" title="属性窗口" style="padding:5px;height:auto">
 	<div class="easyui-tabs" style="width:95%;height:300px" fit='true'  data-options="tabWidth:100,tabHeight:40,plain:true" >
         <div title="<span class='tt-inner'><img src='<%=request.getContextPath()%>/easyUI/themes/icons/customed/user.png'/><br>用户管理</span>" style="padding:10px">
          <table class="easyui-datagrid"  fit='true'  id="adUserGrid"
		            data-options="singleSelect:true,fitColumns:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,method:'post',toolbar:'#utb'">
		        <thead>
		            <tr>
					     <th data-options="field:'dpName',width:100 ">部门名称</th>
					     <th data-options="field:'dpStatus',width:100  ,formatter:function(value){if(value==0){return '有效'}else if(value==1){return '无效'}else {return '未知'}}">部门状态</th>
		            </tr>
		        </thead>
	    	</table>	
        </div>
    </div>
</div>
<div id="utb" style="padding:2px 5px;">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" data-options="plain:true" onclick="javascript:searchUser()">添加用户</a>
 </div>