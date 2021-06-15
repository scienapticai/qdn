package com.hp.BMT.services.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class SpringsRepo {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * 
	 * @Transactional public void insertWithQuery(UrlDetailsHistory person) {
	 * entityManager.
	 * createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)"
	 * ) .setParameter(1, person.getId()).setParameter(2, person.getFirstName())
	 * .setParameter(3, person.getLastName()).executeUpdate(); }
	 * 
	 * 
	 * @Transactional public void insertWithEntityManager(UrlDetailsHistory person)
	 * { this.entityManager.persist(person); }
	 * 
	 * @Transactional public void removeWithEntityManager(UrlDetailsHistory person)
	 * { this.entityManager.remove(person); }
	 * 
	 * @Transactional public void insertWithEntityManagerForAddUrl(UrlDetails url) {
	 * this.entityManager.persist(url); }
	 * 
	 * @Transactional public void
	 * insertWithEntityManagerForAddKeyword(KeywordDetails url) {
	 * this.entityManager.persist(url); }
	 * 
	 */ @SuppressWarnings("unchecked")
	@Transactional
	public List<String> queryWithEntityManager(String query) {
		return this.entityManager.createNativeQuery(query).getResultList();
	}

}
