package me.springadv.app.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.springadv.trace.LogTrace;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.template.AbstractTemplate;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderControllerV4 {

	public final OrderServiceV4 orderService;
	private final LogTrace trace;

	@GetMapping("/v4/request")
	public String request(String itemId) {

		AbstractTemplate<String> template = new AbstractTemplate<>(trace){
			@Override
			protected String call() {
				orderService.orderItem(itemId);
				return "ok";
			}
		};

		return template.execute("OrderControllerV5.request");
	}
}
