/**
 * 判断是否刷新分页效果变量
 */
var isPageDivCreate = true;
/**
 * 存放基金公司名称map变量
 */
var nameDatas = null;
/**
 * 标示点击筛选 0，输入框筛选 1，用于区分点击和输入筛选的不同查询接口
 */
var type = 0;
$(function() {
	// 增加菜单效果
	addMenuStyle('nav02', 'active');

	// 初始化筛选项的默认值
	setCastSurly();
	
	//判断是否热销基金接口进入
	isHotsaleFunIn();
	
	//清空搜索框文字
	clearSerachText();
	
	//筛选项点击事件
	/**
	 * 基金定投筛选点击事件
	 */
	$("#case-types li a").click(function() {
		clearCompareDiv();
		resetOrder();
		isPageDivCreate = true;
		var val = $(this).attr("data-value");// 获取点击的data-value
		$("#case-types li a").removeClass("castActive");// 清除样式
		addActiveStyle("case-types li a", val, "castActive");// 增加样式
		loadFundMarketList(packageData());
		clearSerachText();
	});
	
	/**
	 * 基金类型筛选点击事件
	 */
	$("#fund-types li a").click(function() {
		clearCompareDiv();
		resetOrder();
		isPageDivCreate = true;
		var val = $(this).attr("data-value");// 获取点击的data-value
		$("#fund-types li a").removeClass("castActive");// 清除样式
		addActiveStyle("fund-types li a", val, "castActive");// 增加样式
		loadFundMarketList(packageData());
		clearSerachText();
	});
	
	/**
	 * 基金规模筛选点击事件
	 */
	$("#money-types li a").click(function() {
		clearCompareDiv();
		resetOrder();
		isPageDivCreate = true;
		var val = $(this).attr("data-value");// 获取点击的data-value
		$("#money-types li a").removeClass("castActive");// 清除样式
		addActiveStyle("money-types li a", val, "castActive");// 增加样式
		loadFundMarketList(packageData());
		clearSerachText();
	});
	
	/** 
	 * 基金公司名称首字母点击事件
	 */
	$("#company-types li a").click(function() {
		var val = $(this).attr("data-value");// 获取点击的data-value

		if(val == 0){
			$('#company-names').hide();
		}else{
			$('#company-names').show();
		}
		
		$("#company-types li a").removeClass("castActive");// 清除样式
		addActiveStyle("company-types li a", val, "castActive");// 增加样式
		//如果为0表示选中 ‘不限制’，触发加载数据事件
		if(val=='0'){
			clearCompareDiv();
			resetOrder();
			isPageDivCreate = true;
			//删除基金公司名称选中样式
			$("#company-names span a").removeClass("castActive");
			loadFundMarketList(packageData());
		}
		var str = $(this).attr("data-info");
		getFundCompany(str);
		clearSerachText();
	});
	
	/**
	 * 基金主题筛选点击事件
	 */
	$("#fund-themes li a").click(function() {
		clearCompareDiv();
		resetOrder();
		isPageDivCreate = true;
		var val = $(this).attr("data-value");
		$("#fund-themes li a").removeClass("castActive");
		addActiveStyle("fund-themes li a", val, "castActive");
//		loadFundMarketList(packageData()); 暂不启用
		clearSerachText();
	});
	
	/**
	 * 基金排序点击事件,默认DESC
	 */
 	$("#orders a").click(function() {
 		clearCompareDiv();
 		isPageDivCreate = true;
 		$("#orders a").removeClass("hover");
		var val = $(this).attr("data-info");
		$("#orders a[data-info='"+val+"']").addClass("hover");
		var data;
		if(type==1){
			data = {"param":$("#searchText").val()};
		}else {
			data = packageData();
		}
		data.order = val;
		//排序类型 desc/asc
		var orderType = $(this).attr("data-order");
		data.orderType = orderType;
		if(orderType=="asc") {
			$("#orders a[data-info='"+val+"']").attr("data-order","desc");
		} else {
			$("#orders a[data-info='"+val+"']").attr("data-order","asc");
		}
		$("#orders a[data-info!='"+val+"']").attr("data-order","desc");
		loadFundMarketList(data);
	});
 	
 	/**
 	 * 重置选项点击事件
 	 */
 	$("#reloadCondition").click(function(){
 		clearCompareDiv();
 		resetOrder();
 		isPageDivCreate = true;
 		$(".shaixuan ul li a").removeClass("castActive");
 		$("#company-names p").empty();
 		addActiveStyle("case-types li a", 0, "castActive");
 		addActiveStyle("fund-types li a", 0, "castActive");
 		addActiveStyle("money-types li a", 0, "castActive");
 		addActiveStyle("company-types li a", 0, "castActive");
 		addActiveStyle("fund-themes li a", 0, "castActive");
 		var datas = packageData();
 		loadFundMarketList(datas);
 	});
 	
	//加载基金超市列表
	loadFundMarketList(packageData());
	searchTextInput();
	closeSearchDatagrid();
});

