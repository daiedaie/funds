$(function() {
	$('.password input').focus(function(){
		console.log('aaaaa');
		$(this).attr('type', 'password');
	});
});