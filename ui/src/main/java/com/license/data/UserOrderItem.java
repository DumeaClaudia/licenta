package com.license.data;

public class UserOrderItem {
	
	String username;
	double price;
	
	public UserOrderItem(String username, double price) {
		this.username = username;
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

		
}
