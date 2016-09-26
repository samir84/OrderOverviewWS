package com.hs.eai.orderoverview.service;

import java.util.List;

import com.hs.eai.orderoverview.model.OrderHdr;

public interface OrderHdrService {

	List<OrderHdr> findAll();
	List<OrderHdr> fullTextOrderHdrSearchWildcard(String searchText );
	List<OrderHdr> loadLazyOrderHdrs(Integer startIndex, Integer maxResult);
	List<OrderHdr> findByShipTo(String shipTo);
	List<OrderHdr> findByBillTo(String billTo);
	List<OrderHdr> findByName(String name);
	List<OrderHdr> findByOrganization(String organization);
	List<OrderHdr> findByProject(String project);
	Integer countAlle();
}
