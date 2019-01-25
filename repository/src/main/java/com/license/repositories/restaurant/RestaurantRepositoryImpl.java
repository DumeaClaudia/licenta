package com.license.repositories.restaurant;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.license.Product;
import com.license.Restaurant;
import com.license.entities.ProductEntity;
import com.license.entities.RestaurantEntity;

@Stateless
@Remote(RestaurantRepository.class)

public class RestaurantRepositoryImpl implements RestaurantRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();

	public List<Restaurant> retrieveRestaurants() {
		

		List<RestaurantEntity> restaurants = new ArrayList<RestaurantEntity>();
		List<Restaurant> restaurantsResponse = new ArrayList<Restaurant>();
		
		Query query = em.createNamedQuery("restaurant.display");
		restaurants = (List<RestaurantEntity>) query.getResultList();
		
		if (restaurants == null) {
			System.out.println("se pare ca nu au fost gasite restaurante in baza de date ha ha ha");
			// return "";
		}
		
		for(RestaurantEntity restaurantEntity: restaurants) {
			Restaurant res = new Restaurant();
			res.setId(restaurantEntity.getId());
			res.setName(restaurantEntity.getName());
			res.setDescription(restaurantEntity.getDescription());
			res.setAddress(restaurantEntity.getAddress());
			res.setGeolocation(restaurantEntity.getGeolocation());
			res.setStars(restaurantEntity.getStars());
			res.setImage(restaurantEntity.getImage());

			restaurantsResponse.add(res);
		}
		return restaurantsResponse;
	}
	
	public Restaurant retrieveRestaurantById(long id) {
		
		RestaurantEntity restaurant = new RestaurantEntity();
		Restaurant restaurantResponse = new Restaurant();
		Query query = em.createNamedQuery("restaurant.searchForRestaurant");
		query.setParameter("findId", id);
		restaurant = (RestaurantEntity) query.getSingleResult();
		
		restaurantResponse.setId(restaurant.getId());
		restaurantResponse.setName(restaurant.getName());
		restaurantResponse.setDescription(restaurant.getDescription());
		restaurantResponse.setAddress(restaurant.getAddress());
		restaurantResponse.setGeolocation(restaurant.getGeolocation());
		restaurantResponse.setStars(restaurant.getStars());
		restaurantResponse.setImage(restaurant.getImage());
		
		return restaurantResponse;
	}
	
	
	public List<Product> retrieveAllProducts(long idRestaurant){ 
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		List<Product> productsResponse = new ArrayList<Product>();
		
		Query query = em.createNamedQuery("product.getAllProducts");
		query.setParameter("idRestaurant", idRestaurant);
		products = (List<ProductEntity>) query.getResultList();
		
		if (products == null) {
			System.out.println("se pare ca nu au fost gasite produse in baza de date ha ha ha");
			// return "";
		}
		
		for(ProductEntity productEntity: products) {
			Product product = new Product();
			product.setId(productEntity.getId());
			product.setIdRestaurant(productEntity.getIdRestaurant());
			product.setImage(productEntity.getImage());
			product.setCategory(productEntity.getCategory());
			product.setName(productEntity.getName());
			product.setDescription(productEntity.getDescription());
			product.setPrice(productEntity.getPrice());
			product.setDiscount(productEntity.getDiscount());

			productsResponse.add(product);
		}
		return productsResponse;
	}
	
	public List<Product> retrieveProductsByCategory(String category , long idRestaurant){ 
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		List<Product> productsResponse = new ArrayList<Product>();
		
		Query query = em.createNamedQuery("product.getProductsByCategory");
		query.setParameter("category", category);
		query.setParameter("idRestaurant", idRestaurant);
		
		products = (List<ProductEntity>) query.getResultList();
		
		if (products == null) {
			System.out.println("se pare ca nu au fost gasite produse in baza de date ha ha ha");
			// return "";
		}
		
		for(ProductEntity productEntity: products) {
			Product product = new Product();
			product.setId(productEntity.getId());
			product.setIdRestaurant(productEntity.getIdRestaurant());
			
			product.setImage(productEntity.getImage());
			product.setCategory(productEntity.getCategory());
			product.setName(productEntity.getName());
			product.setDescription(productEntity.getDescription());
			product.setPrice(productEntity.getPrice());
			product.setDiscount(productEntity.getDiscount());

			productsResponse.add(product);
		}
		return productsResponse;
	}

	public List<String> retrieveCategories(long idRestaurant) {

		List<ProductEntity> products = new ArrayList<ProductEntity>();
		List<String> categoriesResponse = new ArrayList<String>();

		Query query = em.createNamedQuery("product.getAllProducts");
		query.setParameter("idRestaurant", idRestaurant);
		products = (List<ProductEntity>) query.getResultList();

		if (products == null) {
			System.out.println("se pare ca nu au fost gasite produse in baza de date ha ha ha");
			// return "";
		}

		for (ProductEntity p : products) {
			if (!categoriesResponse.contains(p.getCategory())) {
				categoriesResponse.add(p.getCategory());
			}
		}
		System.out.println("Categoriile filtrate sunt =" + categoriesResponse);
		return categoriesResponse;
	}
	
	public Product retrieveProductById(long idProduct) {
			
		ProductEntity productEntity = new ProductEntity();		
		Query query = em.createNamedQuery("product.getProductById");
		query.setParameter("idProduct", idProduct);
		
		Product productResponse = new Product();
		productEntity = (ProductEntity) query.getSingleResult();
		
		productResponse.setId(productEntity.getId());
		productResponse.setName(productEntity.getName());
		productResponse.setDescription(productEntity.getDescription());
		productResponse.setImage(productEntity.getImage());
		productResponse.setCategory(productEntity.getCategory());
		productResponse.setPrice(productEntity.getPrice());
		productResponse.setIdRestaurant(productEntity.getIdRestaurant());
		productResponse.setDiscount(productEntity.getDiscount());
		
		return productResponse;
		
		
		
	}
}
