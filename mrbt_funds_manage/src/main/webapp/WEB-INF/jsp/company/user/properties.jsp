 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="tb_proper" data-options="region:'south',hideCollapsedContent:false" title="属性窗口" style="padding:5px;height:auto">
 	<div class="easyui-tabs" id="tab_proper" style="width:95%;height:300px" fit='true'  
 	     data-options="tabWidth:100,tabHeight:40,plain:true,
 	     onSelect:function(title,index){
 	     	refreshTab();
 	     }" >
         <div title="<span class='tt-inner'><img src='<%=request.getContextPath()%>/easyUI/themes/icons/customed/group.png'/><br>权限管理</span>" style="padding:10px">
          <table class="easyui-datagrid"  fit='true'  id="adGrid0"
		            data-options="singleSelect:true,fitColumns:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',url:'/rest/properties/user_properties/listByUid',collapsible:true,method:'post',toolbar:'#roletb'">
		        <thead>
		            <tr>
		            	<th data-options="field:'id',width:100 ">id</th>
					    <th data-options="field:'rName',width:100 ">权限名称</th>
				     	<th data-options="field:'description',width:100 ">描述信息</th>
					    <th data-options="field:'rStatus',width:100  ,formatter:function(value){if(value==0){return '有效'}else if(value==1){return '无效'}else {return '未知'}}">权限状态</th>
		            </tr>
		        </thead>
	    	</table>	
        </div>
        <div title="<span class='tt-inner'><img src='<%=request.getContextPath()%>/easyUI/themes/icons/customed/organ.png'/><br>部门管理</span>" style="padding:10px">
          	<table class="easyui-datagrid" title="查询结果" fit='true' id="adGrid1"
	            data-options="singleSelect:true,fitColumns:true,iconCls:'icon-view',loadMsg:'请稍后，正在加载数据...',idField:'id',collapsible:true,method:'post',toolbar:'#dptb',
	            onLoadSuccess:function(data){
        			$('.sz_button').switchbutton({ 
		                onText:'是',
		                offText:'否',
		                onChange:function(checked){
		                	changeSwitchButton(this,checked);
		                }
		            });
    			}">
		        <thead>
		            <tr>
					     <th data-options="field:'dpName',width:100 ">部门名称</th>
					     <th data-options="field:'dpStatus',width:100  ,formatter:function(value){if(value==0){return '有效'}else if(value==1){return '无效'}else {return '未知'}}">部门状态</th>
					     <th data-options="field:'isLeader',width:100  ,formatter:formatterIsLeader">是否为负责人</th>
		            </tr>
		        </thead>
	    	</table>
        </div>
    </div>
</div>
<div id="roletb" style="padding:2px 5px;">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" data-options="plain:true" onclick="javascript:showRole()">添加权限</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" data-options="plain:true" onclick="javascript:deleteRole()">删除权限</a>
</div>

<div id="dptb" style="padding:2px 5px;">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" data-options="plain:true" onclick="javascript:showDepart()">添加部门</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" data-options="plain:true" onclick="javascript:deleteDepart()">删除部门</a>
</div>

