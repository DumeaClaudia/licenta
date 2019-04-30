package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.User;
import com.license.data.CartDetailsItem;
import com.license.data.UserOrderItem;
import com.license.data.UsersCartItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

public class GetAllUsersForCartServlet extends HttpServlet {

	private static final long serialVersionUID = -8827137267495363779L;
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
		List<UserOrderItem> jsonResponse = getUserOrderDetails(userId);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}

	public List<UserOrderItem> getUserOrderDetails(Long userId) {
		List<UserOrderItem> userDetails = new ArrayList<>();


		

		if (userId != null) {
			Long currentCart = (shoppingCartService.getCurrentCart(userId));
			List<Long> usersIds = userService.getUsersIds(currentCart);

			for (Long idUser : usersIds) {
				User user = userService.getUserById(idUser);
				Double price = CartDetailsItem
						.getCartDetailsItem(shoppingCartService, restaurantService, idUser, currentCart)
						.getCartSummary().getTotalPrice();
				UserOrderItem userOrderItem = new UserOrderItem(user.getUsername(), price);
				userDetails.add(userOrderItem);
			}
		}

		return userDetails;
	}
}
