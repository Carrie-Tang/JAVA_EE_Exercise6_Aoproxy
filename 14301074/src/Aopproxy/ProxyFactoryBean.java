package Aopproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import dev.edu.javaee.spring.bean.BeanDefinition;
import dev.edu.javaee.spring.factory.BeanFactory;

public class ProxyFactoryBean /*implements InvocationHandler*/{
	private String interceptorNames;
	private String proxyInterfaces;
	private Object target;
	private Class<?> proxy;
	private Class<?> advice;
	private Object objProxy;
	private Object objAdvice;
	
	public ProxyFactoryBean() {
		//this.getClass().cast(target);
	}
	
	public void setProxy(Class<?> proxy) {
		this.proxy = proxy;
	}

	public void setFactory(BeanFactory factory) {
		this.factory = factory;
	}

	private BeanFactory factory;
	
	
	public void setInterceptorNames(String interceptorNames) {
		this.interceptorNames = interceptorNames;
	}


	public void setProxyInterfaces(String proxyInterfaces) {
		this.proxyInterfaces = proxyInterfaces;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
	

	public String getInterceptorNames() {
		return interceptorNames;
	}

	public String getProxyInterfaces() {
		return proxyInterfaces;
	}

	public Object getTarget() {
		return target;
	}
	
	
	//
	//set up a new bean by its id
	//
	public void setup(String id) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//this.getClass().cast(target);
		proxy = Class.forName("Aopproxy." + proxyInterfaces);
		//objProxy = proxy.newInstance();
		objAdvice = factory.getBean(interceptorNames);
		advice = objAdvice.getClass();
		//new handler
		MyInvocationHandler handler = new MyInvocationHandler(target, objAdvice);
		
		//new proxy obj
		Object proxy_obj = Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), handler);
		//re register for proxy obj
		BeanDefinition bdf = new BeanDefinition();
		bdf.setBean(proxy_obj);
		bdf.setBeanClassName(proxy.getName());
		factory.registerBeanDefinition(id, bdf);
	}
	
}
