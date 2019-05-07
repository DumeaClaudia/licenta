package com.license.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.license.data.CartDetailsItem;
import com.license.data.CartSummaryItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

@ManagedBean
@SessionScoped
public class HistoryCartListBean implements Serializable {

	private static final long serialVersionUID = -2420714267303092129L;
	private Long cartId;
	private Long userId;

	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;

	public CartDetailsItem getCartDetails() {
		List<Long> cartIdsUser = null;
		if (userId != null) {
			cartIdsUser = shoppingCartService.getAllShoppingCartsForUser(userId);
		}
		if (userId != null && cartId != null && cartIdsUser.contains(cartId)) {
			return CartDetailsItem.getCartDetailsItem(shoppingCartService, restaurantService, userId, cartId);
		}

		return new CartDetailsItem();
	}

	public List<CartSummaryItem> getCartList() {

		if (!this.userIsLogged()) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext extContext = context.getExternalContext();
			try {
				extContext.redirect(extContext.getRequestContextPath() + "/pages/home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		//userId = (Long) context.getExternalContext().getSessionMap().get("userId");
		Long currentCartId = shoppingCartService.getCurrentCart(userId);
		context.getExternalContext().getSessionMap().put("currentCartId", currentCartId);

		List<CartSummaryItem> carts = new ArrayList<CartSummaryItem>();
		if (userId != null) {
			carts = CartSummaryItem.getCartsSummary(shoppingCartService, restaurantService, userId.intValue());

			if (cartId == null && carts.size() != 0) {
				cartId = carts.get(0).getIdCart();
			}
		}
		return carts;
	}

	private boolean userIsLogged() {
		if (userId == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			userId = (Long) context.getExternalContext().getSessionMap().get("userId");
		}

		return userId != null;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
