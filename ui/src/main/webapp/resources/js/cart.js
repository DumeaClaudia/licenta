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
			//toastr.info("Added to cart: " + data.shoppingCart.product + ".");
			toastr.info("Added to cart: " + data.product.name + ".");
			
			var div1 = $("<div/>");		
			var span1 = $("<span class='label' style='margin:4px;padding:4px; color:black; font-size:13px;' />");
			var span2 = $("<span class='label' style='margin-left:10px;padding:4px; color:black; font-size:13px;' />");
			
        	span1.text(data.product.name);
        	span2.text(data.product.price + " RON");
        	
        	div1.append(span1);
        	div1.append(span2);
        	
        	$("#card-products-list").empty();
        	
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


