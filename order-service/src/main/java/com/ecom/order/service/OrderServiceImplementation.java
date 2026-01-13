package com.ecom.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.order.client.PaymentClient;
import com.ecom.order.entity.OrderEntity;
import com.ecom.order.exception.PaymentFailedException;
import com.ecom.order.repository.OrderRepository;
import com.ecom.order.request.OrderRequest;
import com.ecom.order.request.PaymentRequest;
import com.ecom.order.response.PaymentResponse;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImplementation implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	PaymentClient paymentClient;

	@Override
	@Transactional
	public int createOrder(OrderRequest orderRequest)
	{
		OrderEntity entity = new OrderEntity();

		entity.setItemName(orderRequest.getItemName());
		entity.setAmount(orderRequest.getAmount());
		entity.setQty(orderRequest.getQty());
		entity.setDescription(orderRequest.getDescription());
		entity.setUserId(orderRequest.getUserId());
		entity = orderRepository.save(entity);

		PaymentRequest request = new PaymentRequest();
		request.setOrderId(entity.getOrderId());
		request.setAmount(entity.getAmount());
		PaymentResponse response = paymentClient.makePayment(request);
		System.out.println("PaymentId is :"+response.getPaymentId());
		if(!response.getStatus().equals("SUCCESS")) {
			throw new PaymentFailedException("Payment Failed...!"+response.getStatus());
		}
		return entity.getOrderId();

	}

}
