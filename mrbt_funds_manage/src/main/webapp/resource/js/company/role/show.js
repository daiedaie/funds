
//添加窗口的显示
function add(){
	$('#ffAdd').form('clear');
	$("#DivAdd").dialog('open');
}
//删除
function del(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要删除吗？', function(r) {
		if (r) {
			$.getJSON("/rest/company/role/delete?r=" + Math.random() + "&id=" + rows[0].id,
				    function(info) {
				       if(info.code==200){
				    	   $.messager.alert('提示', '删除成功', 'info');
				    	   $("#adGrid").datagrid('reload');
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
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$("#ffEdit").form('clear');
	$("#ffEdit").form('load',rows[0]);
	$("#DivEdit").dialog('open');
}
//保存
function save(){
	showLoading();
	$('#ffAdd').form('submit',{
		url: '/rest/company/role/save',
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
		url: '/rest/company/role/update',
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
	var rows = $("#adGrid").datagrid("getSelections");
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
	$('#adGrid').datagrid('load',{
		page:1,
	     rName:$("#srName").val()
	});
}
