package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecom.order.request.PaymentRequest;
import com.ecom.order.response.PaymentResponse;

@FeignClient(name="payment-service")
public interface PaymentClient {
	
	@PostMapping("makePayment")
	PaymentResponse makePayment(PaymentRequest payment);
}
