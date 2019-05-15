package com.license.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.Restaurant;
import com.license.restaurant.RestaurantService;

public class AndroidGetRestaurantListServlet extends HttpServlet {

	private static final long serialVersionUID = -4151143308531421548L;
	@EJB
	public RestaurantService restaurantService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();

		write_mapper.writeValue(response.getOutputStream(), restaurants);
	}
	
}
