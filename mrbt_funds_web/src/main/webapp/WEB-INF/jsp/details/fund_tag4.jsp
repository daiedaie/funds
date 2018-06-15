<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="tabox">
	<h3>天相评级</h3>
	<table>
		<thead>
			<tr>
				<td>基金代码</td>
				<td>基金简称</td>
				<td>基金类型</td>
				<td>风险等级</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${code }</td>
				<td>${shortName }</td>
				<td>${typeName }</td>
				<td>${risk }</td>
			</tr>
		</tbody>
	</table>
	<h3>特色数据</h3>
	<table>
		<thead>
			<tr>
				<td>基金风险指标</td>
				<td>近1年</td>
				<td>近2年</td>
				<td>近3年</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>标准差</td>
				<td>4.71%</td>
				<td>/</td>
				<td>/</td>
			</tr>
			<tr>
				<td>夏普比例(Sharpe)</td>
				<td>-0.2163</td>
				<td>/</td>
				<td>/</td>
			</tr>
		</tbody>
	</table>
</div>
<div class="pbox clearfix">
	<span>注：</span>
	<p>
		标准差：基金每周收益相对于近1年、近2年或近3年内周平均收益的波动率，用来衡量基金收益的波动性。<br>夏普比率：(基金周平均收益率－平均无风险收益率)/基金收益率标准差。表示基金单位总风险所带来的超额收益。
	</p>
</div>
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