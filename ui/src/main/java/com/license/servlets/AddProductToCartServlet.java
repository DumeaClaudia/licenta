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

public class AddProductToCartServlet extends HttpServlet {

	private static final long serialVersionUID = -1295554876626694255L;
	
	@EJB
	private ShoppingCartService shoppingCartService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		response.setContentType("application/json");

		ObjectMapper mapper = new ObjectMapper();
		AddProductRequest jsonRequest = mapper.readValue(json, AddProductRequest.class);
		
		long idUser = (Long) request.getSession().getAttribute("userId");
		Long currentCart = shoppingCartService.getCurrentCart(idUser);

		Long productID = jsonRequest.getIdProduct();
		Product product = shoppingCartService.getProduct(productID);

		int nrProducts = shoppingCartService.getNumberOfProducts(idUser, productID, currentCart);
		
		if (nrProducts == 0) {
			shoppingCartService.addProductToCart(idUser, productID, currentCart);
		} else {
			shoppingCartService.updateNumberOfProducts(idUser, productID, currentCart, nrProducts + 1);
		}
		
		ShoppingCartResponse jsonResponse = new ShoppingCartResponse();
		jsonResponse.setProduct(product);
		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
