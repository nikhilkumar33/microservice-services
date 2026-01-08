package com.ecom.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.order.entity.OrderEntity;
import com.ecom.order.exception.PaymentFailedException;
import com.ecom.order.repository.OrderRepository;
import com.ecom.order.request.OrderRequest;

@Service
public class OrderServiceImplementation implements OrderService
{
	@Autowired
	OrderRepository orderRepository;

	@Override
	public int createOrder(OrderRequest orderRequest) {
		OrderEntity entity = new OrderEntity();
		
		if(orderRequest.getPaymentStatus().equals("PAID")) {
			entity.setItemName(orderRequest.getItemName());
			entity.setAmount(orderRequest.getAmount());
			entity.setQty(orderRequest.getQty());
			entity.setDescription(orderRequest.getDescription());
			entity.setPaymentStatus(orderRequest.getPaymentStatus());
			
			entity = orderRepository.save(entity);
			return entity.getOrderId();

		} 
		else
		{
			throw new PaymentFailedException("Payment failed.. payment status: "+orderRequest.getPaymentStatus());
		}
	}

}
