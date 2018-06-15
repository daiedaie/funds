var toolbar = [ {
	text : '添加',
	iconCls : 'icon-add',
	handler : function() {
		add();
	}
}, '-', {
	text : '修改',
	iconCls : 'icon-edit',
	handler : function() {
		edit();
	}
}, '-', {
	text : '删除',
	iconCls : 'icon-remove',
	handler : function() {
		del();
	}
}, '-', {
	text : '查看',
	iconCls : 'icon-view',
	handler : function() {
		view();
	}
}];
//添加窗口的显示
function add(){
	$('#ffAdd').form('clear');
	$("#DivAdd").dialog('open');
}
//删除
function del(){
	var rows = $("#adGrid").datagrid("getSelected");
	if (！rows) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	showLoading();
	$.getJSON("${deleteUrl}?r=" + Math.random() + "&id=" + rows.id,
	    function(info) {
	       if(info.code==200){
	    	   $.messager.alert('提示', '删除成功', 'info');
	    	   $("#adGrid").datagrid('reload');
	       }else{
	    	   $.messager.alert('错误提示', info.msg, 'error');
	       }
	});
	closeLoading();
}
function edit(){
	var rows = $("#adGrid").datagrid("getSelected");
	if (!rows) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$("#ffEdit").form('clear');
	$("#ffEdit").form('load',rows);
	$("#DivEdit").dialog('open');
}
//保存
function save(){
	showLoading();
	$('#ffAdd').form('submit',{
		url: '${saveUrl}',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			console.log(json)
			if(json.code == 200){
				$("#DivAdd").dialog('close');
				$("#adGrid").datagrid('reload');
			}
			else
			{
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}
function update(){
	showLoading();
	$('#ffEdit').form('submit',{
		url: '${updateUrl}',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			if(json.code == 200){
				$("#DivEdit").dialog('close');
				$("#adGrid").datagrid('reload');
			}
			else
			{
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}
function view(){
	var rows = $("#adGrid").datagrid("getSelected");
	if (!rows) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$("#ffView").form('clear');
	$("#ffView").form('load',rows);
	$("#DivView").dialog('open');
}

//查询
function search(){
	$('#adGrid').datagrid('load',{
		page:1,
		<#list searchList as s>
	     ${s.name}:$("#s${s.name}").val()<#if s_has_next>,</#if>
		</#list>
	});
}
