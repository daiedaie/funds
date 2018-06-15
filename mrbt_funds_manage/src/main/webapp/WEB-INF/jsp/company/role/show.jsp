<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限管理</title>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/company/role/show.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/company/role/other.js"></script>
</head>
<body>
	<div class="easyui-layout"  fit="true">
			<div class="easyui-panel" data-options="region:'north',title:'查询窗口',iconCls:'icon-book'" style="padding:5px;height:80px">
	            <form id="searchFrm">
	            	<table>
				 			
					 	<tr>
					 			<th>
		                           <label for="rName" width='200'>权限名称：</label>
		                        </th>
		                        <td>
		                        		<input  class="easyui-textbox" name="rName" id="srName" style="width:200px" ></input>
		                        </td>
	            					<td><a href="javascript:search()" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-search" >查询</a></td>
	            					</tr>
	            		
	            	</table>
	            </form>
	        </div>
		<!-- 列表层 -->
		<jsp:include page="grid.jsp"></jsp:include>
	</div>
		<!-- 添加的弹出层 -->
		<jsp:include page="add.jsp"></jsp:include>
		<!-- 添加的弹出层     end-->
		<!-- 编辑的弹出层 -->
		<jsp:include page="update.jsp"></jsp:include>
       <!-- 编辑的弹出层    end-->
       <!-- 显示的弹出层 -->
	   <jsp:include page="view.jsp"></jsp:include>
       <!-- 编辑的弹出层    end-->
        <!-- 分配权限 -->
       <jsp:include page="other.jsp"></jsp:include>
        <!-- 分配权限 	end-->
       <div id='toolbar' style="padding:2px 5px;">
	       <table cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
					</td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					<td>
						<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
					</td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					<td>
						<a href="javascript:del()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
					</td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					<td>
						<a href="javascript:view()" class="easyui-linkbutton" iconCls="icon-view" plain="true">查看</a>
					</td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					<td>
						<a href="javascript:allocation()" class="easyui-linkbutton" iconCls="icon-setting" plain="true">分配权限</a>
					</td>
				</tr>
			</table>
       </div>
</body>
</html>