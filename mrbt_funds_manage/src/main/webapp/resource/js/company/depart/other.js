function searchUser(){
	var depart=$("#adGrid").treegrid('getSelected');
	if(!depart){
		$.messager.alert("提示", "请选择部门", "error");
		return;
	}
	$("#DivUserPlugingAdd").dialog("open");
}