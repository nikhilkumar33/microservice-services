package com.ecom.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.order.request.OrderRequest;
import com.ecom.order.service.OrderService;
import com.netflix.discovery.converters.Auto;

@RestController
public class OrderController
{
	@Autowired
	OrderService orderService;
	
	@PostMapping("placeOrder")
	public String placeOrder(@RequestBody OrderRequest orderRequest)
	{
		int orderId = orderService.createOrder(orderRequest);
		return "Order placed successfully. Order id is: "+orderId;
	}
}
