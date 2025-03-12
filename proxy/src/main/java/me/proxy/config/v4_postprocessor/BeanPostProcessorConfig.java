package me.proxy.config.v4_postprocessor;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import me.proxy.config.AppV1Config;
import me.proxy.config.AppV2Config;
import me.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import me.proxy.config.v4_postprocessor.postprocessor.PackageLogTraceProxyPostProcessor;
import me.proxy.trace.LogTrace;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

	@Bean
	public PackageLogTraceProxyPostProcessor logTraceProxyPostProcessor(LogTrace logTrace) {
		return new PackageLogTraceProxyPostProcessor("me.proxy.app", getAdvisor(logTrace));
	}

	private Advisor getAdvisor(LogTrace logTrace) {
		NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
		nameMatchMethodPointcut.setMappedNames("request*", "order*", "save*");

		LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

		return new DefaultPointcutAdvisor(nameMatchMethodPointcut, logTraceAdvice);
	}
}
