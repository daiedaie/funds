

function formatTimer(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd");
	}
	return "";
}

// 查询
function search(){
	$('#adGrid').datagrid('load',{
		page:1,
		typeId:$("#typeId").combogrid("getValue")
	});
}
