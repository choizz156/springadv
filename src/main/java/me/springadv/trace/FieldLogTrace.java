package me.springadv.trace;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

	private static final String START_PREFIX = "==>";
	private static final String END_PREFIX = "<==";
	private static final String EX_PREFIX = "<=X==";

	private TraceId traceIdHolder;

	@Override
	public TraceStatus begin(String msg) {
		syncTraceId();

		TraceId traceId = this.traceIdHolder;
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
		if (traceIdHolder == null) {
			traceIdHolder = new TraceId();
			return;
		}
		traceIdHolder = traceIdHolder.toNextId();
	}

	private void releaseTraceId() {
		if (traceIdHolder.isFirstLevel()) {
			traceIdHolder = null;
			return;
		}
		traceIdHolder = traceIdHolder.toPrevId();
	}

	private String addSpace(String startPrefix, int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append((i == level - 1) ? "|" + startPrefix : "|   ");
		}
		return sb.toString();
	}
}
