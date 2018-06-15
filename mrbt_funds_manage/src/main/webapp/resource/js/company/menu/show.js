var toolbar = [ {
	text : '添加',
	iconCls : 'icon-add',
	handler : function() {
		add();
	}
}, '-', {
	text : '修改',
	iconCls : 'icon-edit',
	handler : function() {
		edit();
	}
}, '-', {
	text : '删除',
	iconCls : 'icon-remove',
	handler : function() {
		del();
	}
} ];
var clickNode;
function treeClick(node){
	clickNode = node;
	$("#adGrid").datagrid('load',{pid:node.id})
}
function selectNode(){
	if(clickNode){
		$("#menuTree").tree('select',$('#menuTree').tree('find', clickNode.id).target);
	}
}
function add(){
	var node = $("#menuTree").tree('getSelected');
	if(node){
		$('#ffAdd').form('clear');
		$("#apname").textbox("setValue",node.text)
		$("#apid").val(node.id);
		$("#aseq").textbox('readonly',false);
		$("#aicon").combobox('readonly',false);
		if(deepTree($("#menuTree"),$("#menuTree").tree('getSelected'))==3){
			$("#aActionTr").show();
			$("#aUrlTr").hide();
			$("#atype").combobox('setValue',2);
			$("#atype").combobox('readonly',true);
		}else if(deepTree($("#menuTree"),$("#menuTree").tree('getSelected'))==2){
			$("#aActionTr").hide();
			$("#aUrlTr").show();
			$("#atype").combobox('setValue',1);
			$("#atype").combobox('readonly',true);
		}
		$("#DivAdd").dialog('open');
	}else{
		 $.messager.alert('信息提示', '请先选择左侧菜单', 'info');
	}
}

function del(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	showLoading();
	$.messager.confirm('确认', '您确认想要删除吗？', function(r) {
		if (r) {
			$.getJSON("/rest/company/menu/delete?r=" + Math.random() + "&id=" + rows[0].id,
				    function(info) {
				       if(info.code==200){
				    	   $.messager.alert('提示', '删除成功', 'info');
				    	   refreshTree();
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

function changeIcon(index,obj) {
    $('#imgIcon'+index).attr('class', obj);
}
//保存
function save(){
	showLoading();
	$('#ffAdd').form('submit',{
		url: '/rest/company/menu/save',
		ajax:true,
		success:function(data){
			var json = JSON.parse(data);
			if(json.code == 200){
				refreshTree();
				$("#adGrid").datagrid('reload');
				$("#DivAdd").dialog('close');
			}
			else
			{
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}
//验证是否就2层
function validTreeLevel(treeObj,node){
	if(node){
		var node1 = $(treeObj).tree('getParent',node.target);
		if(node1 && $(treeObj).tree('getParent',node1.target)){
			return false;
		}
		return true;
	}
}
//查看树的深度
function deepTree(treeObj,node){
	var level = 1;
	if(node){
		level=level+deepTree(treeObj,$(treeObj).tree('getParent',node.target));
	}
	else{
		return 0;
	}
	return level;
}
function edit(){
	var rows = $("#adGrid").datagrid("getSelections");
	if (rows.length == 0) {
	    $.messager.alert("提示", "请选择一条记录", "error");
	    return
	}
	$("#ffEdit").form('clear');
	$("#ffEdit").form('load',rows[0]);
	$('#imgIcon1').attr('class', rows[0].icon);
	$("#epid").combotree("setValue",rows[0].parentId);
	$("#eseq").textbox('readonly',false);
	$("#eicon").combobox('readonly',false);
	$("#epid").combotree('readonly',false);
	if(deepTree($("#menuTree"),$("#menuTree").tree('getSelected'))==3){
		$("#eActionTr").show();
		$("#eUrlTr").hide();
		$("#etype").combobox('readonly',true);
		$("#epid").combotree('readonly',true);
	}else if(deepTree($("#menuTree"),$("#menuTree").tree('getSelected'))==2){
		$("#eActionTr").hide();
		$("#eUrlTr").show();
		$("#atype").combobox('setValue',1);
		$("#atype").combobox('readonly',true);
	}
	$("#DivEdit").dialog('open');
}
function refreshTree(){
	$("#menuTree").tree('reload');
}
//更新
function update(){
	var treeObj = $("#epid").combotree('tree');
	//不能选择当前记录的目录
	if($("#eid").val() == $("#epid").combotree('getValue')){
		$.messager.alert('错误提示', "不能选择当前目录作为父母录", 'error');
		return;
	}
	//判断目录结构是否超过3层
	if(!deepTree(treeObj,$(treeObj).tree('getSelected'))>3){
		$.messager.alert('错误提示', "此系统只支持到3层深度,请修改父菜单", 'error');
		return;
	}
	showLoading();
	$('#ffEdit').form('submit',{
		url: '/rest/company/menu/update',
		ajax:true,
		success:function(data){
			var json =JSON.parse(data);
			if(json.code == 200){
				$("#adGrid").datagrid('reload');
				refreshTree();
				$("#DivEdit").dialog('close');
			}else{
				$.messager.alert('错误提示', json.msg, 'error');
			}
		}
	});
	closeLoading();
}
