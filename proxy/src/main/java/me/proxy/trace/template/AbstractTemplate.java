package me.proxy.trace.template;

import me.proxy.trace.LogTrace;
import me.proxy.trace.TraceStatus;

public abstract class AbstractTemplate<T> {

	private final LogTrace logTrace;

	public AbstractTemplate(LogTrace logTrace) {
		this.logTrace = logTrace;
	}

	public T execute(String msg) {

		TraceStatus status = null;

		try {

			status = logTrace.begin(msg);

			T result = call();

			logTrace.end(status);
			return result;

		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}

	protected abstract T call();
}
