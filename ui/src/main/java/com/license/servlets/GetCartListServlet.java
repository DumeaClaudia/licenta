package com.license.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.data.CartItem;

public class GetCartListServlet extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1115909816033418452L;

	private List<CartItem> cartItems = new ArrayList<CartItem>();
	@Override
	   public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
		
		//String s = request.getQueryString();
		cartItems.clear();
		//System.out.println("ID = " + s + " + " + request.getParameter("id"));
	    cartItems.add(new CartItem("Restaurant1", "12.12.2012"));
        cartItems.add(new CartItem("Restaurant1", "12.12.2012"));
        cartItems.add(new CartItem("Restaurant1", "12.12.2012"));
		
		// get_cart_example e apelat din android....
		
	      // Set the response message's MIME type
	      response.setContentType("application/json");
	      ObjectMapper mapper = new ObjectMapper();
	      
	      mapper.writeValue(response.getOutputStream(), cartItems);
	      
	   }
}
