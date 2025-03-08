package me.proxy.trace.callback;

import me.proxy.trace.LogTrace;
import me.proxy.trace.TraceStatus;

public class TraceTemplate {

	private final LogTrace logTrace;

	public TraceTemplate(LogTrace logtrace) {
		this.logTrace = logtrace;
	}

	public <T> T execute(String msg, TraceCallback<T> callback) {
		TraceStatus status = null;

		try {

			status = logTrace.begin(msg);

			T result = callback.call();

			logTrace.end(status);
			return result;

		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
