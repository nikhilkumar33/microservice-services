package com.ecom.payment.service;

import com.ecom.payment.request.PaymentRequest;

public interface PaymentService
{
	public int processsPayment(PaymentRequest paymentRequest);
}
