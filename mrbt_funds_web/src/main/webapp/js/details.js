var chartType = 1;
$(function() {
	// 基金详情页第一统计图
	netValueChart(1);
	
	$('#net_value_hart a').on('click',function(){
		var num = $(this).attr("data-value");
		$('#net_value_hart a').removeClass('active')
		$(this).addClass("active");
		netValueChart(num);
	});
	
	$('#net_value_tag a').on('click', function(){
		//获取曲线data-value
		var type = $(this).attr("data-value");
		chartType = type;
		//设置样式
		$('#net_value_tag a').removeClass('active');
		$(this).addClass("active");
		
		//去掉按钮的样式
		$('#net_value_hart a').removeClass('active');
		$('#net_value_hart a[data-value=1]').addClass('active');
		netValueChart(1);
	});
	
	//基金投资，行业配置
	industryAlloca("");
	
	//获取持仓信息
	fundPositions("");
	
	//行业配置比较
	industryAllocaCompare();
	
	//获取基金经理信息
	managersInfo();
	
	//报告规模变动
	reportingPeriod();
	
	//基金费率
	rateInfo();
	
	// 同类基金对比折线统计图
	$('#container4')
			.highcharts(
					{
						chart : {
							zoomType : 'xy'
						},
						title : {
							text : null
						},
						subtitle : {
							text : null
						},
						xAxis : [ {
							categories : [ '04-25', '04-25', '04-25', '04-25',
									'04-25', '04-25', '04-25', '04-25',
									'04-25', '04-25' ],
							crosshair : true
						} ],
						yAxis : [ { // Primary yAxis
							labels : {
								format : '{value}',
								style : {
									color : Highcharts.getOptions().colors[1]
								}
							},
							title : {
								text : null,
								style : {
									color : Highcharts.getOptions().colors[1]
								}
							}
						}, { // Secondary yAxis
							title : {
								text : null,
								style : {
									color : Highcharts.getOptions().colors[0]
								}
							},
							labels : {
								format : '{value}',
								style : {
									color : '#cccccc'
								},
								x : -920,
								y : 5,
								max : 10

							},
							opposite : true
						} ],
						tooltip : {
							shared : true
						},
						legend : {
							layout : 'hide',
							align : 'left',
							x : 1000000,
							verticalAlign : 'top',
							y : 100,
							floating : true,
							backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColor)
									|| '#FFFFFF'
						},
						series : [
								{
									name : '净值',
									type : 'line',
									yAxis : 1,
									data : [ 49.9, 71.5, 106.4, 129.2, 144.0,
											176.0, 135.6, 144.0, 176.0, 135.6 ],
									tooltip : {
										valueSuffix : ''
									},
									color : '#19a9d1'

								}, {
									tooltip : {
										valueSuffix : 'c'
									}
								} ]
					});
});

/**
 * 基金净值/累计净值曲线
 * data{基金代码,区间,标签ID}
 */
function netValueChart(interval) {
	$.ajax({
		type : "POST",
		url : "getNetValueChart",
		data : {id : $('#model_code').val(), interval : interval, type : chartType},
		dataType : "json",
		cache : false,
		success : function(result) {
			if(result.code == 200){
				$('#container').highcharts({
					chart: {
						 type:"spline",
						 marginTop : 30,
						 marginBottom : 20
					},
					plotOptions: {
	                     series: {
	                    	 marker: {
	                    		 enabled: false
	                    	 }
	                     },
					},
			        title: {
			            text: null,
			        },
			        subtitle: {
			            text: null,
			        },
			        xAxis: {
			        	showLastLabel: true,
			            tickInterval:result.data.spanning,
			            tickPosition:"inside",
			            tickLength:1,
			            categories: result.data.x,
			            labels: {
			            	x:25,
			            }
			        },
			        yAxis: {
			        	tickInterval: 0.25,
			        	lineWidth: 1,
			            tickWidth: 1,
			            title: {
			                align: 'high',
			                offset: 0,
			                text: '单位(元)',
			                rotation: 0,
			                y: -15
			            },
			            labels: {
			            	formatter: function() {
			            		return this.value.toFixed(2);//这里是两位小数，你要几位小数就改成几
			            	}
			            },
			        },
			        legend: {
			            enabled: false
			        },
			        series: [{
			        	name: '净值',
			        	tooltip: {
			                valueSuffix: '元'
			            },
			            color:'#19a9d1',
			            data: result.data.y,
			        }],
			        tooltip: {
			        	valueDecimals: 4 //数据值保留小数位数
			        }
			    });
			}
		}
	});
}

