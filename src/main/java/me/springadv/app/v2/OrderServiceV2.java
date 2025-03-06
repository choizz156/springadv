package me.springadv.app.v1;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.TraceV1;

@RequiredArgsConstructor
@Service
public class OrderServiceV1 {

	private final OrderRepositoryV1 repository;
	private final TraceV1 trace;

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
