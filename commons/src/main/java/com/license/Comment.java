package com.license;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

	private static final long serialVersionUID = 133161657895309927L;
	
	private long idUser;
	private long isCart;
	private Date date;
	private String description;
	
	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIsCart() {
		return isCart;
	}

	public void setIsCart(long isCart) {
		this.isCart = isCart;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
