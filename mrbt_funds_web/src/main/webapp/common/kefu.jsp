<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/kefu.css">
<script type="text/javascript">
	$(function() {
		
		var window_h = $(window).height();
		var win_h = window_h - 100 - 20;
		var auto_h = ($(".he").height()) - 30 - win_h;
		
		$(".prev").click(function() {
			var marginTop = $(".he").css("margin-top");
			if (marginTop != "0px") {
				$(".he").stop(true);
				$(".he").animate({"marginTop" : 0}, 500);
			}
		});

		$(".next").click(function() {
			var marginTop = $(".he").css("margin-top");
			if (marginTop == "0px") {
				$(".he").stop(true);
				$(".he").animate({"marginTop" : "-" + auto_h + "px"}, 500);
			}

		})
		$(window).resize(function() {
			
			var window_h = $(window).height();
			var win_h = window_h - 100 - 20;
			var auto_h = ($(".he").height()) - 30 - win_h;
			
			$(".prev").click(function() {
				var marginTop = $(".he").css("margin-top");
				if (marginTop != "0px") {
					$(".he").stop(true);
					$(".he").animate({"marginTop" : 0}, 500);
				}
			});

			$(".next").click(function() {
				var marginTop = $(".he").css("margin-top");
				if (marginTop == "0px") {
					$(".he").stop(true);
					$(".he").animate({
						"marginTop" : "-" + auto_h + "px"
					}, 500);
				}

			})
		});

		var flag = false;
		$(".kefu").hover(function() {
			flag = true;
			$("#rz-box-bg-kefu").show();
			$(this).stop(true, false).animate({"right" : "0"}, 1000);
		}, function() {
			$(this).stop(true, false).animate({"right" : "-225px"}, 1000);

			$("#rz-box-bg-kefu").hide();
			$(".he").css("margin-top", 0);
		})

		$(".kefuLeft").click(function() {

			if (flag) {
				$(".kefu").stop(true, false).animate({"right" : "-225px"}, 1000);
				$("#rz-box-bg-kefu").hide();
				$(".he").css("margin-top", 0);
				flag = false;
			} else {
				flag = true;
				$("#rz-box-bg-kefu").show();
				$(".kefu").stop(true, false).animate({"right" : "0"}, 1000);

			}
		});

		$("#QQkefu").click(
				function() {
					var string = "3232833073,3163127228,1549025970"; //原始数据
					var array = string.split(","); //转化为数组
					var value = array[Math.round(Math.random() * (array.length - 1))]; //随机抽取一个值
					window.open("http://wpa.qq.com/msgrd?v=3&uin=" + value + "&site=qq&menu=yes", "_blank");
				});

	})
</script>

<div id="rz-box-bg-kefu"></div>
<div class="kefu">
	<div class="kefuLeft">
		<a href="javascript:void(0)"><img src="/images/img_kefu1.png"
			alt="领钱吉祥物"></a>
	</div>
	<div class="kefu-top">
		<div class="logo_kefu">
			<a href="index.html"><img src="/images/logo_kefu.png" /></a>
		</div>
		<div style="overflow: hidden">
			<div class="he">
				<div class="serviceline">
					<p style="text-align: center">
						<span
							style="display: inline-block; margin-right: 3px; padding-left: 28px; background: url('/images/img_kefuTel.png') no-repeat left top">全国服务热线</span>
					</p>
					<p
						style="padding-top: 5px; font-family: 'myImpact'; font-size: 26px; color: #ea5513; text-align: center">400-0051-655</p>
				</div>
				<div class="kefu_email">
					<p>客服邮箱</p>
					<p>Service@lingmoney.cn</p>
				</div>
				<div class="kefu_qq" id="hhService">
					<p>
						<a title="点击这里给我发消息"
							href="http://kefu.qycn.com/vclient/chat/?websiteid=109459&clerkid=1303043"
							target="_blank" class="kefu_qq1">在线客服 01</a>
					</p>
					<p>
						<a title="点击这里给我发消息"
							href="http://kefu.qycn.com/vclient/chat/?websiteid=109459&clerkid=1303189"
							target="_blank" class="kefu_qq2">在线客服 02</a>
					</p>
					<p>
						<a title="点击这里给我发消息"
							href="http://kefu.qycn.com/vclient/chat/?websiteid=109459&clerkid=1303195"
							target="_blank" class="kefu_qq3">在线客服 03</a>
					</p>
					<p>
						<a title="点击这里给我发消息" href="javascript:void(0)" target="_blank"
							class="kefu_qq4" id="QQkefu">在线QQ客服</a>
					</p>
				</div>

				<div class="kefu_weixin">
					<img src="/images/img_kefuMa.jpg">
				</div>
			</div>
		</div>
	</div>
	<div class="kefuBtn">
		<img src="/images/kefu-up.png" class="prev" /> <img
			src="/images/kefu-down.png" class="next" />
	</div>

</div>


