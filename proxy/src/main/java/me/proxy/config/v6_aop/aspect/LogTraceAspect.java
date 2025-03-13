package me.proxy.config.v6_aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import me.proxy.trace.LogTrace;
import me.proxy.trace.TraceStatus;

@Aspect
public class LogTraceAspect {

	private final LogTrace logTrace;

	public LogTraceAspect(LogTrace logTrace) {
		this.logTrace = logTrace;
	}

	@Around("execution(* me.proxy.app..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		TraceStatus status = null;
		// joinPoint.getTarget();

		try {
			String message = joinPoint.getSignature().toShortString();

			status = logTrace.begin(message);
			//로직 호출
			Object result = joinPoint.proceed();
			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