//设置默认选中项
function setCastSurly() {
	var is_cast = $('#cast_surly').val();
	//基金定投
	if(Number(is_cast)==1){
		addActiveStyle("case-types li a", 1, "castActive");
		addActiveStyle("fund-types li a", 0, "castActive");
		addActiveStyle("money-types li a", 0, "castActive");
		addActiveStyle("company-types li a", 0, "castActive");
	} else {
		addActiveStyle("case-types li a", is_cast, "castActive");
		addActiveStyle("fund-types li a", is_cast, "castActive");
		addActiveStyle("money-types li a", is_cast, "castActive");
		addActiveStyle("company-types li a", is_cast, "castActive");
	}
}

/**
 * 判断是否从首页热销基金接口进入
 */
function isHotsaleFunIn() {
	var hotsale = $("#hotsale").val();
	if(1==hotsale) {
		$("#orders a[data-info='2']").addClass("hover");
	}
}

/**
 * 搜索按钮点击事件
 */
function fundSearch(t){
	var val = $("#searchText").val().trim();
	if(null!=val && val!=""){
		datas = {"param":val};
		datas.pUrlName = "fundMarketSearch";
		$.ajax({
			url:"queryFundMarketList",
			type:"post",
			data:datas,
			success:function(data) {
				if(null!=data && null!=data.indexBean){
					var table = "<table cellpadding='5px;'>"
						$.each(data.indexBean,function(i ,item){
							table += "<tr><td style='width:86px;'><a target='_blank' href='/details/view?id="+item.market_fund_code+"'>"+item.market_fund_code+"</a></td>" +
									 "<td style='width:86px;'><a target='_blank' href='/details/view?id="+item.market_fund_code+"'>"+item.market_fund_shortname+"</a></td>" +
									 "<td style='width:86px;'><a target='_blank' href='/details/view?id="+item.market_fund_code+"'>"+item.market_fund_type+"</a></td></tr>";
						});
					table += "</table>";
				}
				$("#searchDatagrid div:eq(2)").empty().append(table);
				$("#searchDatagrid").show();
				
			}
		});
	}
}

/**
 * 根据跳转位置加载基金超市列表
 */
