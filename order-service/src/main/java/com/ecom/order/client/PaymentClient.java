package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.order.config.TracingFeignConfig;
import com.ecom.order.request.PaymentRequest;
import com.ecom.order.response.PaymentResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name="payment-service" , configuration = TracingFeignConfig.class)
public interface PaymentClient {
	
	@CircuitBreaker(name = "paymentServiceCB", fallbackMethod = "fallbackPayment")
	@Retry(name = "paymentServiceRetry")
	@PostMapping("makePayment")
	PaymentResponse makePayment(@RequestBody PaymentRequest payment);
	
	
	default PaymentResponse fallbackPayment(PaymentRequest paymentRequest, Throwable ex) {
		// this will get triggred / executed if CB executes.
		System.out.println("Inside Payment client fallback method...");
		System.out.println(ex.getMessage());
	    PaymentResponse response = new PaymentResponse();
	    response.setStatus("Failed");
		return response;
	}
}
