package com.license.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/* NativeQuery se comporta ca in sql, nu jpql cum face NamedQuery*/
@Entity(name = "shopping_cart_products")
@NamedNativeQueries({ 
@NamedNativeQuery(
		  name="shoppingCartProducts.getNativeShoppingCartInfo",
		  query="select   scp.idShoppingCart as idShoppingCart ,p.id as idProduct, u.username as user, r.name as restaurant, p.name as product  FROM shopping_cart_products scp \n" + 
					"	join shopping_cart sc on scp.idShoppingCart=sc.id \n" + 
					"	join restaurant r on sc.idRestaurant=r.id\n" + 
					"	join user u on scp.idUser = u.id \n" + 
					"	join product p on scp.idProduct=p.id\n" + 
					"	where sc.id=?1",
		  resultClass=NativeShoppingCartEntity.class
		)}

)

public class NativeShoppingCartEntity {
	@Id
	@Column(name = "idShoppingCart")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "idProduct")
	private long idProduct;

	@Column(name = "nrProducts")
	private int nrProducts;

	@Column(name = "restaurant")
	private String restaurant;
	
	@Column(name = "product")
	private String product;
	
	@Column(name = "user")
	private String user;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public int getNrProducts() {
		return nrProducts;
	}

	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
