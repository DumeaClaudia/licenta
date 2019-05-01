package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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
import com.license.data.UserOrderItem;
import com.license.data.UsersCartItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

public class GetAllProductsForCartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4860353942885489779L;
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
		List<UserProductsItem> jsonResponse = getUserOrderDetails(userId);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}

	public List<UserProductsItem> getUserOrderDetails(Long userId) {
		List<UserProductsItem> usersCart = new ArrayList<UserProductsItem>();

		if (userId != null) {
			Long currentCart = (shoppingCartService.getCurrentCart(userId));
			List<Long> usersIds = userService.getUsersIds(currentCart);

			for (Long idUser : usersIds) {

				UsersCartItem usersCartItem = new UsersCartItem();
				User user = userService.getUserById(idUser);
			/*	Double price = CartDetailsItem
						.getCartDetailsItem(shoppingCartService, restaurantService, idUser, currentCart)
						.getCartSummary().getTotalPrice();*/
				/*UserOrderItem userOrderItem = new UserOrderItem(user.getUsername(), price);*/
				CartDetailsItem cartDetailsItem = CartDetailsItem.getCartDetailsItem(shoppingCartService,
						restaurantService, idUser, currentCart);
			/*	usersCartItem.setUserDetails(userOrderItem);*/
				usersCartItem.setCartDetails(cartDetailsItem);

				UserProductsItem usersProducts = new UserProductsItem();
				List<RestaurantProductsItem> restaurantItems = cartDetailsItem.getRestaurantProducts();

				if (cartDetailsItem != null && restaurantItems != null) {
					List<ProductDetailsCartItem> productCartDetails = new ArrayList<ProductDetailsCartItem>();

					for (RestaurantProductsItem restaurantProducts : restaurantItems) {
						for (ProductDetailsItem product : restaurantProducts.getProducts()) {
							ProductDetailsCartItem productDetails = new ProductDetailsCartItem();

							productDetails.setProductName(product.getName());
							productDetails.setNrProducts(product.getNrProducts());
							productDetails.setPrice(product.getPrice());
							productDetails.setRestaurantName(restaurantProducts.getRestaurantName());
							productCartDetails.add(productDetails);
						}
					}
					usersProducts.setUsername(user.getUsername());
					usersProducts.setCartDetails(productCartDetails);
					usersCart.add(usersProducts);
				}
			}
		}
//		System.out.println("Servlet: GetAllProductsForCart: " + usersCart);
		return usersCart;
	}
}
