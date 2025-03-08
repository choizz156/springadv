package me.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import me.proxy.config.v1_proxy.ConcreteProxyConfig;
import me.proxy.config.v1_proxy.InterfaceProxyConfig;
import me.proxy.trace.LogTrace;
import me.proxy.trace.ThreadLocalLogTrace;

// @Import({AppV1Config.class, AppV2Config.class})
// @Import(InterfaceProxyConfig.class)
@Import(ConcreteProxyConfig.class)
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
