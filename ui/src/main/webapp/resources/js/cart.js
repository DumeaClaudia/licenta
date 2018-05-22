$(document).ready(function() {	
	$(".cart-add-button").click(function() {
		
		var productId = this.dataset.productId;
		var productName = this.dataset.productName;
		
		var addProductRequest = new Object();
		
		addProductRequest.idProduct = productId;
		addProductRequest.idCart = 1;
		
		addProductToCartAjaxRequest(addProductRequest);
		
		return false;
	});
});


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
			var div1 = $("<div/>");
			
			var span = $("<span class='label' style='margin:4px;padding:4px' />");
        	span.text(data.shoppingCart.product);
			//span.text(data.product.name);
        	div1.append(span);
        	
        	/*$("#card-products-list").empty();*/
        	
        	$("#card-products-list").append(div1);
			
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
<<<<<<< HEAD
$(document).ready(function() {	
	$(".cart-add-button").click(function() {
		var productId = this.dataset.productId;
		var productName = this.dataset.productName;
		var addProductRequest = new Object();
		addProductRequest.idProduct = productId;
		addProductRequest.idCart = 2;
		addProductToCartAjaxRequest(addProductRequest);
		return false;
	});
});
=======
>>>>>>> branch 'master' of https://github.com/DumeaClaudia/licenta.git
