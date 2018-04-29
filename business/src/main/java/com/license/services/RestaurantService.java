package com.license.services;

import java.util.List;
import javax.ejb.Remote;

import com.license.Restaurant;

@Remote
public interface RestaurantService {
	List<Restaurant> displayRestaurants();
	//getRestaurantById
}
