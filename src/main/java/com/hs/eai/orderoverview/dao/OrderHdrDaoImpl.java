package com.hs.eai.orderoverview.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.SystemPropertyUtils;

import com.hs.eai.orderoverview.common.GenericDaoImpl;
import com.hs.eai.orderoverview.model.OrderHdr;

@Repository
public class OrderHdrDaoImpl extends GenericDaoImpl<OrderHdr> implements OrderHdrDao {

	private static final Logger logger = LoggerFactory.getLogger(OrderHdrDaoImpl.class);

	//@PersistenceContext
	//private EntityManager entityManager;
	/** Hibernate Full Text Entity Manager. */
	private FullTextEntityManager ftem;
	
	/**
	 * Convenience method to get Full Test Entity Manager. Protected scope to
	 * assist mocking in Unit Tests.
	 * 
	 * @return Full Text Entity Manager.
	 */
	protected FullTextEntityManager getFullTextEntityManager() {
		if (ftem == null) {
			ftem = Search.getFullTextEntityManager(em);
		}
		return ftem;
	}

	@Override
	@Transactional
	public List<OrderHdr> findAll() {
		logger.debug("Find all OrderHdrs .");
		List<OrderHdr> orders = null ;
		try{
			Query query = em.createNamedQuery("OrderHdr.findAll");
			orders = (List<OrderHdr>)query.getResultList();
			
		}catch(Exception ex){
			logger.error("Error Find all OrderHdrs error is: ",ex.getMessage());
		}
		return orders;
	}

	@Override
	public List<OrderHdr> findByName(String name) {
		
		logger.debug("Find OrderHdr by name {} ",name ,".");
		List<OrderHdr> orders = null ;
		try{
			Query query = em.createNamedQuery("OrderHdr.findByName");
			query.setParameter("name", name);
			orders = (List<OrderHdr>)query.getResultList();
		}catch(Exception ex){
			logger.error("Error Find OrderHdr by name {} ",name," error is: ",ex.getMessage());
		}
		return orders;
	}


	@Transactional
	public List<OrderHdr> findByExample(OrderHdr OrderHdr) {
		return super.findByExample(OrderHdr);
	}


	

	@Override
	public List<OrderHdr> loadLazyOrderHdrs(Integer page, Integer maxResult) {
	  
		logger.debug("List<OrderHdr> loadLazyOrderHdrs: startIndex: {} ",page , "maxResult: {} ",maxResult);
		return super.Pagination(page, maxResult);
	}

	@Override
	public Integer countAlle() {
		// TODO Auto-generated method stub
		return (int) super.countAll();
	}

	@Override
	@Transactional
	public List<OrderHdr> findByShipTo(String shipTo) {
		
		logger.debug("Find OrderHdr by shipTo {} ",shipTo ,".");
		List<OrderHdr> orders = null ;
		try{
			Query query = em.createNamedQuery("OrderHdr.findByShipTo");
			query.setParameter("shipto", shipTo);
			orders = (List<OrderHdr>)query.getResultList();
		}catch(Exception ex){
			logger.error("Error Find OrderHdr by shipTo {} ",shipTo," error is: ",ex.getMessage());
		}
		return orders;
	}

	@Override
	public List<OrderHdr> findByBillTo(String billTo) {
		
		logger.debug("Find OrderHdr by billTo {} ",billTo ,".");
		List<OrderHdr> orders = null ;
		try{
			Query query = em.createNamedQuery("OrderHdr.findByBillTo");
			query.setParameter("billto", billTo);
			orders = (List<OrderHdr>)query.getResultList();
		}catch(Exception ex){
			logger.error("Error Find OrderHdr by billTo {} ",billTo," error is: ",ex.getMessage());
		}
		return orders;
	}


	@Override
	public List<OrderHdr> findByOrganization(Integer salesOrganizationId) {
		logger.debug("Find OrderHdr by organization {} ",salesOrganizationId ,".");
		List<OrderHdr> orders = null ;
		try{
			Query query = em.createNamedQuery("OrderHdr.findByOrganization");
			query.setParameter("salesOrganizationId", salesOrganizationId);
			orders = (List<OrderHdr>)query.getResultList();
		}catch(Exception ex){
			logger.error("Error Find OrderHdr by organization {} ",salesOrganizationId," error is: ",ex.getMessage());
		}
		return orders;
	}

	@Override
	public List<OrderHdr> findByProject(String project) {
		
		logger.debug("Find OrderHdr by project {} ",project ,".");
		List<OrderHdr> orders = null ;
		/*try{
			Query query = entityManager.createNamedQuery("OrderHdr.findByProject");
			query.setParameter("project", project);
			orders = (List<OrderHdr>)query.getResultList();
		}catch(Exception ex){
			logger.error("Error Find OrderHdr by project {} ",project," error is: ",ex.getMessage());
		}*/
		return orders;
	}
	
	

	/**
	 * Search for a OrderHdr.
	 */

	@Transactional
	public List<OrderHdr> fullTextOrderHdrSearchWildcard(String searchText ) {

		
		logger.debug("Searching OrderHdrs  for phrase {}" , searchText , ".");
		List<OrderHdr> OrderHdrs = null;
		try {
			// Create a Query Builder
			QueryBuilder qb = getFullTextEntityManager().getSearchFactory().buildQueryBuilder().forEntity(OrderHdr.class)
					.get();
			getFullTextEntityManager().createIndexer().startAndWait();
			
			org.apache.lucene.search.Query luceneQuery = qb.bool()
			.should(qb.keyword().wildcard().onField("refno").matching(searchText).createQuery())
			.should(qb.keyword().wildcard().onField("shipto").matching(searchText).createQuery())
			.should(qb.keyword().wildcard().onField("billto").matching(searchText).createQuery())
			.should(qb.keyword().wildcard().onField("emailAddress").matching(searchText).createQuery())
			.should(qb.keyword().wildcard().onField("name").matching(searchText).createQuery())
			
			.createQuery();
			
			Query fullTextQuery = getFullTextEntityManager().createFullTextQuery(luceneQuery, OrderHdr.class);

			// Run Query and print out results to console
			OrderHdrs = (List<OrderHdr>) fullTextQuery.getResultList();
		} catch (InterruptedException e) {
			System.out.println("error: "+e.getMessage());
			e.printStackTrace();
		} finally {
			if (getFullTextEntityManager() != null) {
				ftem.close();
			}
			ftem = null;
		}

		return OrderHdrs;
	}
	
	//
}
