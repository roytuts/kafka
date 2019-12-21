package com.roytuts.spring.apache.kafka.streams.websocket.stomp.server.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.roytuts.spring.apache.kafka.streams.websocket.stomp.server.service.GreetingService;

@Component
@EnableScheduling
public class MessageProducer {

	@Value("${kafka.input.topic}")
	private String kafkaInputTopic;

	@Autowired
	private GreetingService greetingService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Scheduled(fixedRate = 1000)
	public void produce() {
		String msg = greetingService.greet();

		System.out.println("Greeting Message :: " + msg);

		kafkaTemplate.send(kafkaInputTopic, msg);
	}

}
