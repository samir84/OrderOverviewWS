package com.hs.eai.orderoverview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.eai.orderoverview.dao.OrderDetailDao;
import com.hs.eai.orderoverview.model.OrderDetail;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	OrderDetailDao orderDetailDao;
	
	@Override
	public List<OrderDetail>  findOrderDetails(Integer id) {
		// TODO Auto-generated method stub
		return orderDetailDao.findOrderDetailsById(id);
	}

}
