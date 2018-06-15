var toolbar = [ {
	text : '设置为推荐基金',
	iconCls : 'icon-add',
	handler : function() {
		add();
	}
}, '-', {
	text : '从推荐基金下线',
	iconCls : 'icon-remove',
	handler : function() {
		del();
	}
}, '-', {
	text : '设置推荐排序',
	iconCls : 'icon-view',
	handler : function() {
		order();
	}
}, '-', {
	text : '从基金超市下线',
	iconCls : 'icon-cancel',
	handler : function() {
		offline();
	}
}, '-', {
	text : '设置推荐理由',
	iconCls : 'icon-ok',
	handler : function() {
		reason();
	}
}, '-', {
	text : '设置基金主题',
	iconCls : 'icon-key',
	handler : function() {
		theme();
	}
}];

function formatTimer(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return "";
}

function formatRecomm(value,row){
	if(value == 0){
		return "否";
	}else if(value == 1){
		return "<font color='green'>是</font>";
	}
}

function removeKindEditor(){
	KindEditor.remove('#vofflineNotes');
	KindEditor.remove('#rrecommReason');
}
function buildKindEditor(){
	var editor_1 = createKindEditor('#vofflineNotes').html('');
}
// 设置为推荐基金
function add(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	if (rows[0].isRecomm == 1) {
	    $.messager.alert("提示", "该基金已设置为推荐基金，请勿重复设置！", "error");
	    return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要设置吗？', function(r) {
		if (r) {
			$.getJSON("/rest/funds/market/setToFundsRecomm?r=" + Math.random() + "&fundsCode=" + rows[0].fundsCode,
				    function(info) {
				       if(info.code==200){
				    	   $.messager.alert('提示', '设置成功！', 'info');
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

// 从推荐基金下线
function del(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	if (rows[0].isRecomm == 0) {
	    $.messager.alert("提示", "该基金已从推荐基金下线，请勿重复设置！", "error");
	    return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要设置吗？', function(r) {
		if (r) {
			$.getJSON("/rest/funds/market/offlineFromFundsRecomm?r=" + Math.random() + "&fundsCode=" + rows[0].fundsCode,
				    function(info) {
				       if(info.code==200){
				    	   $.messager.alert('提示', '设置成功！', 'info');
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

// 推荐排序弹框
function order(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	if (rows[0].isRecomm == 0) {
	    $.messager.alert("提示", "只有推荐基金才可设置推荐排序！", "error");
	    return
	}
	$("#ffOrder").form('clear');
	if (rows[0].createTime) {
		rows[0].createTime = formatTimer(new Date(rows[0].createTime));
	}
	$("#ffOrder").form('load',rows[0]);
	$("#DivOrder").dialog('open');
}

// 设置推荐排序
function setRecommOrder(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	if (rows[0].isRecomm == 0) {
	    $.messager.alert("提示", "只有推荐基金才可设置推荐排序！", "error");
	    return
	}
	showLoading();
	$('#ffOrder').form('submit',{
		url: '/rest/funds/market/setRecommOrder',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			console.log(json)
			if(json.code == 200){
				$("#DivOrder").dialog('close');
				$.messager.alert('提示', '设置成功！', 'info');
				$("#adGrid").datagrid('reload');
			}else{
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}

// 从基金超市下线
function offline(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	removeKindEditor();
	$("#ffOffline").form('clear');
	createKindEditor('#vofflineNotes').html('');
	$("#ffOffline").form('load',rows[0]);
	$("#DivOffline").dialog('open');
}
// 从基金超市下线
function offlineFromFundsMarket(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	var content = $("#vofflineNotes").val();
	if (content == '') {
	    $.messager.alert("提示", "请输入下线备注", "error");
	    return
	}
	showLoading();
	$('#ffOffline').form('submit',{
		url: '/rest/funds/market/offlineFromFundsMarket',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			console.log(json)
			if(json.code == 200){
				$("#DivOffline").dialog('close');
				$.messager.alert('提示', '设置成功！', 'info');
				$("#adGrid").datagrid('reload');
			}else{
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}

// 推荐理由
function reason(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	removeKindEditor();
	$("#ffReason").form('clear');
	var editor_1 = createKindEditor('#rrecommReason').html('');
	$.getJSON("/rest/funds/market/searchRecommReason?r=" + Math.random() + "&fundsCode=" + rows[0].fundsCode, function(info) {
		if (info.code == 200) {
			editor_1.html(info.msg);
		} else {
			$.messager.alert('错误提示', info.msg, 'error');
		}
	});
	$("#ffReason").form('load',rows[0]);
	$("#DivReason").dialog('open');
}
// 设置推荐理由
function setRecommReason(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	var content = $("#rrecommReason").val();
	if (content == '') {
	    $.messager.alert("提示", "请输入推荐理由", "error");
	    return
	}
	showLoading();
	$('#ffReason').form('submit',{
		url: '/rest/funds/market/setRecommReason',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			console.log(json)
			if(json.code == 200){
				$("#DivReason").dialog('close');
				$.messager.alert('提示', '设置成功！', 'info');
				$("#adGrid").datagrid('reload');
			}else{
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}

// 设置基金主题
function theme(){
	
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$("#ffTheme").form('clear');
	$("#DivTheme").dialog('open');
	if(rows[0].fundsTheme==null){
		$("#ffTheme").form('load',{
			fundsCode : rows[0].fundsCode
		});
		$("#tfundsTheme").combogrid("enable");
	}else{
		$("#ffTheme").form('load',rows[0]);
		$("#tfundsTheme").combogrid("disable");
	}
}
// 设置基金主题
function setFundsTheme(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	showLoading();
	$('#ffTheme').form('submit',{
		url: '/rest/funds/market/setFundsTheme',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			console.log(json)
			if(json.code == 200){
				$("#DivTheme").dialog('close');
				$.messager.alert('提示', '设置成功！', 'info');
				$("#adGrid").datagrid('reload');
			}else{
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
