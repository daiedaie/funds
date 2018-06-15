<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<h3>同类基金收益对比</h3>
<div class="tldb_tab clearfix">
	<a class="active" href="javascript:void(0);">同类排名走势</a> <a
		href="javascript:void(0);">百分比排名走势</a>
</div>
<div class="tb_1">
	<div class="tb_1top clearfix">
		<div class="sx clearfix">
			<a href="javascript:void(0);">1个月</a> <a href="javascript:void(0);">3个月</a>
			<a href="javascript:void(0);">6年</a> <a href="javascript:void(0);">1年</a>
			<a href="javascript:void(0);">3年</a> <a href="javascript:void(0);">最大</a>
		</div>
		<div class="sel">
			<span>选择指标：</span> <select>
				<option>近3月排名</option>
				<option>近6月排名</option>
				<option>近一年排名</option>
			</select>
		</div>
	</div>
	<div class="tj">
		<!--折线统计图-->
		<div id="container4" class="zt4"
			style="width: 930px; height: 250px; margin: 0 auto"></div>
	</div>
</div>
<div class="tb_2" style="display: none;">
	<div class="tb_2top clearfix">
		<div class="sx clearfix">
			<a href="javascript:void(0);">1天</a> <a href="javascript:void(0);">1个月</a>
			<a href="javascript:void(0);">1个月</a> <a href="javascript:void(0);">1年</a>
			<a href="javascript:void(0);">3年</a> <a href="javascript:void(0);">最大</a>
		</div>
		<div class="sel">
			<span>选择指标：</span> <select>
				<option>近3月排名</option>
				<option>近6月排名</option>
				<option>近一年排名</option>
			</select>
		</div>
	</div>
	<div class="tj">
		<!--折现统计图-->
	</div>
</div>