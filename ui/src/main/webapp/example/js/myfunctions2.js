$(document).ready(function() {
	
	
	
	var statusUpdate = function statusUpdate(data) {
		console.log(data);
	};
	
	jsf.ajax.addOnEvent(statusUpdate);
	/* 
	 * -->
	 */
	
	$(".cart-add-button").click(function() {
		var baseUrl = "";
		var productId = this.dataset.productId;
		var productName = this.dataset.productName;

		jsf.ajax.request(this, event, {
			execute : 'inputname',
			render : 'outputname',
			onevent: function(data) {
				toastr.info("Add to cart: \'" + productName+"\'.");
				console.log(data);
			},
			onerror: function (data) {
				toastr.error("Error adding to cart " + productName);
				console.log(data);
			}
		});
		/*
		$.ajax({
			url : baseUrl + 'testRestaurant2.xhtml',
			type : "POST",
			dataType : "json",
			data : {
				"productId" : productId
			},
			success : function(data) {
				toastr.info("Add to cart " + productName);
			},
			error : function(e) {
				toastr.error("Error adding to cart " + productName);
			}
		});*/
		return false;
	});
});