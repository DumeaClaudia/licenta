$(document).ready(function() {
	$("#validate-cart").click(function() {
		var validateCartRequest = new Object();

		validateCartRequest.firstName = $(".user-first-name").val();
		validateCartRequest.lastName = $(".user-last-name").val();
		validateCartRequest.email = $(".user-email").val();
		validateCartRequest.telephone = $(".user-telephone").val();
		validateCartRequest.address = $(".user-address").val();
		validateCartRequest.payment = $(".user-payment").val();

		validateAjaxRequest(validateCartRequest);
		return false;
	});

	$('#checkoutModal').on('hide.bs.modal', function(e) {
		location.assign("home.xhtml");
	});

});

function validateAjaxRequest(request) {
	$.ajax({
		url : "../jsonservlet/current_cart_servlet",
		type : 'POST',
		dataType : 'json',
		data : JSON.stringify(request),
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(result) {
			if (result.valid == false) {
				$("#cart-validation-message").text(result.message);
			} else {
				$("#cart-validation-message").text("");
				$("#checkoutModal").modal('show');

			}
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}

$(document).ready(function() {
	$('#addUser').click(function() {
		var selectedText = $("#selectedUser").val();
		sendValue(selectedText);
	});
});

function sendValue(value) {
	$.ajax({
		url : "../jsonservlet/get_selected_user",
		type : 'POST',
		dataType : 'json',
		data : JSON.stringify(value),
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(result) {
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}
