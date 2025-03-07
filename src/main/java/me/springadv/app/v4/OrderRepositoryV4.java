package me.springadv.app.v4;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.springadv.trace.LogTrace;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.template.AbstractTemplate;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV4 {

	private final LogTrace trace;

	public void save(String itemId) {

		AbstractTemplate<String> template = new AbstractTemplate<>(trace){
			@Override
			protected String call() {
				if (itemId.equals("ex")) {
					throw new IllegalStateException("예외 발생!");
				}

				sleep(1000);
				return null;
			}
		};

		template.execute("OrderRepositoryV5.save");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
