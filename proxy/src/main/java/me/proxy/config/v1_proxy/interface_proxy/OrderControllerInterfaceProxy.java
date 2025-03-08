package me.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import me.proxy.app.v1.OrderControllerV1;
import me.proxy.trace.LogTrace;
import me.proxy.trace.TraceStatus;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

	private final OrderControllerV1 target;
	private final LogTrace logTrace;

	@Override
	public String request(String itemId) {

		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderController.request()"); //target 호출
			String result = target.request(itemId);
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}

	@Override
	public String noLog() {
		return target.noLog();
	}
}
