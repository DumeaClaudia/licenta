package com.license.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.Product;
import com.license.Restaurant;
import com.license.restaurant.RestaurantService;

public class AndroidGetRestaurantProductsServlet extends HttpServlet {

	private static final long serialVersionUID = 1506094678470162365L;
	@EJB
	public RestaurantService restaurantService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		long restaurantId = Long.parseLong(request.getParameter("restaurantId"));

		Restaurant restaurantResponse = new Restaurant();
		if (restaurantId != 0) {
			restaurantResponse = restaurantService.getRestaurantById(restaurantId);
			List<Product> products = restaurantService.getAllProductsForRestaurant(restaurantId);
			restaurantResponse.setProducts(products);
		}

		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();

		write_mapper.writeValue(response.getOutputStream(), restaurantResponse);
	}

}
