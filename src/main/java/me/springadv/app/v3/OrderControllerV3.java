package me.springadv.app.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.springadv.trace.LogTrace;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.TraceV2;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderControllerV3 {

	public final OrderServiceV3 orderService;
	private final LogTrace trace;

	@GetMapping("/v3/request")
	public String request(String itemId) {

		TraceStatus status = null;

		try {
			status = trace.begin("OrderControllerV5.request");
			orderService.orderItem(itemId);
			trace.end(status);
			return "success";
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
