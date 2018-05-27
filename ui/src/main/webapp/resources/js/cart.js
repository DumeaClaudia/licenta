$(document).ready(function() {	
	$(".cart-add-button").click(function() {
		
		var productId = this.dataset.productId;
		var productName = this.dataset.productName;
		
		var addProductRequest = new Object();
		
		addProductRequest.idProduct = productId;
		addProductRequest.idUser = 1;
		
		addProductToCartAjaxRequest(addProductRequest);
		
		return false;
	});
	$(".cart-add-error").click(function(){
		toastr.error("You have to login before added to cart...");
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
			var span1 = $("<span class='product-list-name  ' />");
			var span2 = $("<span class='product-list-price  ' />");
			
        	span1.text(data.product.name);
        	span2.text(data.product.price + " RON");
        	var minus = $("<span class='glyphicon glyphicon-minus product-list-delete' />");
        	
        	div1.append(span1);
        	
        	div1.append(minus);
        	div1.append(span2);
        	//$("#card-products-list").empty();
        	
        	
        	
        /*
            	$("tr:has(td)").remove();

            	$.each(data, function (index, article) {
                	
                    var td_namesProducts = $("<td/>");
                    $.each(article.categories, function (i, tag) {
                    	var spanName = $("<span class='label' style='margin:4px;padding:4px; color:black; font-size:13px;' />");
                    	spanName.text(tag);
                    	td_namesProducts.append(spanName);
                    });
                    
                    var td_prices = $("<td/>");
                    $.each(article.tags, function (i, tag) {
                    	var spanPrice = $("<span class='label' style='margin-left:10px;padding:4px; color:black; font-size:13px;' />");
                    	spanPrice.text(tag);
                    	td_prices.append(spanPrice);
                    });
                    
                    var minus = $("<span class='glyphicon glyphicon-minus' style='margin-left:10px;padding:4px; color:red;'/>");
                    
                    $("#added-articles").append($('<tr/>')
                    		.append($('<td/>').html("<a href='"+article.url+"'>"+article.title+"</a>"))
                    		.append(td_namesProducts)
                    		.append(td_prices)
                    		.append(minus)
                    );
                
                    
                }); */
        
        	
        	$("#card-products-list").append(div1);
        	var nrProducts = $("#nr-products-cart").text();
        	nrProducts= 1 + +nrProducts;
        	$("#nr-products-cart").text(nrProducts);
			
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


