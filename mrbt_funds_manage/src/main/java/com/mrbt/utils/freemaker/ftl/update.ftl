 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 <div id="DivEdit" class="easyui-dialog" style="width:850px;height:${hight}px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="title:'修改',iconCls: 'icon-add',buttons: '#dlg-buttons'">
			 <form id="ffEdit" method="post" novalidate="novalidate">
			 	<table id="tblEdit" class="view">
			 		<#assign index=0>
			 		<#list updateList as column>
			 			<#--判断是否为hidden或者是id属性，则设置为hidden -->
				 		<#if column.type?upper_case=="hidden" || column.id>
				 			<input type="hidden" name=${column.name} id="e${column.name}" />
				 		<#else>
				 		<#--没行显示两条数据，如果是%2==0，则结束tr，并添加新的tr -->
				 			<#if index %2==0 >
				 	<tr>
				 			</#if>
				 			<#assign index=index+1>
				 			<th>
	                           <label for="${column.name}" width='${column.labelWidth}'>${column.label}</label>
	                        </th>
	                        <#--如果%2！=0，是第行中的第一列数据，并且没有吓一跳数据的时候，colspan=3 -->
	                        <td <#if index % 2!=0 && !column_has_next>colspan="3"</#if>>
	                        	<#--string类型 -->
	                        	<#if column.type?upper_case=="STRING">
	                        		<input  class="easyui-textbox" name="${column.name}" id="e${column.name}" style="width:200px" data-options="required:true"></input>
	                        	<#elseif column.type?upper_case=="DATE">
	                        		<input  class="easyui-datebox" name="${column.name}" id="e${column.name}" style="width:200px" data-options="required:true"></input>
	                        	<#elseif column.type?upper_case=="INT">
	                        		<input  class="easyui-numberbox" name="${column.name}" id="e${column.name}" style="width:200px" data-options="required:true"></input>
	                        	<#elseif column.type?upper_case=="TIME">
	                        		<input  class="easyui-datetimebox" name="${column.name}" id="e${column.name}" style="width:200px" data-options="required:true"></input>
	                        	<#elseif column.type?upper_case=="COMBOBOX">
	                        		<select class="easyui-combobox" name="${column.name}" id="e${column.name}" style="width: 200px" data-options="required:true">
	                        			<#list comboMap[column.comboName] as typeCombo>
	                        				<option value="${typeCombo.option}">${typeCombo.text}</option>
	                        			</#list>
	                        		</select>
	                        	</#if>
	                        </td>
	                        <#if index %2==0 ||!column_has_next>
			 		</tr>
	                        </#if>
				 		</#if>
					</#list>
			 		<tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-ok" onclick="update()">确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEdit').dialog('close')">关闭</a>
                        </td>
                    </tr>
			 	</table>
			 </form>
		</div>