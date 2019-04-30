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

	setInterval(function() {
		displayComments();
	}, 1000);
	
	$("#comment-list").scrollTop($("#comment-list")[0].scrollHeight);
});

function displayComments(){
	
	$.ajax({
		url : "../jsonservlet/get_comments",
		type : 'GET',
		dataType : 'json',
		data : {},
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(result) {

				
			var scrollBottom = false;
			if (($("#comment-list")[0].scrollHeight - $("#comment-list")[0].scrollTop) < 210) {
				scrollBottom = true;
			}	
				
			$("#comment-list").empty();
				
			$.each(	result,
				function(index, comment) {					
										
					var comment_panel = $("<div class='col-md-8 col-sm-8 panel panel-default arrow  comment.ownComment ? 'col-md-offset-2': 'left'' />");
					var div_details_comm = $("<div class='panel-body'/>");				
					var header = $("<header class='text-left'/>");
					var div_row = $("<div class='row'/>");
					
					var div_username = $("<div class='comment-user'><i class='fa fa-user'></i><small> " + comment.username +" </small></div>"); 
					var div_description = $("<div class='comment-post'><p> " + comment.description + "</p></div>");
					var div_time = $("<div class='comment-date'><small>" + comment.date +  "</small></div>");
		
					comment_panel.append(div_details_comm);
					comment_panel.append(header);
					comment_panel.append(div_row);

					comment_panel.append(div_username);
					comment_panel.append(div_description);
					comment_panel.append(div_time);
					
					$("#comment-list").append(comment_panel);
				
			});
				
			if (scrollBottom) {
				$("#comment-list").scrollTop($("#comment-list")[0].scrollHeight);
			}
				
		
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}
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

/* Add Comment */

$(document).ready(function() {
	$("#sendComment").click(addComment);
	$("#textComment").keypress(function (e){
		if ( e.keyCode == 13 ) {  // detect the enter key
			addComment();
		}
	});
});

function addComment() {
	var comment = $("#textComment").val();
	$("#textComment").val("");
	if (comment.trim() != "") {

		sendCommentAjaxRequest(comment);
	}
	return false;
}
function sendCommentAjaxRequest(request) {
	$.ajax({
		url : "../jsonservlet/add_comment",
		type : 'POST',
		dataType : 'json',
		data : JSON.stringify(request),
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(result) {
			displayComments();
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}
