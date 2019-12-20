package com.roytuts.spring.apache.kafka.producer.consumer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roytuts.spring.apache.kafka.producer.MessageProducer;

@SpringBootApplication(scanBasePackages = "com.roytuts.spring.apache.kafka")
public class SpringKafkaProdConsApp implements CommandLineRunner {

	@Autowired
	private MessageProducer messageProducer;

	private CountDownLatch countDownLatch;

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaProdConsApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		countDownLatch = new CountDownLatch(1);
		countDownLatch.await(10, TimeUnit.SECONDS);
		messageProducer.produceMessage("This message is sent to topic -> roytuts");
	}

}
