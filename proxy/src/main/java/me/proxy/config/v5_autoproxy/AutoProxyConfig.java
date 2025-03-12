package me.proxy.config.v5_autoproxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import me.proxy.config.AppV1Config;
import me.proxy.config.AppV2Config;
import me.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import me.proxy.trace.LogTrace;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

	// AnnotationAwareAspectJAutoProxyCreator 빈 후처리기를 자동으로 등록
	// @Bean
	public Advisor advisor1(LogTrace logTrace) {
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*");
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		//advisor = pointcut + advice
		return new DefaultPointcutAdvisor(pointcut, advice);
	}

	@Bean
	public Advisor advisor2(LogTrace logTrace) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* hello.proxy.app..*(..))");
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		//advisor = pointcut + advice
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}
