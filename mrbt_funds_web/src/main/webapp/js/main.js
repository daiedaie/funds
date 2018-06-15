$(function(){
	//input兼容问题
	initPlaceholder();
	
	mores();//更多展开
	removeP();//删除对比标签
	zhbtype();
	jjxqTab();
	
	//顶部登录按钮事件
	$('.login').on('click', function(){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"/user/setSession",//请求页面
			data:{pUrl:window.location.pathname},
			success:function(data){
				window.location.href ="/user/login";
			}//跳转页面
		});
	});
	
});

function initPlaceholder(){
	// 判断浏览器是否支持placeholder属性
	supportPlaceholder = 'placeholder' in document.createElement('input'),
	placeholder = function(input) {
		var text = input.attr('placeholder'), defaultValue = input.defaultValue;
		if (!defaultValue) {
			input.val(text).addClass("phcolor");
		}
		input.focus(function() {
			if (input.val() == text) {
				$(this).val("");
			}
		});
		input.blur(function() {
			if (input.val() == "") {
				$(this).val(text).addClass("phcolor");
			}
		});
		// 输入的字符不为灰色
		input.keydown(function() {
			$(this).removeClass("phcolor");
		});
	};

	// 当浏览器不支持placeholder属性时，调用placeholder函数
	if (!supportPlaceholder) {
		$('input').each(function() {
			text = $(this).attr("placeholder");
			if ($(this).attr("type") == "text") {
				placeholder($(this));
			}
		});
	}
}

function openDetailsPage(code){
	console.log(code);
}

function addMenuStyle(name){
	$("#" + name).addClass("active");
}

/**
 * @param tabName	标签名称
 * @param num	编号
 * @param sytleName	样式名称
 */
function addActiveStyle(tabName, num ,sytleName){
	var fullName = tabName + "[data-value='" + num +"']"
	$("#" + tabName + "[data-value='" + num +"']").addClass(sytleName);
}

function mores(){
	$('.more').on('click',function(){
		if(!$(this).hasClass('active')){
			$(this).css({
				'background': 'url("../../images/select_up.png") 27px 4px no-repeat'
			})
			$('.where').css({
				'height':'auto'
			});
			$(this).addClass('active')
		}else if($(this).hasClass('active')){
			$(this).css({
				'background': 'url("../../images/select_down.png") 27px 4px no-repeat'
			})
			$('.where').css({
				'height':'35px'
			});
			$(this).removeClass('active')
		}
	});
}

function removeP(){
	$('.search .s_left').find('span').on('click',function(){
		$(this).parent().remove();
	});
	
}

function zhbtype(){
	$('.zhbtype a').on('click',function(){
		var aindex=$(this).index()
		$(this).parent().parent().parent().find('.zhbleft_'+aindex).addClass('active');
		$(this).parent().parent().parent().find('.zhbleft_'+aindex).siblings().removeClass('active');

	});
}

//基金详情tab切换效果
function jjxqTab(){
	$('.jjxqTable>.jtab').find('a').on('click',function(){
		$(this).addClass('active').siblings().removeClass('active');
		var tabNum=($(this).index())+1;
		var Tab='.jt_'+tabNum;
		//alert(Tab);
		$(Tab).show().siblings().hide();

	});
}

