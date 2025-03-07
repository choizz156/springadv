package me.springadv.app.v3;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.springadv.trace.LogTrace;
import me.springadv.trace.TraceId;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.TraceV2;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV3 {

	private final LogTrace trace;

	public void save(String itemId) {

		TraceStatus status = null;

		try {
			status = trace.begin("OrderRepositoryV3.save");

			if (itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생!");
			}

			sleep(1000);

			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
