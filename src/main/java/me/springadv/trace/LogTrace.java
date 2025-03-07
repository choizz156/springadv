package me.springadv.trace;

public interface LogTrace {

	TraceStatus begin(String msg);
	void end(TraceStatus status);
	void exception(TraceStatus status, Exception e);
}
