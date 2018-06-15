

function formatTimer(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd");
	}
	return "";
}

function formatTimers(value, row) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return "";
}
function formatStyle(value,row){
	if(value == 0){
		return "定时任务";
	}else if(value == 1){
		return "<font color='green'>手动</font>";
	}
}
// 查询
function search(){
	$('#adGrid').datagrid('load',{
		page:1,
		createTime:$("#createTime").datebox("getValue"),
		operateStyle:$("#operateStyle").combogrid("getValue")
	});
}
