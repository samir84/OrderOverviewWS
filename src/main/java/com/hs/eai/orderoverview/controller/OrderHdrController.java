package com.hs.eai.orderoverview.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.eai.orderoverview.model.OrderDetail;
import com.hs.eai.orderoverview.model.OrderHdr;
import com.hs.eai.orderoverview.service.OrderDetailService;
import com.hs.eai.orderoverview.service.OrderHdrService;


@RestController
@RequestMapping("orders")
public class OrderHdrController {

	private static final Logger logger = LoggerFactory.getLogger(OrderHdrController.class);
	
	@Autowired
	OrderHdrService orderHdrService;
	@Autowired
	OrderDetailService orderDetailService;
	
	 /**
     * Retrieve All OrderHdrs
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderHdr>> listAllOrderHdrs() {
        List<OrderHdr> OrderHdrs = orderHdrService.findAll();
        if(OrderHdrs.isEmpty()){
            return new ResponseEntity<List<OrderHdr>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<OrderHdr>>(OrderHdrs, HttpStatus.OK);
    }
  
	/**
	 * Retrieve Lazy loading orders
	 * 
	 * @param
	 * @return list orders
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderHdr>> lazyLoadorders(
			@RequestParam(required = true, value = "startIndex") Integer startIndex,
			@RequestParam(required = true, value = "maxResult") Integer maxResult) {
		List<OrderHdr> OrderHdrs = orderHdrService.loadLazyOrderHdrs(startIndex, maxResult);
		
		if (OrderHdrs.isEmpty()) {
			return new ResponseEntity<List<OrderHdr>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<OrderHdr>>(OrderHdrs, HttpStatus.OK);
	}
	/**
	 * Retrive Project details
	 * 
	 * @param project
	 *            id
	 * @return ProjectDetails object
	 */
	@RequestMapping(value = "/details/{orderId}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDetail> > getOrderDetails(@PathVariable("orderId") Integer orderId) {

		List<OrderDetail>  orderDetails = null;
		try {
			orderDetails  = orderDetailService.findOrderDetails(orderId);

			return new ResponseEntity<List<OrderDetail>>(orderDetails, HttpStatus.OK);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return new ResponseEntity<List<OrderDetail>>(HttpStatus.BAD_REQUEST);
		}

	}
	/**
	 * Retrive Order by pname
	 * 
	 * @param order
	 *            not yet implemented!!
	 * @return project object
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderHdr>> fullTextordersearchWildcard(@RequestParam(required = true, value = "q") String searchTerm) {

		logger.debug("Search orders with searchTerm " + searchTerm);
		System.out.println("Search orders with searchTerm " + searchTerm.toLowerCase());
		List<OrderHdr> orders = orderHdrService.fullTextOrderHdrSearchWildcard(searchTerm.toLowerCase());
		if (orders.isEmpty()) {
			logger.debug("No orders found with search phrase " + searchTerm + " .");
			return new ResponseEntity<List<OrderHdr>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<OrderHdr>>(orders, HttpStatus.OK);
	}
	/**
	 * Count All
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> countOrders() {
		Integer count = orderHdrService.countAlle();
		if (count.equals(null)) {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}
 
}
