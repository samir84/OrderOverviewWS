package com.hs.eai.orderoverview.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hs.eai.orderoverview.common.GenericDaoImpl;
import com.hs.eai.orderoverview.model.OrderDetail;
import com.hs.eai.orderoverview.model.OrderHdr;
import com.sun.xml.ws.api.tx.at.Transactional;

@Repository
public class OrderDetailDaoImpl extends GenericDaoImpl<OrderDetail> implements OrderDetailDao{

	private static final Logger logger = LoggerFactory.getLogger(OrderHdrDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<OrderDetail> findOrderDetailsById(Integer id) {
		
		logger.debug("Find Order detail by header id  {}|",id," .");
		List<OrderDetail> orderDetails = null ;
		try{
			OrderHdr orderHdr = entityManager.find(OrderHdr.class, id);
			Query query = entityManager.createNamedQuery("OrderDetail.findByOrderHdrId");
			
			query.setParameter("orderHdr", orderHdr);
			orderDetails =  query.getResultList();
		}catch(Exception ex){
			logger.error("Error Find Order detail by header id  {} ",id," error is: ",ex.getMessage());
			ex.printStackTrace();
		}
		return orderDetails;
	}

	
}
