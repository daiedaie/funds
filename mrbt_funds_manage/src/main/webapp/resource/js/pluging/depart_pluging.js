function searchDepart(){
	var user=$("#adGrid").treegrid('getSelected');
	$('#adGridDepartPlug').datagrid('load',{
		uId:user.id,
		dpName:$("#sdpName").val()
	});
}
function saveDepart(){
	var user=$("#adGrid").treegrid('getSelected');
	var dp_arr = $("#adGridDepartPlug").datagrid('getChecked');
	if(dp_arr.length>0){
		$("#ffAddDepart").form('clear');
		$("#setDepartUId").val(user.id);
		var arr = [];
		$.each(dp_arr,function(i,n){
			arr.push(n.id);
		})
		$("#setDpIds").val(arr.join(","));
		showLoading();
		$("#ffAddDepart").form('submit',{
			url: '/rest/pluging/depart_pluging/updateUserRDepart',
			ajax:true,
			success:function(data){
				var json = JSON.parse(data);
				if(json.code == 200){
					$("#adGrid1").datagrid("reload");
				}else{
					$.messager.alert('错误提示', json.msg, 'error');
				}
			}
		});
		closeLoading();
	}
	
	$('#DivDepartPlugingAdd').dialog('close');
}

function deleteDepart(){
	var user=$("#adGrid").treegrid('getSelected');
	if (!user) {
	    $.messager.alert("提示", "请先选择人员", "error");
	    return
	}
	
	var depart=$("#adGrid1").treegrid('getSelected');
	if (!depart) {
	    $.messager.alert("提示", "请选择一条权限记录", "error");
	    return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要删除吗？', function(r) {
		if (r) {
			$.getJSON("/rest/pluging/depart_pluging/deleteByUser?r=" + Math.random() + "&uId=" + user.id+"&dpId="+depart.id,
				    function(info) {
				       if(info.code==200){
				    	   $.messager.alert('提示', '删除成功', 'info');
				    	   $("#adGrid1").datagrid('reload');
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