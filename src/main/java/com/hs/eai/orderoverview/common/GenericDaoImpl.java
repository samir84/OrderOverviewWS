package com.hs.eai.orderoverview.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

	@PersistenceContext(name = "SQLServerEntityManagerFactory")
	protected EntityManager em;
	
	private Class<T> type;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public long countAll() {

		Long count = null;
		try {
			final StringBuffer queryString = new StringBuffer("SELECT count(o) from ");
			queryString.append(type.getSimpleName()).append(" o ");
			final Query query = this.em.createQuery(queryString.toString());
			count =  (Long) query.getSingleResult();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return count;
		

	}

	@Override
	public T create(final T t) {
		this.em.persist(t);
		return t;
	}

	@Override
	public void deleteById(final Object id) {
		this.em.remove(this.em.getReference(type, id));
	}

	@Override
	public void delete(final Object object) {
		em.remove(object);
	}

	@Override
	public T find(final Object id) {

		try {
			T obj = this.em.find(type, id);
			return obj;
		} catch (NoResultException ex) {

			return null;
		}
	}

	@Override
	public T update(final T t) {
		return this.em.merge(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> objects = null;
		final StringBuffer queryString = new StringBuffer("select o from ");
		queryString.append(this.getClass().getName()).append(" order by id desc ");;
		final Query query = this.em.createQuery(queryString.toString());
		objects = (List<T>) query.getResultList();
		return objects;
	}

	@Transactional
	public List<T> Pagination(int page, int maxResult) {

		List<T> objects = null;

		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(type.getSimpleName()).append(" o ").append(" order by id desc ");

		final Query query = this.em.createQuery(queryString.toString());

		if(page == 1){
			query.setFirstResult(0);
		}else{
			query.setFirstResult(maxResult * (page-1));
			
		}
		query.setMaxResults(maxResult);
		objects = query.getResultList();
		return objects;
	}

	private List<T> findObjectBy(String prop) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
		Root<T> object = criteriaQuery.from(type);

		criteriaQuery.select(object).where(cb.equal(object.get(prop), prop));
		return em.createQuery(criteriaQuery).getResultList();
	}

	public List<T> findByExample(T instance) {
		logger.debug("finding OrderHdr instance by example");
		try {
			List<T> results = null;
			Session session = em.unwrap(Session.class);
			Criteria crit = session.createCriteria(type);
			Example example = Example.create(instance);
			crit.add(example);
			results = crit.list();
			logger.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.error("find by example failed", re);
			throw re;
		} finally {
			em.close();
		}
	}
	
	

}
