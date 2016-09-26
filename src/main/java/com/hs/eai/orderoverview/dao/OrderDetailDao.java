package com.hs.eai.orderoverview.dao;

import java.util.List;

import com.hs.eai.orderoverview.model.OrderDetail;

public interface OrderDetailDao {

	List<OrderDetail>  findOrderDetailsById(Integer id);

}
