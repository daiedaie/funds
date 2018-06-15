var company = "";
var fondJson = "";

$(function() {
	
	//查询公司名称数据
	getFoudCompany();
	
	$("[name = show_check]:checkbox").change(function() { 
		if($(this).val() == 0){
			if($(this).prop('checked')){
				$('[value="0"]:checkbox').attr('checked',true);
				$('[value="1"]:checkbox').attr('checked',true);
				$('[value="2"]:checkbox').attr('checked',true);
				$('[value="3"]:checkbox').attr('checked',true);
				$('[value="4"]:checkbox').attr('checked',true);
				$('[value="5"]:checkbox').attr('checked',true);
				$('.box_1').show();
				$('.box_2').show();
				$('.box_3').show();
				$('.box_4').show();
				$('.box_5').show();
			}else{
				$('[value="0"]:checkbox').attr('checked',false);
				$('[value="1"]:checkbox').attr('checked',false);
				$('[value="2"]:checkbox').attr('checked',false);
				$('[value="3"]:checkbox').attr('checked',false);
				$('[value="4"]:checkbox').attr('checked',false);
				$('[value="5"]:checkbox').attr('checked',false);
				$('.box_1').hide();
				$('.box_2').hide();
				$('.box_3').hide();
				$('.box_4').hide();
				$('.box_5').hide();
			}
		}else{
			if($(this).prop('checked')){
				$('[value="'+ $(this).val() +'"]:checkbox').attr('checked',true);
				$('.box_' + $(this).val()).show();
			}else{
				$('[value="'+ $(this).val() +'"]:checkbox').attr('checked',false);
				$('.box_' + $(this).val()).hide();
			}
		}
		
		if($('input:checkbox[value="1"]').prop('checked') &&
				$('input:checkbox[value="2"]').prop('checked') &&
					$('input:checkbox[value="3"]').prop('checked') &&
						$('input:checkbox[value="4"]').prop('checked') &&
							$('input:checkbox[value="5"]').prop('checked')){
			$('input:checkbox[value="0"]').attr('checked', true);
		}else{
			$('input:checkbox[value="0"]').attr('checked', false);
		}
	}); 
	
	$('.ob_2 input').keypress(function(){
		$.ajax({
			type : "POST",
			async:true,
			url:"contrasFullTextSearch",
			data:{param : $(this).attr('value')},
			success: function(result){
				console.log(result)
			}
		});
	});
	
	
	//单击下拉框，生成input的下拉菜单
	$('.ob_2 select').on('change', function() {
		var value = $(this).find("option:selected").val();
		var txt = $(this).find("option:selected").text();// 选中的文本
		if(value != 0){
			var num = $(this).attr('data-value');
			$('#inputx_' + num + 'a').empty();
			var funds = fondJson['' + value + ''];
			
			var str = '<table id="menu_table"><tr><th>基金代码</th><th>基金类型</th></tr>';
			
			for (var int = 0; int < funds.length; int++) {
				str += '<tr onclick=addFundContrast("' + funds[int].fundCode + '","' + num +'")>';
				str += '<td>';
				str += '<a href="javascript:void(0);">' + funds[int].fundCode + '</a>';
				str += '</td>';
				str += '<td>';
				str += '<a href="javascript:void(0);">' + funds[int].fundName + '</a>';
				str += '</td>';
				str += '</tr>';
			}
			str += '</table>'
			$('#inputx_' + num + 'a').append(str);
		}
	});
	
	//单击input显示下拉菜单
	$(".ob_2 input").click(function(e){
		var num = $(this).attr('data-value');
        $("#inputx_" + num + "a").show();
        var ev = e || window.event;
        if(ev.stopPropagation){
            ev.stopPropagation();
        }
        else if(window.event){
            window.event.cancelBubble = true;//兼容IE
        }
    })
    
    //单击其他位置，input的下拉菜单隐藏
    document.onclick = function(){
        $(".ob_2 div").hide();
    }
	
	//防止冒泡
    $(".ob_2 div").click(function(e){
        var ev = e || window.event;
        if(ev.stopPropagation){
            ev.stopPropagation();
        }
        else if(window.event){
            window.event.cancelBubble = true;//兼容IE
        }
    })
    
    //生成对比样式
    $("input:radio[name=highlight]").change(function () {
    	$('#bast_info tr td').removeClass('tabColor_1');
    	$('#bast_info tr td').removeClass('tabColor_2');
    	$('#track_info tr td').removeClass('tabColor_1');
    	$('#track_info tr td').removeClass('tabColor_2');
    	$('#managers_info tr td').removeClass('tabColor_1');
    	$('#managers_info tr td').removeClass('tabColor_2');

    	var style = $(this).val();
    	//净值
    	contrastStyleNum('bast_info', 2, style);
    	//分险等级
    	contrastStyleStr('bast_info', 3, style);
    	//最新规模
    	contrastStyleNum('bast_info', 6, style);
    	//申购费率
    	contrastStyleNum('bast_info', 8, style);
    	//累计分红
    	contrastStyleNum('bast_info', 9, style);
    	//近一周业绩增长率
    	contrastStyleNum('track_info', 0, style);
    	//近一月业绩增长率
    	contrastStyleNum('track_info', 1, style);
    	//近三月业绩增长率
    	contrastStyleNum('track_info', 2, style);
    	//近六月业绩增长率
    	contrastStyleNum('track_info', 3, style);
    	//近一年业绩增长率
    	contrastStyleNum('track_info', 4, style);
    	//近三年业绩增长率
    	contrastStyleNum('track_info', 7, style);
    	//成立以来业绩增长率
    	contrastStyleNum('track_info', 8, style);
    	//近三个月最大回撤
    	contrastStyleNum('track_info', 11, style);
    	//近半年最大回撤
    	contrastStyleNum('track_info', 12, style);
    	//近一年最大回撤
    	contrastStyleNum('track_info', 13, style);
    	//sharpe值
    	contrastStyleNum('track_info', 14, style);
    	//收益标准差
    	contrastStyleNum('track_info', 15, style);
    	//同类基金区间收益排名
    	contrastStyleNum('managers_info', 4, style);
    });
    
	topCheck();
	opbox();
});


