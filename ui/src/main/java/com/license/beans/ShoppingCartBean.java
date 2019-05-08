package com.license.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.license.Comment;
import com.license.User;
import com.license.data.CartDetailsItem;
import com.license.data.CommentItem;
import com.license.data.ProductDetailsCartItem;
import com.license.data.ProductDetailsItem;
import com.license.data.RestaurantProductsItem;
import com.license.data.UserOrderItem;
import com.license.data.UserProductsItem;
import com.license.data.UsersCartItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

@ManagedBean
@SessionScoped
public class ShoppingCartBean implements Serializable {

	private static final long serialVersionUID = 8618627595602029032L;

	private Long userId;
	private double totalPrice;
	private double cartTotalPrice;
	private List<String> remainingUsers;

	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;
	@EJB
	private UserService userService;

	public void addUserToCart(String value) {
		System.out.println(value);

	}

	public String checkout() {
		return "shoppingCart?faces-redirect=true";
	}

	public List<CommentItem> getAllComments() {
		List<CommentItem> commentItemList = new ArrayList<>();
		if (this.userIsLogged()) {

			Long currentCart = (shoppingCartService.getCurrentCart(userId));
			List<Comment> commentList = new ArrayList<Comment>();
			commentList = shoppingCartService.getAllCommentsForCart(currentCart);

			for (Comment comm : commentList) {
				CommentItem commItem = new CommentItem();

				User user = userService.getUserById(comm.getIdUser());

				commItem.setUsername(user.getUsername());
				commItem.setFirstName(user.getFirstName());
				commItem.setLastName(user.getLastName());
				commItem.setDate(comm.getDate());
				commItem.setDescription(comm.getDescription());
				commItem.setOwnComment(userId.equals(user.getId()));

				commentItemList.add(commItem);

			}
		}
		return commentItemList;

	}

	public CartDetailsItem getCartDetails() {

		long currentCartId = 0;
		if (this.userIsLogged()) {
			currentCartId = shoppingCartService.getCurrentCart(userId);
			if (currentCartId != 0) {
				return CartDetailsItem.getCartDetailsItem(shoppingCartService, restaurantService, userId,
						currentCartId);
			}
		}

		return new CartDetailsItem();
	}

	public List<UserProductsItem> getCartProductDetails() {
		List<UserProductsItem> usersCart = new ArrayList<UserProductsItem>();

		cartTotalPrice = 0.00;
		if (this.userIsLogged()) {
			Long currentCart = (shoppingCartService.getCurrentCart(userId));
			List<Long> usersIds = userService.getUsersIds(currentCart);

			remainingUsers = getUsersName(usersIds);

			for (Long idUser : usersIds) {

				UsersCartItem usersCartItem = new UsersCartItem();
				User user = userService.getUserById(idUser);
				Double price = CartDetailsItem
						.getCartDetailsItem(shoppingCartService, restaurantService, idUser, currentCart)
						.getCartSummary().getTotalPrice();

				CartDetailsItem cartDetailsItem = CartDetailsItem.getCartDetailsItem(shoppingCartService,
						restaurantService, idUser, currentCart);
				usersCartItem.setCartDetails(cartDetailsItem);

				UserProductsItem usersProducts = new UserProductsItem();
				List<RestaurantProductsItem> restaurantItems = cartDetailsItem.getRestaurantProducts();

				if (cartDetailsItem != null && restaurantItems != null) {
					List<ProductDetailsCartItem> productCartDetails = new ArrayList<ProductDetailsCartItem>();

					for (RestaurantProductsItem restaurantProducts : restaurantItems) {
						for (ProductDetailsItem product : restaurantProducts.getProducts()) {
							ProductDetailsCartItem productDetails = new ProductDetailsCartItem();

							productDetails.setProductName(product.getName());
							productDetails.setNrProducts(product.getNrProducts());
							productDetails.setPrice(product.getPrice());
							productDetails.setRestaurantName(restaurantProducts.getRestaurantName());
							productCartDetails.add(productDetails);
						}
					}
					usersProducts.setUsername(user.getUsername());
					usersProducts.setTotalPrice(price);
					usersProducts.setCartDetails(productCartDetails);
					usersCart.add(usersProducts);

					cartTotalPrice += price;
				}
			}
		}
		return usersCart;
	}

	public long getUserId() {
		if (this.userIsLogged()) {
			return this.userId;
		}

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext extContext = context.getExternalContext();
		try {
			extContext.redirect(extContext.getRequestContextPath() + "/pages/home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Map<String, UsersCartItem> getUserOrderDetails() {
		Map<String, UsersCartItem> usersCart = new HashMap<String, UsersCartItem>();
		if (this.userIsLogged()) {
			Long currentCart = (shoppingCartService.getCurrentCart(userId));
			List<Long> usersIds = userService.getUsersIds(currentCart);

			remainingUsers = getUsersName(usersIds);
			totalPrice = 0.00;

			for (Long idUser : usersIds) {
				UsersCartItem usersCartItem = new UsersCartItem();
				User user = userService.getUserById(idUser);
				Double price = CartDetailsItem
						.getCartDetailsItem(shoppingCartService, restaurantService, idUser, currentCart)
						.getCartSummary().getTotalPrice();
				UserOrderItem userOrderItem = new UserOrderItem(user.getUsername(), price);
				CartDetailsItem cartDetailsItem = CartDetailsItem.getCartDetailsItem(shoppingCartService,
						restaurantService, idUser, currentCart);
				usersCartItem.setUserDetails(userOrderItem);
				usersCartItem.setCartDetails(cartDetailsItem);

				totalPrice += cartDetailsItem.getCartSummary().getTotalPrice();

				usersCart.put(user.getUsername(), usersCartItem);

			}
		}

		return usersCart;
	}

	private boolean userIsLogged() {
		if (userId == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			userId = (Long) context.getExternalContext().getSessionMap().get("userId");
		}

		return userId != null;
	}

	private List<String> getUsersName(List<Long> usersIds) {
		List<User> users = userService.getAllUsers(usersIds);
		List<String> usersNames = new ArrayList<>();
		for (User user : users) {
			usersNames.add(user.getUsername());
		}
		return usersNames;
	}

	public void setCartTotalPrice(double cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}

	public void setRemainingUsers(List<String> remainingUsers) {
		this.remainingUsers = remainingUsers;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getCartTotalPrice() {
		return cartTotalPrice;
	}

	public List<String> getRemainingUsers() {
		return remainingUsers;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

}
