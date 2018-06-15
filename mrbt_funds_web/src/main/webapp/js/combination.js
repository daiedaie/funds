$(function(){
	addMenuStyle('nav05', 'active');
	
	$.ajax({
		url:"getList",
		type:"post",
		success: function(data){
			if(null!=data && data.length>0) {
				$.each(data,function(i,item){
					var zhbTemplateDiv = $("#zhbTemplateDiv").clone().attr("id","zhbTemplateDiv"+i);
					//拟合业绩table
					var table = "<table><tbody>";
					var m = 0;
					$.each(item.tableData,function(j,tab){
						m++;
						table+="<tr><td class='td_1'><span><a href='javascript:void(0);'>"+tab.TYPENAME+"</a>"+tab.SHORTNAME+"-"+tab.FUNDCODE+"</span></td>";
						table+="<td><span>" + Number(tab.netValue).toFixed(4) + "</span>最新净值</td>";
						if(tab.yearGain == 9999){
							table+="<td><span>---</span>近一年跌涨</td>";
						}else{
							table+="<td><span>"+Number(tab.yearGain).toFixed(2)+"%</span>近一年跌涨</td>";
						}
						table+="<td><span>" + tab.RATIO + "%</span>配置比例</td></tr>";
					});
					//补充没有数据的行
					for (var int = 0; int < 5 - m; int++) {
						table += "<tr><td></td><td></td><td></td><td></td></tr>"
					}
					
					table+="</tbody></table>";
					
					zhbTemplateDiv.find("#container").attr("id","container"+i);
					zhbTemplateDiv.find("#bing").attr("id","bing"+i);
					zhbTemplateDiv.find(".txleft").append(table);
					//piechartLegend
					var pieLegend = "";
					//图例占比说明
					var piePart = "";
					var part = item.part;
					//配置点评
					var remark = "<span>配置点评：</span>";
					remark += "<p>"+item.analysis+"</p>";
					zhbTemplateDiv.find(".pzdp.clearfix").append(remark);
					zhbTemplateDiv.css("display","").appendTo("#zhbParent");
					createLineHighChart("container"+i,item.labellist,item.datalist);
					caretePieHighChart("bing"+i,item.pieData);
					zhbtype();
				});
			}
		}
	});
	
});

/**
 * 创建曲线图
 * @param id DOM ID
 * @param labelList label列表
 * @param datalist	数据列表
 */
function createLineHighChart(id,labelList,datalist){
	$('#'+id).highcharts({
        title: {
            text: null
        },
        xAxis: {
        	tickInterval: 24,
        	categories: labelList,
            tickPosition:"inside",
            tickLength:5,
            labels:{
           	 style:{
           		 fontSize: '10px',
           	 },
            }
        },
        yAxis: {
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
		        x: -5,
		        y:1
		    },
		    tickInterval: 0.0125,
        },
        tooltip: {
            valueSuffix: ''
        },
        series: [{
            name: '值',
            data: datalist,
            color:'red'
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

/**
 * 创建饼图
 * @param id DOM id
 * @param pieData 数据列表
 */
function caretePieHighChart(id,pieData){
	$('#'+id).highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        x:-20,
        title: {
            text: null
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                },
                innerSize: "35%",
                colors:[
                    '#24a249',
                	'#f4b525',
                	'#ed1f24',
                	'#ea560e',
                ]
            }
        },
        series: [{
            type: 'pie',
            name: '占比',
            data: [{
               	name:'股票型',
              	y:30,
               	sliced:false,
               	selected:false,
               	color:'#24a249'
              },
              {
               	name:'股票型',
              	y:20,
               	sliced:false,
               	selected:false,
               	color:'#ed1f24'
              },
              {
               	name:'股票型',
              	y:20,
               	sliced:false,
               	selected:false,
               	color:'#f4b525'
              },
              {
               	name:'股票型',
              	y:30,
               	sliced:false,
               	selected:false,
               	color:'#ea560e'
              }],
        }],
    });
}

/**
 * 组合宝切换显示点击事件
 */
function zhbtype(){
	$('.zhbtype a').on('click',function(){
		var aindex=$(this).index()
		console.log(aindex);
		$(this).parent().find('a').removeClass('botten_active');
		$(this).addClass('botten_active');
		$(this).parent().parent().parent().find('.zhbleft_'+aindex).addClass('active');
		$(this).parent().parent().parent().find('.zhbleft_'+aindex).siblings().removeClass('active');

	});
	//默认选中第一项：拟合业绩
	$(".zhbtype a:eq(0)").addClass("botten_active");
}