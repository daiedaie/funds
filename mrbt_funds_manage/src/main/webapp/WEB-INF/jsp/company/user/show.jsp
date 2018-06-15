<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工登录账户管理</title>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath()%>/easyUI/datagrid-detailview.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/company/user/show.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/company/user/properties.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/pluging/role_pluging.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/pluging/depart_pluging.js"></script>
	<style scoped="scoped">
        .tt-inner{
            display:inline-block;
            line-height:12px;
            padding-top:5px;
        }
        .tt-inner img{
            border:0;
        }
    </style>
</head>
<body>
	<div class="easyui-layout"  fit="true">
			<div class="easyui-panel" data-options="region:'north',title:'查询窗口',iconCls:'icon-book'" style="padding:5px;height:80px">
	            <form id="searchFrm">
	            	<table>
					 	<tr>
					 			<th>
		                           <label for="loginName" width='200'>登录名称：</label>
		                        </th>
		                        <td>
		                        		<input  class="easyui-textbox" name="loginName" id="sloginName" style="width:200px" onkeydown="javascript:console.log(event.keyCode);"></input>
		                        </td>
	            					<td>
	            						<a href="javascript:search()"  class="easyui-linkbutton" id="btnAddOK" iconcls="icon-search" >查询</a>
	            					</td>
	            		</tr>
	            	</table>
	            </form>
	        </div>
		<!-- 列表层 -->
		<jsp:include page="grid.jsp"></jsp:include>
		<jsp:include page="properties.jsp"></jsp:include>
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
       
       <jsp:include page="../../pluging/role_pluging.jsp"></jsp:include>
       <jsp:include page="../../pluging/depart_pluging.jsp"></jsp:include>
</body>
</html>