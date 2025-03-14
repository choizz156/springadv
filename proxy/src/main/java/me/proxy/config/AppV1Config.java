package me.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.proxy.app.v1.OrderControllerV1;
import me.proxy.app.v1.OrderControllerV1Impl;
import me.proxy.app.v1.OrderRepositoryV1;
import me.proxy.app.v1.OrderRepositoryV1Impl;
import me.proxy.app.v1.OrderServiceV1;
import me.proxy.app.v1.OrderServiceV1Impl;

@Configuration
public class AppV1Config {

	@Bean
	public OrderControllerV1 orderControllerV1() {
		return new OrderControllerV1Impl(orderServiceV1());
	}

	@Bean
	public OrderServiceV1 orderServiceV1() {
		return new OrderServiceV1Impl(orderRepository());
	}

	@Bean
	public OrderRepositoryV1 orderRepository() {
		return new OrderRepositoryV1Impl();
	}
}