/**
 * 行业配置
 * @param year
 */
function industryAlloca(year){
	$.ajax({
		type : "POST",
		async:true,
		url:"industryAlloca",
		data:{id : $('#model_code').val(), year : year},
		success: function(result){
			if(result.code == 200){
				var chart = result.data.chart;
				var title = '<span>行业配置-' + chart.shortName + '<em>(截止' + chart.endDate + '，制造占净值比为' + chart.maxProportion + '，排名第一。)</em></span>';
					title += '<div class="sel">';
					title += '查指定年度行业配置： <select>';
					var arr = result.data.year;
					for(var i = 0; i < arr.length; i ++){
						title += '<option>' + arr[i] + '年</option>';
					};
					title += '</select>';
					title += '</div>';
				$('#industry_alloca_title').append(title);
				industryAllocaChart(result.data.chart);
				industryAllocaTable(result.data.table);
			}
		}
	});
}

/**
 * 行业配置表格
 * @param table
 */
function industryAllocaTable(table){
	var show_table = "";
	for(var i = 0; i < table.length; i ++){
		show_table += '<tr>';
		show_table += '<td>' + table[i].industryId + '</td>';
		show_table += '<td style="width: 100px">' + table[i].industryName + '</td>';
		show_table += '<td><a href="javascript:void(0);">变动详情</a></td>';
		show_table += '<td>' + table[i].proportion + '%</td>';
		show_table += '<td>' + table[i].marketValues + '</td>';
		show_table += '</tr>';
	};
	$('#industry_alloca_table').append(show_table);
}

/**
 * 行业配置柱状图
 * @param chart
 */
function industryAllocaChart(chart){
	$('#container2').highcharts({
			chart : {
				zoomType : 'xy',
			},
			title : {
				text : null
			},
			subtitle : {
				text : null
			},
			xAxis : [ {
				categories : chart.industryId,
				crosshair : true
			} ],
			yAxis : [ { // Primary yAxis
				labels : {
					format : '{value}C',
					style : {
						color : Highcharts.getOptions().colors[1]
					}
				},
				title : {
					text : null,
					style : {
						color : Highcharts.getOptions().colors[1]
					}
				}
			}, { // Secondary yAxis
				title : {
					text : null,
					style : {
						color : Highcharts.getOptions().colors[0]
					}
				},
				labels : {
					format : '{value} .00%',
					style : {
						color : '#cccccc'
					},
					x : -530,
					y : 0,
					max : 10
					},
				opposite : true
			} ],
			tooltip : {
				shared : true
			},
			legend : {
				layout : 'hide',
				align : 'left',
				x : 1000000,
				verticalAlign : 'top',
				y : 100,
				floating : true,
				backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColor)
						|| '#FFFFFF'
			},
			series : [
					{
						name : '占净值比例',
						type : 'column',
						yAxis : 1,
						data : chart.proportion,
						tooltip : {
							valueSuffix : ' %'
						}
						}, {
						tooltip : {
							valueSuffix : 'c'
						}
					} ]
			});
}

/**
 * 基金持仓
 */

function fundPositions(year){
	$.ajax({
		type : "POST",
		async:true,
		url:"getFundPositions",
		data:{id : $('#model_code').val(), year : year},
		success: function(result){
			if(result.code == 200){
				var data = result.data;
				var year = data.year;
				var year_option = "";
				for(var i = 0; i < year.length; i ++){
					year_option += '<option>' + year[i] + '</option>';
				}
				$('#positionsYear').append(year_option);
				fundPositionsChart(data.chart);
				fundPositionsTable(data.table);
			}
		}
	});
}

