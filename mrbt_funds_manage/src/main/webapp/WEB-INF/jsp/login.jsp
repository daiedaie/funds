<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ include file="/common/top.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>睿博基金后台管理系统_用户登录</title>
<script type="text/javascript" src="/easyUI/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/ie.js"></script>
<script type="text/javascript" src="/easyUI/jquery.form.js"></script>
<link rel="stylesheet" type="text/css" href="/resource/css/login.css" />
<script>
	var options = {
		url : '/rest/login',
		dataType : 'json',
		beforeSubmit : function() {
			var reFlag = true;
			var message = "";
			var psw = $("#loginPswId").val();
			var name = $("#loginNameId").val();
			if ($.trim(name) == "") {
				reFlag = false;
				message += "请先填写用户名\n";
			}
			if ($.trim(psw) == "") {
				reFlag = false;
				message += "请先填写密码\n";
			}
			if (!reFlag) {
				alert(message);
			}
			return reFlag;
		},
		success : function(data) {
			if (data.code == 200) {
				forwardMain();
			} else {
				alert(data.msg);
			}
		}
	};
	function forwardMain() {
		this.location.href = "/rest/forward/main"
	}
	function doLogin() {
		//$('#loginForm').ajaxForm(options).submit();
		$('#loginForm').submit();
	}
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 13) { // enter 键
			//要做的事情
			doLogin();
		}
	};
</script>
</head>

<body>
	<c:if test="${!empty error }">
		<script>
			alert("${error}")
		</script>
	</c:if>
	<%-- <div class="error">${error}</div> --%>
	<h1>睿博基金后台管理系统</h1>
	<div class="login" style="margin-top: 50px;">
		<div class="web_qr_login" id="web_qr_login"
			style="display: block; height: 235px;">
			<!--登录-->
			<div class="web_login" id="web_login">
				<div class="login-box">
					<div class="login_form">
						<form name="loginform" accept-charset="utf-8" id="loginForm"
							class="loginForm" method="post" action="/rest/login">
							<input type="hidden" name="did" value="0" /> <input
								type="hidden" name="to" value="log" />
							<div class="uinArea" id="uinArea">
								<label class="input-tips" for="u">帐号：</label>
								<div class="inputOuter" id="uArea">
									<input type="text" id="loginNameId" name="loginName"
										class="inputstyle" />
								</div>
							</div>
							<div class="pwdArea" id="pwdArea">
								<label class="input-tips" for="p">密码：</label>
								<div class="inputOuter" id="pArea">
									<input type="password" id="loginPswId" name="loginPsw"
										class="inputstyle" />
								</div>
							</div>
							<div style="padding-left: 50px; margin-top: 20px;">
								<a href="javascript:void(0);" id="login" onclick="doLogin()"
									class="button_blue" style="width: 150px;">登录</a>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!--登录end-->
		</div>
	</div>
</body>
</html>