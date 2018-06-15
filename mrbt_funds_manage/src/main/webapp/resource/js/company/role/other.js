function allocation(){
	var row = $("#adGrid").datagrid("getSelected");
	if (!row) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$("#ffAddRole").form('clear');
	$("#setRoleId").val(row.id);
	$('#menuTree').tree({
		url:'/rest/company/menu/listAllTreeByRoot?widthAction=true&r='+Math.random(),
		method:'post',
		animate:true,
		lines:true,
		cascadeCheck:false,
		checkbox:true,
		onBeforeLoad:function(){
         	showLoading();
         },
         onLoadSuccess:function(){
        	 getRoleMenu(row.id);
         }
	});
	initDialogSetting(row.description);
}


/**
 * 做菜单设置
 */
function doSetting(){
	var ids = getChecked();
	if(!ids || ids == ""){
		$.messager.alert('错误提示', '请选择相应权限，如果没有，直接退出', 'error')
		return;
	}
	$("#setMenuIds").val(ids)
	showLoading();
	$('#ffAddRole').form('submit',{
		url: '/rest/company/menu_r_role/updateRelation',
		ajax:true,
		success:function(data){
			var json =JSON.parse(data);
			if(json.code == 200){
				$("#DivSet").dialog('close');
			}else{
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}
function getChecked(){
    var nodes = $('#menuTree').tree('getChecked',['checked','indeterminate']);
    var s = '';
    for(var i=0; i<nodes.length; i++){
        if (s != '') s += ',';
        s += nodes[i].id;
    }
    return s;
}
/**
 * 获取当前role所对应的menu
 */
function getRoleMenu(id){
	$.getJSON("/rest/company/menu_r_role/list?rId="+id+"&r=" + Math.random(),
	function(json){
		if(json && json.rows.length>0){
			$.each(json.rows, function(i, n) {
				var node = $('#menuTree').tree('find', n.menuId);
				if(node){
					$('#menuTree').tree('check', node.target);
				}
			});
		}
	});
	closeLoading();
}
//初始化dialog
function initDialogSetting(title){
	$('#DivSet').dialog({
	    title: title,
	    iconCls:'icon-setting',
	    modal: true,
	    toolbar:[{
			text:'刷新',
			iconCls:'icon-reload',
			handler:function(){$('#menuTree').tree('reload');}
		},'-',{
			text:'展开',
			iconCls:'icon-expand',
			handler:function(){$('#menuTree').tree('expandAll');}
		},'-',{
			text:'折叠',
			iconCls:'icon-collapse',
			handler:function(){$('#menuTree').tree('collapseAll');}
		}],
		buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){doSetting();}
		}]
	    
	});
	$('#DivSet').dialog('open');
}