function loadFundMarketList(datas){
	//根据param参数是否存在判断是点击筛选还是输入框筛选
//	if(undefined==datas.param) {
//		type = 0;//点击筛选
//		datas.pUrlName = "fundMarket";
//	}else {
//		type = 1;//输入框筛选
//		datas.pUrlName = "fundMarketSearch";
//	}
	datas.pUrlName = "fundMarket";
	$.ajax({
		url:"queryFundMarketList",
		type:"post",
		data:datas,
		success:function(data) {
			var fundList = "";
			if(null!=data && null!=data.indexBean){
				$.each(data.indexBean,function(i,item){
					fundList += "<div class='jvebox'>";
					fundList += "<div class='jveT_1 clearfix'>";
					fundList += "<div class='jveT_1_left clearfix'>";
					fundList += "<input value='"+item.market_fund_code+"' type='checkbox' id='checkbox-"+item.market_fund_code+"' class='regular-checkbox' />";
					fundList += "<label for='checkbox-"+item.market_fund_code+"'></label>";
					fundList += "<div class='prdName' style='width:530px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'>";
					fundList += "<span>"+item.market_fund_type+"</span>";
					fundList += "<a target='_blank' style='margin-right:20px;' href='/details/view?id="+item.market_fund_code+"'>";
					fundList += "<em>"+item.market_fund_code+"</em>";
					fundList += "</a>";
					fundList += "<a target='_blank' href='/details/view?id="+item.market_fund_code+"'>"+item.market_fund_shortname+"</a>";
					fundList += "</div>"
					if(item.market_fund_daygain > 0){
						fundList += "<em style='color: #ea560e;'>" + item.market_fund_latestnetvalue.toFixed(4) + "</em>最新净值(元)";
					}else if(item.market_fund_daygain == 0){
						fundList += "<em style='color: #cccccc;'>" + item.market_fund_latestnetvalue.toFixed(4) + "</em>最新净值(元)";
					}else{
						fundList += "<em style='color: #ea560e; margin-right: 5px;  font-size: 25px;'>" + item.market_fund_latestnetvalue.toFixed(4) + "</em>最新净值(元)";
					}
					fundList += "</div>";
					fundList += "<div class='jveT_1_right clearfix'><div class='jtr1_left'><a href='javascript:void(0);'>基金规模<span>"+Number(item.market_fund_scale).toFixed(1)+"亿</span></a>";
					fundList += "<a href='javascript:void(0);'>申购费率<span>"+Number(item.market_fund_buyrate).toFixed(2)+"%</span></a></div>";
					fundList += "<div class='jtr1_right'><a target='_blank' href='/details/view?id="+item.market_fund_code+"'>立即购买</a></div></div></div><div class='jveT_2 clearfix'>";
					if(item.market_fund_daygain == 9999){
						fundList += "<div><span class='colorg'>---</span>日涨幅(%)</div>";
					}else if(item.market_fund_daygain > 0){
						fundList += "<div><span class='colorg'>"+ item.market_fund_daygain.toFixed(2)+"</span>日涨幅(%)</div>";
					}else if(item.market_fund_daygain == 0){
						fundList += "<div><span class='colorgray'>"+item.market_fund_daygain.toFixed(2)+"</span>日涨幅(%)</div>";
					}else{
						fundList += "<div><span class='colorblue'>"+item.market_fund_daygain.toFixed(2)+"</span>日涨幅(%)</div>";
					}
					
					if(item.market_fund_nearlymonthgain == 9999){
						fundList += "<div><span class='colorg'>---</span>近一月(%)</div>";
					}else if(item.market_fund_nearlymonthgain > 0){
						fundList += "<div><span class='colorg'>"+item.market_fund_nearlymonthgain.toFixed(2)+"</span>近一月(%)</div>";
					}else if(item.market_fund_nearlymonthgain == 0){
						fundList += "<div><span class='colorgray'>"+item.market_fund_nearlymonthgain.toFixed(2)+"</span>近一月(%)</div>";
					}else{
						fundList += "<div><span class='colorblue'>"+item.market_fund_nearlymonthgain.toFixed(2)+"</span>近一月(%)</div>";
					}
					
					if(item.market_fund_nearlythreemonthgain == 9999){
						fundList += "<div><span class='colorg'>---</span>近三月(%)</div>";
					}else if(item.market_fund_nearlythreemonthgain > 0){
						fundList += "<div><span class='colorg'>"+item.market_fund_nearlythreemonthgain.toFixed(2)+"</span>近三月(%)</div>";
					}else if(item.market_fund_nearlythreemonthgain == 0){
						fundList += "<div><span class='colorgray'>"+item.market_fund_nearlythreemonthgain.toFixed(2)+"</span>近三月(%)</div>";
					}else{
						fundList += "<div><span class='colorblue'>"+item.market_fund_nearlythreemonthgain.toFixed(2)+"</span>近三月(%)</div>";
					}
					
					if(item.market_fund_yeargain == 9999){
						fundList += "<div><span class='colorg'>---</span>今年以来(%)</div>";
					}else if(item.market_fund_yeargain > 0){
						fundList += "<div><span class='colorg'>"+item.market_fund_yeargain.toFixed(2)+"</span>今年以来(%)</div>";
					}else if(item.market_fund_yeargain == 0){
						fundList += "<div><span class='colorgray'>"+item.market_fund_yeargain.toFixed(2)+"</span>今年以来(%)</div>";
					}else{
						fundList += "<div><span class='colorblue'>"+item.market_fund_yeargain.toFixed(2)+"</span>今年以来(%)</div>";
					}

					if(item.market_fund_yeargain == 9999){
						fundList += "<div><span class='colorg'>---</span>近一年(%)</div>";
					}else if(item.market_fund_yeargain > 0){
						fundList += "<div><span class='colorg'>"+item.market_fund_yeargain.toFixed(2)+"</span>近一年(%)</div>";
					}else if(item.market_fund_yeargain == 0){
						fundList += "<div><span class='colorgray'>"+item.market_fund_yeargain.toFixed(2)+"</span>近一年(%)</div>";
					}else{
						fundList += "<div><span class='colorblue'>"+item.market_fund_yeargain.toFixed(2)+"</span>近一年(%)</div>";
					}

					if(item.market_fund_threeyeargain == 9999){
						fundList += "<div><span class='colorg'>---</span>近三年(%)</div>";
					}else if(item.market_fund_threeyeargain > 0){
						fundList += "<div><span class='colorg'>"+item.market_fund_threeyeargain.toFixed(2)+"</span>近三年(%)</div>";
					}else if(item.market_fund_threeyeargain == 0){
						fundList += "<div><span class='colorgray'>"+item.market_fund_threeyeargain.toFixed(2)+"</span>近三年(%)</div>";
					}else{
						fundList += "<div><span class='colorblue'>"+item.market_fund_threeyeargain.toFixed(2)+"</span>近三年(%)</div>";
					}
					fundList += "</div></div>";
				});
				$("#marketFundListFDiv").empty().append(fundList);
				//创建分页
				if(isPageDivCreate){
					createPageDiv(datas.rows,data.numFound,type);
					isPageDivCreate = false;
				}
				
				//绑定复选框点击事件
				$("[id^='checkbox-']").change(function(){
					$('#compareDivSpan').hide();
					var inputId = $(this).next().attr("for");
					var code = $("#"+inputId).val();
					if($(this).get(0).checked){
						if($("#compareDiv a[class!='duibi']").length==5) {
							alert("最多选择五条对比基金!");
							$(this).get(0).checked = false;
							return false;
						}
						var compareDivText = "<a data-val='"+code+"' href='javascript:void(0);'>"+code+"<span onclick='removeCompareSpan(this)'>×</span></a>";
						$("#compareDiv").append(compareDivText);
					}else {
						$("#compareDiv a[data-val='"+code+"']").remove();
					}
				});
				
			}else {
				$("#pageDiv").empty();
				fundList += "<div style='text-align:center;'><h1>暂无数据</h1></div>"
				$("#marketFundListFDiv").empty().append(fundList);
			}
			
		}
	});
}

