package me.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

	@Pointcut("execution(* me.aop.order..*(..))")
	public void allOrder() {
	}

	//타입 패턴이 *Service
	@Pointcut("execution(* *..*Service.*(..))")
	public void allService() {
	}

	//allOrder && allService
	@Pointcut("allOrder() && allService()")
	public void orderAndService() {
	}
}
