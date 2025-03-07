package me.springadv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.springadv.trace.FieldLogTrace;
import me.springadv.trace.LogTrace;
import me.springadv.trace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

	// @Bean
	// public LogTrace logTrace() {
	// 	return new FieldLogTrace();
	// }

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
