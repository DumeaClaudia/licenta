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
@Entity(name = "comment")
@NamedQueries({
		@NamedQuery(name = "comment.display", query = "Select c FROM comment c where c.idUser=:idUser"),
		@NamedQuery(name = "comment.getAllCommentsForCart", query = "Select c FROM comment c where c.idCart=:idCart order by c.date ")})
public class CommentEntity implements Serializable{
	
	private static final long serialVersionUID = -8210015606459450333L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "idUser")
	private long idUser;
	
	@Column(name = "idCart")
	private long idCart;

	@Column(name = "date")
	private Date date;
	
	@Column(name = "description")
	private String description;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdCart() {
		return idCart;
	}

	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}

}
