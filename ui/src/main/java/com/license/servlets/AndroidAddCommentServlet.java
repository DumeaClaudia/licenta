package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.shoppingCart.ShoppingCartService;

public class AndroidAddCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1233889452854172590L;
	@EJB
	private ShoppingCartService shoppingCartService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String text = "";
		if (br != null) {
			text = br.readLine();
		}
		ObjectMapper mapper = new ObjectMapper();

		HttpSession session = request.getSession(false);
		Long s = (Long) session.getAttribute("userId");
		long userId = s.intValue();

		response.setContentType("application/json");
		long currentCartId = shoppingCartService.getCurrentCart(userId);
		
		shoppingCartService.addNewComment(userId, currentCartId, text, new Date());

		mapper.writeValue(response.getOutputStream(), "ok");
	}
}
