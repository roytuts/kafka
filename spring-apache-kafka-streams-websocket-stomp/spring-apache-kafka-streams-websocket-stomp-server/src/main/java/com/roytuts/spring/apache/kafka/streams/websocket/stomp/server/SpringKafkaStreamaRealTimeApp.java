package com.roytuts.spring.apache.kafka.streams.websocket.stomp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.roytuts.spring.apache.kafka.streams.websocket.stomp.server")
public class SpringKafkaStreamaRealTimeApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaStreamaRealTimeApp.class, args);
	}

}
