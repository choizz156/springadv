package me.proxy.trace;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

	private static final String START_PREFIX = "==>";
	private static final String END_PREFIX = "<==";
	private static final String EX_PREFIX = "<=X==";

	private final ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();
	@Override
	public TraceStatus begin(String msg) {
		syncTraceId();

		TraceId traceId = traceIdHolder.get();
		long startTime = System.currentTimeMillis();
		log.info("[{}] {}{}",
			traceId.getId(),
			addSpace(START_PREFIX, traceId.getLevel()),
			msg
		);

		return new TraceStatus(traceId, startTime, msg);
	}

	@Override
	public void end(TraceStatus status) {
		complete(status, null);
	}

	@Override
	public void exception(TraceStatus status, Exception e) {
		complete(status, e);
	}

	private void complete(TraceStatus traceStatus, Exception e) {
		long stopTime = System.currentTimeMillis();
		long resultTime = stopTime - traceStatus.getStartTime();
		TraceId traceId = traceStatus.getTraceId();

		if (e == null) {
			log.info("[{}] {}{} time={}ms",
				traceId.getId(),
				addSpace(END_PREFIX, traceId.getLevel()),
				traceStatus.getMsg(),
				resultTime
			);
			return;
		}

		log.info("[{}] {}{} time={}ms ex = {}",
			traceId.getId(),
			addSpace(EX_PREFIX, traceId.getLevel()),
			traceStatus.getMsg(),
			resultTime,
			e.toString()
		);

		releaseTraceId();
	}

	private void syncTraceId() {
		TraceId traceId = traceIdHolder.get();
		if (traceId == null) {
			traceIdHolder.set(new TraceId());
			return;
		}
		traceIdHolder.set(traceId.toNextId());
	}

	private void releaseTraceId() {
		TraceId traceId = traceIdHolder.get();

		if (traceId.isFirstLevel()) {
			traceIdHolder.remove();
			return;
		}
		traceIdHolder.set(traceId.toPrevId());
	}

	private String addSpace(String startPrefix, int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append((i == level - 1) ? "|" + startPrefix : "|   ");
		}
		return sb.toString();
	}
}
