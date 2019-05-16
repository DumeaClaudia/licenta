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
import com.license.shoppingCart.ShoppingCartService;

public class AndroidAddProductToCartServlet extends HttpServlet {

	private static final long serialVersionUID = 4083739153617040341L;
	@EJB
	private ShoppingCartService shoppingCartService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		mapper.writeValue(response.getOutputStream(), "ok");
	}
}
