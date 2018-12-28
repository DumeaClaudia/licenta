package com.license;

import java.io.Serializable;

public class Cart implements Serializable{
		
	private static final long serialVersionUID = 4456771367569011086L;
	//`totalPrice`, `createdDate`, `sendDate`, `idRestaurant`)
	private Long idCart;
	private boolean isActive;
	private double totalPrice;
	private String createdDate;
	private String sendDate;
	private Long idRestaurant;
	
	public Long getIdCart() {
		return idCart;
	}
	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public Long getIdRestaurant() {
		return idRestaurant;
	}
	public void setIdRestaurant(Long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}
}
