var page = 0;

$(function(){
	
	// 增加菜单效果
	addMenuStyle('nav04', 'active');
	addActiveStyle("fund-types li a", '', "castActive");// 增加样式

	getRecomFundsList(page);
	loadPriorPeriodCalloutList();
	loadCurrentTransferredList();
	
	$('#fund-types li a').on('click', function(){
		$("#fund-types li a").removeClass("castActive");// 清除样式
		var val = $(this).attr("data-value");// 获取点击的data-value
		addActiveStyle("fund-types li a", val, "castActive");// 增加样式
		
		getRecomFundsList(1);
		
	});
})

function openShowRecom(str){
	$('#recom-text' + str).toggle();
}

/**
 * 基金推荐
 */
function getRecomFundsList(start){
	
	var fund_types = $('#fund-types li a[class="castActive"]').attr('data-value');
	
	$("#recomFundListFDiv").empty();
	$("#pageContainer").empty();
	
	$.ajax({
		type:"post",
		async:true,
		url:"getRecomFundsList",
		data:{"pUrlName":"fundRecom","start":start, "rows":"10", "funds_types": fund_types},
		success: function(data){
			if(data != "null" && data.numFound != 0) {
				var productTable = "";
				$.each(data.indexBean,function(i,item){
					productTable += '<div class="funds-list">';
					productTable += '<div class="funds-info">';
					productTable += '<div class="funds-info-left">';
					productTable += '<div class="funds-info-left-base">';
					var y = item.recommend_fund_order;
					switch(y){
					case 1:
						productTable += '<span class="num1"></span>';
					  break;
					case 2:
						productTable += '<span class="num2"></span>';
					  break;
					case 3:
						productTable += '<span class="num3"></span>';
						break;
					default:
						productTable += '<span class="num"></span>';
					}
					productTable += '<span><a target="_blank" href="/details/view?id=' + item.recommend_fund_code + '" class="funds-type">' + item.recommend_fund_type + '</a></span>';
					productTable += '<span><a target="_blank" href="/details/view?id=' + item.recommend_fund_code + '" class="funds-name">' + item.recommend_fund_abbreviation + '</a></span>';
					productTable += '<span><a target="_blank" href="/details/view?id=' + item.recommend_fund_code + '" class="funds-code">' + item.recommend_fund_code + '</a></span>';
					productTable += '</div>';
					productTable += '<div class="funds-info-letf-number">';
					
					if(item.recommend_fund_latestnetvalue == 9999){
						productTable += '<div><span class="colorgray">---';
					}else if(item.recommend_fund_latestnetvalue > 0){
						productTable += '<div><span class="colorg">' + item.recommend_fund_latestnetvalue.toFixed(4);
					}else if(item.recommend_fund_latestnetvalue == 0){
						productTable += '<div><span class="colorgray">' + item.recommend_fund_latestnetvalue.toFixed(4);
					}else{
						productTable += '<div><span class="colorblue">' + item.recommend_fund_latestnetvalue.toFixed(4);
					}
					productTable += '</span>&nbsp;最新净值(元)</div>'
					
					
					if(item.recommend_fund_nearlydaygain == 9999){
						productTable += '<div><span class="colorgray">---';
					}else if(item.recommend_fund_nearlydaygain > 0){
						productTable += '<div><span class="colorg">' + item.recommend_fund_nearlydaygain.toFixed(2);
					}else if(item.recommend_fund_nearlydaygain == 0){
						productTable += '<div><span class="colorgray">' + item.recommend_fund_nearlydaygain.toFixed(2);
					}else{
						productTable += '<div><span class="colorblue">' + item.recommend_fund_nearlydaygain.toFixed(2) + '';
					}
					productTable += '</span>&nbsp;日涨幅(%)</div>'
					

					if(item.recommend_fund_nearlymonthgain == 9999){
						productTable += '<div><span class="colorgray">---';
					}else if(item.recommend_fund_nearlymonthgain > 0){
						productTable += '<div><span class="colorg">' + item.recommend_fund_nearlymonthgain.toFixed(2);
					}else if(item.recommend_fund_nearlymonthgain == 0){
						productTable += '<div><span class="colorgray">' + item.recommend_fund_nearlymonthgain.toFixed(2);
					}else{
						productTable += '<div><span class="colorblue">' + item.recommend_fund_nearlymonthgain.toFixed(2);
					}
					productTable += '</span>&nbsp;近一月(%)</div>'


					if(item.recommend_fund_nearlythreemonthgain == 9999){
						productTable += '<div><span class="colorgray">---';
					}else if(item.recommend_fund_nearlythreemonthgain > 0){
						productTable += '<div><span class="colorg">' + item.recommend_fund_nearlythreemonthgain.toFixed(2);
					}else if(item.recommend_fund_nearlythreemonthgain == 0){
						productTable += '<div><span class="colorgray">' + item.recommend_fund_nearlythreemonthgain.toFixed(2);
					}else{
						productTable += '<div><span class="colorblue">' + item.recommend_fund_nearlythreemonthgain.toFixed(2);
					}
					productTable += '</span>&nbsp;近三月(%)</div>'


					if(item.recommend_fund_recommendgain == 9999){
						productTable += '<div style="width: 190px;"><span class="colorgray">---';
					}else if(item.recommend_fund_recommendgain > 0){
						productTable += '<div style="width: 190px;"><span class="colorg">' + item.recommend_fund_recommendgain.toFixed(2);
					}else if(item.recommend_fund_recommendgain == 0){
						productTable += '<div style="width: 190px;"><span class="colorgray">' + item.recommend_fund_recommendgain.toFixed(2);
					}else{
						productTable += '<div style="width: 190px;"><span class="colorblue">' + item.recommend_fund_recommendgain.toFixed(2);
					}
					productTable += '</span>推荐期涨跌幅(%)</div>'

					productTable += '</div>';
					productTable += '</div>';
					productTable += '<div class="funds-info-right">';
					productTable += '<div class="funds-info-right-top"><a href="/details/view?id=' + item.recommend_fund_code + '" target="_blank" class="funds-apply">立即购买</a></div>';
					productTable += '<div class="funds-info-right-bottom"><div onclick="openShowRecom(' + i + ')" >优选理由</div></div>';
					productTable += '</div>';
					productTable += '</div>';
					productTable += '<div id="recom-text' + i + '" class="recom-text"><span>优选理由:</span>' + item.recommend_fund_recommendreason + '</div>';
					productTable += '</div>';
				});
				$("#recomFundListFDiv").append(productTable);
				
				var pageInfo = '<a onclick="getRecomFundsList(0)" href="javascript:void(0);">1</a>';
				if(data.numFound > 10){
					pageInfo += '<a onclick="getRecomFundsList(10)" href="javascript:void(0);">2</a>';
				}
				if(data.numFound > 20){
					pageInfo += '<a onclick="getRecomFundsList(20)" href="javascript:void(0);">3</a>';
				}
				$("#pageContainer").append(pageInfo);
			}
		}
	});
};




