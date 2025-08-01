package com.SensorPulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class SensorPulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorPulseApplication.class, args);
	}

}
