package me.aop.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TestRepository {

	private static AtomicInteger seq = new AtomicInteger(0);

	@Trace
	@Retry(value = 4)
	public String save(String itemId){
		seq.incrementAndGet();
		log.info("seq.intValue() = {}", seq.intValue());
		if(seq.get() % 5 == 0){
			throw new RuntimeException(seq+"번째");
		}

		return itemId;
	}

}
