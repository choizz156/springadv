package me.springadv.app.v1;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.TraceV1;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV1 {

	private final TraceV1 trace;

	public void save(String itemId) {

		TraceStatus status = null;
		try{
			status = trace.begin("OrderRepositoryV5.save");

			if (itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생!");
			}

			sleep(1000);

			trace.end(status);
		}catch (Exception e){
			trace.exception(status, e);
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
