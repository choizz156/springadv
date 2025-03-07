package me.springadv.trace;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TraceV2 {

	private static final String START_PREFIX = "==>";
	private static final String END_PREFIX = "<==";
	private static final String EX_PREFIX = "<=X==";

	public TraceStatus begin(String msg) {
		TraceId traceId = new TraceId();
		long startTime = System.currentTimeMillis();
		log.info("[{}] {}{}",
			traceId.getId(),
			addSpace(START_PREFIX, traceId.getLevel()),
			msg
		);
		return new TraceStatus(traceId, startTime, msg);
	}

	public TraceStatus beginSync(TraceId beforeTraceId, String msg) {
		TraceId nextId = beforeTraceId.toNextId();
		long startTime = System.currentTimeMillis();
		log.info("[{}] {}{}",
			nextId.getId(),
			addSpace(START_PREFIX, nextId.getLevel()),
			msg
		);

		return new TraceStatus(nextId, startTime, msg);
	}

	public void end(TraceStatus traceStatus) {
		complete(traceStatus, null);
	}

	public void exception(TraceStatus traceStatus, Exception e) {
		complete(traceStatus, e);
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
	}

	private String addSpace(String startPrefix, int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append((i == level - 1) ? "|" + startPrefix : "|   ");
		}
		return sb.toString();
	}
}
