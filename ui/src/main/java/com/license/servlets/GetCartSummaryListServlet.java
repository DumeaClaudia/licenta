package com.license.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.data.CartSummaryItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

public class GetCartSummaryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1115909816033418452L;
	@EJB
	public ShoppingCartService shoppingCartService;
	@EJB
	public RestaurantService restaurantService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		HttpSession session = request.getSession(false); 
		Long s = (Long) session.getAttribute("userId");
		int userId = s.intValue();
		
		List<CartSummaryItem> carts = CartSummaryItem.getCartsSummary(shoppingCartService, restaurantService, (int) userId);
		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();

		write_mapper.writeValue(response.getOutputStream(), carts);
	}
	
}
