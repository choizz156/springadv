package me.proxy.config.v4_postprocessor.postprocessor;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class PackageLogTraceProxyPostProcessor implements BeanPostProcessor {

	private final String basePackage;
	private final Advisor advisor;

	public PackageLogTraceProxyPostProcessor(String basePackage, Advisor advisor) {
		this.basePackage = basePackage;
		this.advisor = advisor;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		String packageName = bean.getClass().getPackageName();
		if (!packageName.startsWith(basePackage)) {
			return bean;
		}

		ProxyFactory proxyFactory = new ProxyFactory(bean);
		proxyFactory.addAdvisor(advisor);
		Object proxy = proxyFactory.getProxy();

		return proxy;
	}
}
