package com.revature.caliber.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.revature.caliber.config.RelayFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulAPIGatewayApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ZuulAPIGatewayApplication.class, args);
	}
	
	@Bean
	public RelayFilter relayFilter() {
		return new RelayFilter();
	}
}
