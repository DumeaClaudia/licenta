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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.Product;
import com.license.Restaurant;
import com.license.ShoppingCartProducts;
import com.license.data.CurrentCartDetails;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

public class RetakeCartServlet extends HttpServlet {

	private static final long serialVersionUID = -1000517563127492468L;
	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}

		ObjectMapper mapper = new ObjectMapper();

		Long jsonRequest = mapper.readValue(json, Long.class);
		long cartId = jsonRequest;

		response.setContentType("application/json");

		List<ShoppingCartProducts> cartProducts = new ArrayList<ShoppingCartProducts>();
		List<CurrentCartDetails> cartDetailsProduct = new ArrayList<>();

		HttpSession session = request.getSession(false);
		Object idObj = session.getAttribute("userId");

		long idUser;
		
		if (idObj != null) {
			idUser = (Long) idObj;
			Long currentCart = shoppingCartService.getCurrentCart(idUser);
			shoppingCartService.deleteFromTablesOldCart(currentCart, idUser);
			shoppingCartService.updateNewCart(cartId, idUser);
		}

		if (idObj != null) {
			idUser = (Long) idObj;

			cartProducts = shoppingCartService.getCartProductsForUser(idUser, cartId);

			for (ShoppingCartProducts shoppingCartProduct : cartProducts) {

				CurrentCartDetails cartDetails = new CurrentCartDetails();
				Product product = shoppingCartService.getProduct(shoppingCartProduct.getIdProduct());
				Restaurant restaurant = restaurantService.getRestaurantById(product.getIdRestaurant());

				cartDetails.setIdProduct(product.getId());
				cartDetails.setNrProducts(shoppingCartProduct.getNrProducts());
				cartDetails.setNameProduct(product.getName());
				cartDetails.setNameRestaurant(restaurant.getName());
				cartDetails.setPrice(product.getPrice());
				cartDetails.setIdShoppingCart(cartId);

				cartDetailsProduct.add(cartDetails);
			}
		}
		mapper.writeValue(response.getOutputStream(), cartDetailsProduct);
	}
}
