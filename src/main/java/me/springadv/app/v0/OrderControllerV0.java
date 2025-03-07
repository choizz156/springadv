package me.springadv.app.v0;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderControllerV0 {

	public final OrderServiceV0 orderService;

	@GetMapping("/v0/request")
	public String request(String itemId) {
		orderService.orderItem(itemId);
		return "success";
	}
}
