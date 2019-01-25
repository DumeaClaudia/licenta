package com.license.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.license.data.CartDetailsItem;
import com.license.data.CartSummaryItem;
import com.license.data.ProductDetailsItem;
import com.license.data.RestaurantProductsItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

public class GetCartDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1115909816033418452L;
	@EJB
	private RestaurantService restaurantService;
	@EJB
	private ShoppingCartService shoppingCartService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        long cartId =Long.parseLong(request.getParameter("cartId"));
		
		List<RestaurantProductsItem> cartItemsGrouped = new ArrayList<RestaurantProductsItem>();
		Map<Long, List<ProductDetailsItem>> restaurantProductsMap = new HashMap<Long, List<ProductDetailsItem>>();

		List<Product> products = shoppingCartService.getShoppingCartProducts(cartId);
		
		for (Product product : products) {
			ProductDetailsItem item = new ProductDetailsItem();
			
			item.setIdRestaurant(product.getIdRestaurant());
			item.setIdProduct(product.getId());
			item.setCategory(product.getCategory());
			item.setDescription(product.getDescription());
			item.setDiscount(product.getDiscount());
			item.setImage(product.getImage());
			item.setName(product.getName());

			Long restaurantId = product.getIdRestaurant();

			if (restaurantProductsMap.containsKey(restaurantId)) {
				restaurantProductsMap.get(restaurantId).add(item);
			} else {
				List<ProductDetailsItem> newProductsList = new ArrayList<ProductDetailsItem>();
				newProductsList.add(item);
				restaurantProductsMap.put(restaurantId, newProductsList );
			}
		}

		Set<Long> restaurantsIds = restaurantProductsMap.keySet();
		
		for (Long idRestaurant : restaurantsIds) {
			List<ProductDetailsItem> restaurantProducts = restaurantProductsMap.get(idRestaurant);
			Restaurant restaurant = restaurantService.getRestaurantById(idRestaurant);
			
			cartItemsGrouped.add(new RestaurantProductsItem(restaurant.getId(), restaurant.getName(), restaurant.getImage(),
					restaurant.getAddress(), restaurant.getGeolocation(), restaurantProducts));
		}

		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();
		
		Cart cart = shoppingCartService.getCartById(cartId);
		String description = restaurantsIds.size() + " restaurante si " + products.size() + " produse";
		
		CartSummaryItem item = new CartSummaryItem(cart.getIdCart(), cart.isActive(), cart.getCreatedDate(), description);
		CartDetailsItem cartDetailsItem = new CartDetailsItem(item, cartItemsGrouped);

		write_mapper.writeValue(response.getOutputStream(), cartDetailsItem);

	}
}
