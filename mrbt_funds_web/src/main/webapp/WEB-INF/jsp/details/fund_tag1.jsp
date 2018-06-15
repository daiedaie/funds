<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="j_1">
	<div id="industry_alloca_title" class="jz_top clearfix"></div>
	<div class="jz_bottom clearfix">
		<div class="jzb_left">
			<!--柱状统计图-->
			<div id="container2" style="width: 546px; height: 260px; margin: 0"></div>
		</div>
		<div class="jzb_right">
			<table>
				<thead>
					<tr>
						<td style="width: 25px">代码</td>
						<td style="width: 100px">行业类别</td>
						<td style="width: 80px">行业变动</td>
						<td style="width: 60px">占净值比</td>
						<td style="width: 80px">市值(万元)</td>
					</tr>
				</thead>
				<tbody id="industry_alloca_table" class="table_tbody">
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="j_2">
	<div class="j_2Top clearfix">
		<span>基金持仓-${shortName}<em>(报告期末占基金资产净值比例排序的股票一览。)</em></span>
		<div class="sel">
			查指定年度基金持仓: <select id="positionsYear">
			</select>
		</div>
	</div>
	<div class="j_2bottom">
		<a class="tip" href="javascript:void(0);">占净值比(%)</a>
		<div class="tongj">
			<!--柱状统计图-->
			<div id="container3"
				style="width: 700px; height: 250px; margin: 0 auto"></div>
		</div>
		<table>
			<thead>
				<tr>
					<td>序号</td>
					<td>股票代码</td>
					<td>股票名称</td>
					<td>最新价</td>
					<td>涨跌幅</td>
					<td>相关资讯</td>
					<td>占净值比</td>
					<td>持股数(万股)</td>
					<td>持仓市值(万元)</td>
				</tr>
			</thead>
			<tbody id="positionsTable">

			</tbody>
		</table>
	</div>
</div>
<div class="j_3">
	<div class="j_3Top clearfix">
		<span>行业配置比较-博时丝路主题股票A<em>(*基金行业配置数据日期：2016-03-31)</em></span>
	</div>
	<div class="tablebox clearfix">
		<table>
			<thead>
				<tr>
					<td>代码</td>
					<td>行业名称</td>
					<td>基金行业配置</td>
					<td>同类平均</td>
					<td>基金行业超配</td>
				</tr>
			</thead>
			<tbody id="indusAllCom1">

			</tbody>
		</table>
		<table>
			<thead>
				<tr>
					<td>代码</td>
					<td>行业名称</td>
					<td>基金行业配置</td>
					<td>同类平均</td>
					<td>基金行业超配</td>
				</tr>
			</thead>
			<tbody id="indusAllCom2">

			</tbody>
		</table>
	</div>
</div>