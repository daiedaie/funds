var toolbar = [ {
	text : '查看',
	iconCls : 'icon-view',
	handler : function() {
		view();
	}
}];

function formatTimer(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return "";
}
function removeKindEditor(){
	KindEditor.remove('#vofflineNotes');
}
function buildKindEditor(){
	var editor_1 = createKindEditor('#vofflineNotes').html('');
}
function view(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	removeKindEditor();
	$("#ffView").form('clear');
	createKindEditor('#vofflineNotes').html(rows[0].offlineNotes);
	if (rows[0].createTime) {
		rows[0].createTime = formatTimer(new Date(rows[0].createTime));
	}
	$("#ffView").form('load',rows[0]);
	$("#DivView").dialog('open');
}

// 查询
function search(){
	$('#adGrid').datagrid('load',{
		page:1,
		fundsCode:$("#sfundsCode").val()
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
