package me.springadv.trace;

import java.util.UUID;

import lombok.Getter;

@Getter
public class TraceId {

	private String id;
	private int level;

	public TraceId() {
		this.id = UUID.randomUUID().toString().substring(0, 8);
		this.level = 0;
	}

	private TraceId(String id, int level) {
		this.id = id;
		this.level = level;
	}

	public TraceId toNextId(){
		return new TraceId(this.id, this.level + 1);
	}

	public boolean isFirstLevel(){
		return this.level == 0;
	}

	public TraceId toPrevId(){
		return new TraceId(this.id, this.level - 1);
	}
}
