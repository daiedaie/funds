<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html">
	<meta charset="utf-8">
	<title>睿博财富</title>
	<%@ include file="/common/head.jsp"%>
	<script type="text/javascript" src="/js/index.js"></script>
	<link rel="stylesheet" href="/css/home/home.css">
</head>
<body>
	<%@ include file="/common/menu.jsp"%>
	<!--banner 开始-->
	<%@ include file="banners/index_top.jsp"%>
	<!--banner 结束-->
	<!--主内容 开始-->
	<div class="main">
		<!--资讯公告 开始-->
		<div class="main_tip clearfix">
			<a class="mt_1" href="javascript:void(0);">最新资讯公告</a> <a
				class="mt_2 clearfix" href="javascript:void(0);">睿博基金就要上线了，敬请期待！！！（睿博财富将给予您最真诚的服务！）<span>2016.05.13</span></a>
			<a class="mt_3" href="javascript:void(0);">更多>></a>
		</div>
		<!--资讯公告 结束-->
		<!--睿博宝  开始-->
		<div class="rbao clearfix">
			<div class="rbao_left">
				<a href="javascript:void(0);"><img src="images/ggdownimg.jpg"></a>
				<div id="recommendProduct" class="product">
					<!-- 推荐产品列表 -->
				</div>
			</div>
			<div class="rbao_right">
				<div class="rr_top">
					<a href="javascript:void(0);"><img
						src="images/ggdownRightTop.jpg"></a>
				</div>
				<div class="rr_bot">
					<a href="knowledge/view"><img
						src="images/ggdownRightBot.jpg"></a>
				</div>
			</div>
		</div>
		<!--睿博宝  结束-->
		<!--基金新手礼 开始-->
		<div class="jjnew">
			<a href="javascript:void(0);"><img src="images/jjNew.jpg"></a>
		</div>
		<!--基金新手礼 结束-->
		<!-- 热销基金列表  开始-->
		<div class="jijinView">
			<div class="chView clearfix">
				<div class="view_left">
					<a href="/markt/view?start=3"><img
						src="images/hot_jj_leftimg.jpg"></a>
				</div>
				<div class="view_right">
					<div id="hotSellFundFDiv" class="viewBox clearfix">
<!-- 						<div class="j_view"> -->
<!-- 							<table> -->
<!-- 								<tr> -->
<!-- 									<td class="type"><span>指数型</span></td> -->
<!-- 									<td class="name">华夏基金</td> -->
<!-- 									<td class="id">005299</td> -->
<!-- 								</tr> -->
<!-- 							</table> -->
<!-- 							<p class="ptp">华夏沪深300指数增强C</p> -->
<!-- 							<div class="zx_tongji"></div> -->
<!-- 							<div class="shuzhi clearfix"> -->
<!-- 								<div class="sz_left"> -->
<!-- 									<p>1.0200</p> -->
<!-- 									<span>最新净值(元)</span> -->
<!-- 								</div> -->
<!-- 								<div class="sz_right"> -->
<!-- 									<p>15.55</p> -->
<!-- 									<span>年度涨幅(%)</span> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<a href="javascript:void(0);" class="checkBuy">立即购买</a> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
		</div>
		<!-- 热销基金列表  结束-->
		<!-- 基金定投列表  开始-->
		<div class="jijinView">
			<div class="chView clearfix">
				<div class="view_left">
					<a href="/markt/view?start=5"><img
						src="images/jj_dt_leftimg.jpg"></a>
				</div>
				<div class="view_right">
					<div id="fundPledgeFDiv" class="viewBox clearfix">
						<!-- 基金定投 index.js循环创建内容 -->
					</div>
				</div>
			</div>
		</div>
		<!-- 基金定投列表  结束-->
	</div>
	<!--主内容 结束-->
<%@ include file="/common/bottom.jsp"%>
</body>
</html>