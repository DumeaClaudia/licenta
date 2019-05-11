package com.license;

import java.io.Serializable;
import java.util.Date;

public class DeliveryData implements Serializable{
	
	private static final long serialVersionUID = 631852155910929298L;
	
	private long idCart;
	private String username;
	private double totalPrice;
	private Date sendDate;
	private String address;
	
	public long getIdCart() {
		return idCart;
	}
	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
