package com.license.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.license.User;
import com.license.user.UserService;

public class AndroidLoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5468859485287773354L;
	@EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for username and password
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        
        User userDB = new User();
		userDB = userService.login(username, password);
		
        if (userDB!=null) {          	
            //get the old session and invalidate
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //generate a new session
            HttpSession newSession = request.getSession(true);
            System.out.println("Session = " + newSession.getId());
            
            newSession.setAttribute("username", userDB.getUsername());
            newSession.setAttribute("userId", userDB.getId());
            
            //setting session to expiry in 5 mins
            newSession.setMaxInactiveInterval(5*60);
          	response.sendError(HttpServletResponse.SC_OK);
            
        } else {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
} 