/**
 * 组装请求参数,不包含排序
 */
function packageData(){
	//定投
	var castsurely = $("#case-types .castActive").attr("data-info");
	//类型
	var type = $("#fund-types .castActive").attr("data-info");
	//规模
	var scale = $("#money-types .castActive").attr("data-info");
	if(undefined!=scale && scale!="") {
		var scales = scale.split(",");
		if(scales.length==1) {
			scale = Number(scales[0])*100000000 + " TO *";
		} else {
			scale = Number(scales[0])*100000000 + " TO " + Number(scales[1])*100000000;
		}
	}
	//公司名称
	var company = $("#company-names .castActive").attr("data-info");
	//基金主题
	var theme = $("#fund-themes .castActive").attr("data-info");
	//组装参数
	var data = {"castsurely":judgeIsUndefined(castsurely),"type":judgeIsUndefined(type),"scale":judgeIsUndefined(scale),"company":judgeIsUndefined(company),"theme":judgeIsUndefined(theme)};
	//热销基金
	var hotsale = $("#hotsale").val();
	//如果是热销基金，需要加上排序
	if(1==hotsale){
		var hotsaleorder = $("#orders a.hover").attr("data-info");
		var hotsaleorderType = $("#orders a.hover").attr("data-order");
		data.order = hotsaleorder;
		data.orderType = hotsaleorderType;
		$("#orders a.hover").attr("data-order","asc");
		$("#hotsale").val(0);
	}
	return data;
}

/**
 * 将undefined和空字符串转换成Null
 * @param param
 * @returns 如果有值返回，没值返回null
 */
function judgeIsUndefined(param){
	if(undefined==param || null==param || ""==param) {
		return null;
	}
	return param;
}

/**
 * 创建分页div内容
 * @param count 总条数
 * @param rows 一页显示条数
 */
