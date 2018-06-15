<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html">
<meta charset="utf-8">
<title>睿博财富</title>
<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" href="../css/zuhebao/zhb.css">
<script type="text/javascript"></script>
<script type="text/javascript" src="../js/combination.js">
</script>
<style>
       .highcharts-legend-item,.highcharts-button{
        display: none;
       }
       .highcharts-button{
        display: none;
    }
    </style>
</head>
<body>
<!--头部 开始-->
<%@ include file="/common/menu.jsp"%>
<!--头部 结束-->
<!--banner 开始-->
<div class="banner">
	<div class="bannerbox">
		<img src="../images/zuhebanner.jpg">
	</div>
</div>
<!--banner 结束-->
<!---->
<div class="main">
	<!--睿博组合宝介绍  开始-->
		<div class="intro">
			<p class="clearfix"><span></span>睿博组合宝介绍：</p>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基金 “组合宝”是由睿博财富公募基金研究中心推出的优选基金组合。每只基金均经过专业研究人员的严格分析及筛选，免去了个人筛选基金的繁杂过程，实现轻松投资。睿博财富力求帮助每一位投资者找到最适合自己的投资组合，从而获得稳健收益。</br>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基金“组合宝”根据不同的风险偏好将基金组合分为“保守型、稳健型、平衡型、进取型”四个类型。建议客户根据自己的风险承受能力选择相应的投资组合。“一键购买”功能为客户实现一次下单，组合交易的功能，为客户节省操作时间。</br>
		</div>
	<!--睿博组合宝介绍  结束-->
	<div id="zhbParent"> </div>
	<!--组合宝  开始-->
	<div id="zhbTemplateDiv" style="display:none;" class="zhb">
		<div class="zhbox clearfix">
			<div class="zhbtype">
				<div class="tab clearfix">
					<a href="javascript:void(0);" class="t1">拟合业绩</a>
					<a href="javascript:void(0);" class="t2">配置比例</a>
					<a href="javascript:void(0);" class="t3">配置详情</a>
				</div>
			</div>
			<div class="zhbleft_0 zhexian clearfix active">
				<div class="zxleft">
					<div class="zxtop clearfix">
						<div class="sm sm_2 clearfix">
							<a href="javascript:void(0);"></a>上证综合指数
						</div>
						<div class="sm sm_1 clearfix">
							<a href="javascript:void(0);"></a>组合业绩走势
						</div>	
					</div>
					<div class="tongji">
						<div id="container" style="width:630px;height:175px;"></div>
					</div>
				</div>
				<div class="zxright">
					<span>13.6%</span>
					<p>保守型过去一年收益</p>
					<a href="javascript:void(0);">立即购买</a>
				</div>
			</div>
			<div class="zhbleft_1 bingtu clearfix">
				<div class="btleft clearfix">
					<div class="btl_1">
						<div class="bt">
							<div id="bing" style="width:170px;height:160px"></div>
						</div>
						<div class="color_1 sm">
							<a href="javascript:void(0);"></a>股票型
						</div>
						<div class="color_2 sm">
							<a href="javascript:void(0);"></a>股票型
						</div>
						<div class="color_3 sm">
							<a href="javascript:void(0);"></a>股票型
						</div>
						<div class="color_4 sm">
							<a href="javascript:void(0);"></a>股票型
						</div>
					</div>
					<div class="btl_2">
						<div class="clearfix">
							<span>股票型</span>
							<em>10%</em>
						</div>
						<div class="clearfix">
							<span>债券型</span>
							<em>60%</em>
						</div>
						<div class="clearfix">
							<span>现金管理</span>
							<em>20%</em>
						</div>
						<div class="clearfix">
							<span>其他类型</span>
							<em>10%</em>
						</div>
					</div>
				</div>
				<div class="btright">
					<span>13.6%</span>
					<p>保守型过去一年收益</p>
					<a href="javascript:void(0);">立即购买</a>
				</div>
			</div>
			<div class="zhbleft_2 tablex clearfix">
				<div class="txleft">
					
				</div>
				<div class="txright">
					<span>13.6%</span>
					<p>保守型过去一年收益</p>
					<a href="javascript:void(0);">立即购买</a>
				</div>
			</div>
		</div>
		<div class="pzdp clearfix">
			
		</div>
	</div>
	<!--组合宝  结束-->
	
	<!--免责声明 开始-->
	<div class="mianze clearfix">
		<span> 风险提示：</span>
		基金“组合宝”的本质也是投资于基金产品，基金“组合宝”的风险主要由组合中各只基金的风险因素共同决定，基金“组合宝”并不承诺收益和保本，基金“组合宝”在投资过程中，将保护本金不受损失和保持资产流动性作为首要目标。
	</div>
	<!--免责声明 结束-->
</div>
<%@ include file="/common/bottom.jsp"%>
</body>
</html>