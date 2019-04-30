package com.license.repositories.shoppingCart;

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
import com.license.Comment;
import com.license.Product;
import com.license.ShoppingCartProducts;
import com.license.entities.CartForUserEntity;
import com.license.entities.CommentEntity;
import com.license.entities.ProductEntity;
import com.license.entities.ShoppingCartEntity;
import com.license.entities.ShoppingCartProductsEntity;
import com.license.entities.ShoppingCartUserEntity;

@Stateless
@Remote(ShoppingCartRepository.class)

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();

	@SuppressWarnings("unchecked")
	public List<Product> retrieveShoppingCartProductsById(long id) {

		List<Product> scList = new ArrayList<>();
		Query query = em.createNamedQuery("shopping_cart_products.getProductsFromCart");
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

	@SuppressWarnings("unchecked")
	public Long retrieveActiveShoppingCartForUserId(long idUser) {

		Query query = em.createNamedQuery("shoppingCartProducts.getNativeShoppingCartForUser");
		query.setParameter(1, idUser);
		List<CartForUserEntity> shoppingCartIdUsers = query.getResultList();
		if (shoppingCartIdUsers == null) {
			System.out.println("se pare ca nu au fost gasite sc in baza de date ha ha ha");
		}

		if (shoppingCartIdUsers != null && !shoppingCartIdUsers.isEmpty()) {
			return shoppingCartIdUsers.get(0).getIdShoppingCart();

		}
		return createCartForUser(idUser);

	}

	@SuppressWarnings("unchecked")
	public Long retrieveCurrentCartForUser(long idUser) {
		Query query = em.createNamedQuery("shopping_cart_users.getCurrentCartForUser");
		query.setParameter("idUser", idUser);
		query.setParameter("isCurrentCart", true);
		List<Long> cartIds = query.getResultList();
		if (cartIds.size() > 0) { // nu e bine daca sunt mai multe cart-uri cu 1 pt acelasi user..
			return cartIds.get(0);
		}

		return (Long) createCartForUser(idUser);

	}

	public Product getProductById(long idProduct) {

		ProductEntity product = new ProductEntity();
		Product productResponse = new Product();
		Query query = em.createNamedQuery("product.getProductById");
		query.setParameter("idProduct", idProduct);
		product = (ProductEntity) query.getSingleResult();

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
		userCartEntity.setCurrentCart(true);

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

	@SuppressWarnings("unchecked")
	public void removeProductFromCart(long idUser, long idProduct, long idShoppingCart) {

		List<ShoppingCartProductsEntity> shoppingCartProductsEntity = new ArrayList<ShoppingCartProductsEntity>();

		Query query = em.createNamedQuery("shopping_cart_products.deleteProductFromCart");
		query.setParameter("idUser", idUser);
		query.setParameter("idProduct", idProduct);
		query.setParameter("idShoppingCart", idShoppingCart);

		shoppingCartProductsEntity = query.getResultList();

		for (ShoppingCartProductsEntity product : shoppingCartProductsEntity) {
			em.getTransaction().begin();
			em.remove(product);
			em.getTransaction().commit();
			// sterge doar un produs
			break;
		}
		// em.persist(shoppingCartProductsEntity);

	}

	public void removeProductFromCurrentCart(long idUser, long idProduct, long idShoppingCart) {

		ShoppingCartProductsEntity shoppingCartProductsEntity = new ShoppingCartProductsEntity();

		Query query = em.createNamedQuery("shopping_cart_products.deleteProductFromCart");
		query.setParameter("idUser", idUser);
		query.setParameter("idProduct", idProduct);
		query.setParameter("idShoppingCart", idShoppingCart);

		shoppingCartProductsEntity = (ShoppingCartProductsEntity) query.getResultList().get(0);

		int nrProducts = shoppingCartProductsEntity.getNrProducts();

		em.getTransaction().begin();
		em.remove(shoppingCartProductsEntity);

		if (nrProducts > 1) {
			ShoppingCartProductsEntity cartProductsEntity = new ShoppingCartProductsEntity();

			cartProductsEntity.setNrProducts(nrProducts - 1);
			cartProductsEntity.setIdUser(idUser);
			cartProductsEntity.setIdProduct(idProduct);
			cartProductsEntity.setIdShoppingCart(idShoppingCart);
			em.persist(cartProductsEntity);
		}

		em.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	public void removeProductFromCartDetails(long idUser, long idProduct, long idShoppingCart) {

		List<ShoppingCartProductsEntity> shoppingCartProductsEntity = new ArrayList<ShoppingCartProductsEntity>();

		Query query = em.createNamedQuery("shopping_cart_products.deleteProductFromCart");
		query.setParameter("idUser", idUser);
		query.setParameter("idProduct", idProduct);
		query.setParameter("idShoppingCart", idShoppingCart);

		shoppingCartProductsEntity = query.getResultList();

		for (ShoppingCartProductsEntity product : shoppingCartProductsEntity) {
			em.getTransaction().begin();
			em.remove(product);
			em.getTransaction().commit();
			// sterge doar un produs
			break;
		}
		// em.persist(shoppingCartProductsEntity);

	}

	@SuppressWarnings("unchecked")
	public List<Long> retrieveAllShoppingCartForUser(long idUser) {

		List<ShoppingCartUserEntity> shoppingCartsUsersEntity = new ArrayList<ShoppingCartUserEntity>();
		List<Long> cartIds = new ArrayList<Long>();

		Query query = em.createNamedQuery("shopping_cart_users.getAllShoppingCartsForUser");
		query.setParameter("idUser", idUser);

		shoppingCartsUsersEntity = query.getResultList();

		for (ShoppingCartUserEntity cart : shoppingCartsUsersEntity) {
			if (!cart.isCurrentCart()) {
				cartIds.add(cart.getIdShoppingCart());
			}
		}
		return cartIds;
	}

	@SuppressWarnings("unchecked")
	public Cart retrieveCartById(long idCart) {
		Cart cart = new Cart();

		Query query = em.createNamedQuery("shopping_cart.getShoppingCart");
		query.setParameter("idShoppingCart", idCart);

		List<ShoppingCartEntity> shoppingCartEntities = (List<ShoppingCartEntity>) query.getResultList();
		if (shoppingCartEntities.size() > 0) {
			ShoppingCartEntity shoppingCartEntity = shoppingCartEntities.get(0);
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			cart.setIdCart(idCart);
			cart.setIdRestaurant(shoppingCartEntity.getIdRestaurant());
			cart.setTotalPrice(shoppingCartEntity.getTotalPrice());

			cart.setCreatedDate(df.format(shoppingCartEntity.getCreatedDate()));

			if (shoppingCartEntity.getSendDate() != null) {
				cart.setSendDate(df.format(shoppingCartEntity.getSendDate()));
			}

			if (shoppingCartEntity.getSendDate() == null) {
				cart.setActive(true);
			} else if (shoppingCartEntity.getSendDate().before(new Date())) {
				cart.setActive(false);
			}
		}

		return cart;
	}

	public int retrieveNumberOfProducts(long idUser, long idProduct, long idShoppingCart) {

		Query query = em.createNamedQuery("shopping_cart_products.getNrOfProducts");
		query.setParameter("idUser", idUser);
		query.setParameter("idProduct", idProduct);
		query.setParameter("idShoppingCart", idShoppingCart);

		int nrProducts = 0;

		if (!query.getResultList().isEmpty()) {
			nrProducts = (int) query.getResultList().get(0);
		}

		return nrProducts;

	}

	public void updateNrOfProducts(long idUser, long idProduct, long idShoppingCart, int nrProducts) {
		em.getTransaction().begin();
		Query query = em.createQuery("UPDATE shopping_cart_products p SET p.nrProducts = :nrProducts "
				+ "WHERE p.idUser=:idUser and p.idProduct = :idProduct and p.idShoppingCart = :idShoppingCart");

		query.setParameter("idUser", idUser);
		query.setParameter("idProduct", idProduct);
		query.setParameter("idShoppingCart", idShoppingCart);
		query.setParameter("nrProducts", nrProducts);

		query.executeUpdate();
		em.getTransaction().commit();
		em.clear();
	}

	@SuppressWarnings("unchecked")
	public List<ShoppingCartProducts> retrieveShoppingCartProductIds(long idUser, long idCart) {

		List<ShoppingCartProducts> cartProductsForUser = new ArrayList<ShoppingCartProducts>();
		Query query = em.createNamedQuery("shopping_cart_products.getCartProductsForUser");
		query.setParameter("idUser", idUser);
		query.setParameter("idShoppingCart", idCart);
		List<ShoppingCartProductsEntity> shoppingCartEntities = query.getResultList();

		for (ShoppingCartProductsEntity cartProductsEntity : shoppingCartEntities) {

			ShoppingCartProducts cartProducts = new ShoppingCartProducts();
			cartProducts.setIdProduct(cartProductsEntity.getIdProduct());
			cartProducts.setNrProducts(cartProductsEntity.getNrProducts());
			cartProducts.setIdUser(idUser);
			cartProducts.setIdShoppingCart(idCart);

			cartProductsForUser.add(cartProducts);
		}

		return cartProductsForUser;
	}

	public long createCartForUser(long idUser) {

		ShoppingCartEntity entity = new ShoppingCartEntity();
		entity.setCreatedDate(Date.from(Instant.now()));

		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();

		long idCart = entity.getId();

		ShoppingCartUserEntity userCartEntity = new ShoppingCartUserEntity();
		userCartEntity.setIdShoppingCart(idCart);
		userCartEntity.setIdUser(idUser);
		userCartEntity.setCurrentCart(true);

		em.getTransaction().begin();
		em.persist(userCartEntity);
		em.getTransaction().commit();

		return idCart;
	}

	public void updateCartAfterCheckout(long idUser, long idCart) {
		em.getTransaction().begin();
		Query query = em
				.createQuery("UPDATE shopping_cart p SET p.sendDate = :currentDate WHERE p.id = :idShoppingCart");

		query.setParameter("idShoppingCart", idCart);
		query.setParameter("currentDate", new Date());

		// TODO cred ca ar trebui si user in shoppingCart ???
		query.executeUpdate();
		em.getTransaction().commit();

		em.getTransaction().begin();
		query = em.createQuery(
				"UPDATE shopping_cart_users p SET p.currentCart=:isCurrentCart WHERE p.idShoppingCart = :idShoppingCart and p.idUser=:idUser");

		query.setParameter("isCurrentCart", false);
		query.setParameter("idShoppingCart", idCart);
		query.setParameter("idUser", idUser);

		query.executeUpdate();
		em.getTransaction().commit();
		em.clear();

	}

	public void addUserToCurrentCart(long idUser, long currentCartId) {
		ShoppingCartUserEntity cartUserEntity = new ShoppingCartUserEntity();

		cartUserEntity.setIdUser(idUser);
		cartUserEntity.setIdShoppingCart(currentCartId);
		cartUserEntity.setCurrentCart(true);

		em.getTransaction().begin();
		em.persist(cartUserEntity);
		em.getTransaction().commit();
		em.clear();

	}

	public void updateLastCartForNewUser(long idNewUser, long currentCartId) {
		em.getTransaction().begin();
		Query query = em.createQuery(
				"UPDATE shopping_cart_users p SET p.currentCart=:previousCart WHERE p.idShoppingCart not in (:idShoppingCart) and p.idUser=:idUser");

		query.setParameter("previousCart", false);
		query.setParameter("idShoppingCart", currentCartId);
		query.setParameter("idUser", idNewUser);

		query.executeUpdate();
		em.getTransaction().commit();
		em.clear();

	}

	@SuppressWarnings("unchecked")
	public List<Comment> retrieveAllCommentsForCart(Long idCart) {

		List<Comment> comments = new ArrayList<Comment>();
		Query query = em.createNamedQuery("comment.getAllCommentsForCart");
		query.setParameter("idCart", idCart);
		List<CommentEntity> commentEntities = query.getResultList();

		for (CommentEntity commentEntity : commentEntities) {

			Comment comment = new Comment();

			comment.setIdCart(commentEntity.getIdCart());
			comment.setIdUser(commentEntity.getIdUser());
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			comment.setDate(df.format(commentEntity.getDate()));
			comment.setDescription(commentEntity.getDescription());

			comments.add(comment);
		}

		return comments;
	}

	public void addComment(long idUser, long idCart, String description, Date date) {
		CommentEntity commentEntity = new CommentEntity();

		commentEntity.setIdUser(idUser);
		commentEntity.setIdCart(idCart);
		commentEntity.setDate(date);
		commentEntity.setDescription(description);

		em.getTransaction().begin();
		em.persist(commentEntity);
		em.getTransaction().commit();
		em.clear();
	}

}
