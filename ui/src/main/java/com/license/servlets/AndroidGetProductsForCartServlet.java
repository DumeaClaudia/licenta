package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.User;
import com.license.data.CartDetailsItem;
import com.license.data.ProductDetailsCartItem;
import com.license.data.ProductDetailsItem;
import com.license.data.UserProductsItem;
import com.license.data.RestaurantProductsItem;
import com.license.data.UsersCartItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

public class AndroidGetProductsForCartServlet extends HttpServlet {
	private static final long serialVersionUID = 6534492121358104596L;
	
	@EJB
	private UserService userService;
	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		response.setContentType("application/json");

		Long userId = (Long) request.getSession().getAttribute("userId");
		
		Long cartId = Long.parseLong(request.getParameter("cartId"));

		List<UserProductsItem> jsonResponse = getUserOrderDetails(userId,cartId);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}

	public List<UserProductsItem> getUserOrderDetails(Long userId, Long currentCart) {
		List<UserProductsItem> usersCart = new ArrayList<UserProductsItem>();

		if (userId != null) {
			List<Long> usersIds = userService.getUsersIds(currentCart);

			for (Long idUser : usersIds) {

				UsersCartItem usersCartItem = new UsersCartItem();
				User user = userService.getUserById(idUser);
				Double price = CartDetailsItem
						.getCartDetailsItem(shoppingCartService, restaurantService, idUser, currentCart)
						.getCartSummary().getTotalPrice();

				CartDetailsItem cartDetailsItem = CartDetailsItem.getCartDetailsItem(shoppingCartService,
						restaurantService, idUser, currentCart);
				usersCartItem.setCartDetails(cartDetailsItem);

				UserProductsItem usersProducts = new UserProductsItem();
				List<RestaurantProductsItem> restaurantItems = cartDetailsItem.getRestaurantProducts();

				if (cartDetailsItem != null && restaurantItems != null) {
					List<ProductDetailsCartItem> productCartDetails = new ArrayList<ProductDetailsCartItem>();

					for (RestaurantProductsItem restaurantProducts : restaurantItems) {
						for (ProductDetailsItem product : restaurantProducts.getProducts()) {
							ProductDetailsCartItem productDetails = new ProductDetailsCartItem();
							
							productDetails.setProductId(product.getIdProduct());
							productDetails.setProductName(product.getName());
							productDetails.setNrProducts(product.getNrProducts());
							productDetails.setPrice(product.getPrice());
							productDetails.setRestaurantName(restaurantProducts.getRestaurantName());
							productDetails.setRestaurantGeolocation(restaurantProducts.getRestaurantGeolocation());
							productDetails.setRestaurantAddress(restaurantProducts.getRestaurantAddress());
							productCartDetails.add(productDetails);
						}
					}
					usersProducts.setUsername(user.getUsername());
					usersProducts.setTotalPrice(price);

					productCartDetails.sort(new Comparator<ProductDetailsCartItem>() {
	                    @Override
	                    public int compare(ProductDetailsCartItem o1, ProductDetailsCartItem o2) {
	                        return o1.getProductName().compareTo(o2.getProductName());
	                    }
	                });			
	                usersProducts.setCartDetails(productCartDetails);
					usersCart.add(usersProducts);
				}
			}
		}
		return usersCart;
	}
}
