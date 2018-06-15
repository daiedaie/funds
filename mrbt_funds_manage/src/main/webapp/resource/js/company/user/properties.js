function showRole(){
	var user=$("#adGrid").datagrid('getSelected');
	if(!user){
		$.messager.alert("提示", "请先选择用户", "error");
		return;
	}
	$("#DivRolePlugingAdd").dialog("open");
	$("#adGridRolePlug").datagrid('load',{
		uId:user.id
	});
}

function showDepart(){
	var user=$("#adGrid").datagrid('getSelected');
	if(!user){
		$.messager.alert("提示", "请先选择用户", "error");
		return;
	}
	$("#adGridDepartPlug").datagrid('load','/rest/pluging/depart_pluging/listByUid?uId='+user.id);
	$("#DivDepartPlugingAdd").dialog("open");
}
/**
 * 刷新数据
 */
function refreshTab(){
	var user=$("#adGrid").datagrid('getSelected');
	if(user){
		gridLoad(getTabIndex(),user.id);
	}
}

function getTabIndex(){
	var tab = $('#tab_proper').tabs('getSelected');
	var index = $('#tab_proper').tabs('getTabIndex',tab);
	return index;
}
//读取数据
function gridLoad(index,id){
	var gridId = "#adGrid"+index;
	if(index==0){
		$(gridId).datagrid('load',{
			uId:id
		});
	}
	if(index==1){
		$(gridId).datagrid('load','/rest/properties/user_properties/listDepartByUid?uId='+id);
	}
}
/**
 * 格式化是否为领导
 * @param value
 * @returns {String}
 */
function formatterIsLeader(value,row){
	var user=$("#adGrid").datagrid('getSelected');
	if(value==0){
		return "<input class='sz_button' dpid_v="+row.id+" dpuid_v="+user.id+" isLeader='false'>";
	}
	else{
		return "<input class='sz_button' checked dpid_v="+row.id+"  dpuid_v="+user.id+" isLeader='true'>"
	}
}
function changeSwitchButton(button,checked){
	var errorChange;
	if(checked){
		if($(button).attr("isLeader")=='true'){
			return;
		}
		errorChange='uncheck';
	}else{
		if($(button).attr("isLeader")=='false'){
			return;
		}
		errorChange='check';
	}
	showLoading();
	$.getJSON("/rest/properties/user_properties/changeLeader?r=" + Math.random() + "&uId=" + $(button).attr("dpuid_v")+"&dpId="+$(button).attr("dpid_v")+"&checked="+checked,
	    function(info) {
	       if(info.code==200){
	    	   if(checked){
	    		   $(button).attr("isLeader","true");
	    	   }else{
	    		   $(button).attr("isLeader","false");
	    	   }
	       }else{
	    	   $(button).switchbutton(errorChange);
	    	   $.messager.alert('错误提示', info.msg, 'error');
	       }
	});
	closeLoading();
	
}
