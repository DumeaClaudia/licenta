package com.license.repositories.shoppingCart.implementation;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.event.ListSelectionEvent;

import com.license.Product;
import com.license.Restaurant;
import com.license.User;
import com.license.entities.CartForUserEntity;
import com.license.entities.NativeShoppingCartEntity;
import com.license.entities.ProductEntity;
import com.license.entities.RestaurantEntity;
import com.license.entities.ShoppingCartEntity;
import com.license.entities.ShoppingCartProductsEntity;
import com.license.entities.ShoppingCartUserEntity;
import com.license.entities.UserEntity;
import com.license.repositories.restaurant.RestaurantRepository;
import com.license.repositories.shoppingCart.ShoppingCartRepository;

@Stateless
@Remote(ShoppingCartRepository.class)

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();

	public List<Product> retrieveShoppingCartById(long id) {

		List<Product> scList = new ArrayList<>();
		/*
		Query query = em.createNamedQuery("shopping_cart_products_native.getNativeShoppingCartInfo");
		query.setParameter(1, id);

		List<NativeShoppingCartEntity> qResult = query.getResultList();

		if (qResult == null) {
			System.out.println("se pare ca nu au fost gasite sc in baza de date ha ha ha");
		}*/
		
		
		
		Query query = em.createNamedQuery("shopping_cart_products.getProductsForCart");
		query.setParameter("idShoppingCart", id);

		List<ShoppingCartProductsEntity> qResult = query.getResultList();

		if (qResult == null) {
			System.out.println("se pare ca nu au fost gasite sc in baza de date ha ha ha");
		}
		for (ShoppingCartProductsEntity e : qResult) {
			Product product = this.getProductById(e.getIdProduct());
			scList.add(product);
		}
		return scList;
	}

	public List<Long> retrieveActiveShoppingCartForUserId(long idUser) {

		List<Long> idSc = new ArrayList<Long>();

		Query query = em.createNamedQuery("shoppingCartProducts.getNativeShoppingCartForUser");
		query.setParameter(1, idUser);

		List<CartForUserEntity> shoppingCartIdUsers = query.getResultList();

		if (shoppingCartIdUsers == null) {
			System.out.println("se pare ca nu au fost gasite sc in baza de date ha ha ha");
		}

		for (CartForUserEntity e : shoppingCartIdUsers) {
			idSc.add(e.getIdShoppingCart());
		}

		return idSc;
	}

	public Product getProductById(long idProduct) {

			ProductEntity product = new ProductEntity();
			Product productResponse = new Product();
			Query query = em.createNamedQuery("product.getProductById");

			query.setParameter("idProduct", idProduct);
				
			product = (ProductEntity) query.getSingleResult();
			
			if (product == null) {
				// return "";
			}
			
			productResponse.setIdRestaurant(product.getIdRestaurant());
			productResponse.setImage(product.getImage());
			productResponse.setName(product.getName());
			productResponse.setCategory(product.getCategory());
			productResponse.setDescription(product.getDescription());
			productResponse.setPrice(product.getPrice());
			productResponse.setDiscount(product.getDiscount());

			return productResponse;
	 }

	public long createShoppingCart(long idUser, long idRestaurant) {
		
		ShoppingCartEntity entity = new ShoppingCartEntity();

		entity.setIdRestaurant(idRestaurant);
		entity.setCreatedDate(Date.from(Instant.now()));
		
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		
		long idCart = entity.getId();
		
		ShoppingCartUserEntity userCartEntity = new ShoppingCartUserEntity();
		userCartEntity.setIdShoppingCart(idCart);
		userCartEntity.setIdUser(idUser);
		
		em.getTransaction().begin();
		em.persist(userCartEntity);
		em.getTransaction().commit();
		return idCart;	
	}

	
	public long addProductToCart(long idUser, long idProduct, long idShoppingCart) {
		ShoppingCartProductsEntity cartProductsEntity = new ShoppingCartProductsEntity();
		cartProductsEntity.setNrProducts(1);
		cartProductsEntity.setIdUser(idUser);
		cartProductsEntity.setIdProduct(idProduct);
		cartProductsEntity.setIdShoppingCart(idShoppingCart);
		
		em.getTransaction().begin();
		em.persist(cartProductsEntity);
		em.getTransaction().commit();
		
		long id = cartProductsEntity.getId(); 
		return id;
	}
	
}
