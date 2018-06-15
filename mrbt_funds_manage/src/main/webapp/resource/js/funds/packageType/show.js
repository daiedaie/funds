var toolbar = [{
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
}/*, '-', {
	text : '删除',
	iconCls : 'icon-remove',
	handler : function() {
		del();
	}
}*/, '-', {
	text : '查看',
	iconCls : 'icon-search',
	handler : function() {
		view();
	}
}, '-', {
	text : '是否在线',
	iconCls : 'icon-ok',
	handler : function() {
		online();
	}
}, '-', {
	text : '设置组合基金',
	iconCls : 'icon-setting',
	handler : function() {
		setting();
	}
}, '-', {
	text : '生成组合基金曲线',
	iconCls : 'icon-curve',
	handler : function() {
		generateCurve();
	}
}];
//生成组合基金曲线
function generateCurve(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	if (rows[0].onLine == 0) {
		$.messager.alert("提示", "上线后才可以生成组合基金曲线 ", "error");
		return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要生成组合基金曲线吗？', function(r) {
		if (r) {
			$.getJSON("/rest/funds/packageType/generateCurve?r=" + Math.random() + "&id=" + rows[0].id, function(info) {
				if (info.code == 200) {
					$.messager.alert('提示', '生成成功', 'info');
					$("#adGrid").datagrid('reload');
				} else {
					$.messager.alert('错误提示', info.msg, 'error');
				}
			});
		} else {
			return
		}
	});
	closeLoading();
}
function formatTimers(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd");
	}
	return "";
}
function formatOnline(value,row){
	if(value == 0){
		return "否";
	}else if(value == 1){
		return "<font color='green'>是</font>";
	}
}
// 查询
function search(){
	$('#adGrid').datagrid('load',{
		page:1,
		name:$("#sname").val()
	});
}

function add() {
	$('#ffManage').form('clear');
	$("#DivManage").dialog('open');
}
function edit() {
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	$("#ffManage").form('clear');
	$("#ffManage").form('load', rows[0]);
	$("#DivManage").dialog('open');
}
function saveAndUpdate() {
	showLoading();
	$('#ffManage').form('submit', {
		url : '/rest/funds/packageType/saveAndUpdate',
		ajax : true,
		success : function(data) {
			var json = JSON.parse(data);
			if (json.code == 200) {
				$("#DivManage").dialog('close');
				$("#adGrid").datagrid('reload');
			} else {
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}

// 删除
function del() {
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要删除该项吗？', function(r) {
		if (r) {
			$.getJSON("/rest/funds/packageType/del?r=" + Math.random() + "&id=" + rows[0].id, function(info) {
				if (info.code == 200) {
					$.messager.alert('提示', '删除成功', 'info');
					$("#adGrid").datagrid('reload');
				} else {
					$.messager.alert('错误提示', info.msg, 'error');
				}
			});
		} else {
			return
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
	if (rows[0].createTime) {
		rows[0].createTime = formatTimers(new Date(rows[0].createTime));
	}
	$("#ffView").form('load',rows[0]);
	$("#DivView").dialog('open');
}
//是否在线
function online() {
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要修改该项吗？', function(r) {
		if (r) {
			$.getJSON("/rest/funds/packageType/online?r=" + Math.random() + "&id=" + rows[0].id + "&online=" + rows[0].onLine, function(info) {
				if (info.code == 200) {
					$.messager.alert('提示', '修改成功', 'info');
					$("#adGrid").datagrid('reload');
				} else {
					$.messager.alert('错误提示', info.msg, 'error');
				}
			});
		} else {
			return
		}
	});
	closeLoading();
}
var toolbars = [{
	text : '添加',
	iconCls : 'icon-add',
	handler : function() {
		adds();
	}
}, '-', {
	text : '修改',
	iconCls : 'icon-edit',
	handler : function() {
		edits();
	}
}, '-', {
	text : '删除',
	iconCls : 'icon-remove',
	handler : function() {
		dels();
	}
}];


function setting(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	$('#DivSetting').dialog({ 
		title:'设置组合基金'+'('+rows[0].name+')'
	});
	$("#settingGrid").datagrid({ url: '/rest/funds/packageRatio/list?typeId='+rows[0].id });
	$("#DivSetting").dialog('open');
}
function adds() {
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	if (rows.length >= 5) {
		$.messager.alert("提示", "基金组合至多有5个基金", "error");
		return
	}
	$('#ffPackage').form('clear');
	$("#ffPackage").form('load', {
		typeId: rows[0].id,
		typeName: rows[0].name
    });
	$('#typeId, #typeName').textbox({ 
		editable:false 
	});
	$('#DivPackage').dialog({ 
		title:'添加基金组合'+'('+rows[0].name+')'
	});
	$("#DivPackage").dialog('open');
}
function edits() {
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	var rowss = $("#settingGrid").datagrid("getSelections");
	if (rowss.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	$('#ffPackage').form('clear');
	$("#ffPackage").form('load', rowss[0]);
	$('#typeId, #typeName').textbox({ 
		editable:false 
	});
	$('#DivPackage').dialog({ 
		title:'修改基金组合'+'('+rows[0].name+')'
	});
	$("#DivPackage").dialog('open');
}
function saveAndUpdatePackage() {
	showLoading();
	$('#ffPackage').form('submit', {
		url : '/rest/funds/packageRatio/saveAndUpdate',
		ajax : true,
		success : function(data) {
			var json = JSON.parse(data);
			if (json.code == 200) {
				$("#DivPackage").dialog('close');
				$("#settingGrid").datagrid('reload');
			} else {
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}

//删除
function dels() {
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	var rowss = $("#settingGrid").datagrid("getSelections");
	if (rowss.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要删除该项吗？', function(r) {
		if (r) {
			$.getJSON("/rest/funds/packageRatio/del?r=" + Math.random() + "&id=" + rowss[0].id, function(info) {
				if (info.code == 200) {
					$.messager.alert('提示', '删除成功', 'info');
					$("#settingGrid").datagrid('reload');
				} else {
					$.messager.alert('错误提示', info.msg, 'error');
				}
			});
		} else {
			return
		}
	});
	closeLoading();
}

