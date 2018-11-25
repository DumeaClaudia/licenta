package com.license.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCartServlet2 extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1115909816033418452L;


	@Override
	   public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
		String s = request.getQueryString();
		
		System.out.println("ID = " + s + " + " + request.getParameter("id"));
		
		// nume restaurant
		// imaginea restaurant
		// locatie catre google maps
		// pt fiecare produs :  nume + pret + imagine( daca are)
		
		// in android lista de produse (nume + pret + imagine( daca are))
		
	      // Set the response message's MIME type
	      response.setContentType("application/json");
	      // Allocate a output writer to write the response message into the network socket
	      PrintWriter out = response.getWriter();
	 
	      // Write the response message, in an HTML page
	      try {
	         out.println("{value1: 'bla', value2: 'foo'}");
	      
	      } finally {
	         out.close();  // Always close the output writer
	      }
	   }
}
