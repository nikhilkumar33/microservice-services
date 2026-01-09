package com.ecom.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@PostMapping("makePayment")
	public String makePayment(@RequestBody PaymentRequest paymentRequest)
	{
		int paymentId = paymentService.processsPayment(paymentRequest);
		return "Payment successful. payment id is: "+paymentId;
	}
}
