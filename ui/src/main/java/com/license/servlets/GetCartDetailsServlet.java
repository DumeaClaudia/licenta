package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.UserIdRequest;
import com.license.data.CartDetailsItem;
import com.license.data.CartItem;
import com.license.data.CartSummaryItem;

public class GetCartDetailsServlet extends HttpServlet {
	/**
	* 
	*/
	private static final long serialVersionUID = 1115909816033418452L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
		int userId = 1;
		int cartId = 1;
		List<CartDetailsItem> cartItems = new ArrayList<CartDetailsItem>();
		
		

		// get_cart_example e apelat din android....
		System.out.println("Count = " + cartItems.size());
		// Set the response message's MIME type
		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();

		write_mapper.writeValue(response.getOutputStream(), cartItems);

	}
}
