package me.proxy.config.v3_proxyfactory;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.proxy.app.v1.OrderControllerV1;
import me.proxy.app.v1.OrderRepositoryV1;
import me.proxy.app.v1.OrderServiceV1;
import me.proxy.app.v2.OrderControllerV2;
import me.proxy.app.v2.OrderRepositoryV2;
import me.proxy.app.v2.OrderServiceV2;
import me.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import me.proxy.trace.LogTrace;

@Configuration
public class ProxyFactoryConfigV2 {

	@Bean
	public OrderControllerV2 orderControllerV1(LogTrace logTrace) {
		OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(logTrace));
		ProxyFactory factory = new ProxyFactory(orderController);
		factory.addAdvisor(getAdvisor(logTrace));
		OrderControllerV2 proxy = (OrderControllerV2)factory.getProxy();

		return proxy;
	}

	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
		OrderServiceV2 orderService = new
			OrderServiceV2(orderRepositoryV2(logTrace));
		ProxyFactory factory = new ProxyFactory(orderService);
		factory.addAdvisor(getAdvisor(logTrace));
		OrderServiceV2 proxy = (OrderServiceV2)factory.getProxy();
		return proxy;
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
		OrderRepositoryV2 orderRepositoryV1 = new OrderRepositoryV2();

		ProxyFactory proxyFactory = new ProxyFactory(orderRepositoryV1);
		proxyFactory.addAdvisor(getAdvisor(logTrace));

		OrderRepositoryV2 proxy = (OrderRepositoryV2)proxyFactory.getProxy();
		return proxy;
	}

	private Advisor getAdvisor(LogTrace logTrace) {
		NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
		nameMatchMethodPointcut.setMappedNames("request*", "order*", "save*");

		LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

		return new DefaultPointcutAdvisor(nameMatchMethodPointcut, logTraceAdvice);
	}

}
