$(function(){
	$('.s_menu li').on('click', function(){
		window.location.href="/usercentre/view?menu=s_menu li" + "&index=" + $(this).attr('data-value'); 
	})
	
})

function setMenuClass(menu, index){
	var s = menu.split(' ');
	console.log(s[0] + " " + s[1]);
	$('.' + s[0] + ' ' + s[1] + '[data-value=' + index + ']').addClass('active');
}
