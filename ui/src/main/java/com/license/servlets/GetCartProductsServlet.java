package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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

public class GetCartProductsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4099624866563027619L;

	@EJB
	private ShoppingCartService shoppingCartService;

	@EJB
	RestaurantService restaurantService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}

		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");

		List<ShoppingCartProducts> cartProducts = new ArrayList<ShoppingCartProducts>();
		List<CurrentCartDetails> cartDetailsProduct = new ArrayList<>();

		HttpSession session = request.getSession(false);
		Object idObj = session.getAttribute("userId");

		if (idObj != null) {
			long idUser = (Long) idObj;
			Long currentCartId = shoppingCartService.getCurrentCart(idUser);

			if (currentCartId == 0) {
				currentCartId = shoppingCartService.createNewCartForUser(idUser);
			}
			
			cartProducts = shoppingCartService.getCartProductsForUser(idUser, currentCartId);

			for (ShoppingCartProducts shoppingCartProduct : cartProducts) {

				CurrentCartDetails cartDetails = new CurrentCartDetails();
				Product product = shoppingCartService.getProduct(shoppingCartProduct.getIdProduct());
				Restaurant restaurant = restaurantService.getRestaurantById(product.getIdRestaurant());

				cartDetails.setIdProduct(product.getId());
				cartDetails.setNrProducts(shoppingCartProduct.getNrProducts());
				cartDetails.setNameProduct(product.getName());
				cartDetails.setNameRestaurant(restaurant.getName());
				cartDetails.setPrice(product.getPrice());
				cartDetails.setIdShoppingCart(currentCartId);

				cartDetailsProduct.add(cartDetails);
			}
		}
//		System.out.println("Servlet: GetCartProductsServlet: " + cartDetailsProduct);
		mapper.writeValue(response.getOutputStream(), cartDetailsProduct);
	}
}
