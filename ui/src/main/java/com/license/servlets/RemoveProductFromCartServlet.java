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

public class RemoveProductFromCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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

		List<Long> activeCartList = shoppingCartService.getActiveShoppingCartForUser(idUser);

		long activeCart = 0;

		Long productId = jsonRequest.getIdProduct();
		Product product = shoppingCartService.getProduct(productId);

		int nrProducts = shoppingCartService.getNumberOfProducts(idUser, productId, activeCart);

		if (activeCartList.size() != 0) {
			activeCart = activeCartList.get(0);
			
			shoppingCartService.removeAProductFromCurrentCart(idUser, productId, activeCart);
					
			/*
			if (nrProducts == 1) {
				shoppingCartService.removeProductFromCart(idUser, productId, activeCart);
			} else {
				if(nrProducts > 1) {					
					shoppingCartService.updateNumberOfProducts(idUser, productId, activeCart, nrProducts - 1);
				}
			}*/

		} else {

			/*
			 * activeCart =
			 * shoppingCartService.createShoppingCartService(jsonRequest.getIdUser(),
			 * product.getIdRestaurant());
			 */
		}

		jsonResponse.setProduct(product);

		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