/*
 * 加载上期调出列表(luo)
 */
function loadPriorPeriodCalloutList(start,rows,type){
	var callout_mountings=$("#callout_mountings").val();
	$.ajax({
		url:"queryPriorPeriodCalloutList",
		type:"POST",
		data:{"start":start,"rows":rows,"pUrlName":"fundRecom"},
		success:function(call) {
			if(null!=call.data){
				var callout="";
				$.each(call.data,function(i,item){
					callout += '<tr>';
					callout += '<td>' + item.fundCode + '</td>';
					callout += '<td>' + item.shortName + '</td>';
					callout += '<td>' + item.reason + '</td>';
					callout += '</tr>';
				});
				$("#calloutListDiv").append(callout);
			}
			
		}
	});
}

/*
 * 加载本期调入列表(luo)
 */
function loadCurrentTransferredList(start,rows,type){
	var current_mountings = $("#current_mountings").val();
	$.ajax({
		url:"queryCurrentTransferredList",
		type:"POST",
		data:{"start":start,"rows":rows,"pUrlName":"fundRecom"},
		success:function(call) {
			if(null != call.data){
				var current = "";
				$.each(call.data,function(i,item){
					current += '<tr>';
					current += '<td>' + item.fundCode + '</td>';
					current += '<td>' + item.shortName + '</td>';
					current += '<td>' + item.reason + '</td>';
					current += '</tr>';
				});
				$("#TransferredListDiv").append(current);
			}
		}
	});
}
