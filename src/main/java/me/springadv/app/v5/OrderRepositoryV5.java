package me.springadv.app.v5;

import org.springframework.stereotype.Repository;

import me.springadv.trace.LogTrace;
import me.springadv.trace.callback.TraceTemplate;

@Repository
public class OrderRepositoryV5 {

	private final TraceTemplate template;

	public OrderRepositoryV5(LogTrace trace) {
		this.template = new TraceTemplate(trace);
	}

	public void save(String itemId) {

		template.execute("OrderRepository.save", () -> {
			if (itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생!");
			}

			sleep(1000);
			return null;
		});
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