function createPageDiv(rows,count,type){
	//默认十条
	if(undefined==rows || null==rows || ""==rows){
		rows = 10;
	}
	$("#pageDiv").empty();
	var page = "";
	page += "<ul style='float:right;' class='clearfix'>";
	page += "<li><a id='pagePrev' href='javascript:void(0);' class='prev'></a></li>";
	page += "<li class='clearfix'>";
	page += "<a href='javascript:void(0);' data-info='1' class='picked'>1</a>"; 
	//页数
	var pages = Math.ceil(count/rows);
	for(var i = 2 ;i <= pages; i++){
		if(i>8){
			page += "<a href='javascript:void(0);' style='display:none;' data-info='"+i+"' >"+i+"</a> ";
		}else {
			page += "<a href='javascript:void(0);' data-info='"+i+"' >"+i+"</a> ";
		}
	}
	page += "</li>";
	if(Math.ceil(count/rows)>8){
		page += "<span>···</span>";
	}
	page += "<li><a id='pageNext' href='javascript:void(0);' class='next'></a></li>";
	page += "<li class='tiaoz clearfix'><input type='text'> ";
	page += "<a id='pageGoto' href='javascript:void(0);'>跳转</a></li>";
	page += "</ul>";
	$("#pageDiv").append(page);
	//默认隐藏上一页按钮
	$("#pagePrev").css("display","none");
	//少于八页默认隐藏下一页按钮
	if(pages<=8){
		$("#pageNext").css("display","none");
	}
	//绑定翻页按钮点击事件
	//上一页
	pagePrev(pages,type);
	//下一页
	pageNext(pages,type);
	//输入跳转
	pageGoto(pages,type);
	//点击跳转
	pagePick(pages,type);
}

/**
 * 上一页
 */
function pagePrev(pages,type){
	$("#pagePrev").click(function(){
		//清空输入框中的值
		var val = $("#pageGoto").prev().val("");
		$("#pageNext").css("display","");
		var val = Number($("#pageDiv ul li a.picked").attr("data-info"));
		if(val-1==1){
			$("#pagePrev").css("display","none");
		}
		val = val-1>0?val-1:1;
		$(".page ul li a").removeClass("picked");
		$("#pageDiv ul li a[data-info='"+val+"']").addClass("picked");
		var datas;
		if(type==1){
			datas = {"param":$("#searchText").val()};
			datas.start = (Number(val)-1)*10;
		}else {
			datas = packageData();
			datas.start = (Number(val)-1)*10;
		}
		//排序参数
		datas = getOrderDataForPage(datas);
		loadFundMarketList(datas);
		showPageList(pages);
		compareReckeckedDelay();
	});
}

/**
 * 下一页
 */
function pageNext(pages,type){
	$("#pageNext").click(function(){
		//清空输入框中的值
		var val = $("#pageGoto").prev().val("");
		$("#pagePrev").css("display","");
		var val = Number($("#pageDiv ul li a.picked").attr("data-info"));
		if(val+1==pages){
			$("#pageNext").css("display","none");
		}
		val = pages-val>0?val+1:pages;
		$(".page ul li a").removeClass("picked");
		$("#pageDiv ul li a[data-info='"+val+"']").addClass("picked");
		var datas;
		if(type==1){
			datas = {"param":$("#searchText").val()};
			datas.start = (Number(val)-1)*10;
		}else {
			datas = packageData();
			datas.start = (Number(val)-1)*10;
		}
		//排序参数
		datas = getOrderDataForPage(datas);
		loadFundMarketList(datas);
		showPageList(pages);
		compareReckeckedDelay();
	});
}

/**
 * 输入跳转
 */
function pageGoto(pages,type){
	$("#pageGoto").click(function(){
		var val = $("#pageGoto").prev().val();
		var datas;
		if(type==1){
			datas = {"param":$("#searchText").val()};
			datas.start = (Number(val)-1)*10;
		}else {
			datas = packageData();
			datas.start = (Number(val)-1)*10;
		}
		//如果输入的页数超过最高页，自动跳转最后一页，显示最后一页页数
		if(val > pages){
			datas.start = (pages-1)*10;
			$("#pageGoto").prev().val(pages);
			val = pages;
		}
		var datainfo = $(".page ul li a.picked").attr("data-info");
		//如果输入页数和当前页数相同，不发送请求
		if(val == datainfo) {
			return false;
		}
		//如果‘第一页’，隐藏‘上一页’
		if(val==1){
			$("#pagePrev").css("display","none");
		} else {
			$("#pagePrev").css("display","");
		}
		//如果‘最后一页’，隐藏‘下一页’
		if(val==pages){
			$("#pageNext").css("display","none");
		} else {
			$("#pageNext").css("display","");
		}
		$(".page ul li a").removeClass("picked");
		$("#pageDiv ul li a[data-info='"+val+"']").addClass("picked");
		//排序参数
		datas = getOrderDataForPage(datas);
		loadFundMarketList(datas);
		showPageList(pages);
		compareReckeckedDelay();
	});
}

