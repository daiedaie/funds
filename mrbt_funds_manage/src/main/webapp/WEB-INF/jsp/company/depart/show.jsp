<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部门管理</title>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/company/depart/show.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/company/depart/other.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/pluging/user_pluging.js"></script>
	<style scoped="scoped">
        .tt-inner{
            display:inline-block;
            line-height:12px;
            padding-top:5px;
        }
        .tt-inner img{
            border:0;
        }
    </style>
</head>
<body>
	<div class="easyui-layout"  fit="true">
		<!-- 列表层 -->
		<jsp:include page="grid.jsp"></jsp:include>
		<jsp:include page="properties.jsp"></jsp:include>
	</div>
		<!-- 添加的弹出层 -->
		<jsp:include page="add.jsp"></jsp:include>
		<!-- 添加的弹出层     end-->
		<!-- 编辑的弹出层 -->
		<jsp:include page="update.jsp"></jsp:include>
       <!-- 编辑的弹出层    end-->
       <!-- 显示的弹出层 -->
		<jsp:include page="view.jsp"></jsp:include>
       <!-- 编辑的弹出层    end-->
       
       <jsp:include page="../../pluging/user_pluging.jsp"></jsp:include>
</body>
</html>