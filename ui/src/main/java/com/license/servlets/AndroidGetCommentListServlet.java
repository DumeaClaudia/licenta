package com.license.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.Comment;
import com.license.User;
import com.license.data.CommentItem;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

public class AndroidGetCommentListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6590467544114427863L;
	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private UserService userService;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		        
    	HttpSession session = request.getSession(false); 
		Long s = (Long) session.getAttribute("userId");
		long userId = s.intValue();
		
		response.setContentType("application/json");
		ObjectMapper write_mapper = new ObjectMapper();
		
		List<CommentItem> jsonResponse;
		if(userId!=0) {
			long currentCartId = shoppingCartService.getCurrentCart(userId);

			jsonResponse = getAllComments(currentCartId, userId);
		}else {
			jsonResponse = new ArrayList<CommentItem>();
		}
		write_mapper.writeValue(response.getOutputStream(), jsonResponse);
	}


public List<CommentItem> getAllComments(long currentCart, long userId) {

	List<Comment> commentList = new ArrayList<Comment>();
	commentList = shoppingCartService.getAllCommentsForCart(currentCart);

	List<CommentItem> commentItemList = new ArrayList<>();
	for (Comment comm : commentList) {
		CommentItem commItem = new CommentItem();

		User user = userService.getUserById(comm.getIdUser());

		commItem.setUsername(user.getUsername());
		commItem.setFirstName(user.getFirstName());
		commItem.setLastName(user.getLastName());
		commItem.setDate(comm.getDate());
		commItem.setDescription(comm.getDescription());
		commItem.setOwnComment(userId == user.getId());

		commentItemList.add(commItem);

	}
	return commentItemList;

}
	
}
