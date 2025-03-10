package me.proxy.app.v2;

import me.proxy.app.v1.OrderRepositoryV1;
import me.proxy.app.v1.OrderServiceV1;

public class OrderServiceV2{

	private final OrderRepositoryV2 orderRepository;

	public OrderServiceV2(OrderRepositoryV2 orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void orderItem(String itemId) {
		orderRepository.save(itemId);
	}
}
