package Aopproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{
	
	private Object target;
	private Object advice;
	
	public MyInvocationHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public MyInvocationHandler(Object target, Object adive) {
		this.target = target;
		this.advice = adive;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Method[] adiveInterfaces = advice.getClass().getDeclaredMethods();
//		//log
//		System.out.println("advice: " + advice.getClass().getName());
		for (Method md : adiveInterfaces) {
			md.invoke(advice, method, args, target);
		}
		//
		method.invoke(target, args);
		
		return null;
	}

}
