<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="flTop clearfix">
	<div class="ftop_left">
		<h3>申购费率(前端)</h3>
		<table class="table1">
			<thead>
				<tr>
					<td>适用金额</td>
					<td>使用期限</td>
					<td>原费率</td>
					<td>睿博优惠费率</td>
				</tr>
			</thead>
			<tbody id="apply_rate_info">
			</tbody>
		</table>
		<h3>运作费用</h3>
		<table>
			<thead>
				<tr>
					<td>管理费率</td>
					<td>托管费率</td>
					<td>销售服务费率</td>
				</tr>
			</thead>
			<tbody id="oper_exp_rate_info">
			</tbody>
		</table>
	</div>
	<div class="ftop_right">
		<h3>赎回费率</h3>
		<table>
			<thead>
				<tr>
					<td>适用期限</td>
					<td>适用金额</td>
					<td>赎回费率</td>
				</tr>
			</thead>
			<tbody id="redeem_rate_info">
			</tbody>
		</table>
		<a href="javascript:void(0);">！友情提示：</a>
		<p>为保护长期投资者利益，证监会规定，本基金对持有期较短的投资者赎回时，将收取不低于0.5%比例的赎回费。该费用由基金公司收取，并计入基金财产。（详见费率表）</p>
	</div>
</div>
<div class="flBottom">
	<p>注： 在基金首次募集期购买基金的行为称为认购，认购期结束后基金需要进入不超过3个月的封闭期。
		基金封闭期结束后，您若申请购买开放式基金，习惯上称为基金申购，以区分在发行期间的认购。</p>
	<div class="clearfix">
		<span>基金申购费用计算公式：</span>
		<p>
			前端申购费用＝申购金额-申购金额 /（1＋申购费率）<br>后端申购费用＝赎回份额×申购日基金份额净值（基金份额面值）×后端申购（认购）费率
		</p>
	</div>
	<div class="clearfix">
		<span>基金赎回费用计算公式：</span>
		<p>基金赎回费用＝赎回金额×赎回费率</p>
	</div>
	<p class="x">*本基金费率来源基金公司，请以基金公司官网提供的费率数据为准。</p>
</div>