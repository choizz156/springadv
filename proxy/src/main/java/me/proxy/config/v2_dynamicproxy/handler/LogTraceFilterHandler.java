package me.proxy.config.v2_dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.util.PatternMatchUtils;

import me.proxy.trace.LogTrace;
import me.proxy.trace.TraceStatus;

/**
 *  인터페이스가 있는 경우에만 동적 프록시 적용 가능.
 */
public class LogTraceFilterHandler implements InvocationHandler {

	private final Object target;
	private final LogTrace logtrace;
	private final String[] patterns;

	public LogTraceFilterHandler(Object target, LogTrace logtrace, String... patterns) {
		this.target = target;
		this.logtrace = logtrace;
		this.patterns = patterns;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		String methodName = method.getName();

		if(!PatternMatchUtils.simpleMatch(patterns, methodName)) {
			return method.invoke(target, args);
		}

		TraceStatus status = null;

		try{
			String message = method.getDeclaringClass().getSimpleName() + "." + method.getName();
			status = logtrace.begin(message);

			Object result = method.invoke(target, args);

			logtrace.end(status);
			return result;
		}catch (Exception e){
			logtrace.exception(status, e);
			throw e;
		}
	}
}
