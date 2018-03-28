package com.license.repository.implementation;

import java.util.HashMap;
import java.util.Map;

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

	public Map<String, String> find(String username, String password) {
		System.out.println("in find method");

		UserEntity user = new UserEntity();
		Map<String, String> response = new HashMap<String, String>();
		Query query = em.createNamedQuery("user.findUser");
		query.setParameter("prm_username", username);
		query.setParameter("prm_password", password);

		user = (UserEntity) query.getResultList();
		if (user == null) {
			System.out.println("se pare ca userul cautat nu a fost gasit");
			// return "";
		}
		System.out.println("Username is " + user.getUsername());
		System.out.println("Password is" + user.getPassword());
		response.put(user.getUsername(), user.getPassword());

		return response;

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
