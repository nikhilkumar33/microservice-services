package com.ecom.order.service;

import com.ecom.order.request.OrderRequest;

public interface OrderService 
{
	public int createOrder(OrderRequest orderRequest);
}
