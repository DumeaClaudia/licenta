package com.license.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.LoginRequest;
import com.license.User;
import com.license.ValidationResponse;
import com.license.user.UserService;

public class CheckLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}

		ObjectMapper mapper = new ObjectMapper();
		LoginRequest jsonRequest = mapper.readValue(json, LoginRequest.class);
		
		response.setContentType("application/json");
		User user =  userService.login(jsonRequest.getUsername(), jsonRequest.getPassword());	
		
		ValidationResponse jsonResponse = new ValidationResponse();
		if(user==null) {
			jsonResponse.setMessage("Username or password is not valid. Please try again.");
			jsonResponse.setValid(false);
		}else {
			jsonResponse.setMessage("Logged in");
			jsonResponse.setValid(true);
		}
		
		mapper.writeValue(response.getOutputStream(), jsonResponse);
	}
}
