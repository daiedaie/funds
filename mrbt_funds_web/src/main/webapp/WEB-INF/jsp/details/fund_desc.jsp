<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="xqphotoleft">
	<img src="/images/details_image.jpg">
</div>
<div class="xqphotoright">
	<div class="xhr_1">
		<ul>
			<li style="width: 400px;">
				基金全称:
				<span>${fullName}</span>
			</li>
			<li>
				基金管理人：
				<span>${fundManager}</span>
			</li>
		</ul>
	</div>
	<div class="xhr_2">
		<ul>
			<li>
				基金代码：
				<span>${code}</span>
			</li> 
			<li>
				基金类型：
				<span>${typeName}</span>
			</li>
			<li>
				基金托管人：
				<span style="width: 200px;">${custodian}</span>
			</li>
		</ul>
		<ul>
			<li>
				基金经理：
				<span>${fundmanagers}</span>
			</li>
			<li>
				发行日期：
				<span>${issueDate}</span>
			</li> 
			<li>
				成立日期：
				<span>${upDate}</span>
			</li>
		</ul>
		<ul>
			<li>
				分红方式：
				<span>${dividendMethod}</span>
			</li>
			<li>
				收费方式：
				<span>${chargeMode}</span>
			</li>
			<li style="width: 600px;">
				业绩比较基准:
				<span>${perComBen}</span>
			</li>
		</ul>
	</div>
</div>