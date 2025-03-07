package me.springadv.app.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.TraceV2;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderControllerV2 {

	public final OrderServiceV2 orderService;
	private final TraceV2 trace;

	@GetMapping("/v2/request")
	public String request(String itemId) {

		TraceStatus status = null;

		try {
			status = trace.begin("OrderControllerV5.request");
			orderService.orderItem(status.getTraceId(), itemId);
			trace.end(status);
			return "success";
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
