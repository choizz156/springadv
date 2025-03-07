package me.springadv.app.v2;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.springadv.trace.TraceId;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.TraceV2;

@RequiredArgsConstructor
@Service
public class OrderServiceV2 {

	private final OrderRepositoryV2 repository;
	private final TraceV2 trace;

	public void orderItem(TraceId traceId, String itemId) {

		TraceStatus status = null;
		try {
			status = trace.beginSync(traceId, "OrderService.orderItem()");
			repository.save(status.getTraceId(), itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
