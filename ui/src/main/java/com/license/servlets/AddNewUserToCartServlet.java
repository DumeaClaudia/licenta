package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.User;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

public class AddNewUserToCartServlet extends HttpServlet {

	private static final long serialVersionUID = 6101240663830324856L;
	@EJB
	ShoppingCartService shoppingCartService;
	@EJB
	UserService userService;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonRequest = mapper.readValue(json, String.class);
		
		
		HttpSession session = request.getSession(false); 
		Long s = (Long) session.getAttribute("userId");
		long userId = s.longValue();
		
		Long currentCartId = shoppingCartService.getCurrentCart(userId);
		
		User user = userService.getUserByName(jsonRequest);
		long idNewUser = user.getId();
		shoppingCartService.addUserToCart(idNewUser, currentCartId);
		shoppingCartService.updatePreviousCartForNewUser(idNewUser, currentCartId);
		mapper.writeValue(response.getOutputStream(), "ok");
	}

}
