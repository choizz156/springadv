package me.proxy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import me.aop.test.RetryAspect;
import me.aop.test.TestRepository;
import me.aop.test.TestService;
import me.aop.test.TraceAspect;

@Import(value = { TestService.class, TestRepository.class, RetryAspect.class })
@SpringBootTest
class ProxyApplicationTests {

	@Autowired
	TestService testService;

	@Test
	void test1() throws Exception {

		for (int i = 0; i < 5; i++) {
			testService.req(i + "data");
		}
	}
}
