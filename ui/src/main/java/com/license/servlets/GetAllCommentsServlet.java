package com.license.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.Comment;
import com.license.User;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;
import com.license.data.*;

public class GetAllCommentsServlet extends HttpServlet {

	private static final long serialVersionUID = -2359948777658774062L;
	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private UserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();

		Long idUser = (Long) request.getSession().getAttribute("userId");
		List<CommentItem> jsonResponse;
		if(idUser!=null) {
			long currentCartId = shoppingCartService.getCurrentCart(idUser);

			jsonResponse = getAllComments(currentCartId, idUser);
		}else {
			jsonResponse = new ArrayList<CommentItem>();
		}
	
		mapper.writeValue(response.getOutputStream(), jsonResponse);

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
