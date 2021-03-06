package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AddProductRequest;
import com.license.Product;
import com.license.ShoppingCartResponse;
import com.license.shoppingCart.ShoppingCartService;

public class RemoveProductFromCartServlet extends HttpServlet {

	private static final long serialVersionUID = -2688133393619193419L;
	@EJB
	private ShoppingCartService shoppingCartService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		long idUser = (Long) request.getSession().getAttribute("userId");
		ObjectMapper mapper = new ObjectMapper();

		AddProductRequest jsonRequest = mapper.readValue(json, AddProductRequest.class);

		response.setContentType("application/json");

		ShoppingCartResponse jsonResponse = new ShoppingCartResponse();

		long activeCart = shoppingCartService.getCurrentCart(idUser);

		Long productId = jsonRequest.getIdProduct();
		Product product = shoppingCartService.getProduct(productId);

		shoppingCartService.removeAProductFromCurrentCart(idUser, productId, activeCart);

		jsonResponse.setProduct(product);

		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
