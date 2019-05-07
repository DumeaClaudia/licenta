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
import com.license.CurrentCartRequest;
import com.license.ValidationResponse;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

public class CheckoutCartServlet extends HttpServlet {

	private static final long serialVersionUID = -741385148657302336L;
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
		CurrentCartRequest jsonRequest = mapper.readValue(json, CurrentCartRequest.class);

		response.setContentType("application/json");
		ValidationResponse jsonResponse = new ValidationResponse();

		if (jsonRequest.getFirstName() == null || jsonRequest.getFirstName().isEmpty()
				|| jsonRequest.getLastName() == null || jsonRequest.getLastName().isEmpty()
				|| jsonRequest.getEmail() == null || jsonRequest.getEmail().isEmpty()
				|| jsonRequest.getTelephone() == null || jsonRequest.getTelephone().isEmpty()
				|| jsonRequest.getPayment() == null || jsonRequest.getPayment().isEmpty()) {

			jsonResponse.setMessage(
					"*Toate campurile sunt obligatorii. \n Va rugam sa le completati pentru a putea trimite comanda.*");
			jsonResponse.setValid(false);
		} else {
			jsonResponse.setMessage("ok");
			jsonResponse.setValid(true);

			long idUser = (Long) request.getSession().getAttribute("userId");
			Long currentCart = shoppingCartService.getCurrentCart(idUser);

			List<Long> usersIds = userService.getUsersIds(currentCart);

			for (Long utilizatorId : usersIds) {
				shoppingCartService.updateDateForCartAfterCheckout(utilizatorId, currentCart);
			}
		}

		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
