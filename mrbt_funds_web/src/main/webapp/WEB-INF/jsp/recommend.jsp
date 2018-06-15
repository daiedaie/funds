<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="utf-8">
<title>睿博财富</title>

<meta http-equiv="Content-Type" content="text/html">
<meta charset="utf-8">
<title>睿博财富-基金推荐</title>
<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" href="/css/recommend/rec.css">
<script type="text/javascript" src="/js/recommend.js"></script>

</head>
<body><%@ include file="/common/menu.jsp"%>
	<div class="main">
		<!--睿博推荐介绍  开始-->
		<div class="intro">
			<p class="clearfix"><span></span>优选基金介绍：</p>
			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;睿博财富的优选基金板块是根据睿博财富的公募基金研究中心的即期研究成果而得到，代表睿博财富的研究结果和内容，并不代表市场行为，也不对市场行为具有指引作用，旨在给客户提供一个参考。睿博财富的公募基金研究中心会结合市场变化，宏观动态信息等，在长期跟踪研究的基础上，优选三十只以内基金，构建优选基金池，定期对优选基金池做动态调整，力求做到研究贴近市场，紧跟市场。
		</div>
		<!--睿博推荐介绍  结束-->
		<!--排序  筛选-->
		<div class="paixu clearfix">
			<ul id="fund-types">
				<li>基金类型</li>
				<li><a href="javascript:void(0);" data-value="">不限</a></li>
				<li><a href="javascript:void(0);" data-value="0">股票型</a></li>
				<li><a href="javascript:void(0);" data-value="1">债券型</a></li>
				<li><a href="javascript:void(0);" data-value="2">混合型</a></li>
				<li><a href="javascript:void(0);" data-value="3">货币型</a></li>
				<li><a href="javascript:void(0);" data-value="7">保本型</a></li>
				<li><a href="javascript:void(0);" data-value="-1">其他</a></li>
			</ul>
		</div>
		<!--排序  结束-->
		<!--基金列表  开始-->
		<div id="recomFundListFDiv" class="jve">
			
		</div>
		<!--基金列表  结束-->
		<!--分页  开始-->
		<div class="page clearfix">
			<p>免责声明：优选基金代表的是睿博财富的公募基金研究中心的即期研究成果，并不对客户的购买决策起到指引作用，由此带来的损失自行承担。公募基金的风险主要来自市场风险和基金操作风险，详情请参考基金合同。</p>
			
			<ul class="clearfix">
				<li><a href="javascript:void(0);" class="prev"></a></li>
				<li id="pageContainer" class="clearfix">
				</li>
				<li><a href="javascript:void(0);" class="next"></a></li>
			</ul>
		</div>
		<!--分页  结束-->
		<!--上期调出  开始-->
		<div  class="diaochu">
		 <div class="title">
				|&nbsp;上期调出
			</div>
			<table>
				<thead>
					<tr>
						<td class="thd_1">基金代码</td>
						<td class="thd_2">基金名称</td>
						<td class="thd_3">说明</td>
					</tr>
				</thead>
				<tbody id="calloutListDiv">
				</tbody>
			</table> 
		</div>
		<!--上期调出  结束-->
		<!--本期调入  开始-->
		<div class="diaoru">
			<div class="title">
				|&nbsp;本期调入
			</div>
			<table>
				<thead>
					<tr>
						<td class="thd_1">基金代码</td>
						<td class="thd_2">基金名称</td>
						<td class="thd_3">说明</td>
					</tr>
				</thead>
				<tbody id="TransferredListDiv"  >
				</tbody>
			</table>
		</div>
		<!--本期调入  结束-->
	</div>
	<%@ include file="/common/bottom.jsp"%>
</body>
</html>