/**
 * 选中指定页
 */
function pagePick(pages,type){
	$(".page li:eq(1) a").click(function(){
		//清空输入框中的值
		var val = $("#pageGoto").prev().val("");
		var datainfo = $(".page ul li a.picked").attr("data-info");
		var val = $(this).attr("data-info");
		//如果点击页为当前选中页，不发送请求
		if(datainfo==val) {
			return false;
		}
		$(".page ul li a").removeClass("picked");
		$(this).addClass("picked");
		if(val==1){
			$("#pagePrev").css("display","none");
		} else {
			$("#pagePrev").css("display","");
		}
		if(val==pages){
			$("#pageNext").css("display","none");
		} else {
			$("#pageNext").css("display","");
		}
		var datas;
		if(type==1){
			datas = {"param":$("#searchText").val()};
			datas.start = (Number(val)-1)*10;
		}else {
			datas = packageData();
			datas.start = (Number(val)-1)*10;
		}
		//排序参数
		datas = getOrderDataForPage(datas);
		loadFundMarketList(datas);
		showPageList(pages);
		compareReckeckedDelay();
	});
}

/**
 * 翻页滑动
 * @param pages 总页数
 */
function showPageList(pages){
	var val = Number($("#pageDiv ul li a.picked").attr("data-info"));
	$("#pageDiv ul li:eq(1) a").css("display","none");
	//以选中页为中心，左边显示四个，右边显示三个，共八个；如果是首页或者末页，网前后推七条显示
	if((val-4) > 0 && (val+3)<pages){
		for(var i = val-4; i <= (val+3<8?8:val+3); i++) {
			$("#pageDiv ul li a[data-info='"+i+"']").css("display","");
		}
	}else if((val-4)<=0){
		for(var i = 1; i <= 8; i++) {
			$("#pageDiv ul li a[data-info='"+i+"']").css("display","");
		}
	}else if((val+3)>=pages){
		for(var i = pages-7; i <= pages; i++) {
			$("#pageDiv ul li a[data-info='"+i+"']").css("display","");
		}
		$("#pageDiv ul span").css("display","none");
	}
	
	if(Number(pages)-val>3){
		$("#pageDiv ul span").css("display","");
	}
}

/**
 * 获取公司名称列表
 * @param str 首字母
 */
function getFundCompany(str){
	if(null!=nameDatas){
		companyNamePick(nameDatas,str);
	} else {
		$.ajax({
			url:"getMCByFristWord",
			type:"get",
			success:function(data){
				nameDatas = data;
				companyNamePick(data,str);
			}
		});
	}
}
/**
 * 基金公司名称列表组装并绑定点击事件
 * @param data 公司MAP
 * @param str 首字母
 */
function companyNamePick(data,str){
	var names = "";
	if(undefined!=data[str] && null!=data[str]){
		names += str+": ";
		$.each(data[str],function(i,item){
			names += "<span><a data-info='"+item+"' data-value='"+i+"' href='javascript:void(0);'>"+item+"</a></span>&nbsp;&nbsp;";
		});
	}else if(str!="0"){
		names += str+": ";
		names += "<span style='color:red'>暂无此基金</span>&nbsp;&nbsp;";
	}
	$("#company-names p").empty().append(names);
	/**
	 * 基金公司名称筛选点击事件
	 */
	$("#company-names span a").click(function() {
		clearSerachText();
		clearCompareDiv();
		resetOrder();
		isPageDivCreate = true;
		var val = $(this).attr("data-value");// 获取点击的data-value
		$("#company-names span a").removeClass("castActive");// 清除样式
		addActiveStyle("company-names span a", val, "castActive");// 增加样式
		loadFundMarketList(packageData());
	});
}

/**
 * 重置排序
 */
