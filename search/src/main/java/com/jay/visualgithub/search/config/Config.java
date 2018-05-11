package com.jay.visualgithub.search.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(10 * 1000)
                .setReadTimeout(10 * 1000)
                .build();
    }
}
