package me.springadv.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.TraceV1;

@RequiredArgsConstructor
@RestController
public class OrderControllerV1 {

	public final OrderServiceV1 orderService;
	private final TraceV1 trace;

	@GetMapping("/v1/request")
	public String request(String itemId) {
		TraceStatus status = null;

		try{
			status = trace.begin("OrderControllerV1.request");
			orderService.orderItem(itemId);
			trace.end(status);
			return "success";
		}catch(Exception e){
			trace.exception(status, e);
			throw e;
		}
	}
}
