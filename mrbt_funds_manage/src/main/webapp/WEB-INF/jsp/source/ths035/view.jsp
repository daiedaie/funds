
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="DivView" class="easyui-dialog"
	style="width: 900px; height: 500px; padding: 10px 20px" closed="true"
	resizable="true" modal="true"
	data-options="title:'查看',iconCls: 'icon-ok',
			buttons: [{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#DivView').dialog('close');
                    }
                }]">
	<form id="ffView" method="post" novalidate="novalidate">
		<input type="hidden" name=id id="vid" />
		<table id="tblView" class="view">
			<tr>
				<th><label width='200'>真实基金代码</label></th>
				<td><input class="easyui-textbox" name="f002Ths001"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>基金简称</label></th>
				<td><input class="easyui-textbox" name="f003Ths001"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>基金全称</label></th>
				<td><input class="easyui-textbox" name="f005Ths001"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>记录唯一标识</label></th>
				<td><input class="easyui-textbox" name="seq"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>记录创建日期</label></th>
				<td><input class="easyui-datebox" name="ctimeThs035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>记录通讯到用户端日期</label></th>
				<td><input class="easyui-datebox" name="rtimeThs035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>内部基金代码</label></th>
				<td><input class="easyui-textbox" name="f001Ths035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>存续期限</label></th>
				<td><input class="easyui-textbox" name="f004Ths035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>成立日期</label></th>
				<td><input class="easyui-datebox" name="f005Ths035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>基金投资类型</label></th>
				<td><input class="easyui-textbox" name="f007Ths035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>英文名称</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f021Ths035"
					readonly="readonly" style="width: 636px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>托管费率</label></th>
				<td><input class="easyui-textbox" name="f022Ths035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>管理费率</label></th>
				<td><input class="easyui-textbox" name="f023Ths035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>费率提取标准</label></th>
				<td><input class="easyui-textbox" name="f024Ths035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>到期日期</label></th>
				<td><input class="easyui-datebox" name="f025Ths035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>基金管理人ID</label></th>
				<td><input class="easyui-textbox" name="f026Ths035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>基金管理人名称</label></th>
				<td><input class="easyui-textbox" name="f027Ths035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>基金托管人ID</label></th>
				<td><input class="easyui-textbox" name="f028Ths035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>基金托管人名称</label></th>
				<td><input class="easyui-textbox" name="f029Ths035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>投资目标</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f006Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>

			<tr>
				<th><label width='200'>开始托管日期</label></th>
				<td><input class="easyui-datebox" name="f012Ths035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>托管结束日期</label></th>
				<td><input class="easyui-datebox" name="f013Ths035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>投资区域</label></th>
				<td><input class="easyui-textbox" name="f014Ths035"
					readonly="readonly" style="width: 200px"></input></td>
				<th><label width='200'>投资区域说明</label></th>
				<td><input class="easyui-textbox" name="f015Ths035"
					readonly="readonly" style="width: 200px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>市场风险提示</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f016Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>管理风险提示</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f017Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>技术风险提示</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f018Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>赎回风险提示</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f019Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>其他风险提示</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f020Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>投资范围</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f008Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>投资目标</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f006Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>投资策略</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f009Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
			<tr>
				<th><label width='200'>业绩比较基准</label></th>
				<td colspan="3"><input class="easyui-textbox" name="f010Ths035"
					readonly="readonly" data-options="multiline:true"
					style="width: 636px; height: 100px"></input></td>
			</tr>
		</table>
	</form>
</div>