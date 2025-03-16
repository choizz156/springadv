package me.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV1 {

	@Around("execution(* me.aop.order..*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint.proceed();
	}
}
