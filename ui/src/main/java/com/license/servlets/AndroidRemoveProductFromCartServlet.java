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
import com.license.Product;
import com.license.ShoppingCartResponse;
import com.license.shoppingCart.ShoppingCartService;

public class AndroidRemoveProductFromCartServlet extends HttpServlet {

	private static final long serialVersionUID = 878161795044240286L;
	@EJB
	private ShoppingCartService shoppingCartService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String text = "";
		if (br != null) {
			text = br.readLine();
		}
		long productId = Long.parseLong(request.getParameter("productId"));
		ObjectMapper mapper = new ObjectMapper();

		HttpSession session = request.getSession(false);
		Long s = (Long) session.getAttribute("userId");
		long userId = s.intValue();

		response.setContentType("application/json");
		long currentCartId = shoppingCartService.getCurrentCart(userId);

		int nrProducts = shoppingCartService.getNumberOfProducts(userId, productId, currentCartId);

		if (nrProducts == 0) {
			shoppingCartService.addProductToCart(userId, productId, currentCartId);
		} else {
			shoppingCartService.updateNumberOfProducts(userId, productId, currentCartId, nrProducts + 1);
		}

		mapper.writeValue(response.getOutputStream(), "ok");*/

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		
		long productId = Long.parseLong(request.getParameter("productId"));
		HttpSession session = request.getSession(false);
		Long s = (Long) session.getAttribute("userId");
		long userId = s.intValue();

		
		//long idUser = (Long) request.getSession().getAttribute("userId"); // ??????
		ObjectMapper mapper = new ObjectMapper();

	//	AddProductRequest jsonRequest = mapper.readValue(json, AddProductRequest.class);

		response.setContentType("application/json");

		ShoppingCartResponse jsonResponse = new ShoppingCartResponse();

		long activeCart = shoppingCartService.getCurrentCart(userId);//idUser

	//	Long productId = jsonRequest.getIdProduct();
		Product product = shoppingCartService.getProduct(productId);

		shoppingCartService.removeAProductFromCurrentCart(userId, productId, activeCart); // idUser

		jsonResponse.setProduct(product);

		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
