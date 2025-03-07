package me.springadv.app.v5;

import org.springframework.stereotype.Service;

import me.springadv.trace.LogTrace;
import me.springadv.trace.callback.TraceTemplate;

@Service
public class OrderServiceV5 {

	private final OrderRepositoryV5 repository;
	private final TraceTemplate template;

	public OrderServiceV5(OrderRepositoryV5 repository, LogTrace trace) {
		this.repository = repository;
		this.template = new TraceTemplate(trace);
	}

	public void orderItem(String itemId) {
		template.execute("OrderService.orderItem", () -> {
			repository.save(itemId);
			return null;
		});
	}
}
