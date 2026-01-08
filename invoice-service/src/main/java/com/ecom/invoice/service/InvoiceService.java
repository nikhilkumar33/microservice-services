package com.ecom.invoice.service;

import com.ecom.invoice.request.InvoiceRequest;

public interface InvoiceService {
	
	public int createInvoice(InvoiceRequest request);
}
