package me.springadv.app.v3;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.springadv.trace.LogTrace;
import me.springadv.trace.TraceStatus;

@RequiredArgsConstructor
@Service
public class OrderServiceV3 {

	private final OrderRepositoryV3 repository;
	private final LogTrace trace;

	public void orderItem(String itemId) {

		TraceStatus status = null;

		try {
			status = trace.begin("OrderService.orderItem()");
			repository.save(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
