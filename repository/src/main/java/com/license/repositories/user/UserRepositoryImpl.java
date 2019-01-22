package com.license.repositories.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.license.User;
import com.license.entities.UserEntity;

@Stateless
@Remote(UserRepository.class)

public class UserRepositoryImpl implements UserRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();

	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public User find(String username, String password) {
		UserEntity user = new UserEntity();
		User userResponse = new User();
		Query query = em.createNamedQuery("user.findUser");

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			
			query.setParameter("prm_username", username);
			query.setParameter("prm_password", bytesToHex(encodedhash));
			
			List<UserEntity> results = query.getResultList();
	        if (results.isEmpty()) 
	        	return null;
	        else if (results.size() == 1) 	
	        	user = results.get(0);
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}


		userResponse.setId(user.getId());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setUsername(user.getUsername());
		userResponse.setEmail(user.getEmail());
		userResponse.setPassword(user.getPassword().toString());
		
		return userResponse;

	}

	public void register(User user) {

		UserEntity entity = new UserEntity();
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
			entity.setPassword(bytesToHex(encodedhash));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		entity.setId(user.getId());
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setUsername(user.getUsername());

		entity.setEmail(user.getEmail());

		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		
	}

	
	public List<User> retrieveUsers() {
		
		Query query = em.createNamedQuery("user.getUsers");
		List<UserEntity> users = query.getResultList();
		
		List<User> usersResponse = new ArrayList<User>();
		for(UserEntity userEntity: users) {
			User user = new User();
			user.setId(userEntity.getId());
			user.setEmail(userEntity.getEmail());
			user.setFirstName(userEntity.getFirstName());
			user.setLastName(userEntity.getLastName());
			user.setPassword(userEntity.getPassword());
			user.setUsername(userEntity.getUsername());
			
			usersResponse.add(user);	
		}	
		return usersResponse;
	}
}
