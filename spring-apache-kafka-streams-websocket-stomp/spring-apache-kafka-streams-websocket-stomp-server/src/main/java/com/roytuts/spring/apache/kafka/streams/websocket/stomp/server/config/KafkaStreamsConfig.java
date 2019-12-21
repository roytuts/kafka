package com.roytuts.spring.apache.kafka.streams.websocket.stomp.server.config;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaStreamsConfig {

	@Value("${kafka.input.topic}")
	private String kafkaInputTopic;

	@Value("${kafka.output.topic}")
	private String kafkaOutputTopic;

	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBootstrapServer;

	@Bean
	public KStream<String, String> kstream() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "roytuts-stomp-websocket");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		StreamsBuilder streamsBuilder = new StreamsBuilder();

		final KStream<String, String> stream = streamsBuilder.stream(kafkaInputTopic,
				Consumed.with(Serdes.String(), Serdes.String()));

		stream.map((key, value) -> KeyValue.pair(key, value)).to(kafkaOutputTopic,
				Produced.with(Serdes.String(), Serdes.String()));

		KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
		streams.start();

		return stream;
	}

}
