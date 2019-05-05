package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.shoppingCart.ShoppingCartService;

public class AddCommentServlet extends HttpServlet {

	private static final long serialVersionUID = -1013182752426696826L;
	@EJB
	private ShoppingCartService shoppingCartService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonRequest = mapper.readValue(json, String.class);

		long idUser = (Long) request.getSession().getAttribute("userId");	
		long currentCartId = shoppingCartService.getCurrentCart(idUser);
		
		shoppingCartService.addNewComment(idUser, currentCartId, jsonRequest, new Date());
		
		mapper.writeValue(response.getOutputStream(), "ok");
	}
}
