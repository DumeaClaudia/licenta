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
	
	getCartProductsAjaxRequest(carProductsRequest);
	displayUsers(); //hello
	/*displayProductsFromCart();*/
});

	/*Products from modal cart*/
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
				var nrCartProducts = 0;
				$.each(	data,
						function(index, product) {
						
							var div1 = $("<div/>");
							var span1 = $("<span class='product-list-name  ' />");
							var span2 = $("<span class='product-list-price  ' />");

							span1.text(product.nrProducts+ "x "+  product.nameProduct + " ("+product.nameRestaurant+")");
							span2.text(product.nrProducts + "x "+ (product.price).toFixed(2) + " RON");
							
							nrCartProducts = nrCartProducts + + (product.nrProducts);
							total = total + +(product.price*product.nrProducts);
							
							var minus = $("<a class='glyphicon glyphicon-minus product-list-delete'  data-product-id='" + product.idProduct
										+ "' data-product-name='" + product.nameProduct + "' />");

							div1.append(span1);
							div1.append(minus);
							div1.append(span2);

							$("#cart-products-list").append(div1);
						});
										
					$("#total-price").text(total.toFixed(2) + " RON");					
					$("#nr-products-cart").text(nrCartProducts);

					$(".product-list-delete").click(
							function() {
								var productId = this.dataset.productId;
								var productName = this.dataset.productName;								
								
								var removedProductRequest = new Object();
								removedProductRequest.idProduct = productId;
								removeProductFromCartAjaxRequest(
										removedProductRequest, productName);

								return false;
							});
				},
				error : function(data, status, er) {
					/*toastr.error("Getting cart products ");*/
					console.log(data);
					console.log(status);
					console.log(er);
				}
			});
}

function removeProductFromCartAjaxRequest(request, product_name) {
	var cartProductsRequest = new Object();


	$.ajax({
		url : "../jsonservlet/remove_product_from_cart",
		type : 'POST',
		dataType : 'json',
		data : JSON.stringify(request),
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(data) {
			toastr.success("A fost șters din coș: " + product_name + ".");

			getCartProductsAjaxRequest(data);
			displayProductsFromCart();//

			console.log(data);
		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}


$(document).ready(function() {
	toastr.options = {
		onclick : function() {
			$("#myCartModal").modal()
		}
	}

	$(".cart-add-button").click(function() {
		var productId = this.dataset.productId;
		var productName = this.dataset.productName;
		var addProductRequest = new Object();

		addProductRequest.idProduct = productId;
		addProductToCartAjaxRequest(addProductRequest);

		return false;
	});

	$(".cart-add-error").click(function() {
		toastr.error("Trebuie să fiți logat pentru a putea adăuga produse în coș!");
		return false;
	});

	
});

function addProductToCartAjaxRequest(request) {
	var cartProductsRequest = new Object();

	$.ajax({
		url : "../jsonservlet/add_product_to_cart",
		type : 'POST',
		dataType : 'json',
		data : JSON.stringify(request),
		contentType : 'application/json',
		mimeType : 'application/json',

		success : function(data) {
			toastr.success("A fost adăugat în coș: " + data.product.name + ".");
		
			getCartProductsAjaxRequest(data);
			displayProductsFromCart();//
		
			console.log(data);

		},
		error : function(data, status, er) {
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}
