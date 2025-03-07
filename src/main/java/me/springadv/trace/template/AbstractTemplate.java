package me.springadv.trace.template;

import me.springadv.trace.LogTrace;
import me.springadv.trace.TraceStatus;

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
