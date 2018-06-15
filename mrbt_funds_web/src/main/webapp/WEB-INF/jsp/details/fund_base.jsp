<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="xqtr_1 clearfix">
	<span class="sp1"><em>${typeName}</em>${shortName }</span> <span
		class="sp2">基金状态：<em>${state}</em></span>
</div>
<div class="xqtr_2 clearfix">
	<span>基金代码<em>${code}</em></span> <span>基金规模<em>${scale}</em>亿</span> <span>成立日期<em>${upDate}</em></span>
</div>
<div class="xqtr_3 clearfix">
	<div class="x3_1">
		<span>${netValue}<em>-0.95%</em><img src="/images/jtxs.png" /></span>
		<p>
			单位净值<em>(${navTime})</em>
		</p>
	</div>
	<div class="x3_2">
		<span>${totalTalue}</span>
		<p>
			累计净值<em>(${totalTime})</em>
		</p>
	</div>
	<div class="x3_3">
		<span>${ratez}<em>${rate}</em></span>
		<p>购买费率(%)</p>
	</div>
</div>
<p class="fxlevel">
	风险等级:<span>${risk}</span>
</p>
<div class="opation clearfix">
	<input type="text" placeholder="1000.00"> <a href="javascript:void(0);" class="gm">立即购买</a>
	<a href="javascript:void(0);" class="dt">立即定投</a>
</div>
<p class="fxlevel_2">
	单次投资限额:<span></span>
</p>