
$(document).ready(function() {
	
	toastr.options = {
			  "closeButton": true,
			  "debug": true,
			  "newestOnTop": true,
			  "progressBar": true,
			  "positionClass": "toast-top-right",
			  "preventDuplicates": true,
			  "showDuration": "80000",
			  "hideDuration": "2000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			};
	
	var carProductsRequest = new Object();
	carProductsRequest.idUser = 1;
	getCartProductsAjaxRequest(carProductsRequest);
});




function getCartProductsAjaxRequest(request) {
	
	$.ajax({
		url: "../jsonservlet/get_cart_products",
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(request),
		contentType: 'application/json',
		mimeType: 'application/json',
		
		success: function (data) {
			// toastr.info("Added to cart: " + data.shoppingCart.product + ".");
			   
        	$("#card-products-list").empty();
			$.each(data, function (index, product) {
			
				var div1 = $("<div/>");		
				var span1 = $("<span class='product-list-name  ' />");
				var span2 = $("<span class='product-list-price  ' />");
				
	        	span1.text(product.name);
	        	span2.text(product.price + " RON");
	        	var minus = $("<span class='glyphicon glyphicon-minus product-list-delete' />");
	        	
	        	div1.append(span1);
	        	
	        	div1.append(minus);
	        	div1.append(span2);
	        
	        	$("#card-products-list").append(div1);
			});

        	$("#nr-products-cart").text(data.length);
			
		//	console.log(data);
        },
		error:function(data,status,er) {
			// alert("error: "+data+" status: "+status+" er:"+er);
			toastr.error("Getting cart products " );
			console.log(data);
			console.log(status);
			console.log(er);
		}
	});
}