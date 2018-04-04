package com.license.repository.implementation;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.license.User;
import com.license.entities.UserEntity;
import com.license.repository.UserRepository;

@Stateless
@Remote(UserRepository.class)

public class UserRepositoryImplementation implements UserRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();

	public User find(String username, String password) {
		System.out.println("in find method");

		UserEntity user = new UserEntity();
		User userResponse = new User();
		Query query = em.createNamedQuery("user.findUser");
		query.setParameter("prm_username", username);
		query.setParameter("prm_password", password);

		user = (UserEntity) query.getSingleResult();
		if (user == null) {
			System.out.println("se pare ca userul cautat nu a fost gasit");
			// return "";
		}
		System.out.println("Username is: " + user.getUsername());
		System.out.println("Password is: " + user.getPassword());

		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setUsername(user.getUsername());
		userResponse.setEmail(user.getEmail());
		userResponse.setPassword(user.getPassword());

		return userResponse;

	}

	public void register(User user) {
		System.out.println("in register method");

		UserEntity entity = new UserEntity();

		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setUsername(user.getUsername());
		entity.setPassword(user.getPassword());
		entity.setEmail(user.getEmail());

		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}
}
