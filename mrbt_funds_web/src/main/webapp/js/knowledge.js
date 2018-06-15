$(function() {
	$('#know ul li a').on('click', function() {
		openKnowledge($(this).attr("data-value"));
		$('#know ul li a').removeClass('addStyle');
		$(this).addClass('addStyle');
		popCenterWindow();
	});
})

function openKnowledge(fileUrl) {
	$('#showpdf').remove();
	var str = '<a id="showpdf" class="media" href="' + fileUrl + '"></a>';
	$('#pdfview').append(str);
	$('a.media').media({
		width : 1000,
		height : 600
	});
}

// 获取窗口的高度
var windowHeight;
// 获取窗口的宽度
var windowWidth;
// 获取弹窗的宽度
var popWidth;
// 获取弹窗高度
var popHeight;
function init() {
	windowHeight = $(window).height();
	windowWidth = $(window).width();
	popHeight = $(".window").height();
	popWidth = $(".window").width();
}

// 关闭窗口的方法
function closeWindow() {
	$(".title a").click(function() {
		$('#showpdf').remove();
		$(this).parent().parent().hide("slow");
	});
}

// 定义弹出居中窗口的方法
function popCenterWindow() {
	init();
	// 计算弹出窗口的左上角Y的偏移量
	var popY = (windowHeight - popHeight) / 2;
	var popX = (windowWidth - popWidth) / 2;
	// 设定窗口的位置
	$("#center").css("top", popY).css("left", popX).slideToggle("slow");
	closeWindow();
}

function popLeftWindow() {
	init();
	var popY = windowHeight - popHeight;
	// 设定窗口的位置
	$("#left").css("top", popY - 50).css("left", 50).slideToggle("slow");
	closeWindow();
}

function popRightWindow() {
	init();
	// 计算弹出窗口的左上角Y的偏移量
	var popY = windowHeight - popHeight;
	var popX = windowWidth - popWidth;
	// 设定窗口的位置
	$("#right").css("top", popY - 50).css("left", popX - 50)
			.slideToggle("slow");
	closeWindow();
}