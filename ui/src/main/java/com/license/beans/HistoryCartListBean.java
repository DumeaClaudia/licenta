package com.license.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.license.data.CartDetailsItem;
import com.license.data.CartSummaryItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

@ManagedBean
@SessionScoped
public class HistoryCartListBean {
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
			return CartDetailsItem.getCartDetailsItem(shoppingCartService, restaurantService, cartId);
		}

		return new CartDetailsItem();
	}

	public List<CartSummaryItem> getCartList() {
		FacesContext context = FacesContext.getCurrentInstance();
		userId = (Long) context.getExternalContext().getSessionMap().get("userId");

		List<CartSummaryItem> carts;
		if (userId != null) {
			carts = CartSummaryItem.getCartsSummary(shoppingCartService, restaurantService, userId.intValue());

			if (cartId == null && carts.size() != 0) {

				cartId = carts.get(0).getIdCart();
			}

		} else {
			carts = new ArrayList<CartSummaryItem>();

		}

		return carts;
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
