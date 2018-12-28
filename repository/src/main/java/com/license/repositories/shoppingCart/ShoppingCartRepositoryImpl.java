	package com.license.repositories.shoppingCart;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.license.Cart;
import com.license.Product;
import com.license.entities.CartForUserEntity;
import com.license.entities.ProductEntity;
import com.license.entities.ShoppingCartEntity;
import com.license.entities.ShoppingCartProductsEntity;
import com.license.entities.ShoppingCartUserEntity;

@Stateless
@Remote(ShoppingCartRepository.class)

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();

	
	public List<Product> retrieveShoppingCartProductsById(long id) {

		List<Product> scList = new ArrayList<>();
		Query query = em.createNamedQuery("shopping_cart_products.getProductsForCart");
		query.setParameter("idShoppingCart", id);
		List<ShoppingCartProductsEntity> qResult = query.getResultList();
		if (qResult == null) {
			System.out.println("nu au fost gasite produse pt shopping cart-ul ales");
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
			productResponse.setId(product.getId());

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

	public void removeProductFromCart(long idUser, long idProduct, long idShoppingCart) {
		
		List<ShoppingCartProductsEntity> shoppingCartProductsEntity = new ArrayList<ShoppingCartProductsEntity>();		
		
		Query query = em.createNamedQuery("shopping_cart_products.selectForDeleteProductForCart");
		query.setParameter("idUser", idUser);		
		query.setParameter("idProduct", idProduct);	
		query.setParameter("idShoppingCart", idShoppingCart);
		
		shoppingCartProductsEntity = query.getResultList();
		
		
		for( ShoppingCartProductsEntity product: shoppingCartProductsEntity ) {
			em.getTransaction().begin();
			em.remove(product);	
			em.getTransaction().commit();
			// sterge doar un produs
			break;
		}
		//em.persist(shoppingCartProductsEntity);
		
	}

	public List<Long> retrieveAllShoppingCartForUser(long idUser) {
		
		List<ShoppingCartUserEntity> shoppingCartsUsersEntity = new ArrayList<ShoppingCartUserEntity>();		
		
		Query query = em.createNamedQuery("shopping_cart_users.getAllShoppingCartsForUser");
		query.setParameter("idUser", idUser);		
		
		shoppingCartsUsersEntity = query.getResultList();
		
		List<Long> cartIds = new ArrayList<Long>();
		
		for( ShoppingCartUserEntity cart: shoppingCartsUsersEntity ) {
			cartIds.add(cart.getIdShoppingCart());
		}
		System.out.println("cart id-urile gasite sunt: "+cartIds);
		
		return cartIds;
	}

	public Cart retrieveCartById(long idCart) {
		Cart cart = new Cart();
		
		Query query = em.createNamedQuery("shopping_cart.getShoppingCart");
		query.setParameter("idShoppingCart", idCart);		
		
		ShoppingCartEntity shoppingCartEntity = (ShoppingCartEntity) query.getSingleResult();
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		cart.setIdCart(idCart);	
		cart.setCreatedDate(df.format(shoppingCartEntity.getCreatedDate()));
		if(shoppingCartEntity.getSendDate() != null) {
			cart.setSendDate(df.format(shoppingCartEntity.getSendDate()));
		}
		cart.setTotalPrice(shoppingCartEntity.getTotalPrice());			
		cart.setIdRestaurant(shoppingCartEntity.getIdRestaurant());
		if(shoppingCartEntity.getSendDate() == null ) {
			cart.setActive(true);
		}else {
			if(shoppingCartEntity.getSendDate().before(new Date())) {
				cart.setActive(false);			
			}			
		}
			 
		return cart;
	}	
}
