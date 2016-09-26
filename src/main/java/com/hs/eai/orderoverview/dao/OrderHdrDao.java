package com.hs.eai.orderoverview.dao;

import java.util.List;
import java.util.Map;

import com.hs.eai.orderoverview.model.OrderHdr;

public interface OrderHdrDao {

	List<OrderHdr> findAll();
	List<OrderHdr> fullTextOrderHdrSearchWildcard(String searchText );
	List<OrderHdr> loadLazyOrderHdrs(Integer page, Integer maxResult);
	List<OrderHdr> findByShipTo(String shipTo);
	List<OrderHdr> findByBillTo(String billTo);
	List<OrderHdr> findByName(String name);
	List<OrderHdr> findByOrganization(Integer salesOrganizationId);
	List<OrderHdr> findByProject(String project);
	/*List<OrderHdr> findByAssignee(String assignee);
	
	OrderHdr findById(Integer id);
	OrderHdr findByName(String name);
	OrderHdr findByKey(String key);
	OrderHdr findByDescription(String description);
	List<OrderHdr> findByProperties(Map<String, Object> params);
	List<OrderHdr> findByExample(OrderHdr OrderHdr);
	List<OrderHdr> fullTextOrderHdrSearchWildcard(String searchText );
	List<OrderHdr> loadLazyOrderHdrs(Integer startIndex, Integer maxResult);*/
	
	Integer countAlle();

}