/**
 * 对比数据事件，适用于文本 trNum行号, style选择样式
 */
function contrastStyleStr(tableId,trNum, style){
	if(style == 1){
		var x = -999;
		var y = 0;
		var z = 1;
		var b = 0;
		for (var int = 1; int <= 5; int++) {
			if($('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '' ||
					$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == 0 ||
						$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '--'){
				continue;
			}else{
				x = $('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html().replace('中低风险','4')
					.replace('低风险','5').replace('中风险','3').replace('中高风险','2').replace('高风险','1');
			}
			b = 1;
			if(x >= y){
				y = x;
				z = int;
			}
		}
	}else{
		var x = 999;
		var y = 0;
		var z = 1;
		var b = 0;
		for (var int = 1; int <= 5; int++) {
			if($('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '' ||
					$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == 0 ||
						$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '--'){
				continue;
			}else{
				y = $('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html().replace('中低风险','4')
					.replace('低风险','5').replace('中风险','3').replace('中高风险','2').replace('高风险','1');
			}
			b = 1;
			if(x > y){
				x = y;
				z = int;
			}
		}
	}
	//行数据全0，或全空不增加样式
	if(b > 0){
		$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + z + ')').addClass('tabColor_' + style);
	}
}

/**
 * 对比数据事件，适用于数字 trNum行号, style选择样式
 */
function contrastStyleNum(tableId, trNum, style){
	if(style == 1){
		var x = -999;
		var y = 0;
		var z = 1;
		var b = 0;
		for (var int = 1; int <= 5; int++) {
			if($('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '' ||
					$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '0' ||
						$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '--'){
				continue;
			}else{
				x = Number($('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html());
			}
			b = 1;
			if(x >= y){
				y = x;
				z = int;
			}
		}
	}else{
		var x = 999;
		var y = 0;
		var z = 1;
		var b = 0;
		for (var int = 1; int <= 5; int++) {
			if($('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '' ||
					$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == 0 ||
						$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html() == '--'){
				continue;
			}else{
				y = Number($('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + int + ')').html());
			}
			b = 1;
			if(x > y){
				x = y;
				z = int;
			}
		}
	}
	//行数据全0，或全空不增加样式
	if(b > 0){
		$('#'+ tableId +' tr:eq(' + trNum + ') td:nth-child(' + z + ')').addClass('tabColor_' + style);
	}
}

/**
 * 添加数据到表格中
 * @param data
 * @param num
 */
function addTableData(data, num){
	var defVeiw = '--';
	$('.ob_2[data-value=' + num + ']').hide();
	$('.ob_1[data-value=' + num + ']').show();
	$('.ob_1[data-value=' + num + '] div p:eq(0)').html(data.companyName);
	$('.ob_1[data-value=' + num + '] div p:eq(1) span').html(data.shortName);
	
	//基本信息
	$('#bast_info tr:eq(0) td:nth-child(' + num + ')').html(data.fundCode);
	$('#bast_info tr:eq(1) td:nth-child(' + num + ')').html(data.type); 
	$('#bast_info tr:eq(2) td:nth-child(' + num + ')').html(data.netValue);
	if(data.risk == ''){
		$('#bast_info tr:eq(3) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#bast_info tr:eq(3) td:nth-child(' + num + ')').html(data.risk);
	}
	$('#bast_info tr:eq(4) td:nth-child(' + num + ')').html(data.applyState);
	$('#bast_info tr:eq(5) td:nth-child(' + num + ')').html(data.redeemState);
	$('#bast_info tr:eq(6) td:nth-child(' + num + ')').html((data.scale/100000000).toFixed(2));
	$('#bast_info tr:eq(7) td:nth-child(' + num + ')').html(data.adminName);
	$('#bast_info tr:eq(8) td:nth-child(' + num + ')').html(data.applyRates);
	$('#bast_info tr:eq(9) td:nth-child(' + num + ')').html(data.cumulativeDividend.toFixed(2));
	$('#bast_info tr:eq(10) td:nth-child(' + num + ')').html(data.upDate);
	
	//业绩
	if(data.weekFeat == 9999){
		$('#track_info tr:eq(0) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(0) td:nth-child(' + num + ')').html(data.weekFeat);
	}
	if(data.monthFeat == 9999){
		$('#track_info tr:eq(1) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(1) td:nth-child(' + num + ')').html(data.monthFeat);
	}
	if(data.seasonFeat == 9999){
		$('#track_info tr:eq(2) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(2) td:nth-child(' + num + ')').html(data.seasonFeat);
	}
	if(data.halfYearFeat == 9999){
		$('#track_info tr:eq(3) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(3) td:nth-child(' + num + ')').html(data.halfYearFeat);
	}
	if(data.oneYearFeat == 9999){
		$('#track_info tr:eq(4) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(4) td:nth-child(' + num + ')').html(data.oneYearFeat);
	}
	if(data.oneYearCSI300 == 9999){
		$('#track_info tr:eq(5) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(5) td:nth-child(' + num + ')').html(data.oneYearCSI300);
	}
	if(data.oneYearSSE == 9999){
		$('#track_info tr:eq(6) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(6) td:nth-child(' + num + ')').html(data.oneYearSSE);
	}
	if(data.threeYearFeat == 9999){
		$('#track_info tr:eq(7) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(7) td:nth-child(' + num + ')').html(data.threeYearFeat);
	}
	if(data.fullFeat == 9999){
		$('#track_info tr:eq(8) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(8) td:nth-child(' + num + ')').html(data.fullFeat);
	}
	if(data.sevenRateReturn == 9999){
		$('#track_info tr:eq(9) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(9) td:nth-child(' + num + ')').html(data.sevenRateReturn);
	}
	if(data.extremeYield == 9999){
		$('#track_info tr:eq(10) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(10) td:nth-child(' + num + ')').html(data.extremeYield);
	}
	if(data.marchWithdrawal == 9999){
		$('#track_info tr:eq(11) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(11) td:nth-child(' + num + ')').html(data.marchWithdrawal);
	}
	if(data.halfYearWithdrawal == 9999){
		$('#track_info tr:eq(12) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(12) td:nth-child(' + num + ')').html(data.halfYearWithdrawal);
	}
	if(data.oneYearWithdrawal == 9999){
		$('#track_info tr:eq(13) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(13) td:nth-child(' + num + ')').html(data.oneYearWithdrawal);
	}
	if(data.sharpeValue == 9999){
		$('#track_info tr:eq(14) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(14) td:nth-child(' + num + ')').html(data.sharpeValue);
	}
	if(data.incomeDeviation == 9999){
		$('#track_info tr:eq(15) td:nth-child(' + num + ')').html(defVeiw);
	}else{
		$('#track_info tr:eq(15) td:nth-child(' + num + ')').html(data.incomeDeviation);
	}
	
	//基金经理
	$('#managers_info tr:eq(0) td:nth-child(' + num + ')').html(data.fundManagers);
	$('#managers_info tr:eq(1) td:nth-child(' + num + ')').html(data.age);
	$('#managers_info tr:eq(2) td:nth-child(' + num + ')').html(data.workingTime);
	$('#managers_info tr:eq(3) td:nth-child(' + num + ')').html(data.adminFundsNum);
	$('#managers_info tr:eq(4) td:nth-child(' + num + ')').html(data.rankingRange);
	
	//重仓债券 
	$('#bond_info tr:eq(0) td:nth-child(' + num + ')').html(data.theLargestBond1);
	$('#bond_info tr:eq(1) td:nth-child(' + num + ')').html(data.theLargestBond2);
	$('#bond_info tr:eq(2) td:nth-child(' + num + ')').html(data.theLargestBond3);
	$('#bond_info tr:eq(3) td:nth-child(' + num + ')').html(data.theLargestBond4);
	$('#bond_info tr:eq(4) td:nth-child(' + num + ')').html(data.theLargestBond5);
	//行业配置
	$('#industry_info tr:eq(0) td:nth-child(' + num + ')').html(data.theLargestIndustry1);
	$('#industry_info tr:eq(1) td:nth-child(' + num + ')').html(data.theLargestIndustry2);
	$('#industry_info tr:eq(2) td:nth-child(' + num + ')').html(data.theLargestIndustry3);
	$('#industry_info tr:eq(3) td:nth-child(' + num + ')').html(data.theLargestIndustry4);
	$('#industry_info tr:eq(4) td:nth-child(' + num + ')').html(data.theLargestIndustry5);
}

/**
 * 初始加载页面ids部位空
 * @param ids 基金代码
 */
function addFundContrasts(){
	//根据基金代码获取数据
	$.ajax({
		type : "POST",
		async:true,
		url:"getFundsListFromIds",
		data:{ids : $('#model_code').attr('value')},
		success: function(result){
			if(result.code == 200){
				var data = result.data;
				for (var int = 0; int < data.length; int++) {
					addTableData(data[int], int + 1);
				}
			}else{
				alert("没有找到基金信息");
			}
		}
	});
}

/**
 * 单击input的下拉框事件
 * @param code 基金代码
 * @param num 列编号
 */
function addFundContrast(code, num){
	//根据基金代码获取数据
	$.ajax({
		type : "POST",
		async:true,
		url:"getFundsListFromIds",
		data:{ids : code},
		success: function(result){
			$('#inputx_' + num + 'a').hide();
			if(result.code == 200){
				var data = result.data[0];
				addTableData(data, num)
			}else{
				alert("没有找到基金信息");
			}
		}
	});
}
/**
 * 生成选择基金的下拉框
 */
function generateSelect(){
	$('.ob_1').hide();
	$('.ob_2').show();
	
	var num = $('.ob_2').attr('data-value');
	
	var option = "";
	for (var int = 0; int < company.length; int++) {
		option += '<option value="' + company[int].companyId + '">' + company[int].companyName + '</option>';
	}
	$('.ob_2 select').append(option);
}

/**
 * 存储cookie
 * @param name
 * @param value
 */
function Setcookie (name, value){ 
    //设置名称为name,值为value的Cookie
	var expdate = new Date(); // 初始化时间
	expdate.setTime(expdate.getTime() + 60 * 1000);   // 时间
    document.cookie = name + "=" + value + ";expires=" + expdate.toGMTString() + ";path=/";

   //即document.cookie= name+"="+value+";path=/";   时间可以不要，但路径(path)必须要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！~
}

/**
 * 获取cookie
 * @param c_name
 * @returns
 */
function getCookie(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
	return ""
}

/**
 * 验证cookie
 */
function checkCookie() {
	username = getCookie('username')
	if (username != null && username != "") {
		alert('Welcome again ' + username + '!')
	} else {
		username = prompt('Please enter your name:', "")
		if (username != null && username != "") {
			setCookie('username', username, 365)
		}
	}
}

/**
 * 获取基金公司和公司下的基金
 */
function getFoudCompany(){
	$.ajax({
		type : "POST",
		async:true,
		url:"getFoudCompany",
		success: function(result){
			company = result.companys;
			fondJson = result.funds;
			
			var ids = $('#model_code').attr('value').split(',');
			
			if(ids != null && ids != ''){
				//控制顶部联动显示
				for (var int = 0; int < ids.length; int++) {
					$('.ob_1[data-value=' + (int + 1) + ']').show();
					$('.ob_2[data-value=' + (int + 1) + ']').hide();
				}
				generateSelect();
				//获取基金数据并增加数据到表格中
				addFundContrasts();
			}else{
				//生成空页面,顶部联动
				generateSelect();
			}
		}
	});
}


function topCheck() {
	$('.topCheck').find('a').on('click', function() {
		if ($(this).hasClass('active')) {
			$(this).removeClass('active');
		} else {
			$(this).addClass('active');
		}
	});
}


function opbox() {
	$('.opbox .ob_1 p a').on('click', function() {
		$(this).parent().parent().parent().parent().find('.ob_2').show();
		$(this).parent().parent().parent().parent().find('.ob_1').hide();
		var num = $(this).parent().parent().parent().attr('data-value');

		//清空表格列
		$('#bast_info tr td:nth-child(' + num + ')').html('');
		$('#bond_info tr td:nth-child(' + num + ')').html('');
		$('#managers_info tr td:nth-child(' + num + ')').html('');
		$('#track_info tr td:nth-child(' + num + ')').html('');
		$('#industry_info tr td:nth-child(' + num + ')').html('');
	});
}