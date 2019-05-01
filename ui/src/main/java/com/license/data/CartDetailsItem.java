package com.license.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.license.Cart;
import com.license.Product;
import com.license.Restaurant;
import com.license.ShoppingCartProducts;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

public class CartDetailsItem {
	CartSummaryItem cartSummary;
	List<RestaurantProductsItem> restaurantProducts;

	public CartDetailsItem(CartSummaryItem cartSummary, List<RestaurantProductsItem> restaurantProducts) {
		this.cartSummary = cartSummary;
		this.restaurantProducts = restaurantProducts;
	}

	public CartDetailsItem() {
		// TODO Auto-generated constructor stub
	}

	public CartSummaryItem getCartSummary() {

		if (cartSummary != null) {
			double price = 0;
			price = cartSummary.getTotalPrice();
			/*if (price != 0 && price < 30) {
				cartSummary.setTotalPrice(price + 15);
			}*/
			return cartSummary;
		}
		return new CartSummaryItem();
	}

	public List<RestaurantProductsItem> getRestaurantProducts() {
		return restaurantProducts;
	}

	public static CartDetailsItem getCartDetailsItem(ShoppingCartService shoppingCartService,
			RestaurantService restaurantService, long userId, long cartId) {

		List<RestaurantProductsItem> cartItemsGrouped = new ArrayList<RestaurantProductsItem>();
		Map<Long, List<ProductDetailsItem>> restaurantProductsMap = new HashMap<Long, List<ProductDetailsItem>>();
		List<ShoppingCartProducts> cartProductsForUser = shoppingCartService.getCartProductsForUser(userId, cartId);
		int nrTotalProductsFromCart = 0;
		double totalPrice = 0.00;

		for (ShoppingCartProducts productFromCart : cartProductsForUser) {
			long productId = productFromCart.getIdProduct();
			int nrProducts = productFromCart.getNrProducts();
			nrTotalProductsFromCart += nrProducts;

			Product product = shoppingCartService.getProduct(productId);
			ProductDetailsItem item = new ProductDetailsItem();

			item.setIdRestaurant(product.getIdRestaurant());
			item.setIdProduct(product.getId());
			item.setCategory(product.getCategory());
			item.setDescription(product.getDescription());
			item.setDiscount(product.getDiscount());
			item.setPrice(product.getPrice());
			item.setImage(product.getImage());
			item.setName(product.getName());

			item.setNrProducts(nrProducts);
			totalPrice += nrProducts * (item.getPrice());

			Long restaurantId = product.getIdRestaurant();

			if (restaurantProductsMap.containsKey(restaurantId)) {
				restaurantProductsMap.get(restaurantId).add(item);
			} else {
				List<ProductDetailsItem> newProductsList = new ArrayList<ProductDetailsItem>();
				newProductsList.add(item);
				restaurantProductsMap.put(restaurantId, newProductsList);
			}
		}

		Set<Long> restaurantsIds = restaurantProductsMap.keySet();
		String image = "";

		for (Long idRestaurant : restaurantsIds) {
			List<ProductDetailsItem> restaurantProducts = restaurantProductsMap.get(idRestaurant);

			Restaurant restaurant = restaurantService.getRestaurantById(idRestaurant);
			image = restaurant.getImage();
			cartItemsGrouped.add(new RestaurantProductsItem(restaurant.getId(), restaurant.getName(),
					restaurant.getImage(), restaurant.getAddress(), restaurant.getGeolocation(), restaurantProducts));
		}

		Cart cart = shoppingCartService.getCartById(cartId);
		if (cart != null && !cartProductsForUser.isEmpty()) {
			String description = restaurantsIds.size() + " restaurante ";
			String nrProducts = nrTotalProductsFromCart + " produs";
			if (nrTotalProductsFromCart > 1) {
				nrProducts = nrProducts + "e";
			}
			CartSummaryItem item = new CartSummaryItem(cart.getIdCart(), cart.isActive(), cart.getCreatedDate(),
					description, nrProducts, image, totalPrice);
			CartDetailsItem cartDetailsItem = new CartDetailsItem(item, cartItemsGrouped);
			return cartDetailsItem;
		} else {
			return new CartDetailsItem();
		}
	}
}
