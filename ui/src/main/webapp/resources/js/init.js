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

	var carProductsRequest = new Object();
	carProductsRequest.idUser = 1;
	getCartProductsAjaxRequest(carProductsRequest);
});

function getCartProductsAjaxRequest(request) {

	$.ajax({
			url : "../jsonservlet/get_cart_products",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(request),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				$("#cart-products-list").empty();
				var total = 0;
				$.each(	data,
						function(index, product) {
						
							var div1 = $("<div/>");
							var span1 = $("<span class='product-list-name  ' />");
							var span2 = $("<span class='product-list-price  ' />");

							span1.text(product.name);
							span2.text(product.price + " RON");
							total = total + +product.price;
							
							var minus = $("<a class='glyphicon glyphicon-minus product-list-delete'  data-product-id='" + product.id
										+ "' data-product-name='" + product.name + "' />");

							div1.append(span1);
							div1.append(minus);
							div1.append(span2);

							$("#cart-products-list").append(div1);
						});
					
					var minSum = 15;
					var zero = 0;
					if(total<30 && total!=0){
						$("#delivery-price").text(minSum + " RON");
						total = total + 15;
					}
					else{
						$("#delivery-price").text(zero + " RON");
					}
					// $("#total-price").text( Number.parseFloat(total).toFixed(2) + " RON");
					$("#total-price").text(total.toFixed(2) + " RON");
					$("#nr-products-cart").text(data.length);

					$(".product-list-delete").click(
							function() {

								var productId = this.dataset.productId;
								var productName = this.dataset.productName;
								var removedProductRequest = new Object();

								removedProductRequest.idProduct = productId;
								removedProductRequest.idUser = 1;

								removeProductFromCartAjaxRequest(
										removedProductRequest, productName);

								return false;
							});

					// console.log(data);
				},
				error : function(data, status, er) {
					toastr.error("Getting cart products ");
					console.log(data);
					console.log(status);
					console.log(er);
				}
			});
}

function removeProductFromCartAjaxRequest(request, product_name) {
	var cartProductsRequest = new Object();

	cartProductsRequest.idUser = request.idUser;

	$.ajax({
		url : "../jsonservlet/remove_product_from_cart",
		type : 'POST',
		dataType : 'json',
		data : JSON.stringify(request),
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(data) {
			toastr.info("Removed from cart: " + product_name + ".");

			getCartProductsAjaxRequest(cartProductsRequest);
			console.log(data);
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}