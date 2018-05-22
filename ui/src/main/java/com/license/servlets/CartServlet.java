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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AddProductRequest;
import com.license.Product;
import com.license.ShoppingCart;
import com.license.ShoppingCartResponse;
import com.license.services.ShoppingCartService;

public class CartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ShoppingCartService shoppingCartService;

	// This will store all received articles
	
	/***************************************************
	 * URL: /jsonservlet
	 * doPost(): receives JSON data, parse it, map it and send back as JSON
	 ****************************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException{
	    
		// 1. get received JSON data from request
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if(br != null){
			json = br.readLine();
		}
		
		//un fel de deserializare

		// 2. initiate jackson mapper // librarie pt a transforma json-ul in
		// clase de tipul ala..
    	ObjectMapper mapper = new ObjectMapper();
    	
    	// 3. Convert received JSON to Article
    	AddProductRequest jsonRequest = mapper.readValue(json, AddProductRequest.class);

		// 4. Set response type to JSON
		response.setContentType("application/json");		    
    	
    	// 5. Add article to List<Article>
		//articles.add(article);

		ShoppingCartResponse jsonResponse = new ShoppingCartResponse();
		jsonResponse.setProducts( new ArrayList<Product>());

		
		List<ShoppingCart> cart = shoppingCartService.getShoppingCart(1);
		jsonResponse.setShoppingCart(cart.get(0));
		
		// id-ul produsului ar cam trebui 
		
		/*Product product = shoppingCartService.addProductToShoppigCart(3);
		jsonResponse.setProduct(product);*/


		/*
		 * List<ShoppingCart> cart = shoppingCartService.getShoppingCart(1);
		 * jsonResponse.setShoppingCart(cart.get(0));
		 */ // ce era inainte de afisa doar un rand.....

		Product product = shoppingCartService.addProductToShoppigCart(3);
		jsonResponse.setProduct(product);


		//serializare

		// 6. Send List<Article> as JSON to client
    	mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
