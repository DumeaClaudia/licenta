function addProductToCartAjaxRequest(request) {
	
	$.ajax({
		url: "../jsonservlet/cart",
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(request),
		contentType: 'application/json',
		mimeType: 'application/json',
		
		success: function (data) {
			toastr.info("Added to cart: " + data.shoppingCart.product + ".");
			console.log(data);
        },
		error:function(data,status,er) {
			//alert("error: "+data+" status: "+status+" er:"+er);
			toastr.error("Error adding to cart " + productName);
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}
$(document).ready(function() {	
	$(".cart-add-button").click(function() {
		var baseUrl = "";
		var productId = this.dataset.productId;
		var productName = this.dataset.productName;
		var addProductRequest = new Object();
		addProductRequest.idProduct = productId;
		addProductRequest.idCart = 0;
		addProductToCartAjaxRequest(addProductRequest);
		return false;
	});
});