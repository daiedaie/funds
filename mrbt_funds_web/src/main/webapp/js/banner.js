$(function() {
	$("#banner .imglist").eq(0).show().siblings("a").hide();
	$("#banner .btn li").mouseover(
			function() {
				clearInterval(timer);// 当鼠标滑动时，则停止定时任务
				var _index = $(this).index();// 获取当前索引（相对应的位置）
				/* 当鼠标滑动到li相对应的位置上面，则添加一个样式（小圆点） */
				$(this).addClass("hover").siblings().removeClass("hover");
				/* 相对应的背景图片显示和隐藏 */
				$("#banner .imglist").eq(_index).fadeIn(1000).siblings("a")
						.fadeOut(1000);
			}).mouseout(function() {
		autoplay();// 当鼠标离开时，则执行定时任务
	});
	var _index = 0;
	var timer = null;
	autoplay();
	
	/* 定义一个定时任务 */
	function autoplay() {
		timer = setInterval(function() {
			_index++;
			if (_index < 2) {
				$("#banner .btn li").eq(_index).addClass("hover").siblings()
						.removeClass("hover");
				$("#banner .imglist").eq(_index).fadeIn(1000).siblings("a")
						.fadeOut(1000);
			} else {
				_index = -1;
			}

		}, 2000);
	};
})
