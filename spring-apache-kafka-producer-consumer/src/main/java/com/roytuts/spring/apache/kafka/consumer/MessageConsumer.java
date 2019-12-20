package com.roytuts.spring.apache.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

	@KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeMessage(String msg) {
		System.out.println("Message received: " + msg);
	}

}
