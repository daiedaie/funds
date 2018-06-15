function searchRole(){
	var user=$("#adGrid").datagrid('getSelected');
	$('#adGridRolePlug').datagrid('load',{
		uId:user.id,
		rName:$("#srName").val()
	});
}
function saveRole(){
	var user=$("#adGrid").datagrid('getSelected');
	var roles_arr = $("#adGridRolePlug").datagrid('getChecked');
	if(roles_arr.length>0){
		$("#ffAddRole").form('clear');
		$("#setUId").val(user.id);
		var arr = [];
		$.each(roles_arr,function(i,n){
			arr.push(n.id);
		})
		$("#setRIds").val(arr.join(","));
		showLoading();
		$("#ffAddRole").form('submit',{
			url: '/rest/pluging/role_pluging/updateUserRRole',
			ajax:true,
			success:function(data){
				var json = JSON.parse(data);
				if(json.code == 200){
					$("#adGrid0").datagrid("reload");
				}else{
					$.messager.alert('错误提示', json.msg, 'error');
				}
			}
		});
		closeLoading();
	}
	
	$('#DivRolePlugingAdd').dialog('close');
}

function deleteRole(){
	var user=$("#adGrid").datagrid('getSelected');
	if (!user) {
	    $.messager.alert("提示", "请先选择人员", "error");
	    return
	}
	
	var role=$("#adGrid0").datagrid('getSelected');
	if (!role) {
	    $.messager.alert("提示", "请选择一条权限记录", "error");
	    return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要删除吗？', function(r) {
		if (r) {
			$.getJSON("/rest/pluging/role_pluging/deleteByUser?r=" + Math.random() + "&uId=" + user.id+"&rId="+role.id,
				    function(info) {
				       if(info.code==200){
				    	   $.messager.alert('提示', '删除成功', 'info');
				    	   $("#adGrid0").datagrid('reload');
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