package me.aop.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class TraceAspect {

	@Before("@annotation(me.aop.test.Trace)")
	public void before(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		log.info("args = {}", args);
	}
}
