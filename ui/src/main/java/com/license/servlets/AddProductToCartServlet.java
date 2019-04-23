package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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

	private static final long serialVersionUID = 1L;

	@EJB
	private ShoppingCartService shoppingCartService;

	/***************************************************
	 * URL: /jsonservlet doPost(): receives JSON data, parse it, map it and send
	 * back as JSON
	 ****************************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. get received JSON data from request
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		long idUser = (Long) request.getSession().getAttribute("userId");

		// un fel de deserializare

		// 2. initiate jackson mapper // librarie pt a transforma json-ul in
		// clase de tipul ala..
		ObjectMapper mapper = new ObjectMapper();

		// 3. Convert received JSON to AddProductRequest
		AddProductRequest jsonRequest = mapper.readValue(json, AddProductRequest.class);

		// 4. Set response type to JSON
		response.setContentType("application/json");

		ShoppingCartResponse jsonResponse = new ShoppingCartResponse();

		// get active shopping cart for user
		// if not active shopping cart create shopping cart
		// insert into shopping cart

		// List<Long> activeCartList = shoppingCartService.getActiveShoppingCartForUser(idUser);
		Long activeCartList = shoppingCartService.getActiveShoppingCartForUser(idUser);
		
		// TODO change logic for activeCart
		Long productID = jsonRequest.getIdProduct();
		Product product = shoppingCartService.getProduct(productID);
		
		long activeCart = 0;
		if (activeCartList != 0) {
			activeCart = activeCartList;
		} else {
			activeCart = shoppingCartService.createShoppingCartService(idUser, product.getIdRestaurant());
		}
		
		int nrProducts = shoppingCartService.getNumberOfProducts(idUser, productID, activeCart);

		if (nrProducts == 0) {
			shoppingCartService.addProductToCart(idUser, productID, activeCart);
		} else {
			shoppingCartService.updateNumberOfProducts(idUser, productID, activeCart, nrProducts + 1);
		}
		
		
		jsonResponse.setProduct(product);

		// serializare

		// 6. Send List<Article> as JSON to client
		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
