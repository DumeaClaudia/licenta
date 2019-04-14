package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AddProductRequest;
import com.license.Product;
import com.license.ShoppingCartResponse;
import com.license.UserIdRequest;
import com.license.shoppingCart.ShoppingCartService;

public class GetCartProductsServlet extends HttpServlet {

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
		
		ObjectMapper mapper = new ObjectMapper();

		response.setContentType("application/json");
		List<Product> jsonResponse = new ArrayList<Product>();
		

		HttpSession session = request.getSession(false);
		Object idObj = session.getAttribute("userId");

		if (idObj != null) {
			long idUser = (Long) idObj;

			List<Long> activeCartList = shoppingCartService.getActiveShoppingCartForUser(idUser);
			long activeCart = 0;
			if (activeCartList.size() != 0) {
				activeCart = activeCartList.get(0);
				jsonResponse = shoppingCartService.getShoppingCartProducts(activeCart);
			} else {

			}
		}
		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
