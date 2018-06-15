
$(function(){
	//增加菜单效果
	addMenuStyle('nav01');
	
	/**
	 * 基金推荐前三条
	 */
	$.ajax({
		type:"post",
		async:true,
		url:"recom/getFirstThreeRecomFunds",
		data:{"pUrlName":"fundRecom","start":"0", "rows":"3"},
		success: function(data){
			if(data != "null") {
				var productTable = "";
				$.each(data.indexBean,function(i,item){
					productTable+="<table><tr>";
					productTable+="<td class='title'><a target='_blank' style='display:block;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;' href='details/view?id="+item.recommend_fund_code+"'><span>"+item.recommend_fund_type+"</span>"+item.recommend_fund_abbreviation+"</a></td>";
					productTable+="<td class='prdId' colspan='3'><a target='_blank' href='details/view?id="+item.recommend_fund_code+"'>"+item.recommend_fund_code+"</a></td></tr>";
					productTable+="<tr><td class='newjz'><span class='colororg'>"+item.recommend_fund_latestnetvalue.toFixed(4)+"</span>最新净值(元)</td>";
					productTable+="<td class='syl'><span class='colorblue'>"+Number(item.recommend_fund_nearlymonthgain).toFixed(2)+"%</span>本周收益率</td>";
					productTable+="<td class='sgfl'><span class='colororg'>"+Number(item.recommend_fund_buyrate).toFixed(2)+"%</span>申购费率</td>";
					productTable+="<td class='tdbuy'><a target='_blank' href='/details/view?id="+item.recommend_fund_code+"'>立即购买</a></td></tr></table>";
					$("#recommendProduct").append(productTable);
					productTable = "";
				});
			}
		}
	});
	
	/**
	 * 基金定投
	 */
	$.ajax({
		type:"post",
		async:true,
		url:"markt/getFundPledge",
		data:{"pUrlName":"fundPledge","start":"0", "rows":"12"},
		success: function(data) {
			if(data != "null") {
				var fundPledgeStr = "";
				$.each(data, function(i,item){
					fundPledgeStr += "<div class='j_view'><table><tr>";
					fundPledgeStr += "<td class='type'><span>"+item.dT_TYPE+"</span></td>";
					fundPledgeStr += "<td class='name'><a target='_blank' href='details/view?id="+item.dT_FUND_CODE+"'></a></td>";
					fundPledgeStr += "<td class='id'><a target='_blank' href='details/view?id="+item.dT_FUND_CODE+"'>"+item.dT_FUND_CODE+"</a></td></tr></table>";
					fundPledgeStr += "<p class='ptp'>"+item.dT_FUND_JC+"</p>";
					fundPledgeStr += "<div class='zx_tongji'>";
					//heighchart图表使用
					fundPledgeStr += "<div id='fundPledgeContainer"+i+"'></div>";
					fundPledgeStr += "</div><div class='shuzhi clearfix'><div class='sz_left'>";
					fundPledgeStr += "<p>"+Number(item.dT_FUND_LEASTJZ).toFixed(4)+"</p><span>最新净值(元)</span></div>";
					fundPledgeStr += "<div class='sz_right'>";
					if(item.dT_FUND_YEARZF == 9999) {
						fundPledgeStr += "<p>---</p>";
					} else {
						fundPledgeStr += "<p>"+Number(item.dT_FUND_YEARZF).toFixed(2)+"</p>";
					}
					fundPledgeStr += "<span>年度涨幅(%)</span></div></div>";
					fundPledgeStr += "<a target='_blank' class='checkBuy' href='/details/view?id="+item.dT_FUND_CODE+"'>立即购买</a></div>";
					$("#fundPledgeFDiv").append(fundPledgeStr);
					createHeighChart(item.labelList,item.dataList,"fundPledgeContainer"+i);
					fundPledgeStr = "";
					if(i==3){
						return false;
					}
				});
			}
		}
	});
	
	/*热销基金*/
	$.ajax({
		type:"post",
		async:true,
		url:"markt/getHotSellFund",
		data:{"pUrlName":"hotSellFund","start":"0", "rows":"12"},
		success: function(data) {
			if(data != "null") {
				var hotSellStr = "";
				$.each(data, function(i,item){
					hotSellStr += "<div class='j_view'><table><tr>";
					hotSellStr += "<td class='type'><span>"+item.hS_TYPE+"</span></td>";
					hotSellStr += "<td class='name'><a target='_blank' href='details/view?id="+item.hS_FUND_CODE+"'></a></td>";
					hotSellStr += "<td class='id'><a target='_blank' href='details/view?id="+item.hS_FUND_CODE+"'>"+item.hS_FUND_CODE+"</a></td></tr></table>";
					hotSellStr += "<p class='ptp'>"+item.hS_FUND_JC+"</p>";
					hotSellStr += "<div class='zx_tongji'>";
					//hotSellStr += "<div style='width: 160px; height: 120px;'><canvas id='hotSellFundCanvas"+i+"' width='160' height='120'></canvas></div>";
					hotSellStr += "<div id='hotSellFundContainer"+i+"'></div>";
					hotSellStr += "</div><div class='shuzhi clearfix'><div class='sz_left'>";
					hotSellStr += "<p>"+Number(item.hS_FUND_LEASTJZ).toFixed(4)+"</p><span>最新净值(元)</span></div>";
					hotSellStr += "<div class='sz_right'>";
					if(item.hS_FUND_YEARZF == 9999) {
						hotSellStr += "<p>---</p>";
					} else {
						hotSellStr += "<p>"+Number(item.hS_FUND_YEARZF).toFixed(2)+"</p>";
					}
					hotSellStr += "<span>年度涨幅(%)</span></div></div>";
					hotSellStr += "<a target='_blank' class='checkBuy' href='/details/view?id="+item.hS_FUND_CODE+"'>立即购买</a></div>";
					$("#hotSellFundFDiv").append(hotSellStr);
					createHeighChart(item.labelList,item.dataList,"hotSellFundContainer"+i);
					hotSellStr = "";
					if(i==3){
						return false;
					}
				});
			}
		}
	});
	
});

/*
 *创建首页heightchart图表
 *@param labelList 构成label的数据(String 数组)
 *@param dataList 构成data的数据(数组类型)
 *@param documentId 生成canvas的ID
 */
function createHeighChart(labelList,dataList,documentId){
	 $('#'+documentId).highcharts({
		 chart: {
			 type:"spline",
			 height:120,
			 marginTop : 15,
			 marginBottom : 21
		 },
         title: {
             text: null
         },
         xAxis: {
             categories: labelList,
             tickInterval:29,
             tickPosition:"inside",
             tickLength:5,
             labels:{
            	 style:{
            		 fontSize: '10px'
            	 }
             }
         },
         yAxis: {
        	 tickPixelInterval: 10,
             title: {
                 text: null
             },
             plotLines: [{
                 value: 0,
                 width: 1,
                 color: '#25a8e0'
             }],
             labels: {
            	 formatter: function() {
            	 	return this.value.toFixed(2);//这里是两位小数，你要几位小数就改成几
            	 },
	             align: 'left',
	             x: -10,
	             y:-2
	         },
	         minRange:-1
         },
         series: [{
             name: '净值',
             data:  dataList
         }],
     	 plotOptions: {
            series: {
                marker: {
                    radius: 0,  //曲线点半径，默认是4，设为0时隐藏点
                    symbol: 'diamond' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                }
            }
        },
        tooltip: {
        	valueDecimals: 4 //数据值保留小数位数
        }
     });
}


