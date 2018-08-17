package com.revature.caliber.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.revature.caliber.zuul.filter.PreZuulFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulAPIGatewayApplication {

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(ZuulAPIGatewayApplication.class, args);
	}
	@Bean //added filter
	public PreZuulFilter filer()
	{
		return new PreZuulFilter();
	}
}
