package me.aop.order;

import org.springframework.stereotype.Service;

import me.proxy.app.v3.OrderRepositoryV3;

@Service
public class OrderService {

	private final me.proxy.app.v3.OrderRepositoryV3 orderRepository;

	public OrderService(OrderRepositoryV3 orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void orderItem(String itemId) {
		orderRepository.save(itemId);
	}
}
