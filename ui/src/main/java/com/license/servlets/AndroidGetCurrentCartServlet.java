package com.license.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.data.CartDetailsItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

public class AndroidGetCurrentCartServlet extends HttpServlet {
	private static final long serialVersionUID = -4978184052053003912L;
	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		Long s = (Long) session.getAttribute("userId");
		long userId = s.intValue();
		long cartId = shoppingCartService.getCurrentCart(userId);

		CartDetailsItem cartDetailsItem = CartDetailsItem.getCartDetailsItem(shoppingCartService, restaurantService,
				userId, cartId);

		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();
		write_mapper.writeValue(response.getOutputStream(), cartDetailsItem);

	}

}
