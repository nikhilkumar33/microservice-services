package com.ecom.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.payment.entity.PaymentEntity;
import com.ecom.payment.exception.PaymentFailedException;
import com.ecom.payment.repository.PaymentRepository;
import com.ecom.payment.request.PaymentRequest;

@Service
public class UPI implements PaymentService
{
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public int processsPayment(PaymentRequest paymentRequest) {
		
		PaymentEntity entity = new PaymentEntity();
		if(!paymentRequest.getStatus().equals("PAID")) 
		{
			throw new PaymentFailedException("Payment is failed.. Payment status is: "+paymentRequest.getStatus());
		}
		entity.setOrderId(paymentRequest.getOrderId());
		entity.setAmount(paymentRequest.getAmount());
		entity.setStatus(paymentRequest.getStatus());
		
		entity = paymentRepository.save(entity);
		return entity.getPaymentId();
	}

}
