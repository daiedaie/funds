$(function(){
	$('#adGrid').datagrid({
		rowStyler:function(index,row){
			if (row.isRecomm!=null){
				var rowOverrideClass=new Object();
				rowOverrideClass.class="rowOverrideClass";
                return rowOverrideClass;
			}
		}
	});
})




var toolbar = [ {
	text : '添加到基金超市',
	iconCls : 'icon-add',
	handler : function() {
		add();
	}
}, '-', {
	text : '查看',
	iconCls : 'icon-view',
	handler : function() {
		view();
	}
}];
function formatTimers(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return "";
}
function formatTimer(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd");
	}
	return "";
}
// 添加窗口的显示
function add(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	if (rows[0].isRecomm!=null){
		$.messager.alert("提示", "基金超市已存在该基金，请勿重复添加！", "error");
	    return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要添加吗？', function(r) {
		if (r) {
			$.getJSON("/rest/source/ths035/addToFundsMarket?r=" + Math.random() + "&fundsCode=" + rows[0].f002Ths001 + "&fundsCodeInner=" + rows[0].f001Ths001,
				    function(info) {
				       if(info.code==200){
				    	   $.messager.alert('提示', '添加成功！', 'info');
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
function view(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$("#ffView").form('clear');
	if (rows[0].ctimeThs035) {
		rows[0].ctimeThs035 = formatTimers(new Date(rows[0].ctimeThs035));
	}
	if (rows[0].rtimeThs035) {
		rows[0].rtimeThs035 = formatTimers(new Date(rows[0].rtimeThs035));
	}
	if (rows[0].f005Ths035) {
		rows[0].f005Ths035 = formatTimer(new Date(rows[0].f005Ths035));
	}
	if (rows[0].f012Ths035) {
		rows[0].f012Ths035 = formatTimer(new Date(rows[0].f012Ths035));
	}
	if (rows[0].f013Ths035) {
		rows[0].f013Ths035 = formatTimer(new Date(rows[0].f013Ths035));
	}
	if (rows[0].f025Ths035) {
		rows[0].f025Ths035 = formatTimer(new Date(rows[0].f025Ths035));
	}
	$("#ffView").form('load',rows[0]);
	$("#DivView").dialog('open');
}

// 查询
function search(){
	$('#adGrid').datagrid('load',{
		page:1,
		f002Ths001:$("#sf002Ths001").val(),
		f003Ths001:$("#sf003Ths001").val(),
		f005Ths001:$("#sf005Ths001").val(),
		f001Ths001:$("#sf001Ths001").val(),
		isRecomm : $("#isRecomm").combogrid("getValue")
	});
}


