$(document).ready(function() {

	toastr.options = {
		"closeButton" : true,
		"debug" : true,
		"newestOnTop" : true,
		"progressBar" : true,
		"positionClass" : "toast-top-right",
		"preventDuplicates" : true,
		"showDuration" : "80000",
		"hideDuration" : "2000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	};

	toastr.options.onHidden = function() {
		toastr.options.onclick = undefined;
	}

	displayUsers();

});

$(document).ready(function() {
	$("#retakeOrderButton").click(function() {
	var idCart = $("#retakeOrderButton").data("value");

		$.ajax({
			url : "../jsonservlet/retake_cart",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(idCart),
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
	});
});

$(document).ready(function() {
	$("#checkoutSendButton").click(function() {
		var validateCartRequest = new Object();

		validateCartRequest.firstName = $(".user-first-name").val();
		validateCartRequest.lastName = $(".user-last-name").val();
		validateCartRequest.email = $(".user-email").val();
		validateCartRequest.telephone = $(".user-telephone").val();
		validateCartRequest.address = $(".user-address").val();
		validateCartRequest.payment = $(".user-payment").val();

		validateCartRequest.totalPrice = cartPriceTotal;

		validateUserDataBeforeCheckout(validateCartRequest);

		return false;
	});

	displayComments();
	displayUsers();

	setInterval(function() {
		displayComments();
		displayUsers();
		displayProductsFromCart();
	}, 1000);

	$("#comment-list").scrollTop($("#comment-list")[0].scrollHeight);
});

function validateUserDataBeforeCheckout(request) {
	console.log("in validate cart");
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

				// resetez valorile din modal... sau altfel...
				$("#nr-products-cart").text("0");
				$("#cart-products-list").empty();
				$("#total-price").text("0");

				$('#checkoutPopup').modal('show');
				$('#backToHomePage').click(function() {
					window.location="/ui/pages/home.xhtml";
				});
				
			}
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}


var user_count = 0;
var user_comments_count = -1;

function setSidebarHeader(title) {
	$("#sidebarTitle").text(title);
}
function displayComments() {

	$
			.ajax({
				url : "../jsonservlet/get_comments",
				type : 'GET',
				dataType : 'json',
				data : {},
				contentType : 'application/json',
				mimeType : 'application/json',

				success : function(result) {

					var nr_all_comments = result.length;
					var comm = "comentarii";
					if(nr_all_comments===1){
						comm="comentariu";
						
					}
					var users_name = "utilizatori";
					if(user_count===1){
						users_name="utilizator";
					}
					setSidebarHeader(nr_all_comments + " " + comm + " " + "("
							+ user_count + "  " + users_name + ")");

					if (nr_all_comments != user_comments_count
							&& nr_all_comments > 0) {

						if (user_comments_count >= 0) {

							toastr.options.onclick = function() {
								if (!$("#page-body")
										.hasClass("sidebar-toggled")) {
									$("#page-body").toggleClass(
											"sidebar-toggled");
								}
							};

							for (var i = user_comments_count; i < nr_all_comments; i++) {
								if (!result[i].ownComment) {
									toastr.info(result[i].description,
											"Mesaj nou de la "
													+ result[i].username);
								}
							}

						}

						var scrollBottom = false;
						if (($("#comment-list")[0].scrollHeight - $("#comment-list")[0].scrollTop) < 310) {
							scrollBottom = true;
						}

						$("#comment-list").empty();

						$
								.each(
										result,
										function(index, comment) {
											var div_content = $("<div class='text-left'/>");
											var div_row = $("<div class='row'/>")
											var div_align = $("<div class='col-sm-8 comment-content "
													+ (comment.ownComment ? 'col-sm-offset-4'
															: 'left') + "' />");
											var div_color = $("<div class='box-color'/>");

											var div_username = $("<div class='comment-user'><i class='fa fa-user'></i><small> "
													+ comment.username
													+ " </small></div>");
											var div_description = $("<div class='comment-post'><p> "
													+ comment.description
													+ "</p></div>");
											var div_time = $("<div class='text-right'><small>"
													+ comment.date
													+ "</small></div>");

											div_content.append(div_row);
											div_row.append(div_align);
											div_align.append(div_color);

											div_color.append(div_username);
											div_color.append(div_description);
											div_color.append(div_time);

											$("#comment-list").append(
													div_content);

										});

						if (scrollBottom) {
							$("#comment-list").scrollTop(
									$("#comment-list")[0].scrollHeight);
						}
					}
					if (nr_all_comments > 0) {
						user_comments_count = nr_all_comments;
					}
				},

				error : function(data, status, er) {

					console.log(data);
					console.log(status);
					console.log(er);
				}
			});
}

function displayUsers() {

	$.ajax({
		url : "../jsonservlet/get_users_cart",
		type : 'GET',
		dataType : 'json',
		data : {},
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(result) {

			$("#listUsers").empty();
			user_count = result.length;
			$.each(result, function(index, user) {

				var user_panel = $("<div class='user-row'/>");
				var div_list = $("<div/>");
				var div_username = $("<span><b>" + user.username
						+ "</b></span>");
				var div_price = $("<div class='pull-right'><b>"
						+ (user.price).toFixed(2) + " RON</b>");

				user_panel.append(div_list);
				user_panel.append(div_username);
				user_panel.append(div_price);

				$("#listUsers").append(user_panel);

			});
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}

var cartPriceTotal = 0;
function displayProductsFromCart() {

	$.ajax({
		url : "../jsonservlet/get_products_cart",
		type : 'GET',
		dataType : 'json',
		data : {},
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(result) {

			$("#cartProductsList").empty();
			var total = 0;
			$.each(result, function(index, user) {
				var div_username = $("<div class='user-item'><b>"
						+ user.username + "</b></div>");
				$("#cartProductsList").append(div_username);

				$.each(user.cartDetails, function(index, product) {

					var products_panel = $("<div class='cart-row'>");
					var item_list = $("<span>" + product.nrProducts + "x "
							+ product.productName + " <i> ("
							+ product.restaurantName + " )</i></span>");

					var div_price = $("<span class='pull-right'>"
							+ product.nrProducts + "x "
							+ (product.price).toFixed(2) + " RON</span>");

					products_panel.append(item_list);
					products_panel.append(div_price);

					$("#cartProductsList").append(products_panel);
				});

				var div_user_price = $("<div class='price-item'>Subtotal: "
						+ (user.totalPrice).toFixed(2) + " RON</div>");
				total = total + +(user.totalPrice).toFixed(2);
				$("#cartProductsList").append(div_user_price);
			});

			$("#priceTotalCart").text(total.toFixed(2));

			cartPriceTotal = total;

			if (total == 0) {
				$('#checkoutSendButton').attr('disabled', 'disabled');
			} else {
				$('#checkoutSendButton').removeAttr('disabled');
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

		$.ajax({
			url : "../jsonservlet/get_selected_user",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(selectedText),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(result) {
				displayUsers();
				$('#addUser').val("");
			},
			error : function(data, status, er) {
				console.log(data);
				console.log(status);
				console.log(er);
			}
		});
	});
});

/* Add Comment */

$(document).ready(function() {
	$("#sendComment").click(addComment);
	$("#textComment").keypress(function(e) {
		if (e.keyCode == 13) { // detect the enter key
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
			console.log(request);
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}

/*
 * $(function () { $('[data-toggle="tooltip"]').tooltip() })
 */

/*--------------------------------------------------------------------------------------------------------------------------------*/
/*
 * $('.form').find('input, textarea').on('keyup blur focus', function(e) {
 * 
 * var $this = $(this), label = $this.prev('label');
 * 
 * if (e.type === 'keyup') { if ($this.val() === '') { label.removeClass('active
 * highlight'); } else { label.addClass('active highlight'); } } else if (e.type
 * === 'blur') { if ($this.val() === '') { label.removeClass('active
 * highlight'); } else { label.removeClass('highlight'); } } else if (e.type ===
 * 'focus') {
 * 
 * if ($this.val() === '') { label.removeClass('highlight'); } else if
 * ($this.val() !== '') { label.addClass('highlight'); } }
 * 
 * });
 * 
 * $('.tab a').on('click', function(e) {
 * 
 * e.preventDefault();
 * 
 * $(this).parent().addClass('active');
 * $(this).parent().siblings().removeClass('active');
 * 
 * target = $(this).attr('href');
 * 
 * $('.tab-content > div').not(target).hide();
 * 
 * $(target).fadeIn(600);
 * 
 * });
 */