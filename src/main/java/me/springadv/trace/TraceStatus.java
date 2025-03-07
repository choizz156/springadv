package me.springadv.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TraceStatus {

	private TraceId traceId;
	private Long startTime;
	private String msg;

}
