package me.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import me.proxy.app.v1.OrderServiceV1;
import me.proxy.trace.LogTrace;
import me.proxy.trace.TraceStatus;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

	private final OrderServiceV1 target;
	private final LogTrace logTrace;

	@Override
	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderService.orderItem()"); //target 호출
			target.orderItem(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
