function showLoading(){
	$.messager.progress({ 
		title:'请稍后', 
		msg:'页面加载中...' 
	}); 
}
function closeLoading(){
	$.messager.progress('close');
}