function fundPositionsTable(table){
	var table_data = "";
	for(var i = 0; i < table.length; i ++){
		table_data += '<tr>';
		table_data += '<td>' + (i + 1) + '</td>';
		table_data += '<td><span>' + table[i].fundCode + '</span></td>';
		table_data += '<td><span>' +  table[i].shortName + '</span></td>';
		table_data += '<td><span>' +  table[i].newPrice + '</span></td>';
		table_data += '<td><span>' +  table[i].riseFall.toFixed(2) + '%</span></td>';
		table_data += '<td><a href="javascript:void(0);">变动详情</a></td>';
		table_data += '<td>' +  table[i].netValue.toFixed(2) + '%</td>';
		table_data += '<td>' +  table[i].positNumber + '</td>';
		table_data += '<td>' +  table[i].positPrice + '</td>';
		table_data += '</tr>';;
	}
	$('#positionsTable').append(table_data);
}

function fundPositionsChart(chart){
	$('#container3').highcharts({
		chart : {
			zoomType : 'xy',
		},
		title : {
			text : null
		},
		subtitle : {
			text : null
		},
		xAxis : [ {
			categories : chart.x,
			crosshair : true
		} ],
		yAxis : [ { // Primary yAxis
			labels : {
				format : '{value}C',
				style : {
					color : Highcharts.getOptions().colors[1]
				}
			},
			title : {
				text : null,
				style : {
					color : Highcharts.getOptions().colors[1]
				}
			}
		}, { // Secondary yAxis
			title : {
				text : null,
				style : {
					color : Highcharts.getOptions().colors[0]
				}
			},
			labels : {
				format : '{value} .00%',
				style : {
					color : '#cccccc'
				},
				x : -690,
				y : 5,
			},
			opposite : true
		} ],
		tooltip : {
			shared : true
		},
		legend : {
			layout : 'hide',
			align : 'left',
			x : 1000000,
			verticalAlign : 'top',
			y : 100,
			floating : true,
			backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColor)
					|| '#FFFFFF'
		},
		series : [
				{
					name : '占净值比例',
					type : 'column',
					yAxis : 1,
					data : chart.y,
					tooltip : {
						valueSuffix : ' %'
					}
					}, {
					tooltip : {
						valueSuffix : 'c'
					}
				} ]
		});
}

function industryAllocaCompare(){
	$.ajax({
		type : "POST",
		async:true,
		url:"industryAllocaCompare",
		data:{id : $('#model_code').val()},
		success: function(result){
			if(result.code == 200){
				var table = result.data.table;
				var table_data = "";
				for(var i = 0; i < table.length; i ++){
					table_data += '<tr>';
					table_data += '<td>' + table[i].indusCode + '</td>';
					table_data += '<td>' + table[i].indusName + '</td>';
					table_data += '<td>' + table[i].indusConf + '%</td>';
					table_data += '<td>' + table[i].indusAge + '%</td>';
					table_data += '<td>' + table[i].indusSper + '%</td>';
					table_data += '</tr>';
				}
				$('#indusAllCom1').append(table_data);
				$('#indusAllCom2').append(table_data);
			}
		}
	});
}

/**
 * 基金经理信息
 */
function managersInfo(){
	$.ajax({
		type : "POST",
		async:true,
		url:"fundsManagersInfo",
		data:{id : $('#model_code').val()},
		success: function(result){
			if(result.code == 200){
				var data = result.data;
				var show = "";
				for(var i = 0; i < data.length; i ++){
					var info = data[i];
					show += '<div class="jl">';
					show += '<div class="jlinfo clearfix">';
					show += '<a href="javascript:void(0);">' + info.name + '</a>';
					show += '<div>';
					show += '<span>性别：<em>' + info.sex + '</em></span> <span>学历：<em>' + info.education + '</em></span> <span';
					show += ' class="noborder">上任日期：<em>' + info.servingTime + '</em></span>';
					show += '</div>';
					show += '</div>';
					show += '<div class="jljj">';
					show += '<p> <span>-</span>' + info.desc + '</p>';
					show += '</div><table><thead><tr><td>基金代码</td><td>基金名称</td><td>基金类型</td><td>起始时间</td><td>截止时间</td><td>任职天数</td><td>任职回报</td></tr></thead>';
					show += '<tbody>';
					
					var exper = info.exper;
					for(var j = 0; j < exper.length; j ++){
						show += '<tr>';
						
						show += '<td>' + exper[j].fundsCode + '</td>';
						show += '<td>' + exper[j].shortName + '</td>';
						show += '<td>' + exper[j].fundsType + '</td>';
						show += '<td>' + exper[j].servingTime + '</td>';
						show += '<td>' + exper[j].outgoingDate + '</td>';
						show += '<td>' + exper[j].servingDay + '天</td>';
						show += '<td>' + exper[j].inReturn + '%</td>';
						
						show += '</tr>';
					}
					
					show += '</tbody></table></div>';
					$('#managerInfo').append(show);
				}
			}
		}
	});
}

