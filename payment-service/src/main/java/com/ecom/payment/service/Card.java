package com.ecom.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.payment.entity.PaymentEntity;
import com.ecom.payment.repository.PaymentRepository;
import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;

import jakarta.transaction.Transactional;

@Service("card")
public class Card implements PaymentService
{
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	@Transactional
	public PaymentResponse processsPayment(PaymentRequest paymentRequest) {
		
		PaymentEntity entity = new PaymentEntity();
	
		entity.setOrderId(paymentRequest.getOrderId());
		entity.setAmount(paymentRequest.getAmount());
		entity.setStatus("SUCCESS");
		
		entity = paymentRepository.save(entity);
		
		PaymentResponse response = new PaymentResponse();
		response.setPaymentId(entity.getPaymentId());
		response.setAmount(entity.getAmount());
		response.setStatus(entity.getStatus());
		return response;
	}
}
