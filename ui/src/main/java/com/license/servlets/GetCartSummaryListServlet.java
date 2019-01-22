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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.Cart;
import com.license.Product;
import com.license.Restaurant;
import com.license.shoppingCart.ShoppingCartService;
import com.license.data.CartSummaryItem;
import com.license.data.ProductDetailsItem;
import com.license.restaurant.RestaurantService;

public class GetCartSummaryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1115909816033418452L;

	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false); 
		Long s = (Long) session.getAttribute("userId");
		int userId = s.intValue();
		
		List<CartSummaryItem> carts = new ArrayList<CartSummaryItem>();
		List<Long> allCartList = shoppingCartService.getAllShoppingCartsForUser(userId);
		
		for (long idCart : allCartList) {
			List<ProductDetailsItem> productItems = new ArrayList<ProductDetailsItem>();
			String description = new String();
			Set<Long> restaurantsIds = new HashSet<Long>();		
			List<Product> products = shoppingCartService.getShoppingCartProducts(idCart);
			
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
			
			if (restaurantsIds.size() == 1) {
				Restaurant restaurant = restaurants.get(0);
				description = "Cosul contine " + products.size() + " produse, de la restaurantul " + restaurant.getName();
			} else {
				description = "Cosul contine " + products.size() + " produse, de la " + restaurantsIds.size() + "restaurante.";
			}

			Cart cart = shoppingCartService.getCartById(idCart);				
			CartSummaryItem item = new CartSummaryItem(cart.getIdCart(), cart.isActive(), cart.getCreatedDate(), description);
			carts.add(item);
		}

		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();

		write_mapper.writeValue(response.getOutputStream(), carts);

	}
}
