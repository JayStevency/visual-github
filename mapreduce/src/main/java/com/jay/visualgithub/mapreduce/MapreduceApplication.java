package com.jay.visualgithub.mapreduce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MapreduceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapreduceApplication.class, args);
	}
}
