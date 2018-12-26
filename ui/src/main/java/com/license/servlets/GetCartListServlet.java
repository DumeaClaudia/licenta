package com.license.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.Product;
import com.license.Restaurant;
import com.license.shoppingCart.ShoppingCartService;
import com.license.data.ProductDetailsItem;
import com.license.restaurant.RestaurantService;

public class GetCartListServlet extends HttpServlet {

	@EJB
	private ShoppingCartService shoppingCartService;
	
	@EJB
	private RestaurantService restaurantService;

	private static final long serialVersionUID = 1115909816033418452L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int userId = 1;

		List<ProductDetailsItem> productItems = new ArrayList<ProductDetailsItem>();
		List<Product> products = new ArrayList<Product>();
		List<Long> allCartList = shoppingCartService.getAllShoppingCartsForUser(userId);
		String description = new String();
		Set<Long> restaurantsIds = new HashSet<Long>();

		for (Long cartId : allCartList) {
			
			products = shoppingCartService.getShoppingCartProducts(cartId);		
			for (Product product : products) {

				ProductDetailsItem item = new ProductDetailsItem();

				item.setCategory(product.getCategory());
				item.setDescription(product.getDescription());
				item.setDiscount(product.getDiscount());
				item.setImage(product.getImage());
				item.setName(product.getName());

				restaurantsIds.add(product.getIdRestaurant());
				productItems.add(item);
			}
		}

		List<Restaurant> restaurants = new ArrayList<Restaurant>();

		for (Long idRestaurant : restaurantsIds) {
			Restaurant restaurant = restaurantService.getRestaurantById(idRestaurant);
			restaurants.add(restaurant);
		}

		int nrProducts = products.size(); 
		int nrRestaurants = restaurantsIds.size(); 
		
		if (nrRestaurants == 1) {
			Restaurant restaurant = restaurants.get(0);
			description = restaurant.getName() + " " + restaurant.getImage() + ' ' + restaurant.getProducts();
		} else {
			description = "Cart-ul are " + nrProducts + " produse.";
		}

		// get all restaurants id
		// if one restaurant id:
		// description = get restaurant name from restaurant id
		// else
		// description = count(distinct restaurant ids)

		// get_cart_example e apelat din android....
		System.out.println("Count = " + productItems.size());

		// Set the response message's MIME type
		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();

		write_mapper.writeValue(response.getOutputStream(), description);

	}
}
