package me.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class RetryAspect {

	@Around("@annotation(retry)")
	public Object retry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
		log.info("joinPoint.getSignature() = {}", joinPoint.getSignature());
		log.info("retry = {}", retry);

		int maxRetry = retry.value();
		Exception exceptionHolder = null;

		for (int i = 0; i <= maxRetry; i++) {
			try {
				log.info("retry count = {}", i);
				return joinPoint.proceed();

			} catch (Exception e) {
				exceptionHolder = e;
			}
		}
		throw exceptionHolder;
	}
}
