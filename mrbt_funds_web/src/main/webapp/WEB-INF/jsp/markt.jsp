<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html">
	<meta charset="utf-8">
	<title>睿博财富</title>
	<%@ include file="/common/head.jsp"%>
	<script type="text/javascript" src="../js/markt.js"></script>
	<link rel="stylesheet" href="/css/markt/markt.css">
</head>
<body>
	<input id="cast_surly" style="display: none;" value="${cast_surely}">
	<input id="hotsale" style="display: none;" value="${hotsale}">
	<%@ include file="/common/menu.jsp"%>
	<!--banner 开始-->
	<%@ include file="banners/markt_top.jsp"%>
	<!--banner 结束-->
	
	<div class="main">
		<!--筛选  开始-->
		<div class="shaixuan">
			<ul id="case-types" class="dingtou clearfix">
				<li class="typename">基金定投</li>
				<li><a data-info="" data-value="0" href="javascript:void(0);">不限制</a></li>
				<li><a data-info="5" data-value="1" href="javascript:void(0);">定投</a></li>
				<li><a data-info="0" data-value="2" href="javascript:void(0);">非定投</a></li>
			</ul>
			<ul id="fund-types" class="leixing clearfix">
				<li class="typename">基金类型</li>
				<li><a data-info="" data-value="0" href="javascript:void(0);">不限制</a></li>
				<li><a data-info="0" data-value="1" href="javascript:void(0);">股票型</a></li>
				<li><a data-info="1" data-value="2" href="javascript:void(0);">债券型</a></li>
				<li><a data-info="2" data-value="3" href="javascript:void(0);">混合型</a></li>
				<li><a data-info="3" data-value="4" href="javascript:void(0);">货币型</a></li>
				<li><a data-info="7" data-value="6" href="javascript:void(0);">保本型</a></li>
				<li><a data-info="-1" data-value="7" href="javascript:void(0);">其它</a></li>
			</ul>
			<ul id="money-types" class="guimo clearfix">
				<li class="typename">基金规模</li>
				<li><a data-info="" data-value="0" href="javascript:void(0);">不限制</a></li>
				<li><a data-info="0,10" data-value="1" href="javascript:void(0);">0-10亿</a></li>
				<li><a data-info="10,50" data-value="2" href="javascript:void(0);">10-50亿</a></li>
				<li><a data-info="50,100" data-value="3" href="javascript:void(0);">50-100亿</a></li>
				<li><a data-info="100,150" data-value="4" href="javascript:void(0);">100-150亿</a></li>
				<li><a data-info="150" data-value="5" href="javascript:void(0);">150亿以上</a></li>
			</ul>
			<ul id="company-types" class="gongsi clearfix">
				<li class="typename">基金公司</li>
				<li class="nowhere"><a data-info="0" data-value="0" href="javascript:void(0);">不限制</a></li>
				<li><a data-info="A" data-value="1"  href="javascript:void(0);">A</a></li>
				<li><a data-info="B" data-value="2"  href="javascript:void(0);">B</a></li>
				<li><a data-info="C" data-value="3"  href="javascript:void(0);">C</a></li>
				<li><a data-info="D" data-value="4"  href="javascript:void(0);">D</a></li>
				<li><a data-info="F" data-value="5"  href="javascript:void(0);">F</a></li>
				<li><a data-info="G" data-value="6"  href="javascript:void(0);">G</a></li>
				<li><a data-info="H" data-value="7"  href="javascript:void(0);">H</a></li>
				<li><a data-info="J" data-value="8"  href="javascript:void(0);">J</a></li>
				<li><a data-info="K" data-value="9"  href="javascript:void(0);">K</a></li>
				<li><a data-info="L" data-value="10" href="javascript:void(0);">L</a></li>
				<li><a data-info="M" data-value="11" href="javascript:void(0);">M</a></li>
				<li><a data-info="N" data-value="12" href="javascript:void(0);">N</a></li>
				<li><a data-info="P" data-value="13" href="javascript:void(0);">P</a></li>
				<li><a data-info="Q" data-value="14" href="javascript:void(0);">Q</a></li>
				<li><a data-info="R" data-value="15" href="javascript:void(0);">R</a></li>
				<li><a data-info="S" data-value="16" href="javascript:void(0);">S</a></li>
				<li><a data-info="T" data-value="17" href="javascript:void(0);">T</a></li>
				<li><a data-info="W" data-value="18" href="javascript:void(0);">W</a></li>
				<li><a data-info="X" data-value="19" href="javascript:void(0);">X</a></li>
				<li><a data-info="Y" data-value="20" href="javascript:void(0);">Y</a></li>
				<li><a data-info="Z" data-value="21" href="javascript:void(0);">Z</a></li>
			</ul>
			<div id="company-names" class="jieguo" style="display: none;">
				<p></p>
			</div>

			<div id="fund-themes" class="where">
				<ul class="clearfix">
					<li class="typename">基金主题</li>
					<li class="nowhere"><a data-value="0" href="javascript:void(0);">不限制</a></li>
					<li><a data-info="" data-value="1" href="javascript:void(0);">有色金属</a></li>
					<li><a data-info="" data-value="2" href="javascript:void(0);">国防军工</a></li>
					<li><a data-info="" data-value="3" href="javascript:void(0);">信息产业</a></li>
					<li><a data-info="" data-value="4" href="javascript:void(0);">房地产</a></li>
					<li><a data-info="" data-value="5" href="javascript:void(0);">房地产证劵</a></li>
					<li><a data-info="" data-value="6" href="javascript:void(0);">环保</a></li>
					<li><a data-info="" data-value="7" href="javascript:void(0);">TMT产业</a></li>
					<li><a data-info="" data-value="8" href="javascript:void(0);">互联网</a></li>
					<li><a data-info="" data-value="9" href="javascript:void(0);">移动互联网</a></li>
					<li><a data-info="" data-value="10" href="javascript:void(0);">国企改革</a></li>
					<li><a data-info="" data-value="11" href="javascript:void(0);">健康生活</a></li>
					<li><a data-info="" data-value="12" href="javascript:void(0);">战略转型</a></li>
					<li><a data-info="" data-value="13" href="javascript:void(0);">新能源</a></li>
					<li><a data-info="" data-value="14" href="javascript:void(0);">工业革命4.0</a></li>
					<li><a data-info="" data-value="15" href="javascript:void(0);">新能源汽车</a></li>
					<li><a data-info="" data-value="16" href="javascript:void(0);">一带一路</a></li>
					<li><a data-info="" data-value="17" href="javascript:void(0);">新兴产业</a></li>
					<li><a data-info="" data-value="18" href="javascript:void(0);">美丽中国</a></li>
					<li><a data-info="" data-value="19" href="javascript:void(0);">证劵</a></li>
					<li><a data-info="" data-value="20" href="javascript:void(0);">高端制造</a></li>
					<li><a data-info="" data-value="21" href="javascript:void(0);">内需增长</a></li>
					<li><a data-info="" data-value="22" href="javascript:void(0);">创新主题</a></li>
					<li><a data-info="" data-value="23" href="javascript:void(0);">创业板</a></li>
					<li><a data-info="" data-value="24" href="javascript:void(0);">互联网金融</a></li>
					<li><a data-info="" data-value="25" href="javascript:void(0);">黄金合约</a></li>
					<li><a data-info="" data-value="26" href="javascript:void(0);">银行</a></li>
					<li><a data-info="" data-value="27" href="javascript:void(0);">保险主题</a></li>
					<li><a data-info="" data-value="28" href="javascript:void(0);">可转债</a></li>
					<li><a data-info="" data-value="29" href="javascript:void(0);">中小板</a></li>
					<li><a data-info="" data-value="30" href="javascript:void(0);">资源行业</a></li>
					<li><a data-info="" data-value="31" href="javascript:void(0);">社会责任</a></li>
					<li><a data-info="" data-value="32" href="javascript:void(0);">食品饮料</a></li>
					<li><a data-info="" data-value="33" href="javascript:void(0);">金融地产</a></li>
					<li><a data-info="" data-value="34" href="javascript:void(0);">酒指数</a></li>
					<li><a data-info="" data-value="35" href="javascript:void(0);">钢铁主题</a></li>
					<li><a data-info="" data-value="36" href="javascript:void(0);">煤炭主题</a></li>
					<li><a data-info="" data-value="37" href="javascript:void(0);">大宗商品</a></li>
					<li><a data-info="" data-value="38" href="javascript:void(0);">农业主题</a></li>
					<li><a data-info="" data-value="39" href="javascript:void(0);">其他</a></li>
				</ul>
				<a href="javascript:void(0);" class="more">更多</a>
			</div>
			<div id="reloadCondition">重置选项</div>
		</div>
		<!--筛选 结束-->
		<!--条件  开始-->

		<!--条件 结束-->
		<!--对比查询 开始-->
		<div class="search clearfix">
			<div id="compareDiv" class="s_left clearfix">
				<em id='compareDivSpan'>勾选选择对比的基金(最多可勾选5只基金)</em>
				<a href="javascript:void(0);" class="duibi" onClick="compareFunds()">对比</a>
			</div>
			<div class="s_right clearfix" style="position:relative;">
				<input id="searchText" type="text" placeholder="基金名称/代码"> <a href="javascript:void(0);" onclick="fundSearch(this)">搜索</a>
				<div id="searchDatagrid" style="display:none;width:298px;height:300px;border:1px solid black;position:absolute;z-index:999;top:32px;background-color:white;overflow:hidden;">
					<div style="position:absolute;right:0px;top:0px;height:30px;width:40px;font-size:16px;text-align:center;cursor:pointer;">[X]</div>
					<div style="position:absolute;left:0px;top:0px;height:30px;width:258px;font-size:16px;">
						<table cellpadding="5px;"><tr><th style="width:86px;">基金代码</th><th style="width:86px;">基金简称</th><th style="width:86px;">基金类型</th></tr></table>
					</div>
					<div style="width:100%;height: 270px;position: absolute; top: 30px; overflow: auto;">
					</div>
				</div>
			</div>
		</div>
		<!--对比查询 结束-->
		<!--排序 开始 默认按基金代码排序 code = 1-->
		<div id="orders" class="paixu clearfix">
			<a class="pai" style="padding-left: 15px; width: 50px;">排序</a>
			<a class="hot" data-order="desc" data-info="2" href="javascript:void(0);">热销基金</a> 
			<a class="clearfix" data-order="desc" data-info="3" href="javascript:void(0);">单位净值<span>&nbsp;</span></a> 
			<a class="clearfix" data-order="desc" data-info="4" href="javascript:void(0);">日涨跌<span>&nbsp;</span></a>
			<a class="clearfix" data-order="desc" data-info="5" href="javascript:void(0);">近一月<span>&nbsp;</span></a> 
			<a class="clearfix" data-order="desc" data-info="6" href="javascript:void(0);">近三月<span>&nbsp;</span></a>
			<a class="clearfix" data-order="desc" data-info="7" href="javascript:void(0);">今年以来<span>&nbsp;</span></a> 
			<a class="clearfix" data-order="desc" data-info="10" href="javascript:void(0);">近一年<span>&nbsp;</span></a> 
			<a class="clearfix" data-order="desc" data-info="8" href="javascript:void(0);">近三年<span>&nbsp;</span></a>
			<a class="clearfix" data-order="desc" data-info="9" href="javascript:void(0);">基金规模<span>&nbsp;</span></a>
		</div>
		<!--排序  结束-->
		<!--基金列表  开始-->
		<div id="marketFundListFDiv" class="jve">
		</div>
		<!--基金列表  结束-->
		<!--分页  开始-->
		<div id="pageDiv" class="page">
		</div>
		<!--分页  结束-->
	</div>
	<%@ include file="/common/bottom.jsp"%>
</body>
</html>