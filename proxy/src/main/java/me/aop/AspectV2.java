package me.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV2 {

	@Pointcut("execution(* me.aop.order..*(..))")
	private void allOrder(){}

	@Around("allOrder()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint.proceed();
	}
}
