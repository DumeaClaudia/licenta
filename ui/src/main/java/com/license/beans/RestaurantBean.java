package com.license.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.license.Restaurant;
import com.license.User;
import com.license.entities.RestaurantEntity;
import com.license.services.RestaurantService;
import com.license.services.UserService;

@ManagedBean
@RequestScoped
// @SessionScoped
public class RestaurantBean implements Serializable{

	private String name;
	private String description;
	private int stars;
	private String street;
	private String number;
	private List<Restaurant> restaurants; // = new ArrayList<Restaurant>();
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();
	
	@PostConstruct
	public void init() {
		//System.out.println("in display method repository");

		List<RestaurantEntity> restaurants = new ArrayList<RestaurantEntity>();
		List<Restaurant> restaurantsResponse = new ArrayList<Restaurant>();
		
		Query query = em.createNamedQuery("restaurant.display");

		restaurants = (List<RestaurantEntity>) query.getResultList();
		
		if (restaurants == null) {
			//System.out.println("se pare ca nu au fost gasite restaurante in baza de date ha ha ha");
			// return"";
		}
	
		for(RestaurantEntity r: restaurants) {
			Restaurant res = new Restaurant();
		
			res.setName(r.getName());
			res.setDescription(r.getDescription());
			res.setStars(r.getStars());
			res.setStreet(r.getStreet());
			res.setNumber(r.getNumber());
			restaurantsResponse.add(res);
		}
		
	

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}