/**
 * 报告规模变动
 */
function reportingPeriod(){
	$.ajax({
		type : "POST",
		async:true,
		url:"getReportingPeriod",
		data:{id : $('#model_code').val()},
		success: function(result){
			if(result.code == 200){
				reportingPeriodChart(result.data.chart);
				reportingPeriodTable(result.data.table);
			}
		}
	});
}

function reportingPeriodTable(table){
	var data = "";
	for(var i = 0; i < table.length; i ++){
		var info = table[i];
		data += '<tr>';
		data += '<td>' + info.reportDate + '</td>';
		data += '<td>' + info.purchase.toFixed(2) + '</td>';
		data += '<td>' + info.redeem.toFixed(2) + '</td>';
		data += '<td>' + info.thefinal.toFixed(2) + '</td>';
		data += '<td>' + info.rateChange.toFixed(2) + '%</td>';
		data += '<td>' + info.netAssetSize.toFixed(2) + '</td>';
		data += '</tr>';
	}
	$('#reportingPeriodTable').append(data);
}

function reportingPeriodChart(chart){
	$('#container5').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: null
        },
        subtitle: {
            text: null
        },
        xAxis: {
            categories: chart.x
        },
        yAxis: {
            min: 0,
            title: {
                text: null
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '期间申购(亿份)',
            data: chart.y1,
        	color:'#8bf6cc'
        }, {
            name: '期间赎回(亿份)',
            data: chart.y2,
            color:'#8baef6'
        }, {
            name: '期末总份额(亿份)',
            data: chart.y3,
            color:'#8bd6f6'
        },{
            type: 'spline',
            name: '净资产规模(亿元)',
            data: chart.y4,
            marker: {
                lineWidth: 2,
                lineColor: Highcharts.getOptions().colors[3],
                fillColor: 'white'
           }}]
    });
}

/**
 * 基金费率
 */
function rateInfo(){
	$.ajax({
		type : "POST",
		async:true,
		url:"getRateInfo",
		data:{id : $('#model_code').val()},
		success: function(result){
			if(result.code == 200){
				applyRateInfo(result.data.apply);
				redeemRateInfo(result.data.redeem);
				operExpRateInfo(result.data.oper_exp);
			}
		}
	});
}

function operExpRateInfo(oper_exp){
	if(oper_exp != null){
		var data = "";
		data += '<tr>';
		data += '<td>' + oper_exp.managed + '%(每年)</td>';
		data += '<td>' + oper_exp.management + '%(每年)</td>';
		data += '<td>---</td>';
		data += '</tr>';
		$('#oper_exp_rate_info').append(data);
	}
}

function redeemRateInfo(redeem){
	if(redeem != null){
		var data = "";
		for(var i = 0; i < redeem.length; i ++){
			data += '<tr>';
			data += '<td>' + redeem[i].title + '</td>';
			data += '<td>---</td>';
			data += '<td>' + redeem[i].rate + '%</td>';
			data += '</tr>';
		}
		$('#redeem_rate_info').append(data);
	}
}

function applyRateInfo(apply){
	if(apply != null){
		var data = "";
		for(var i = 0; i < apply.length; i ++){
			data += '<tr>';
			data += '<td>' + apply[i].title + '</td>';
			data += '<td>---</td>';
			data += '<td>' + apply[i].rate + '%</td>';
			data += '<td>' + (apply[i].rate * 0.4).toFixed(2) + '%</td>';
			data += '</tr>';
		}
	}
}