function resetOrder(){
	$("#orders a").removeClass("hover");
	$("#orders a").attr("data-order","desc");
}

/**
 * 对比框X点击事件
 */
function removeCompareSpan(ts){
	$(ts).parent().remove();
	var val = $(ts).parent().attr("data-val");
	var check = document.getElementById("checkbox-"+val);
	if(check!=undefined) {
		check.checked = false;
	}
	if($("#compareDiv a[class!='duibi']").length==0) {
		$('#compareDivSpan').show();
	}
}

/**
 * 翻页后绑定勾选框
 */
function reChecked(){
	var a = $("#compareDiv a[class!='duibi']");
	if(a.length!=0) {
		a.each(function(){
			var code = $(this).attr("data-val");
			var check = document.getElementById("checkbox-"+code);
			if(check!=undefined) {
				check.checked = true;
			}
		});
	}
}

/**
 *清空对比框
 */
function clearCompareDiv(){
	$("#compareDiv a[class!='duibi']").remove();
}

/**
 * 获取排序数据，翻页使用
 */
function getOrderDataForPage(data){
	var order = $("#orders a[class='hover']");
	if(undefined!=order && order.length>0){
		var val = order.attr("data-info");
		data.order = val;
		var orderType = order.attr("data-order");
		//排序事件点击后会变更排序规则，分页需还原
		if("desc"==orderType) {
			orderType = "asc";
		} else {
			orderType = "desc";
		}
		data.orderType = orderType;
	}
	return data;
}

/**
 * 基金对比按钮点击事件
 */
function compareFunds() {
	var codes = "";
	var a = $("#compareDiv a[class!='duibi']");
	if(a.length!=0) {
		var x = 1;
		a.each(function(){
			var code = $(this).attr("data-val");
			if(x == a.length){
				codes += code;
			}else{
				codes += code+",";
			}
			x++;
		});
	}
	window.location.href="/contrast/view?ids=" + codes;
}

/**
 * 清空搜索框
 */
function clearSerachText() {
	$("#searchText").val("");
}

/**
 * 翻页后重新勾选上对比框中的对应数据，需要延时执行
 */
function compareReckeckedDelay() {
	setTimeout(reChecked,300);
}

/**
 * 搜索框事件监听
 */
function searchTextInput(){
	$("#searchText").on("keyup",function(){
		var val = $(this).val();
		serchTextInputPost(val);
	});
	
	$("#searchText").on("focus",function(){
		var val = $(this).val();
		serchTextInputPost(val);
	});
	
	//点击弹出框以外的区域隐藏弹出框
	$("#searchDatagrid").parent().on("click",function(e){
		e = e ? e:window.event; 
		window.event ? window.event.cancelBubble = true : e.stopPropagation();
	});
	
	document.onclick = function(){
		$("#searchDatagrid").hide();
	}
}

/**
 * 搜索框请求事件
 * @param val
 */
function serchTextInputPost(val){
	if(null!=val && val.trim()!=""){
		datas = {"param":val};
		datas.pUrlName = "fundMarketSearch";
		$.ajax({
			url:"queryFundMarketList",
			type:"post",
			data:datas,
			success:function(data) {
				if(null!=data && null!=data.indexBean){
					var table = "<table cellpadding='5px;'>"
						$.each(data.indexBean,function(i ,item){
							table += "<tr><td style='width:86px;'><a target='_blank' href='/details/view?id="+item.market_fund_code+"'>"+item.market_fund_code+"</a></td>" +
									 "<td style='width:86px;'><a target='_blank' href='/details/view?id="+item.market_fund_code+"'>"+item.market_fund_shortname+"</a></td>" +
									 "<td style='width:86px;'><a target='_blank' href='/details/view?id="+item.market_fund_code+"'>"+item.market_fund_type+"</a></td></tr>";
						});
					table += "</table>";
				}
				$("#searchDatagrid div:eq(2)").empty().append(table);
				$("#searchDatagrid").show();
				//跳转详情页后关闭下拉框
				$("#searchDatagrid table a").on("click", function(){
					$("#searchDatagrid").hide();
				});
			}
		});
	}
}

/**
 * 搜索框下拉列表关闭事件
 */
function closeSearchDatagrid(){
    $("#searchDatagrid div:eq(0)").on("click",function(){
    	$("#searchDatagrid").hide();
    })
}

