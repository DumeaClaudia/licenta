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

	public String find(String username, String password) {
		System.out.println("in find method");

		UserEntity user = new UserEntity();

		Query query = em.createNamedQuery("user.findUser");
		query.setParameter("prm1", username);
		query.getResultList();
		if (user == null) {
			return "";
		}

		return user.getUsername();
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
