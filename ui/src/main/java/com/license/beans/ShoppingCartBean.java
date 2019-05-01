package com.license.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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

	public CartDetailsItem getCartDetails() {
		FacesContext context = FacesContext.getCurrentInstance();
		userId = (Long) context.getExternalContext().getSessionMap().get("userId");

		long currentCartId = 0;
		if (userId != null) {
			currentCartId = shoppingCartService.getCurrentCart(userId);
			if (currentCartId != 0) {
				return CartDetailsItem.getCartDetailsItem(shoppingCartService, restaurantService, userId,
						currentCartId);
			}
		}

		return new CartDetailsItem();
	}

	public Map<String, UsersCartItem> getUserOrderDetails() {
		Map<String, UsersCartItem> usersCart = new HashMap<String, UsersCartItem>();
		if (userId != null) {
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

	public List<UserProductsItem> getCartProductDetails() {
		List<UserProductsItem> usersCart = new ArrayList<UserProductsItem>();
		cartTotalPrice = 0.00;
		if (userId != null) {
			Long currentCart = (shoppingCartService.getCurrentCart(userId));
			List<Long> usersIds = userService.getUsersIds(currentCart);

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

	public List<CommentItem> getAllComments() {
		List<CommentItem> commentItemList = new ArrayList<>();
		if (userId != null) {

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

	private List<String> getUsersName(List<Long> usersIds) {
		List<User> users = userService.getAllUsers(usersIds);
		List<String> usersNames = new ArrayList<>();
		for (User user : users) {
			usersNames.add(user.getUsername());
		}
		return usersNames;
	}

	public void addUserToCart(String value) {
		System.out.println(value);

	}

	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String address;
	private String payment = new String("Ramburs");
	private List<String> paymentList = new ArrayList<>();

	@PostConstruct
	public void init() {
		paymentList.add("Ramburs");
		paymentList.add("Card Debit");
		paymentList.add("Card Credit");
	}

	public long getUserId() {
		if (this.userId != null && this.userId != 0) {
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

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public List<String> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<String> paymentList) {
		this.paymentList = paymentList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getCartTotalPrice() {
		return cartTotalPrice;
	}

	public void setCartTotalPrice(double cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}

	public List<String> getRemainingUsers() {
		return remainingUsers;
	}

	public void setRemainingUsers(List<String> remainingUsers) {
		this.remainingUsers = remainingUsers;
	}

	public String checkout() {
		return "shoppingCart?faces-redirect=true";
	}

}
