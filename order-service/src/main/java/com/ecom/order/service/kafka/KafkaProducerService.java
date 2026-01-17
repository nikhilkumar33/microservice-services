package com.ecom.order.service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecom.order.config.TracingFeignConfig;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@Service
public class KafkaProducerService {
	private final TracingFeignConfig tracingFeignConfig;
	private static final String TOPIC = "order-placed";
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, TracingFeignConfig tracingFeignConfig)
	{
		this.kafkaTemplate = kafkaTemplate;
		this.tracingFeignConfig = tracingFeignConfig;
	}
	
	@CircuitBreaker(name="kafkaPublisherCB", fallbackMethod="fallbackKafkaProducer")
	@Retry(name="kafkaProducerRetry")
	public void sendMessage(String message)
	{
		kafkaTemplate.send(TOPIC, message);
		System.out.println("Message sent to Kafka...");
	}
	
	public void fallbackKafkaProducer(String message, Throwable ex)
	{
		System.out.println("After retries failed to send message to kafka. CircuitBreaker triggered.");
		System.out.println("Reson: "+ex.getMessage());
	}
	
}
