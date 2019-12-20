package com.roytuts.spring.apache.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

	@Value("${topic.name}")
	private String topic;

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	public void produceMessage(String msg) {
		kafkaTemplate.send(topic, msg);
	}

}
