package me.springadv.trace.callback;

import me.springadv.trace.LogTrace;
import me.springadv.trace.TraceStatus;

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
