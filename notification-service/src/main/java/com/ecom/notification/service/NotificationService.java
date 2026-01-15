package com.ecom.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void sendEmail(String message) {
		
		System.out.println("Email sended with message: "+message);
		
	}

}
