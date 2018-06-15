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
}, '-', {
	text : '删除',
	iconCls : 'icon-remove',
	handler : function() {
		del();
	}
}, '-', {
	text : '是否在线',
	iconCls : 'icon-ok',
	handler : function() {
		online();
	}
}];

function formatTimer(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
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

function removeKindEditor(){
	KindEditor.remove('#vofflineNotes');
}
function buildKindEditor(){
	var editor_1 = createKindEditor('#vofflineNotes').html('');
}

// 查询
function search(){
	$('#adGrid').datagrid('load',{
		page:1,
		title:$("#stitle").val()
	});
}

function createKindEditor(contentId) {
	var editor = KindEditor.create(contentId, {
		allowFileManager : false,
		newlineTag : "br",
		uploadJson : '/rest/info/uploadImg',
		afterChange : function() {
			this.sync();
		},
		afterBlur : function() {
			this.sync();
		}
	});
	return editor;
}

function add() {
	$("#DivManage").css("height","75px");
	$("#tr").hide();
	$('#pictureImg').filebox({
		prompt:'请上传图片',
		required:true 
	});
	$('#textPdf').filebox({ 
		prompt:'请上传pdf文件',
		required:true 
	});
	$('#ffManage').form('clear');
	$("#DivManage").dialog('open');
}
function edit() {
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	}
	$("#DivManage").css("height","110px");
	$("#tr").show();
	$('#pictureImg').filebox({ 
		required:false,
		prompt:'默认不修改图片'
	});
	$('#textPdf').filebox({ 
		required:false,
		prompt:'默认不修改文件'
	});
	$("#picture").attr("href", rows[0].picture);
	$("#picture").text(rows[0].title);
	$("#text").attr("href", rows[0].text);
	$("#text").text(rows[0].title);
	$("#ffManage").form('clear');
	$("#ffManage").form('load', rows[0]);
	$("#DivManage").dialog('open');
}
function saveAndUpdate() {
	showLoading();
	$('#ffManage').form('submit', {
		url : '/rest/funds/knowledge/saveAndUpdate',
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
	if (rows[0].onLine == 1) {
		$.messager.alert("提示", "已上线小知识不可删除！", "error");
		return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要删除该项吗？', function(r) {
		if (r) {
			$.getJSON("/rest/funds/knowledge/del?r=" + Math.random() + "&id=" + rows[0].id, function(info) {
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
			$.getJSON("/rest/funds/knowledge/online?r=" + Math.random() + "&id=" + rows[0].id + "&online=" + rows[0].onLine, function(info) {
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
