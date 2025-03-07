package me.springadv.app.v4;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.springadv.trace.LogTrace;
import me.springadv.trace.TraceStatus;
import me.springadv.trace.template.AbstractTemplate;

@RequiredArgsConstructor
@Service
public class OrderServiceV4 {

	private final OrderRepositoryV4 repository;
	private final LogTrace trace;

	public void orderItem(String itemId) {

		AbstractTemplate<String> template = new AbstractTemplate<>(trace){
			@Override
			protected String call() {
				repository.save(itemId);
				return "ok";
			}
		};

		template.execute("OrderServiceV5.orderItem");
	}
}
