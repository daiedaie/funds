<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath()%>/${resourcePath}show.js"></script>
</head>
<body>
	<div class="easyui-layout"  fit="true">
		<#if (searchList?size > 0)>
			<div class="easyui-panel" data-options="region:'north',title:'查询窗口',iconCls:'icon-book'" style="padding:5px;height:${height}px">
	            <form id="searchFrm">
	            	<table>
	            		<#assign index=0>
				 		<#list searchList as column>
				 			
					 		<#--没行显示两条数据，如果是%3==0，则结束tr，并添加新的tr -->
					 			<#if index %3==0 >
					 	<tr>
					 			</#if>
					 			<#assign index=index+1>
					 			<th>
		                           <label for="${column.name}" width='${column.labelWidth}'>${column.label}：</label>
		                        </th>
		                        <td>
		                        	<#--string类型 -->
		                        	<#if column.type?upper_case=="STRING">
		                        		<input  class="easyui-textbox" name="${column.name}" id="s${column.name}" style="width:200px" ></input>
		                        	<#elseif column.type?upper_case=="DATE">
		                        		<input  class="easyui-datebox" name="${column.name}" id="s${column.name}" style="width:200px"></input>
		                        	<#elseif column.type?upper_case=="INT">
		                        		<input  class="easyui-numberbox" name="${column.name}" id="s${column.name}" style="width:200px" ></input>
		                        	<#elseif column.type?upper_case=="TIME">
		                        		<input  class="easyui-datetimebox" name="${column.name}" id="s${column.name}" style="width:200px" ></input>
		                        	<#elseif column.type?upper_case=="COMBOBOX">
		                        		<select class="easyui-combobox" name="${column.name}" id="s${column.name}" style="width: 200px" >
		                        			<#list comboMap[column.comboName] as typeCombo>
		                        				<option value="${typeCombo.option}">${typeCombo.text}</option>
		                        			</#list>
		                        		</select>
		                        	</#if>
		                        </td>
		                        <#if index %3==0 && column_has_next>
		                        	</tr>
		                        <#elseif index %3==0 && !column_has_next>
				 		</tr>
				 		<tr>
	            			<td colspan="6"><a href="javascript:search()" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-search" >查询</a></td>
	            		</tr>
	            				<#elseif (index%3>0) && !column_has_next>
	            					<td><a href="javascript:search()" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-search" >查询</a></td>
	            					</tr>
		                        </#if>
						</#list>
	            		
	            	</table>
	            </form>
	        </div>
		</#if>
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
</body>
</html>