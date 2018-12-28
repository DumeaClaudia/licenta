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
import com.license.Cart;
import com.license.Product;
import com.license.Restaurant;
import com.license.shoppingCart.ShoppingCartService;
import com.license.data.CartSummaryItem;
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
		List<CartSummaryItem> carts = new ArrayList<CartSummaryItem>();;
		List<Long> allCartList = shoppingCartService.getAllShoppingCartsForUser(userId);
		
		for (long idCart : allCartList) {
			List<ProductDetailsItem> productItems = new ArrayList<ProductDetailsItem>();
			List<Product> products = new ArrayList<Product>();
			String description = new String();
			Set<Long> restaurantsIds = new HashSet<Long>();
			
			products = shoppingCartService.getShoppingCartProducts(idCart);		
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
			
			List<Restaurant> restaurants = new ArrayList<Restaurant>();

			for (Long idRestaurant : restaurantsIds) {
				Restaurant restaurant = restaurantService.getRestaurantById(idRestaurant);
				restaurants.add(restaurant);
			}

			int nrProducts = products.size(); 
			int nrRestaurants = restaurantsIds.size(); 
			
			if (nrRestaurants == 1) {
				Restaurant restaurant = restaurants.get(0);
				description = restaurant.getName();
			} else {
				description = "";
			}

			Cart cart = new Cart();
			cart = shoppingCartService.getCartById(idCart);
					
			CartSummaryItem item = new CartSummaryItem(cart.getIdCart(), cart.isActive(), cart.getCreatedDate(), description);
			carts.add(item);
		}

		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();

		write_mapper.writeValue(response.getOutputStream(), carts);

	}
}
