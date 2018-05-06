package com.license.repositories.user.implementation;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.license.User;
import com.license.entities.UserEntity;
import com.license.repositories.user.UserRepository;
import com.mysql.jdbc.authentication.Sha256PasswordPlugin;

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
		System.out.println("in find method repository");

		UserEntity user = new UserEntity();
		User userResponse = new User();
		Query query = em.createNamedQuery("user.findUser");

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

			
			query.setParameter("prm_username", username);
			query.setParameter("prm_password", bytesToHex(encodedhash));

			user = (UserEntity) query.getSingleResult();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user == null) {
			System.out.println("se pare ca userul cautat nu a fost gasit");
			// return "";
		}
		System.out.println("Username is: " + user.getUsername());
		System.out.println("Password is: " + user.getPassword());
		
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
			// TODO Auto-generated catch block
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
}
