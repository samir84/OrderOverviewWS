package com.hs.eai.orderoverview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.eai.orderoverview.dao.OrderHdrDao;
import com.hs.eai.orderoverview.model.OrderHdr;


@Service
public class OrderHdrServiceImpl implements OrderHdrService {

	@Autowired
	OrderHdrDao orderHdrDao;

	@Override
	public List<OrderHdr> findAll() {
		// TODO Auto-generated method stub
		return orderHdrDao.findAll();
	}

	@Override
	public List<OrderHdr> fullTextOrderHdrSearchWildcard(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderHdr> loadLazyOrderHdrs(Integer startIndex, Integer maxResult) {
		// TODO Auto-generated method stub
		return orderHdrDao.loadLazyOrderHdrs(startIndex, maxResult);
	}

	@Override
	public List<OrderHdr> findByShipTo(String shipTo) {
		// TODO Auto-generated method stub
		return orderHdrDao.findByShipTo(shipTo);
	}

	@Override
	public List<OrderHdr> findByBillTo(String billTo) {
		// TODO Auto-generated method stub
		return orderHdrDao.findByBillTo(billTo);
	}

	@Override
	public List<OrderHdr> findByName(String name) {
		// TODO Auto-generated method stub
		return orderHdrDao.findByName(name);
	}

	@Override
	public List<OrderHdr> findByOrganization(String organization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderHdr> findByProject(String project) {
		// TODO Auto-generated method stub
		return orderHdrDao.findByProject(project);
	}

	@Override
	public Integer countAlle() {
		// TODO Auto-generated method stub
		return orderHdrDao.countAlle();
	}
	
	

}
