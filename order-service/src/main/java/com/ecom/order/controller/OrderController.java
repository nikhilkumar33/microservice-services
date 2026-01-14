package com.ecom.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecom.order.request.OrderRequest;
import com.ecom.order.service.OrderService;

@RestController
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody OrderRequest orderRequest) {

		logger.info("Place order request received: {}",orderRequest);

		int orderId = orderService.createOrder(orderRequest);

		return "Order placed successfully. Order id is: " + orderId;
	}
}
