package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AddProductRequest;
import com.license.Product;
import com.license.Restaurant;
import com.license.ShoppingCartResponse;
import com.license.UserIdRequest;
import com.license.services.RestaurantService;
import com.license.services.ShoppingCartService;

public class GetRestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private RestaurantService restaurantsService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		ObjectMapper mapper = new ObjectMapper();
		UserIdRequest jsonRequest = mapper.readValue(json, UserIdRequest.class);

		response.setContentType("application/json");
		

		Restaurant jsonResponse = new Restaurant();
		Restaurant restaurantDetails = restaurantsService.getRestaurantById(jsonRequest.getIdUser());
		
		if (restaurantDetails != null) {
			
			jsonResponse = restaurantsService.getRestaurantById(4);
		} else {
			
		}
		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
