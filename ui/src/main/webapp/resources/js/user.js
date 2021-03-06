$(document).ready(function() {
	$("#login-button").click(function() {
		var loginRequest = new Object();

		loginRequest.username = $(".login-username").val();
		loginRequest.password = $(".login-password").val();

		loginAjaxRequest(loginRequest);
		return false;
	});
	
	$("#sidebar-toggle-menu").click(function(e) {
		e.preventDefault();
		$("#page-body").toggleClass("sidebar-toggled");
	});

	$("#sidebar-minimize").click(function(e) {
		e.preventDefault();
		$("#page-sidebar").toggleClass("sidebar-minimized");
		$("#sidebar-minimize").toggleClass("hidden");
		$("#sidebar-maximize").toggleClass("hidden");
	});
	$("#sidebar-maximize").click(function(e) {
		e.preventDefault();
		$("#page-sidebar").toggleClass("sidebar-minimized");
		$("#sidebar-minimize").toggleClass("hidden");
		$("#sidebar-maximize").toggleClass("hidden");
	});
	
	$(".passwordUser").keypress(function(e) {
		if (e.keyCode == 13) { // detect the enter key
			$("#login-button").click();
		}
	});
});

function loginAjaxRequest(request) {
	$.ajax({
		url : "../jsonservlet/login_servlet",
		type : 'POST',
		dataType : 'json',
		data : JSON.stringify(request),
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(result) {
			if (result.valid == false) {
				$("#validation-message").text(result.message);
			} else {
				$("#validation-message").text("");
				$(".submit-link").click();
			}
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}
