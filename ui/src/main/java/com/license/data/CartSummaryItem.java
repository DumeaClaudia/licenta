package com.license.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.license.Cart;
import com.license.Product;
import com.license.Restaurant;
import com.license.ShoppingCartProducts;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

public class CartSummaryItem {

	long idCart;
	boolean cartActive;
	String createdDate;
	String cartDescription;
	String nrProducts;
	String imageRestaurant;
	double totalPrice;

	public CartSummaryItem(long idCart, boolean cartActive, String createdDate, String cartDescription,
			String nrProducts, String imageRestaurant, double totalPrice) {
		this.idCart = idCart;
		this.cartActive = cartActive;
		this.createdDate = createdDate;
		this.cartDescription = cartDescription;
		this.nrProducts = nrProducts;
		this.imageRestaurant = imageRestaurant;
		this.totalPrice = totalPrice;

	}

	public boolean getCartActive() {
		return cartActive;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getCartDescription() {
		return cartDescription;
	}

	public long getIdCart() {
		return idCart;
	}

	public String getNrProducts() {
		return nrProducts;
	}

	public void setNrProducts(String nrProducts) {
		this.nrProducts = nrProducts;
	}

	public String getImageRestaurant() {
		return imageRestaurant;
	}

	public void setImageRestaurant(String imageRestaurant) {
		this.imageRestaurant = imageRestaurant;
	}

	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}

	public void setCartActive(boolean cartActive) {
		this.cartActive = cartActive;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setCartDescription(String cartDescription) {
		this.cartDescription = cartDescription;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public static List<CartSummaryItem> getCartsSummary(ShoppingCartService shoppingCartService,
			RestaurantService restaurantService, int userId) {
		List<CartSummaryItem> carts = new ArrayList<CartSummaryItem>();
		List<Long> allCartList = shoppingCartService.getAllShoppingCartsForUser(userId);
		int nrTotalProductsFromCart = 0;
		double totalPrice = 0.00;

		for (long idCart : allCartList) {
			List<ProductDetailsItem> productItems = new ArrayList<ProductDetailsItem>();
			String description = new String();
			Set<Long> restaurantsIds = new HashSet<Long>();

			List<ShoppingCartProducts> cartProductsForUser = shoppingCartService.getCartProductsForUser(userId, idCart);

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

				restaurantsIds.add(product.getIdRestaurant());
				totalPrice += nrProducts * (item.getPrice());
				productItems.add(item);
			}

			List<Restaurant> restaurants = new ArrayList<Restaurant>();

			String image = new String(), nrProducts;

			for (Long idRestaurant : restaurantsIds) {
				Restaurant restaurant = restaurantService.getRestaurantById(idRestaurant);
				restaurants.add(restaurant);
				image = restaurant.getId() + "/" + restaurant.getImage();
			}

			if (restaurantsIds.size() > 0) {
				if (restaurantsIds.size() == 1) {
					Restaurant restaurant = restaurants.get(0);
					description = restaurant.getName();
					nrProducts = nrTotalProductsFromCart + " produs" + (nrTotalProductsFromCart == 1 ? "" : "e");

				} else {

					description = "Comanda de la " + restaurantsIds.size() + " restaurante";
					nrProducts = nrTotalProductsFromCart + " produs" + (nrTotalProductsFromCart == 1 ? "" : "e");
				}
				Cart cart = shoppingCartService.getCartById(idCart);
				CartSummaryItem item = new CartSummaryItem(cart.getIdCart(), cart.isActive(), cart.getCreatedDate(),
						description, nrProducts, image, totalPrice);
				carts.add(item);
			}

		}

		carts.sort(new Comparator<CartSummaryItem>() {

			@Override
			public int compare(CartSummaryItem o1, CartSummaryItem o2) {

				Date d1;
				Date d2;
				try {
					d1 = new SimpleDateFormat("dd.MM.yyyy").parse(o1.getCreatedDate());
					d2 = new SimpleDateFormat("dd.MM.yyyy").parse(o2.getCreatedDate());
				} catch (ParseException e) {
					return 0;
				}

				return d2.compareTo(d1);
			}

		});
		return carts;
	}
}
