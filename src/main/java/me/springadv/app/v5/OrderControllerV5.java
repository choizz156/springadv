package me.springadv.app.v5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import me.springadv.trace.LogTrace;
import me.springadv.trace.callback.TraceTemplate;

@Slf4j
@RestController
public class OrderControllerV5 {

	private final OrderServiceV5 orderService;
	private final TraceTemplate traceTemplate;

	public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
		this.orderService = orderService;
		this.traceTemplate = new TraceTemplate(trace);
	}

	@GetMapping("/v5/request")
	public String request(String itemId) {
		return traceTemplate.execute("OrderControllerV5.request",
			() -> {
				orderService.orderItem(itemId);
				return "ok";
			});
	}
}
