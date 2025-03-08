package me.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.proxy.app.v1.OrderControllerV1;
import me.proxy.app.v1.OrderControllerV1Impl;
import me.proxy.app.v1.OrderRepositoryV1;
import me.proxy.app.v1.OrderRepositoryV1Impl;
import me.proxy.app.v1.OrderServiceV1;
import me.proxy.app.v1.OrderServiceV1Impl;
import me.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import me.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import me.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import me.proxy.trace.LogTrace;

@Configuration
public class InterfaceProxyConfig {

	@Bean
	public OrderControllerV1 orderController(LogTrace logTrace) {
		OrderControllerV1Impl orderControllerV1 = new OrderControllerV1Impl(orderService(logTrace));
		return new OrderControllerInterfaceProxy(orderControllerV1, logTrace);
	}

	@Bean
	public OrderServiceV1 orderService(LogTrace logTrace) {
		OrderServiceV1Impl orderServiceV1 = new OrderServiceV1Impl(orderRepository(logTrace));
		return new OrderServiceInterfaceProxy(orderServiceV1, logTrace);
	}

	@Bean
	public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
		OrderRepositoryV1Impl orderRepositoryV1 = new OrderRepositoryV1Impl();
		return new OrderRepositoryInterfaceProxy(orderRepositoryV1, logTrace);
	}
}
