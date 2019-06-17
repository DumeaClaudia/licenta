package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.CheckoutDetails;
import com.license.CurrentCartRequest;
import com.license.DeliveryData;
import com.license.ValidationResponse;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

public class AndroidCheckoutCartServlet extends HttpServlet {
	private static final long serialVersionUID = 4080798399070107560L;
	@EJB
	ShoppingCartService shoppingCartService;
	@EJB
	UserService userService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}

		ObjectMapper mapper = new ObjectMapper();
		CheckoutDetails jsonRequest = new CheckoutDetails();
		try {
			jsonRequest = mapper.readValue(json, CheckoutDetails.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");

		long idUser = (Long) request.getSession().getAttribute("userId");
		Long currentCart = shoppingCartService.getCurrentCart(idUser);

		List<Long> usersIds = userService.getUsersIds(currentCart);

		shoppingCartService.updateDateForCartAfterCheckout(usersIds, currentCart);

		DeliveryData deliveryDetails = new DeliveryData();

		deliveryDetails.setIdCart(currentCart);
		deliveryDetails.setTotalPrice(0); // jsonRequest.getTotalPrice());
		deliveryDetails.setSendDate(new Date());
		deliveryDetails.setAddress(jsonRequest.getAddress());
		deliveryDetails.setUsername(""); // jsonRequest.getFirstName() + " " + jsonRequest.getLastName());

		shoppingCartService.addDeliveryData(deliveryDetails);
		

		mapper.writeValue(response.getOutputStream(), currentCart + "");

	}
}
