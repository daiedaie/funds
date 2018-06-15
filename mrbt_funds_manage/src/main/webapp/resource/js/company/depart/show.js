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
}];
//添加窗口的显示
function add(){
	var row = $("#adGrid").treegrid("getSelected");
	if (!row) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$('#ffAdd').form('clear');
	$('#aparentId').val(row.id);
	$('#aparentDpName').textbox('setValue',row.dpName);
	$("#DivAdd").dialog('open');
}
//删除
function del(){
	var row = $("#adGrid").treegrid("getSelected");
	if (!row) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	if(!row.parentId){
		$.messager.alert("提示", "不能删除总部", "error");
	    return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要删除吗？', function(r) {
		if (r) {
			$.getJSON("/rest/company/depart/delete?r=" + Math.random() + "&id=" + row.id,
				    function(info) {
				       if(info.code==200){
				    	   $.messager.alert('提示', '删除成功', 'info');
				    	   $("#adGrid").treegrid('reload');
				       }else{
				    	   $.messager.alert('错误提示', info.msg, 'error');
				       }
				});
		} else {
			return
		}
	});
	closeLoading();
}
function edit(){
	var rows = $("#adGrid").treegrid("getSelected");
	if (!rows) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	if(!rows.parentId){
		$.messager.alert("提示", "不能修改总部", "error");
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
		url: '/rest/company/depart/save',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			if(json.code == 200){
				$("#DivAdd").dialog('close');
				$("#adGrid").treegrid('reload');
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
		url: '/rest/company/depart/update',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			if(json.code == 200){
				$("#DivEdit").dialog('close');
				$("#adGrid").treegrid('reload');
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
	var rows = $("#adGrid").treegrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$("#ffView").form('clear');
	$("#ffView").form('load',rows[0]);
	$("#DivView").dialog('open');
}

//查询
function search(){
	$('#adGrid').treegrid('load',{
		page:1,
	    dpName:$("#sdpName").val()
	});
}
