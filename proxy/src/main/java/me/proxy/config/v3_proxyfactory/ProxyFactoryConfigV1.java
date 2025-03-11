package me.proxy.config.v3_proxyfactory;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.proxy.app.v1.OrderControllerV1;
import me.proxy.app.v1.OrderControllerV1Impl;
import me.proxy.app.v1.OrderRepositoryV1;
import me.proxy.app.v1.OrderRepositoryV1Impl;
import me.proxy.app.v1.OrderServiceV1;
import me.proxy.app.v1.OrderServiceV1Impl;
import me.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import me.proxy.trace.LogTrace;

@Configuration
public class ProxyFactoryConfigV1 {

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
		OrderControllerV1 orderController = new
			OrderControllerV1Impl(orderServiceV1(logTrace));
		ProxyFactory factory = new ProxyFactory(orderController);
		factory.addAdvisor(getAdvisor(logTrace));
		OrderControllerV1 proxy = (OrderControllerV1)factory.getProxy();

		return proxy;
	}

	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
		OrderServiceV1 orderService = new
			OrderServiceV1Impl(orderRepositoryV1(logTrace));
		ProxyFactory factory = new ProxyFactory(orderService);
		factory.addAdvisor(getAdvisor(logTrace));
		OrderServiceV1 proxy = (OrderServiceV1)factory.getProxy();
		return proxy;
	}

	@Bean
	public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
		OrderRepositoryV1Impl orderRepositoryV1 = new OrderRepositoryV1Impl();

		ProxyFactory proxyFactory = new ProxyFactory(orderRepositoryV1);
		proxyFactory.addAdvisor(getAdvisor(logTrace));

		OrderRepositoryV1 proxy = (OrderRepositoryV1)proxyFactory.getProxy();
		return proxy;
	}

	private Advisor getAdvisor(LogTrace logTrace) {
		NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
		nameMatchMethodPointcut.setMappedNames("request*", "order*", "save*");

		LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

		return new DefaultPointcutAdvisor(nameMatchMethodPointcut, logTraceAdvice);
	}

}
