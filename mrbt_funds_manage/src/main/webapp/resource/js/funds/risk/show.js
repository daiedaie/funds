var toolbar = [{
	text : '导入基金风险数据',
	iconCls : 'icon-add',
	handler : function() {
		add();
	}
}];

function formatTimer(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return "";
}

// 查询
function search(){
	$('#adGrid').datagrid('load',{
		page:1,
		fundsCode:$("#sfundsCode").val()
	});
}


function add() {
	$('#ffAdd').form('clear');
	$("#DivAdd").dialog('open');
}
//保存
function upload(){
	showLoading();
	$('#ffAdd').form('submit',{
		url: '/rest/funds/risk/upload',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
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

