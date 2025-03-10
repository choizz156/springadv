package me.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import me.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import me.proxy.trace.LogTrace;
import me.proxy.trace.ThreadLocalLogTrace;

// @Import({AppV1Config.class, AppV2Config.class})
// @Import(InterfaceProxyConfig.class)
// @Import(ConcreteProxyConfig.class)
// @Import(DynamicProxyBasicConfig.class)
@Import(DynamicProxyFilterConfig.class)
@SpringBootApplication(scanBasePackages = "me.proxy.app.v3")
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logtrace() {
		return new ThreadLocalLogTrace();
	}

}
