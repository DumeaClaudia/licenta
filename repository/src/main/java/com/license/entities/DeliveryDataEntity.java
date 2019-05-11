package com.license.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "delivery_data")
@NamedQueries(
		@NamedQuery(name = "delivery_data.getDeliveryData", query = "Select p FROM delivery_data p where p.idCart = :idCart"))
public class DeliveryDataEntity implements Serializable{
	
	private static final long serialVersionUID = 8825785208351972041L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "idCart")
	private long idCart;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "totalPrice")
	private double totalPrice;

	@Column(name = "sendDate")
	private Date sendDate;
	
	@Column(name = "address")
	private String address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
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
