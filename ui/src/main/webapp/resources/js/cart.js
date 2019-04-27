$(document).ready(function() {
	toastr.options = { onclick: function () { $("#myCartModal").modal() } }
	
	$(".cart-add-button").click(function() {

		var productId = this.dataset.productId;
		var productName = this.dataset.productName;
		var addProductRequest = new Object();

		addProductRequest.idProduct = productId;
		addProductToCartAjaxRequest(addProductRequest);

		return false;
	});
	
	$(".cart-add-error").click(function() {
		toastr.error("You have to login before added to cart...");
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
			toastr.info("Added to cart: " + data.product.name + ".");

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


