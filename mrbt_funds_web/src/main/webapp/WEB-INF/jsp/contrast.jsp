<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html">
	<meta charset="utf-8">
	<title>睿博财富-基金对比</title>
	<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" href="/css/contrast/contrast.css">
<script type="text/javascript" src="/js/contrast.js"></script>

</head>
<body>
	<%@ include file="/common/menu.jsp"%>
	<input id='model_code' style="display: none;" value='${ids}' />
<!--主内容 开始-->
<div class="main">
	<div class="goback">
		<a href="/markt/view">返回到基金超市</a>
	</div>
	<div class="opation clearfix">
		<div class="opleft">
			<div class="addb">
				添加对比基金：
			</div>
			<div class="topCheck">
				<div style="color: #ea560e;"><input name="highlight" value='1' type="radio"><span>单只基金优胜项</span></div>
				<div style="color: #31ace2;"><input name="highlight" value='2' type="radio"><span>单只基金最弱项</span></div>
			</div>
		</div>
		
		<div class="opright clearfix">
			<div class="opbox">
				<div class="ob_1" data-value = "1">
					<div class="ob1_top">
						<p>嘉实中证金边中期国</p>
						<p class="clerfix">
							<span>债ETF联接A</span>
							<a data-value = "1" href="javascript:void(0);">更换</a>
						</p>
					</div>
					<a class="buy" href="javascript:void(0);">立即购买</a>
				</div>
				<div class="ob_2" data-value = "1">
					<select data-value = "1" >
						<option value = '0'>请选择基金公司</option>
					</select>
					<input data-value = "1" type="text" placeholder="输入代码/名称拼音">
					<div id='inputx_1a'>
					</div>
				</div>
			</div>
			
			
			<div class="opbox">
				<div class="ob_1" data-value = "2">
					<div class="ob1_top">
						<p>嘉实中证金边中期国</p>
						<p class="clerfix">
							<span>债ETF联接A</span>
							<a data-value = "1" href="javascript:void(0);">更换</a>
						</p>
					</div>
					<a class="buy" href="javascript:void(0);">立即购买</a>
				</div>
				<div class="ob_2" data-value = "2">
					<select data-value = "2" >
						<option value = '0'>请选择基金公司</option>
					</select>
					<input data-value = "2" type="text" placeholder="输入代码/名称拼音">
					<div id='inputx_2a'>
					
					</div>
				</div>
			</div>
			
			<div class="opbox">
				<div class="ob_1" data-value = "3">
					<div class="ob1_top">
						<p>嘉实中证金边中期国</p>
						<p class="clerfix">
							<span>债ETF联接A</span>
							<a data-value = "1" href="javascript:void(0);">更换</a>
						</p>
					</div>
					<a class="buy" href="javascript:void(0);">立即购买</a>
				</div>
				<div class="ob_2" data-value = "3">
					<select data-value = "3" >
						<option value = '0'>请选择基金公司</option>
					</select>
					<input data-value = "3" type="text" placeholder="输入代码/名称拼音">
					<div id='inputx_3a'>
					</div>
				</div>
			</div>
			
			<div class="opbox">
				<div class="ob_1" data-value = "4">
					<div class="ob1_top">
						<p>嘉实中证金边中期国</p>
						<p class="clerfix">
							<span>债ETF联接A</span>
							<a data-value = "1" href="javascript:void(0);">更换</a>
						</p>
					</div>
					<a class="buy" href="javascript:void(0);">立即购买</a>
				</div>
				<div class="ob_2" data-value = "4">
					<select data-value = "4" >
						<option value = '0'>请选择基金公司</option>
					</select>
					<input data-value = "4" type="text" placeholder="输入代码/名称拼音">
					<div id='inputx_4a'>
					</div>
				</div>
			</div>
			
			<div class="opbox">
				<div class="ob_1" data-value = "5">
					<div class="ob1_top">
						<p>嘉实中证金边中期国</p>
						<p class="clerfix">
							<span>债ETF联接A</span>
							<a data-value = "1" href="javascript:void(0);">更换</a>
						</p>
					</div>
					<a class="buy" href="javascript:void(0);">立即购买</a>
				</div>
				<div class="ob_2" data-value = "5">
					<select data-value = "5" >
						<option value = '0'>请选择基金公司</option>
					</select>
					<input data-value = "5" type="text" placeholder="输入代码/名称拼音">
					<div id='inputx_5a'>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<div class="check_box">
		<ul>
			<li><span style="font-weight:bold;">显示比较项</span></li>
			<li><input name="show_check" type="checkbox" value='0'><span>全部显示</span></li>
			<li><input name="show_check" type="checkbox" value='1' checked="checked"><span>基本信息</span></li>
			<li><input name="show_check" type="checkbox" value='2'><span>业绩比较</span></li>
			<li><input name="show_check" type="checkbox" value='3'><span>基金经理</span></li>
			<li><input name="show_check" type="checkbox" value='4'><span>重仓债券</span></li>
			<li><input name="show_check" type="checkbox" value='5'><span>行业配置</span></li>
		</ul>
	</div>
	<div class="dbBox">
		<h3><input name="show_check" type="checkbox" value='1' checked="checked"><span>基本信息</span></h3>
		<div class="box_1 clearfix">
			<table class="table_1">
				<tr>
					<td>基金代码</td>
				</tr>
				<tr>
					<td>基金类型</td>
				</tr>
				<tr>
					<td>基金净值(元)</td>
				</tr>
				<tr>
					<td>风险等级</td>
				</tr>
				<tr>
					<td>申购状态</td>
				</tr>
				<tr>
					<td>赎回状态</td>
				</tr>
				<tr>
					<td>最新规模(亿元)</td>
				</tr>
				<tr>
					<td>管理人</td>
				</tr>
				<tr>
					<td>申购费率(%)</td>
				</tr>
				<tr>
					<td>累计分红(元)</td>
				</tr>
				<tr>
					<td>成立日期</td>
				</tr>
			</table>
			<table id="bast_info" class="table_2">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		<h3><input name="show_check" type="checkbox" value='2'><span>业绩比较</span></h3>
		<div class="box_2 clearfix" style="display: none;">
			<table class="table_1">
				<tr>
					<td>近一周业绩增长率(%)</td>
				</tr>
				<tr>
					<td>近一月业绩增长率(%)</td>
				</tr>
				<tr>
					<td>近三月业绩增长率(%)</td>
				</tr>
				<tr>
					<td>近六月业绩增长率(%)</td>
				</tr>
				<tr>
					<td>近一年业绩增长率(%)</td>
				</tr>
				<tr>
					<td>近一年沪深300增长率(%)</td>
				</tr>
				<tr>
					<td>近一年上证指数增长率(%)</td>
				</tr>
				<tr>
					<td>近三年业绩增长率(%)</td>
				</tr>
				<tr>
					<td>成立以来业绩增长率(%)</td>
				</tr>
				<tr>
					<td>七日年化收益率(%)</td>
				</tr>
				<tr>
					<td>万分收益</td>
				</tr>
				<tr>
					<td>近三月最大回撤(%)</td>
				</tr>
				<tr>
					<td>近半年最大回撤(%)</td>
				</tr>
				<tr>
					<td>近一年最大回撤(%)</td>
				</tr>
				<tr>
					<td>sharpe值</td>
				</tr>
				<tr>
					<td>收益标准差</td>
				</tr>
			</table>
			<table id="track_info" class="table_2">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		<h3><input name="show_check" type="checkbox" value='3'><span>基金经理</span></h3>
		<div class="box_3 clearfix" style="display: none;">
			<table class="table_1">
				<tr>
					<td>基金经理</td>
				</tr>
				<tr>
					<td>年龄</td>
				</tr>
				<tr>
					<td>从业日期</td>
				</tr>
				<tr>
					<td>旗下基金数</td>
				</tr>
				<tr>
					<td>同类基金区间收益排名</td>
				</tr>
			</table>
			<table id="managers_info" class="table_2">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		<h3><input name="show_check" type="checkbox" value='4'><span>重仓债券</span></h3>
		<div class="box_4 clearfix" style="display: none;">
			<table class="table_1">
				<tr>
					<td>重仓债券1</td>
				</tr>
				<tr>
					<td>重仓债券2</td>
				</tr>
				<tr>
					<td>重仓债券3</td>
				</tr>
				<tr>
					<td>重仓债券4</td>
				</tr>
				<tr>
					<td>重仓债券5</td>
				</tr>
			</table>
			<table id="bond_info" class="table_2">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		<h3><input name="show_check" type="checkbox" value='5'><span>行业配置</span></h3>
		<div class="box_5 clearfix" style="display: none;">
			<table class="table_1">
				<tr>
					<td>重仓行业1</td>
				</tr>
				<tr>
					<td>重仓行业2</td>
				</tr>
				<tr>
					<td>重仓行业3</td>
				</tr>
				<tr>
					<td>重仓行业4</td>
				</tr>
				<tr>
					<td>重仓行业5</td>
				</tr>
			</table>
			<table id="industry_info" class="table_2">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<!--主内容 结束-->
<%@ include file="/common/bottom.jsp"%>
</body>
</html>