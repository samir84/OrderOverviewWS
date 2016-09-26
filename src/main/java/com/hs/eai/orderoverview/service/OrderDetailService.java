package com.hs.eai.orderoverview.service;

import java.util.List;

import com.hs.eai.orderoverview.model.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail>  findOrderDetails(Integer id);

}
