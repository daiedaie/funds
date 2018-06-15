<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="utf-8">
<title>睿博财富-基金知识库</title>
<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" href="/css/knowledge/knowledge.css">
<script type="text/javascript" src="/js/jquery-1.7.2/jquery.media.js"></script>
<script type="text/javascript" src="/js/knowledge.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@ include file="/common/menu.jsp"%>
	<div class='main'>
		<div id='know' class='knowList'>
			<ul>
				<li><a> <span>标题</span> <span>作者</span> <span>创建时间</span>
				</a></li>
				<c:forEach var="fuwa" items="${list}" varStatus='status'>
					<li><a href="javascript:void(0);" data-value='${fuwa.text}'
						id='list${status.index}'> <span>${fuwa.title}</span> <span>${fuwa.author}</span>
							<span>${fuwa.createTime}</span>
					</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="pageContainer">
			<c:if test="${currentpage != 1}">
				<a href="/knowledge/view?page=${currentpage - 1}">上一页</a>
				<a href="/knowledge/view?page=1">1</a>
				<c:if test="${currentpage-1 != 1}">
					<span>...</span>
					<a href="/knowledge/view?page=${currentpage-1}">${currentpage-1}</a>
				</c:if>
			</c:if>
			<a href="javascript:;" style="color: #6CF;">${currentpage}</a>
			<c:if test="${currentpage != pagenum}">
				<c:if test="${currentpage + 1 != pagenum}">
					<a href="/knowledge/view?page=${currentpage + 1}">${currentpage+1}</a>
					<span>...</span>
				</c:if>
				<a href="/knowledge/view?page=${pagenum}">${pagenum}</a>
				<a href="/knowledge/view?page=${currentpage + 1}">下一页</a>
			</c:if>
		</div>
		
		<div class="window" id="center">
			<div id="title" class="title">
				<a href="javascript:void(0);">关闭</a>
			</div>
			<div id='pdfview' class="pdfcontent">
				
			</div>
		</div>
	</div>
<%@ include file="/common/bottom.jsp"%>
</body>
</html>